<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 引入bootstrap样式 -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/sb-admin.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/index.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/resource/js/bootstrap.min.js"></script>
<script type="text/javascript">
	//收藏
	function add(text) {
		//判断用户有没有登录
		//没有登录 去登录页面
		var user = '${sessionScope.user}';
		if (user == "") {
			location.href = "/user/login";

		} else {
			//如果已经登录  进行收藏
			//ajax做收藏   text(title) url
			//获得收藏夹地址  ==当前页面的访问地址
			var url = window.location.href;
			$.post("/favorite/addFavo", {
				text : text,
				url : url
			}, function(msg) {
				if (msg == "-1") {
					alert("url不合法")
				} else if (msg == "0") {
					alert("收藏失败,请重试");
				} else {
					alert("收藏成功");

				}
			}, "json");

		}

	}

	function goPage(pageNum) {
		var id = '${article.id}';
		location.href = "/indexs/select?id=" + id + "&pageNum=" + pageNum;
	}

	//添加评论
	function addComment() {
		//评论的内容
		var content = $("#content").val();
		//评论的文章id
		var id = '${article.id}';
		$.post("/comment/addComment", {
			"article.id" : id,
			content : content

		}, function(msg) {
			if (msg == "-1") {
				alert("未登录");
			} else if (msg == "0") {
				alert("评论失败,请稍候重试");
			} else {
				alert("评论成功");
				location.reload();
			}
		}, "json"

		);
	}
</script>
</head>


<div class="container">

	<h1>${article.title }</h1>
	${article.user.username }
	<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd" />
	${article.content }<br>
	<br>

	<button type="button" class="btn btn-info"
		onclick="add('${article.title }')">收藏</button>


	<hr style="border-color: red">
	<c:if test="${sessionScope.user==null }">
		<h5 style="color: red">请登录后评论!</h5>
	</c:if>
	<c:if test="${sessionScope.user!=null }">
		<textarea rows="3" cols="123" id="content"></textarea>
		<button type="button" onclick="addComment()" class="btn btn-secondary">提交评论</button>
	</c:if>
	<br>

	<ul class="list-group list-group-flush">
		<c:forEach items="${info.list }" var="comment">

			<li class="list-group-item">${comment.user.nickname }&nbsp;&nbsp;&nbsp;&nbsp;${comment.created}<br>
				${comment.content }
			</li>


		</c:forEach>
	</ul>
	<jsp:include page="/WEB-INF/view/public/page.jsp"></jsp:include>

</div>


</body>
</html>
