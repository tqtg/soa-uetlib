module.exports = function(app, mongoose, passport) {
	var Book = require('../models/book.js');

	/* GET books listing. */
	app.get('/books/api', function(req, res, next) {
		Book.find(function(err, books) {
			if (err) return next(err);
			res.json(books);
		})
	});

	/* GET /books/:id */
	app.get('/books/api/:id', function(req, res, next) {
		Book.findById(req.params.id, function(err, post) {
			if (err) return next(err);
			res.json(post);
		})
	});

	/* POST /books */
	app.post('/books/api', function(req, res, next) {
		Book.create(req.body, function(err, post) {
			if (err) return next(err);
			res.json(post);
		})
	});

	/* PUT /books */
	app.put('/books/api', function(req, res, next) {
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
	app.put('/books/api/:id', function(req, res, next) {
		Book.findByIdAndUpdate(req.params.id, req.body, function(err, post) {
			if (err) return next(err);
			res.json(post);
		})
	});


	/* DELETE /books/:id */
	app.delete('/books/api/:id', function(req, res, next) {
		Book.findByIdAndRemove(req.params.id, req.body, function(err, post) {
			if (err) return next(err);
			res.json(post);
		})
	});
}

// route middleware to make sure a user is logged in
function isLoggedIn(req, res, next) {

    // if user is authenticated in the session, carry on 
    if (req.isAuthenticated())
        return next();

    // if they aren't redirect them to the home page
    res.redirect('/');
}