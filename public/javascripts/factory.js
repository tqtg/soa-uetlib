app.factory('soaFactory', function($http) {
	var urlBase = "/books/api";
	var bookService = {};

	bookService.getBooks = function() {
		return $http.get(urlBase);
	}

	bookService.saveBook = function(book) {
		return $http.post(urlBase, book);
	}

	bookService.updateBook = function(id, book) {
		return $http.put(urlBase + '/' + id, book);
	}

	bookService.deleteBook = function(id) {
		return $http.delete(urlBase + '/' + id)
	}

	return bookService;
});