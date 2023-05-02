<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../../include/_meta.jsp"></jsp:include>
<title>利捷人力资源管理系统</title>
</head>
<body>
<!--_header 作为公共模版分离出去-->
<jsp:include page="../../include/emp_header.jsp"></jsp:include>
<!--/_header 作为公共模版分离出去-->
<!--_menu 作为公共模版分离出去-->
<jsp:include page="../../include/emp_menu.jsp"></jsp:include>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<!--/_menu 作为公共模版分离出去-->

<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页
		<span class="c-gray en">&gt;</span>
		个人中心
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20" style="width:480px;">
			<table class="table table-border">
				<tr>
					<td class="text-r">员工号</td>
					<td>${emp.empNo }</td>
				</tr>
				<tr>
					<td class="text-r">部门</td>
					<td>${emp.dept }</td>
				</tr>
				<tr>
					<td class="text-r">姓名</td>
					<td>${emp.empName }</td>
				</tr>
				<tr>
					<td class="text-r">性别</td>
					<td>${emp.gender }</td>
				</tr>
				<tr>
					<td class="text-r">出生日期</td>
					<td>${emp.birthday }</td>
				</tr>
				<tr>
					<td class="text-r">级别</td>
					<td>
						<c:if test="${emp.role eq 1 }"><span class="label label-secondary radius" style="font-size: 12px;">职员</span></c:if>
						<c:if test="${emp.role eq 2 }"><span class="label label-warning radius" style="font-size: 12px;">主管</span></c:if>
					</td>
				</tr>
				<tr>
					<td class="text-r">职位</td>
					<td>${emp.position }</td>
				</tr>
				<tr>
					<td class="text-r">状态</td>
					<td>
						<c:if test="${emp.status eq -1 }"><span class="badge radius">已离职</span></c:if>
						<c:if test="${emp.status eq 1 }"><span class="radius badge" style="background-color: #5cb85c;">在职</span></c:if>
					</td>
				</tr>
				<tr>
					<td class="text-r">薪资</td>
					<td>${emp.salary }</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td>
						<a class="btn btn-success radius" href="/emp/emp/tochpwd">&nbsp;&nbsp;修改密码&nbsp;&nbsp;</a>
					</td>
				</tr>
			</table>
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

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>