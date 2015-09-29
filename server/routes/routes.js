module.exports = function(app, passport) {
    // login
    app.get('/login', function(req, res, next) {
        res.render('login.html', {
            message: req.flash('loginMessage')
        });
    });

    app.post('/login', passport.authenticate('local-login', {
        successRedirect: '/',
        failureRedirect: '/login',
        failureFlash: true
    }));

    app.post('/mobile/login', function(req, res, next) {
        passport.authenticate('local-login', function(err, user, info) {
            if (err) {
                return res.send(404, 'Error');
            }
            if (!user) {
                return res.send(401, 'Fail');
            }
            req.logIn(user, function(err) {
                if (err) {
                    res.send(401, 'Fail');
                }
                return res.send(200, 'OK');
            });
        })(req, res, next);
    });

    app.post('/admin', passport.authenticate('admin-login', {
        failureFlash: true
    }), function(req, res) {
        res.send(200, 'Authorized');
    });

    // signup
    app.get('/signup', function(req, res, next) {
        res.render('signup.html', {
            message: req.flash('signupMessage')
        });
    });

    app.post('/signup', passport.authenticate('local-signup', {
        successRedirect: '/',
        failureRedirect: '/signup',
        failureFlash: true
    }));

    app.post('/mobile/signup', function(req, res, next) {
        passport.authenticate('local-signup', function(err, user) {
            if (err) {
                return res.send(404, 'Error');
            }
            if (user) {
            	return res.send(200, 'OK');
            } else {
                return res.send(401, 'Username is already taken');
            }

        })(req, res, next);
    });

    // logout
    app.get('/logout', function(req, res, next) {
        req.logout();
        res.redirect('/login');
    });

    // user infor
    app.get('/user', isLoggedIn, function(req, res, next) {
        res.json(req.user.username);
    });

    // use passport to control
    app.get('/', isLoggedIn, function(req, res, next) {
        res.sendfile('views/index.html');
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