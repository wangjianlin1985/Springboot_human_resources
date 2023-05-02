<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../../include/_meta.jsp"></jsp:include>
<title>利捷人力资源管理系统</title>
</head>
<body onload="start();">
<!--_header 作为公共模版分离出去-->
<jsp:include page="../../include/mgr_header.jsp"></jsp:include>
<!--/_header 作为公共模版分离出去-->
<!--_menu 作为公共模版分离出去-->
<jsp:include page="../../include/mgr_menu.jsp"></jsp:include>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<!--/_menu 作为公共模版分离出去-->

<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20" style="padding-top: 0;">
			<div class="mt-20">
				<h2 class="text-center" id="timeDiv">${now }</h2>
				<div style="width: 40%; margin: 0 auto;">
					<div class="text-center">
						<form action="checkin" method="get">
							<c:if test="${checkin ne null }">
								<h3>今天上班&nbsp;<strong class="c-orange">${checkin }</strong>&nbsp;已打卡</h3>
							</c:if>
							<c:if test="${checkin eq null }">
							<input type="hidden" value="${today }" name="today">
							<p><input class="btn radius btn-secondary size-XL" type="submit" value="上班打卡"></p>
							</c:if>
						</form>
					</div>
					<div class="text-center">
						<form action="checkout" method="get">
							<c:if test="${checkout ne null }">
								<h3>今天下班&nbsp;<strong class="c-green">${checkout }</strong>&nbsp;已打卡</h3>
							</c:if>
							<c:if test="${checkout eq null }">
							<input type="hidden" value="${today }" name="today">
							<input class="btn btn-success radius size-XL" type="submit" value="下班打卡">
							</c:if>
						</form>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

<!--_footer 作为公共模版分离出去-->
<jsp:include page="../../include/_footer.jsp"></jsp:include>
<!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
function check(val) {
	if (val < 10) {
		return ("0" + val);
	} 
	else {
		return (val);
	}
}
function displayTime() {
	//获取div元素
	var timeDiv=document.getElementById("timeDiv");
	//获取系统当前的年、月、日、小时、分钟、毫秒
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var minutes = date.getMinutes();
	var second = date.getSeconds();
	var timestr = year + "年" + month + "月" + day + "日  " + check(hour)
			+ ":" + check(minutes) + ":" + check(second);
	//将系统时间设置到div元素中
	timeDiv.innerHTML = timestr;
}
//每隔1秒调用一次displayTime函数
function start(){
    window.setInterval("displayTime()",1000)//单位是毫秒
}
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>