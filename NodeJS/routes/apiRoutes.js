var express = require("express");
var router = express.Router();
var userRoutes = require("./userRoutes");
var groupRoutes = require("./groupRoutes");

router.use("/user",userRoutes);
router.use("/group",groupRoutes);
var Group = require("../models/group");
var User = require("../models/user");


function compute(a)
{
    var n = a.length;
    var pos = [];
    var neg = [];
    var ans = [];
    var clone_array=[];

    a.forEach(function(obj,idx)
    {
        if(obj!=null)
        clone_array.push(parseInt(obj.value));
    });

    for(var i=1;i<n;i++)
    {
        var fd = a[i];
        if(fd.value > 0 )
        {
            pos.push(fd);
            //console.log(pos[0].value);
        }
        else if(fd.value<0)
        {
            fd.value = parseInt(-1*fd.value);
            neg.push(fd);
            //console.log(neg[0].value);
        }
    }
    for(var i=0;i<pos.length;i++)
    {
        var max = -1;
        var ind = i;
        for(var j=i;j<pos.length;j++)
        {

            if(pos[j].value>max)
            {
                max = pos[j].value;
                ind = j;
            }
        }
        var temp = pos[i];
        pos[i] = pos[ind];
        pos[ind] = temp;

    }
    for(var i=0;i<neg.length;i++)
    {
        var max = -1;
        var ind = i;
        for(var j=i;j<neg.length;j++)
        {
            if(neg[j].value>max)
            {
                max = neg[j].value;
                ind = j;
            }
        }
        var temp = neg[i];
        neg[i] = neg[ind];
        neg[ind] = temp;

    }
    var k=0;
    for(var i=0;i<pos.length;i++)
    {
        var d = pos[i].value;
        for(var j=k;j<neg.length;j++)
        {
            var e = neg[j].value;
            //console.log(neg[j].value);
            if(d==e)
            {

                ans.push({user1:pos[i].user,user2:neg[j].user,value: d});
                k=j+1;
                break;
            }
            else if(d>e)
            {
                ans.push({user1: pos[i].user,user2: neg[j].user,value: d-e});
                d = d - e;
            }
            else
            {
                ans.push({user1: pos[i].user,user2: neg[j].user,value: d});
                neg[j].value = e -d;
                for(var l=j;l<neg.length;l++)
                {
                    var max = -1;
                    var ind = i;
                    for(var m=l;m<neg.length;m++)
                    {
                        if(neg[m].value>max)
                        {
                            max = neg[m].value;
                            ind = m;
                        }
                    }
                    var temp = neg[l];
                    neg[l] = neg[ind];
                    neg[ind] = temp;

                }
                k=j;
            }
        }
    }

    a.forEach(function(obj,idx)
    {
        if(obj!=null)
        {
        obj.value = parseInt(clone_array[idx-1]);
        console.log(obj);
        }
    });

    return ans;
}








router.post("/recieve",function(req,res)
{
    if(req.body.group_id != null && req.body.email != null)
    {
        console.log(req.body.group_id);
        console.log(req.body.email);
        console.log(req.body.users);
        console.log(req.body.values);

        Group.findOne({_id: req.body.group_id}).populate("users.user").exec(function(err,group)
        {
            var total = 0;

            req.body.users.forEach(function(user_email,index)
            {
                console.log(group.users);
                group.users.forEach(function(user_block,idx)
                {
                    if(user_block != null)
                    {
                        if(user_block.user.email === user_email)
                        {
                            user_block.value += parseInt(req.body.values[index]);
                            total += parseInt(req.body.values[index]);
                        }
                    }
                });
            });
            group.users.forEach(function(user_block,idx)
            {
                if(user_block != null)
                {
                    console.log(user_block);
                    if(user_block.user.email === req.body.email)
                    {
                        user_block.value -= total;
                    }
                    // console.log("0---0");
                }
            });
            console.log('********************');
            console.log(group.users);
            console.log('********************');
            group.matrix = compute(group.users);
            group.save();
            res.json(group);
        });
    }
    else {
        res.json(
            {
                "message": "Bad Parameters",
            }
        )
    }
});

router.get("/",function(req,res)
{
    res.json(
        {
            message: "You have reached our API section. Please see documentation for more information.",
        }
    );
});



module.exports = router;
