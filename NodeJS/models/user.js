var mongoose = require("mongoose");

var userSchema = new mongoose.Schema({

  name: String,
  email: String,
  phone: String,
  groups: [
      {
          type: mongoose.Schema.Types.ObjectId,
          ref: "Group",
      }
  ],
  photo_url: String,
  log: [
      {
          type: mongoose.Schema.Types.ObjectId,
          ref: "Transaction",
      }
  ],
 });

module.exports = mongoose.model("User",userSchema);
