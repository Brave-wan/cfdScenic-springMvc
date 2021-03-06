<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>订单管理-开票管理
			</div>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>查询条件</h2>
				<em></em>
			</div>
		</div>
		<div class="querydiv">
			<p>
				<label for="searchValue">查询类别：</label> <select id="searchValue"
					class="easyui-combobox"
					data-options="panelHeight: 'auto',editable:false">
					<option checked value="1">已开</option>
					<option value="2">未开</option>
				</select>
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="refundManager.search()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>用户申请审核</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			<a id="baseCompanyManagerUserBtn" href="javascript:void(0)"
				onclick="refundManager.toCheck(1)" class="easyui-linkbutton"
				iconCls="icon-redo">取消</a> <a id="baseCompanyManagerRoleBtn"
				href="javascript:void(0)" onclick="refundManager.kaip()"
				class="easyui-linkbutton" iconCls="icon-redo">开票</a>
				<!-- <a id="baseCompanyManagerRoleBtn"
				href="javascript:void(0)" onclick="refundManager.dingdan()"
				class="easyui-linkbutton" iconCls="icon-redo">查看订单</a> -->
		</div>
	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="refundManagerGid">
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="scripts/opensource/background/invoice/Manager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
