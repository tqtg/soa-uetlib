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

	//GET BY PAGE WITHOUT CATEGORY
	services.getByPage = function(page) {
		return $http.get(urlBase + '/page/' + page);
	}

	services.getCategories = function() {
		return $http.get('/categories');
	}
	
	services.getBySearch = function(query) {
		return $http.get(urlBase + '/search/' + query);
	}

	services.getUserInfo = function() {
		return $http.get('/user');
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