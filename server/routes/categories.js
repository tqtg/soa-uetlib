var Category = require('../models/category.js');

module.exports = function(app, mongoose, passport) {
	/* GET all categories */
	app.get('/categories', function(req, res, next) {
		Category.find(function(err, categories) {
			if (err) return next(err);
			res.json(categories);
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