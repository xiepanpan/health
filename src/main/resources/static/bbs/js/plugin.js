function topic_init(wrap, lst){
	var template = '<div class="topic-item" >' + 
			'<header>'+
			'	<span class="date"></span>'+
			'	<img class="avatar" />'+
			'	<span class="nick">Admin</span>'+
			'	<span class="clear"></span>'+
			'</header>'+
			'<article>'+
			'	<h4></h4>'+
			'	<p></p>'+
			'</article>'+
			'<footer>'+
			'	<span class="fav rt"><i class="fa fa-heart-o"></i>&nbsp;<span class="fav-count"></span></span>'+
			'	<span class="cmt rt"><i class="fa fa-eye"></i>&nbsp; <span class="view-count"></span></span>'+
			'	<span class="clear"></span>'+
			'</footer>'+
		'</div>';
	wrap.html("");
	for(var x =0 ; x < lst.length;x++){
		var t_item = $(template);
		var data_item = lst[x];
		t_item.find('.date').html(data_item['createDate']);
		t_item.find('.avatar').attr('src', data_item['avatar']);
		t_item.find('.nick').html(data_item['loginName']);
		t_item.find('h4').html(data_item['topicTitle']);
		t_item.find('p').html(data_item['topicContent']);
		t_item.find(".fav-count").html(data_item['favCount']);
		t_item.find(".view-count").html(data_item["viewCount"]);
		t_item.attr("data-id", data_item['topicId']);
		wrap.append(t_item);
	}
}

function topic_init_2(wrap, lst){
	var template = '<div class="topic-item" >' + 
			'<header>'+
			'	<a class="topic-edit">编辑</a><a class="topic-del">删除</a>'+
			'	<img class="avatar" />'+
			'	<span class="nick">Admin</span>'+
			'	<span class="clear"></span>'+
			'</header>'+
			'<article>'+
			'	<h4></h4>'+
			'	<p></p>'+
			'</article>'+
			'<footer>'+
			'	<span class="fav rt"><i class="fa fa-heart-o"></i>&nbsp;<span class="fav-count"></span></span>'+
			'	<span class="cmt rt"><i class="fa fa-comment-o"></i>&nbsp; <span class="cmt-count"></span></span>'+
			'	<span class="clear"></span>'+
			'</footer>'+
		'</div>';
	wrap.html("");
	for(var x =0 ; x < lst.length;x++){
		var t_item = $(template);
		var data_item = lst[x];
		t_item.find('.topic-edit').attr('data-id',data_item['topicId']);
		t_item.find('.topic-del').attr('data-id',data_item['topicId']);
		t_item.find('.avatar').attr('src', data_item['avatar']);
		t_item.find('.nick').html(data_item['loginName']);
		t_item.find('h4').html(data_item['topicTitle']);
		t_item.find('p').html(data_item['topicContent']);
		t_item.find(".fav-count").html(data_item['favCount']);
		t_item.find(".cmt-count").html(data_item["cmtCount"]);
		t_item.attr("data-id", data_item['topicId']);
		wrap.append(t_item);
	}
}

function check_login(){
	var uid = $("#login_user_id").val();
	if(uid && uid.trim().length > 0){
		return true;
	}else{
		location.href = "/login/admin_login";
		return false;
	}
}

$(function(){
	if($("#footer").length > 0){
		$(document.body).css('padding-bottom', "80px");
	}
	
	$(document.body).delegate(".topic-item", "click", function(){
		location.href = "/index/detail?id=" + $(this).attr("data-id");
	});
	
	$(document.body).delegate(".topic-edit", "click", function(e){
		e.stopPropagation();e
		location.href = "/user/edit?id=" + $(this).attr("data-id");
	})
	
	$(document.body).delegate(".topic-del","click", function(e){
		e.stopPropagation();
		location.href = "/user/del?id=" + $(this).attr('data-id');
	});
})