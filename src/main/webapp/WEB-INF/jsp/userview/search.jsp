<%@ page contentType="text/html;charset=UTF-8" language="java"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>欣享数码商城 搜索结果</title>
<link href="resources/css/bootstrap.min.css" rel="stylesheet">
<link href="resources/css/style.css" rel="stylesheet">
<script src="resources/js/jquery.min.js" type="text/javascript"></script>
<script src="resources/js/bootstrap.min.js" type="text/javascript"></script>
<script src="resources/js/layer.js" type="text/javascript"></script>
</head>
<body>
	<!--导航栏部分-->
	<jsp:include page="include/header.jsp" />

	<!-- 中间内容 -->
	<div class="container">
		<div class="row margin-t">
			<div class="form-horizontal">
				<div class="col-md-2 col-sm-2"></div>
				<div class="form-group form-group-lg col-sm-6 col-md-6">
					<input type="text" class="form-control" id="newSearchKeyWord"
						placeholder="iPhone X" />
				</div>
				<button class="btn btn-primary btn-lg col-sm-2 col-md-2 big-button"
					onclick="searchPre()">查找商品</button>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div id="searchResultArea">
				<div class="span16" style="width:1120px;">
					<ul>
						<c:forEach items="${searchList }" var="g" varStatus="t">
							<c:if test="${t.count%4!=0 }">
								<a href="goods/detail?goodsId=${g.goodsId }">
									<li><img src="upload/${g.goodsImg }" />
										<p class="goods-title">${g.goodsName }</p>
										<p class="goods-desc">${g.goodsDesc }</p>
										<p>
											<span class="newprice">${g.goodsPrice }</span>&nbsp;
										</p>
									</li>
								</a>
							</c:if>
							<c:if test="${t.count%4==0 }">
								<a href="goods/detail?goodsId=${g.goodsId }">
									<li class='brick4'><img src="upload/${g.goodsImg }" />
										<p class="goods-title">${g.goodsName }</p>
										<p class="goods-desc">${g.goodsDesc }</p>
										<p>
											<span class="newprice">${g.goodsPrice }元</span>&nbsp;
										</p>
									</li>
								</a>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>

	</div>
	<div class="col-md-12 column" id="clickHotGoods">
	</div>

	<!-- 尾部 -->
	<jsp:include page="include/foot.jsp" />

	<script type="text/javascript">
		$(function() {
			showClickHotGoods();
		})
		function searchPre() {
			var word = $("#newSearchKeyWord").val();
			$.ajax({
				type: "post",
				url: "goods/searchPre",
				data: "keyword=" + word,
				dataType: "json",
				success: function (arr) {
					var str = "<div class='span16' style='width:1120px;'><ul>";
					for (var i = 0; i < arr.length; i++) {
						if ((i + 1) % 4 != 0) {
							str = str + "<a href='goods/detail?goodsId=" + arr[i].goodsId + "'><li>" +
									"<img src='upload/" + arr[i].goodsImg + "' /><p class='goods-title'>" + arr[i].goodsName + "</p>" +
									"<p class='goods-desc'>" + arr[i].goodsDesc + "</p><p><span class='newprice'>" + arr[i].goodsPrice + "元</span>&nbsp;" +
									"</p></li></a>";
						} else {
							str = str + "<a href='goods/detail?goodsId=" + arr[i].goodsId + "'><li class='brick4'>" +
									"<img src='upload/" + arr[i].goodsImg + "' /><p class='goods-title'>" + arr[i].goodsName + "</p>" +
									"<p class='goods-desc'>" + arr[i].goodsDesc + "</p><p><span class='newprice'>" + arr[i].goodsPrice + "元</span>&nbsp;" +
									"</p></li></a>";
						}
					}
					var str = str + "</ul></div>";
					$("#searchResultArea").html(str);
					showClickHotGoods();
				}

			});
		}
			// 点击热度推荐
			function showClickHotGoods() {
				$.ajax({
					type : "post",
					url : "guess/findClickHotGoods",
					dataType : "json",
					success : function(arr) {
						var str = "<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
								"猜你喜欢（点击热度推荐）</h2><div><div class='span16' style='width:1120px;'><ul>";
						if (arr == null || arr == "") {
							str = str + "<h2>用户行为信息不足</h2>"
						} else {
							for (var i = 0; i < arr.length; i++) {
								str = str
										+ "<a href='goods/detail?goodsId="
										+ arr[i].recommendId
										+ "'>"
										+ "<li><img src='upload/" + arr[i].recommendImg + "' /><p class='goods-title'>"
										+ arr[i].recommendName + "</p>"
										+ "<p class='goods-desc'>"
										+ arr[i].recommendDesc
										+ "</p><p><span class='newprice'>"
										+ arr[i].recommendPrice + "</span>&nbsp;"
										+ "</p><p class='goods-desc'>游览次数："
										+ arr[i].recommendNum
										+"</p></li></a>";
							}
						}
						str = str + "</ul></div></div>"

							$("#clickHotGoods").html(str);

					}
				});
		}
	</script>
</body>
</html>