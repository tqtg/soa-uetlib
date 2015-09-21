var mongoose = require('mongoose');

var CategorySchema = new mongoose.Schema({
	id : String,
	name : String,
});

module.exports = mongoose.model('Category', CategorySchema)