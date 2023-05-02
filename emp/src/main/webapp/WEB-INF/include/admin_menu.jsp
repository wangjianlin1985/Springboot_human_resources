<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<dl id="menu-appmgr">
			<dt><i class="Hui-iconfont" style="font-size: 18px;">&#xe62b;&nbsp;</i>&nbsp;员工信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/emp/admin/empmgr" title="">员工列表</a></li>
					<li><a href="/emp/admin/toaddemp" title="">增加员工</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-appmgr">
			<dt><i class="Hui-iconfont" style="font-size: 18px;">&#xe637;&nbsp;</i>&nbsp;考勤管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/emp/admin/attmgr" title="">考勤记录</a></li>
					<li><a href="/emp/admin/attmgr-chart" title="">汇总统计</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-appmgr">
			<dt><i class="Hui-iconfont" style="font-size: 18px;">&#xe63a;&nbsp;</i>&nbsp;薪资管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/emp/admin/tosalaryadd" title="">发放工资</a></li>
					<li><a href="/emp/admin/salarymgr" title="">工资报表</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-appmgr">
			<dt><i class="Hui-iconfont" style="font-size: 18px;">&#xe68a;&nbsp;</i>&nbsp;会议通知管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/emp/admin/meetingmgr" title="">会议列表</a></li>
					<li><a href="/emp/admin/toaddmetting" title="">发起会议</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-appmgr">
			<dt><i class="Hui-iconfont" style="font-size: 18px;">&#xe639;&nbsp;</i>&nbsp;培训管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/emp/admin/trainmgr" title="">培训记录</a></li>
				</ul>
			</dd>
		</dl>
	</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>