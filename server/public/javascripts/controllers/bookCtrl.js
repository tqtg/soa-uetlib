app.controller('BookCtrl', function($rootScope, $scope, $http, soaFactory, ngDialog) {
	$scope.categories = [];
	$scope.books = [];
	$scope.isEditable = [];
	$scope.userInfo = '';
	$scope.searchQuery = '';
	$scope.currentCategory = '';
	$searchMode = false;
	$scope.currentPage = 1;
	$scope.pageLength = 108; //Number of page for all books, hand fixed the the sake of server performance

  $scope.clickToOpen = function (book) {
  		var cateId = book.category;
      var cateName = '';
      for (i = 0; i < $scope.categories.length; i++){
       if($scope.categories[i].id == cateId){
         cateName = $scope.categories[i].name;
         break;
       }
      }

      content = '<div id="dialog">\
                      <div class="row">\
                        <div class="col-md-3 col-md-offset-1">\
                          <img src="'+book.image+'" style="padding-top: 20px;">\
                        </div>\
                        <div class="col-md-7 col-md-offset-1">\
                          <h3>'+book.title+'</h3>';
      if(book.author != null) content = content + '<p style="padding-top: 15px;"><b>Tác giả:</b> '+book.author+'</p>';  /*style="margin-bottom:2px;"*/
      if(book.publisher != null) content = content + '<p><b>Nhà xuất bản:</b> '+book.publisher+'</p>';
      if(book.date != null) content = content + '<p><b>Ngày xuất bản:</b> '+book.date+'</p>';
      if(book.page != null) content = content + '<p><b>Số trang:</b> '+book.page+'</p>';
          
      content = content + '<p><b>Thể loại:</b> '+ cateName+'</p></div>';
			if(book.description.length < 1000){
				content = content + '<div class="col-md-offset-1 col-md-10">\
														</br>\
														<h5><b>Giới thiệu sách: </b></h5>\
		    										<p style="float:left;color: #666666; font-family: Candara,sans-serif; font-size: 14px; font-weight:;">'+book.description+'</p>\
		   										</div>';
		  }else{
		  	content = content + '<div class="col-md-offset-1 col-md-10">\
														</br>\
														<h5><b>Giới thiệu sách: </b></h5>\
		    										<p style="max-height: 180px; overflow-y: scroll; padding-right: 3px;float:left;color: #666666; font-family: Candara,sans-serif; font-size: 14px; font-weight:;">'+book.description+'</p>\
		   										</div>';
		  }
		  ngDialog.open({ 	
		  		 template: content,
		  		 plain: true,
		  		 className: 'ngdialog-theme-default'	
		  });						
  };
   // -----------------------------------------------
   $scope.getUserInformation = function() {
		soaFactory.getUserInfo().then(function(data) {
			$scope.userInfo = data.data;
		})
	}

	// get categories
	soaFactory.getCategories().then(function(data) {
		$scope.categories = data.data;
		$scope.getUserInformation();
	})

	// get first 16 books of page 1 without category
	soaFactory.getByPage(1).then(function(data) {
		$scope.books = data.data;
	})

	$scope.changePageBySearch = function(page){
		if($scope.searchMode == true){
			soaFactory.getBySearch($scope.searchQuery, page).then(function(data) {
				$scope.books = data.data;
			})
		}
	}

	$scope.getBooksBySearch = function(query) {
		$scope.currentPage=1; 
		$scope.searchMode = true;
		$scope.searchQuery = query;
		soaFactory.getSearchResultLength(query).then(function(data) {
			$scope.pageLength = Math.floor(data.data/16);
		})
		$scope.changePageBySearch(1);
		// console.log($scope.pageLength);
	}

	$scope.changePageByCategory = function(page){
		if($scope.searchMode == false){
			soaFactory.getByCategory($scope.currentCategory, page).then(function(data) {
				$scope.books = data.data;
			})
		}
	}

	$scope.getBookByCategory = function(category) {
		$scope.currentPage=1; 
		$scope.currentCategory = category;
		$scope.searchMode=false;
		soaFactory.getCategoryLength(category).then(function(data) {
			$scope.pageLength = Math.floor(data.data/16);
			// console.log($scope.pageLength);
		})
		$scope.changePageByCategory(1);
	}

	// MAY BE SLOW DOWN SERVER
	$scope.getAllBooksLength = function() {
		soaFactory.getAllBooksLength().then(function(data) {
			$scope.pageLength = Math.floor(data.data/16);
		})
	}
});