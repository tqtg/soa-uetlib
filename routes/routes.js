var express = require('express');
var router = express.Router();

router.get('/books', function(req, res, next) {
	res.render('index.html');
});

router.get('/', function(req, res, next) {
	res.render('index.html');
});

module.exports = router;
