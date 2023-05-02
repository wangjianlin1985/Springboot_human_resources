<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../../include/_meta.jsp"></jsp:include>
<title>员工信息管理系统</title>
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
		薪资信息管理
		<span class="c-gray en">&gt;</span>
		工资详情
		<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<div class="Hui-article">
		<article class="cl pd-20" style="width:680px;">
		<form action="saveSalary" method="post" class="form form-horizontal">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">发放月份：</label>
				<div class="formControls col-xs-8 col-sm-9">
					${salary.salaryDate }
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">员工：</label>
				<div class="formControls col-xs-8 col-sm-3">
				${emp.empName }
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">基本工资：</label>
				<div class="formControls col-xs-8 col-sm-9">
				${salary.baseAmt }
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">餐补：</label>
				<div class="formControls col-xs-8 col-sm-9">
				${salary.mealAmt }
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">房补：</label>
				<div class="formControls col-xs-8 col-sm-9">
				${salary.houseAmt }
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">全勤奖：</label>
				<div class="formControls col-xs-8 col-sm-9">
				${salary.fullAtt }
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">额外补助：</label>
				<div class="formControls col-xs-8 col-sm-9">
				${salary.subsidy }
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">个税：</label>
				<div class="formControls col-xs-8 col-sm-9">
				<strong class="c-warning">-${salary.tax }</strong>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">罚款：</label>
				<div class="formControls col-xs-8 col-sm-9">
				<strong class="c-warning">-${salary.fine }</strong>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">罚款明细：</label>
				<div class="formControls col-xs-8 col-sm-9">
				${salary.fineDetail }
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">实发工资：</label>
				<div class="formControls col-xs-8 col-sm-9">
				${salary.actAmount }
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<a href="#" onclick="javascript:history.go(-1);"><input class="btn btn-primary radius" type="button" value="&nbsp;&nbsp;返回&nbsp;&nbsp;"></a>
				</div>
			</div>
		</form>
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
var E = window.wangEditor
var editor = new E('#editor'); // 两个参数也可以传入 elem 对象，class 选择器
//开启debug模式
editor.customConfig.debug = true;
// 关闭粘贴内容中的样式
editor.customConfig.pasteFilterStyle = false
// 忽略粘贴内容中的图片
editor.customConfig.pasteIgnoreImg = true
// 使用 base64 保存图片
//editor.customConfig.uploadImgShowBase64 = true

// 上传图片到服务器
editor.customConfig.uploadFileName = 'myFile'; //设置文件上传的参数名称
editor.customConfig.uploadImgServer = '/emp/upload'; //设置上传文件的服务器路径
editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024; // 将图片大小限制为 3M
editor.customConfig.menus =[
    'head',  // 标题
    'bold',  // 粗体
    'fontSize',  // 字号
    'fontName',  // 字体
    'italic',  // 斜体
    'underline',  // 下划线
    'strikeThrough',  // 删除线
    'foreColor',  // 文字颜色
    'backColor',  // 背景颜色
    'link',  // 插入链接
    'image',  // 插入图片
    'list',  // 列表
    'justify',  // 对齐方式
    'table',  // 表格
    'undo',  // 撤销
    'redo'  // 重复
];
//自定义上传图片事件
editor.customConfig.uploadImgHooks = {
	before : function(xhr, editor, files) {
		
	},
	success : function(xhr, editor, result) {
		console.log("上传成功");
	},
	fail : function(xhr, editor, result) {
		console.log("上传失败,原因是"+result);
	},
	error : function(xhr, editor) {
		console.log("上传出错");
	},
	timeout : function(xhr, editor) {
		console.log("上传超时");
	}
}

editor.create()

function getEditor(){
	var content = editor.txt.html();
	$("#content").val(content);
	return true;
}

</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>