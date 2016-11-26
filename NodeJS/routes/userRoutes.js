var express = require("express");
var router = express.Router();
var User = require("../models/user");

router.get("/:email",function(req,res)
{
    console.log(req.params);
    User.findOne({email: req.params.email}).populate('groups').populate('log').exec(function(err,users)
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
            res.json(users);
        }
    });
});

router.get("/",function(req,res)
{
    console.log(req.params);
    User.find({}).populate('groups').populate('log').exec(function(err,users)
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
            res.json(users);
        }
    });
});

router.post("/",function(req,res)
{
    // console.log(req.body);
    User.findOne({email: req.body.email}).populate('groups').populate('log').exec(function(err,user)
    {
        if(err||(user === null))
        {
            var user = {name: req.body.name, email: req.body.email, photo_url: req.body.url};
            User.create(user,function(err,user)
            {
                if(err || (user ===null))
                {
                    res.json(
                        {
                            message: "Something went wrong while registration!",
                            error: err,
                        });
                }
                else
                {
                    res.json(user);
                }
            });
        }
        else
        {
            // console.log(user);
            res.json(
                {
                    message: "User already exists",
                    existing: user,
                }
            );
        }
    });
});

router.put("/:id",function(req,res)
{
    User.findOne({_id: req.params.id},function(err,user)
    {
        if(err || (user === null))
        {
            res.json(
                {
                    message: "Error! User does not exist",
                }
            );
        }
        else
        {
            user.name = req.body.name;
            user.email = req.body.email;
            user.photo_url = req.body.url;
            user.save();
            res.json(
                {
                    message: "Information update successfull",
                }
            );
        }
    });
});

module.exports = router;
