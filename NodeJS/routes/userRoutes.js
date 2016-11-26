var express = require("express");
var router = express.Router();

router.get("/",function(req,res)
{
    res.json(
        {
            message: "User asked for",
        }
    );
});

module.exports = router;
