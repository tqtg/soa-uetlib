app.controller('BookCtrl', function($rootScope, $scope, soaFactory, ngDialog) {
	$scope.categories = [];
	$scope.books = [];
	$scope.isEditable = [];
	// --------------------------------------
	$scope.clickToOpen = function (book) {
        ngDialog.open({
				    template: '<div id="dialog">\
				    						<div class="row">\
				    							<div class="col-md-3 col-md-offset-1">\
				    								<img src="'+book.image+'">\
				    							</div>\
				    							<div class="col-md-7 col-md-offset-1">\
				    								<h4>'+book.title+'</h4>\
				    								<p>'+book.author+'</p>\
				    							</div>\
				    						</div>\
				    					 </div>',
				    plain: true,
				    className: 'ngdialog-theme-default'
				});
    };
   // -----------------------------------------------

	// get categories
	soaFactory.getCategories().then(function(data) {
		$scope.categories = data.data;
	})

	// get first 50 books
	soaFactory.getBooks().then(function(data) {
		$scope.books = data.data;
	})


	// // save a book to the server
	// $scope.save = function($event) {
	// 	if ($event.which == 13 && $scope.bookInput) {
	// 		soaFactory.saveBook({
	// 			"title": $scope.bookInput
	// 		}).then(function(data) {
	// 			$scope.books.push(data.data);
	// 		})
	// 		$scope.bookInput = '';
	// 	}
	// }

	// // update the book
	// $scope.edit = function($event, i) {
	// 	if ($event.which == 13 && $event.target.value.trim()) {
	// 		var b = $scope.books[i];
	// 		soaFactory.updateBook(b._id, {
	// 			title: $event.target.value.trim()
	// 		}).then(function(data) {
	// 			if (data.data) {
	// 				b.title = $event.target.value.trim();
	// 				$scope.isEditable[i] = false;
	// 			} else {
	// 				alert('Oops something went wrong!');
	// 			}
	// 		})
	// 	}
	// }

	// // delete a book
	// $scope.delete = function(i) {
	// 	soaFactory.deleteBook($scope.books[i]._id).then(function(data) {
	// 		if (data.data) {
	// 			$scope.books.splice(i, 1);
	// 		}
	// 	})
	// }
});