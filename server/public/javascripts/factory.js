app.factory('soaFactory', function($http) {
	var urlBase = "/books";
	var services = {};

	services.getBooks = function() {
		return $http.get(urlBase);
	}

	services.getAllBooks = function() {
		return $http.get(urlBase + '/all');
	}

	services.getByCategory = function(category, page) {
		return $http.get(urlBase + '/category/' + category + '/' + page);
	}

	services.getBook = function(id) {
		return $http.get(urlBase + '/id/' + id);
	}

	services.getCategories = function() {
		return $http.get('/categories');
	}

	// bookService.saveBook = function(book) {
	// 	return $http.post(urlBase, book);
	// }

	// bookService.updateBook = function(id, book) {
	// 	return $http.put(urlBase + '/' + id, book);
	// }

	// bookService.deleteBook = function(id) {
	// 	return $http.delete(urlBase + '/' + id)
	// }

	return services;
});