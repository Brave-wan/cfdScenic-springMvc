<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>订单管理-酒店订单管理
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
				<label for="searchId">订单号：</label> <input id="searchId"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchValue">查询状态：</label> <select id="searchValue1"
					class="easyui-combobox"
					data-options="panelHeight: 'auto',editable:false">
					<option checked value="">请选择订单状态</option>
					<option value="1">未使用</option>
					<option value="2">已使用</option>
					<option value="3">退款订单</option>
				</select>
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="refundManager.serch()">查询</a>
			</p>
		</div>
		<div class="menubtndiv">
			<a id="baseCompanyManagerUserBtn" href="javascript:void(0)"
				onclick="refundManager.toCheck()" class="easyui-linkbutton"
				iconCls="icon-redo">修改酒店订单状态</a>
				<a id="shareRuleYlBtn"
				href="javascript:void(0)" onclick="refundManager.yl()" 
				class="easyui-linkbutton" iconCls="icon-add">预览</a>
				<!-- <a id="shareRuleYlBtn"
				href="javascript:void(0)" onclick="refundManager.kaip()" 
				class="easyui-linkbutton" iconCls="icon-add">开票</a> -->
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
<div id="editWindow" class="easyui-window" title="修改酒店订单状态"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="editForm" method="post">
				<input type="hidden" name="id" id="id"/>
				<input type="hidden" name="userId" id="userId"/>
				<input type="hidden" name="shopInformationId" id="shopInformationId"/>
				<input type="hidden" name="realPrice" id="realPrice"/>
				<p>
					<label class="bolb">状态:</label>
					<select name="orderState" id="orderState">
<!-- 						<option value="1">确认订单</option> -->
						<option value="2">支付订单</option>
						<option value="5">已过期</option>
						<option value="4">已使用</option>
						<option value="3">申请退款</option>
						<option value="6">申请退款成功</option>
						<option value="7">申请退款失败</option>
					</select>
				</p>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="refundManager.addSub()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="refundManager.cancel()">取消</a>
		</div>
	</div>
<script type="text/javascript"
	src="scripts/opensource/background/Order/hotelorderManager.js"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
