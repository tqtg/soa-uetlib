var Book = require('../models/book.js');

module.exports = function(app, mongoose, passport) {
	// decode utf-8 for request from java app
	app.use('/books', function(req, res, next) {
		for (x in req.body) {
			req.body[x] = decodeURIComponent(req.body[x]);
			console.log(req.body);
		}
		next();
	});

	/* GET all books */
	app.get('/books/all', isLoggedIn, function(req, res, next) {
		Book.find(function(err, books) {
			if (err) return next(err);
			res.json(books);
		})
	});

	/* GET books listing. Limit 50 */
	app.get('/books', isLoggedIn, function(req, res, next) {
		Book.find({}, {}, {
			limit : 20
		}, function(err, books) {
			if (err) return next(err);
			res.json(books);
		})
	});

	/* GET /books/id/:id */
	app.get('/books/id/:id', isLoggedIn, function(req, res, next) {
		Book.findById(req.params.id, function(err, post) {
			if (err) return next(err);
			res.json(post);
		})
	});

	/* GET /books/id/:id */
	app.get('/books/page/:page', isLoggedIn, function(req, res, next) {
		Book.find({}, {}, {
			skip : (req.params.page - 1) * 20,
			limit : 20
		}, function(err, books) {
			if (err) return next(err);
			res.json(books);
		})
	});

	/* GET /books/:category/:page */
	app.get('/books/category/:category', isLoggedIn, function(req, res, next) {
		Book.find({
			'category' : req.params.category
		}, function(err, post) {
			if (err) return next(err);
			res.json(post);
		})
	});

	/* GET /books/:category/:page */
	app.get('/books/category/:category/:page', isLoggedIn, function(req, res, next) {
		Book.find({
			'category' : req.params.category
		}, {}, {
			skip : (req.params.page - 1) * 20,
			limit : 20
		}, function(err, post) {
			if (err) return next(err);
			res.json(post);
		})
	});

	/**
	* Searching
	* GET /books/search/:query
	*/
	app.get('/books/search/:query', isLoggedIn, function(req, res, next) {
		Book.find({
			'$or' : [{
				"title": {
					'$regex' : '.*' + req.params.query + '.*',
					'$options' : 'i'
				}
			}, {
				"author": {
					'$regex' : '.*' + req.params.query + '.*',
					'$options' : 'i'
				}
			}]
		}, function(err, books) {
			if (err) return next(err);
			res.json(books);
		})
	});

	/**
	* Searching
	* GET /books/search/:query
	*/
	app.get('/books/search/:query/:page', isLoggedIn, function(req, res, next) {
		Book.find({
			'$or' : [{
				"title": {
					'$regex' : '.*' + req.params.query + '.*',
					'$options' : 'i'
				}
			}, {
				"author": {
					'$regex' : '.*' + req.params.query + '.*',
					'$options' : 'i'
				}
			}]
		}, {}, {
			skip : (req.params.page - 1) * 20,
			limit : 20
		}, function(err, books) {
			if (err) return next(err);
			res.json(books);
		})
	});


	/* POST /books */
	app.post('/books', isAdmin, function(req, res, next) {
		Book.create(req.body, function(err, post) {
			if (err) return next(err);
			res.json(post);
		})
	});

	/* PUT /books */
	app.put('/books', isAdmin, function(req, res, next) {
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
	app.put('/books/:id', isAdmin, function(req, res, next) {
		Book.findByIdAndUpdate(req.params.id, req.body, function(err, post) {
			if (err) return next(err);
			res.json(post);
		})
	});


	/* DELETE /books/:id */
	app.delete('/books/:id', isAdmin, function(req, res, next) {
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
    res.send(401, 'Unauthorized');
}

// route middleware to make sure a user is admin
function isAdmin(req, res, next) {
	// if user is authenticated in the session, carry on 
    if (req.isAuthenticated() && req.user.role === 2)
        return next();

    // if they aren't redirect them to the home page
    res.send(401, 'Unauthorized');
}