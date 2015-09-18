// server
var express = require('express');
var app = express();
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');

var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var passport = require('passport');
var flash = require('connect-flash');
var session      = require('express-session');

var mongoose = require('mongoose');
var db = require('./config/db');

// configuration ===============================================================
mongoose.connect(db.url);
require('./config/passport')(passport); // pass passport for configuration

// set up our express application
app.set('views', path.join(__dirname, 'views'));
app.engine('html', require('ejs').renderFile);
app.set('view engine', 'ejs');

//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// required for passport
app.use(session({ secret: 'soa-assignment1-library-management-system' })); // session secret
app.use(passport.initialize());
app.use(passport.session()); // persistent login sessions
app.use(flash()); // use connect-flash for flash messages stored in session

// routes ======================================================================
require('./routes/books.js')(app, mongoose, passport);
require('./routes/routes.js')(app, passport);

//	startup our app at http://localhost:3000
app.set('port', process.env.PORT || 3000);
var server = app.listen(app.get('port'), function() {
	console.log('Express server listening on port ' + server.address().port);
});

module.exports = app;
