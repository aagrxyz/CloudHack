var mongoose = require("mongoose");

var groupSchema = mongoose.Schema({
	name: String,
	users: [
        {
    		user: {
    			type: mongoose.Schema.Types.ObjectId,
    			ref: "User",
    		},
    		value: Number,
        }
    ],
    size: Number,
    matrix: [
        {
            user1: {
                type: mongoose.Schema.Types.ObjectId,
    			ref: "User",
            },
            user2: {
                type: mongoose.Schema.Types.ObjectId,
    			ref: "User",
            },
            value: Number,
        }
    ],
	log: [
		{
			type: mongoose.Schema.Types.ObjectId,
			ref: "Transaction",
		}
	],
});

module.exports = mongoose.model("Group",groupSchema);
