var express = require("express");
var router = express.Router();
var Group = require("../models/group");
var User = require("../models/user");

router.get("/:id",function(req,res)
{
    Group.findOne({_id: req.params.id}).populate("log").populate({path: 'users',model: "User"}).populate({path: "matrix", model: "User"}).exec(function(err,group)
    {
        if(err)
        {
            res.json(
                {
                    message: "Something went wrong!",
                    error: err,
                });
        }
        else
        {
            res.json(group);
        }
    });
});

router.get("/",function(req,res)
{
    console.log(req.params);
    Group.find({}).populate("log").populate("users.user").populate("matrix").exec(function(err,groups)
    {
        if(err)
        {
            res.json(
                {
                    message: "Something went wrong!",
                    error: err,
                }
            );
        }
        else
        {
            res.json(groups);
        }
    });
});

router.post("/",function(req,res)
{
    console.log("********\n"+req.body.name+"\n"+req.body.users+"\n******\n");
    var gr = {name: req.body.name,size: req.body.users.length};

    Group.create(gr,function (err,group)
    {
        var size = req.body.users.length;
        group.users = [];
        for(i=0;i<size;i++)
        {
            group.users.push(null);
        }
        console.log(group.users);
        // console.log("***************************************************8")
        group.users = [null];

        req.body.users.forEach(function(user_individual,index)
        {
            User.findOne({email: user_individual},function(err,user_object)
            {
                if(user_object != null)
                {
                    group.users.push({user: user_object,value: 0});
                    console.log(index+" - "+group.users[index]);
                    group.save();
                    user_object.groups.push(group);
                    user_object.save();
                }
            });
        });

        res.json(
            {
                message: "New Group Created!",
                id: group._id,
            }
        );
    });
});

router.post("/:id/add_member",function(req,res)
{
    Group.findOne({_id: req.params.id},function(err,group)
    {
        if(err || (group === null))
        {
            res.json(
                {
                    message: "Error! Group does not exist",
                }
            );
        }
        else
        {
            User.findOne({email: req.body.email},function(err,user)
            {
                if(user != null)
                {
                    var flag = false;
                    group.users.forEach(usr,idx)
                    {
                        if(usr.user._id === user._id)
                        {
                            flag = true;
                        }
                    }
                    if(flag != true)
                    {
                        group.users.push({user: user, value: req.body.value});
                        req.json(
                            {
                                message: "User add successfull",
                            }
                        );
                    }
                    else {
                        req.json(
                            {
                                message: "User already in the group",
                            }
                        );
                    }
                }
                else {
                    req.json(
                        {
                            message: "No such user exists",
                        }
                    );
                }
            });
        }
    });
});

module.exports = router;
