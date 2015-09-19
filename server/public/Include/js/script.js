$(document).ready(function(){
	var IE='\v'=='v';
	// hide #back-top first
	$("#back-top").hide();
	// fade in #back-top
	$(function () {
		$(window).scroll(function () {
			if (!IE) {
				if ($(this).scrollTop() > 100) {
					$('#back-top').fadeIn();
				} else {
					$('#back-top').fadeOut();
				}
			}
			else {
				if ($(this).scrollTop() > 100) {
					$('#back-top').show();
				} else {
					$('#back-top').hide();
				}	
			}
		});
	});
	$('#back-top a').click(function () {
		$('body,html').animate({
			scrollTop: 0
		}, 800);
		return false;
	});

	$("#cart").on("mouseenter", function(){
		$("#cart .heading span.link_a").popover("destroy");
		clearInterval(backgroundInterval);
		$("#new-notice").removeClass("notice-red");
		var result = getNewNotice();
		if(result){
			$(this).addClass('active');
		} else {
			$("#cart .empty").text("Có lỗi xảy ra");
		}
	});

	$("#cart").on("mouseleave", function(){
		$(this).removeClass('active');
	});

	$("#cart .heading span.link_a").popover("show");
	var backgroundInterval = setInterval(function(){
		$("#new-notice").toggleClass("notice-red");
	},300)

	setTimeout(function(){
		$("#cart .heading span.link_a").popover("hide");
		clearInterval(backgroundInterval);
		$("#new-notice").addClass("notice-red");
	}, 3000);

	$(".maxheight-feat").matchHeight();
});

function getNewNotice(){
	return true;
}