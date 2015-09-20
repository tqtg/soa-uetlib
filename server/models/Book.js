var mongoose = require('mongoose');

var BookSchema = new mongoose.Schema({
	title : String,
	author : String,
	description : String,
	publisher : String,
	date : String,
	category : String,
	page : Number,
	image : String,
});

module.exports = mongoose.model('Book', BookSchema)