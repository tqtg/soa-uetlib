// server
var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var mongoose = require('mongoose');

// config
var db = require('./config/db');

// route
var routes = require('./routes/routes');
var books = require('./routes/books');

var app = express();

// connect to mongoDB
mongoose.connect(db.url);

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.engine('html', require('ejs').renderFile);
app.set('view engine', 'ejs');

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// decode utf-8
app.use(function(req, res, next) {
	for (x in req.body) {
		req.body[x] = decodeURIComponent(req.body[x]);
		console.log(req.body);
	}
	next();
});

app.use('/', routes);
app.use('/books/api', books);

//	startup our app at http://localhost:3000
app.set('port', process.env.PORT || 3000);
var server = app.listen(app.get('port'), function() {
	console.log('Express server listening on port ' + server.address().port);
});

module.exports = app;
