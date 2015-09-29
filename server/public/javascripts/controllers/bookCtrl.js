app.controller('BookCtrl', function($rootScope, $scope, $http, soaFactory, ngDialog) {
	$scope.categories = [];
	$scope.books = [];
	$scope.isEditable = [];
	$scope.userInfo = '';

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
	// get categories
	soaFactory.getCategories().then(function(data) {
		$scope.categories = data.data;
	})

	// get first 20 books of page 1 without category
	soaFactory.getByPage(1).then(function(data) {
		$scope.books = data.data;
	})

	$scope.getBooksBySearch = function(query) {
		// console.log(query);
		soaFactory.getBySearch(query).then(function(data) {
			$scope.books = data.data;
		})
	}

	$scope.getBookByCategory = function(category, page) {
		soaFactory.getByCategory(category, page).then(function(data) {
			$scope.books = data.data;
		})
	}

	$scope.getUserInformation = function() {
		soaFactory.getUserInfo().then(function(data) {
			$scope.userInfo = data.data;
		})
	}

	$scope.nextPage = function(){
	}

	$scope.backPage = function(){
	}
	   $scope.filteredTodos = []
  ,$scope.currentPage = 1
  ,$scope.numPerPage = 10
  ,$scope.maxSize = 5;
  
  $scope.makeTodos = function() {
    $scope.todos = [];
    for (i=1;i<=1000;i++) {
      $scope.todos.push({ text:'todo '+i, done:false});
    }
  };
  $scope.makeTodos(); 
  
  $scope.$watch('currentPage + numPerPage', function() {
    var begin = (($scope.currentPage - 1) * $scope.numPerPage)
    , end = begin + $scope.numPerPage;
    
    $scope.filteredTodos = $scope.todos.slice(begin, end);
  });
});