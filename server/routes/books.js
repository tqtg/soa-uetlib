var express = require('express');
var router = express.Router();

var mongoose = require('mongoose');
var Book = require('../models/Book.js');

/* GET books listing. */
router.get('/', function(req, res, next) {
	Book.find(function(err, books) {
		if (err) return next(err);
		res.json(books);
	})
});

/* GET /books/:id */
router.get('/:id', function(req, res, next) {
	Book.findById(req.params.id, function(err, post) {
		if (err) return next(err);
		res.json(post);
	})
});

/* POST /books */
router.post('/', function(req, res, next) {
	Book.create(req.body, function(err, post) {
		if (err) return next(err);
		res.json(post);
	})
});

/* PUT /books */
router.put('/', function(req, res, next) {
	Book.update({
		_id: mongoose.Types.ObjectId(req.body._id)
	}, {
		title: req.body.title,
		author: req.body.author
	}, {}, function(err, post) {
		if (err) return next(err);
		res.json(post);
	})
});

/* PUT /books/:id */
router.put('/:id', function(req, res, next) {
	Book.findByIdAndUpdate(req.params.id, req.body, function(err, post) {
		if (err) return next(err);
		res.json(post);
	})
});


/* DELETE /books/:id */
router.delete('/:id', function(req, res, next) {
	Book.findByIdAndRemove(req.params.id, req.body, function(err, post) {
		if (err) return next(err);
		res.json(post);
	})
});

module.exports = router;
