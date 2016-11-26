var mongoose = require("mongoose");

var transactionSchema = mongoose.Schema({
    payer:{
      type: mongoose.Schema.Types.ObjectId,
      ref: "User",
    },
    payee:{
      type: mongoose.Schema.Types.ObjectId,
      ref: "User",
    },
    date: {
      type: Date,
      default: Date.now(),
    },
    amount: Number,
    purpose: String,
    approval: Boolean,
});

module.exports = mongoose.model("Transaction",transactionSchema);
