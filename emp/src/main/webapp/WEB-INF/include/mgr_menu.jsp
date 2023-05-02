<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<dl id="menu-appmgr">
			<dt><i class="Hui-iconfont" style="font-size: 18px;">&#xe62b;&nbsp;</i>&nbsp;员工信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/emp/manager/empmgr" title="">员工列表</a></li>
					<li><a href="/emp/manager/toaddemp" title="">增加员工</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-appmgr">
			<dt><i class="Hui-iconfont" style="font-size: 18px;">&#xe637;&nbsp;</i>&nbsp;考勤管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/emp/manager/home" title="">考勤打卡</a></li>
					<li><a href="/emp/manager/attmgr" title="">考勤记录</a></li>
					<li><a href="/emp/manager/attmgr-chart" title="">汇总统计</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-appmgr">
			<dt><i class="Hui-iconfont" style="font-size: 18px;">&#xe68a;&nbsp;</i>&nbsp;会议通知管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/emp/manager/meetingmgr" title="">会议列表</a></li>
					<li><a href="/emp/manager/toaddmetting" title="">发起会议</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-appmgr">
			<dt><i class="Hui-iconfont" style="font-size: 18px;">&#xe62d;&nbsp;</i>&nbsp;招聘信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/emp/manager/jobmgr" title="">应聘信息列表</a></li>
					<li><a href="/emp/manager/toaddjob" title="">录入应聘信息</a></li>
					<li><a href="/emp/manager/rckmgr" title="">人才库</a></li>
				</ul>
			</dd>
		</dl>
		<dl id="menu-appmgr">
			<dt><i class="Hui-iconfont" style="font-size: 18px;">&#xe639;&nbsp;</i>&nbsp;培训管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a href="/emp/manager/trainmgr" title="">培训记录</a></li>
					<li><a href="/emp/manager/toaddtrain" title="">录入培训计划</a></li>
				</ul>
			</dd>
		</dl>
		
	</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>