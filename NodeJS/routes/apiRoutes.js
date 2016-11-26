var express = require("express");
var router = express.Router();
var userRoutes = require("./userRoutes");
var groupRoutes = require("./groupRoutes");

router.use("/user",userRoutes);
router.use("/group",groupRoutes);
var Group = require("../models/group");
var User = require("../models/user");

// router.post("/recieve",function(req,res)
// {
//
// });

router.get("/",function(req,res)
{
    res.json(
        {
            message: "You have reached our API section. Please see documentation for more information.",
        }
    );
});



module.exports = router;
