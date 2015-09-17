module.exports = function(app, passport) {
	// login
	app.get('/login', function(req, res, next) {
		res.render('login.html');
	});

	app.post('/login', passport.authenticate('local-login', {
		successRedirect : '/',
		failureRedirect : '/login',
		failureFlash : true
	}));

	// signup
	app.get('/signup', function(req, res, next) {
		res.render('signup.html');
	});

	app.post('/signup', passport.authenticate('local-signup', {
		successRedirect : '/',
		failureRedirect : '/signup',
		failureFlash : true
	}));

	// logout
	app.get('/logout', function(req, res, next) {
		req.logout();
		res.redirect('/login');
	});

	// use passport to control
	app.get('/', isLoggedIn, function(req, res, next) {
		res.render('index.html');
	});

	app.get('/*', function(req, res, next) {
		res.redirect('/');
	});
}

// route middleware to make sure a user is logged in
function isLoggedIn(req, res, next) {
    // if user is authenticated in the session, carry on 
    if (req.isAuthenticated())
        return next();

    // if they aren't redirect them to the home page
    res.redirect('/login');
}