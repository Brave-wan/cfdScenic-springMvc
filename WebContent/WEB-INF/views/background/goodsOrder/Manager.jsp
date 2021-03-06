<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<i class="curLoca"></i><font class="fontbold">当前位置:</font>订单管理-商品订单管理
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
				<label for="searchValueNickName">订单号：</label> <input id="searchValue1"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchValueNickName">下单人：</label> <input id="searchValue2"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchValueNickName">下单时间：</label> <input id="searchValue3"
					class="easyui-validatebox">
			</p>
			<p>
				<label for="searchValueNickName">订单状态：</label> <select id="searchValue4"
					class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
                    <option checked value="">请选择订单状态</option>
                    <option  value="1">待支付</option>
                    <option  value="2">待发货</option>
                    <option  value="3">已发货</option>
                    <option  value="4">已收货</option>
                    <option  value="5">订单完成</option>
                    <option  value="6">申请退款</option>
                    <option  value="7">审核通过</option>
                    <option  value="11">待收货</option>
                    <option  value="8">退款中</option>
                    <option  value="9">退款成功</option>
                    <option  value="10">已驳回</option>
                </select>
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="goodsOrderManager.search()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>商品订单设置</h2>
				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			 <a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="goodsOrderManager.edit()"
				class="easyui-linkbutton" iconCls="icon-redo">修改订单状态</a>
<!-- 			 	<a id="shareRuleEditBtn" -->
<!-- 				href="javascript:void(0)" onclick="goodsOrderManager.yanpiao()" -->
<!-- 				class="easyui-linkbutton" iconCls="icon-redo">验票</a> -->
				<a
				id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="goodsOrderManager.del()" class="easyui-linkbutton"
				iconCls="icon-remove">删除</a>
				<a id="shareRuleYlBtn"
				href="javascript:void(0)" onclick="goodsOrderManager.yl()" 
				class="easyui-linkbutton" iconCls="icon-add">预览</a>
				<!--修改配送费 -->
				<a id="shareRuleYlBtn"
				href="javascript:void(0)" onclick="goodsOrderManager.showDeleverFee()" 
				class="easyui-linkbutton" iconCls="icon-redo">修改订单运费</a>
				
				<!-- <a id="shareRuleYlBtn"
				href="javascript:void(0)" onclick="goodsOrderManager.fp()" 
				class="easyui-linkbutton" iconCls="icon-add">开发票</a> -->
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="goodsOrderGid" >
				</table>
			</div>
		</div>
	</div>
</div>

<div id="ImageSubWindow" class="easyui-window" title="订单状态"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="uploadForm" method="post">
				<input type="hidden" id="orderCode" />
                <input type="hidden" id="realPrice" />
                <input type="hidden" id="userId" />
                <input type="hidden" id="orderStateNumber" />
				<p>
					<label class="bolb">订单状态：</label>
					<select id="orderState" class="easyui-combobox" data-options="panelHeight: 'auto',editable:false">
                        <option value='0'>请选择</option>
                        <option value="3">已发货</option>
                        <option value="4">已收货</option>
                        <option value="6">申请退款</option>
                        <option value="7">审核通过</option>
                        <option value="8">退款中</option>
                        <option value="9">退款成功</option>
                        <option value="10">已驳回</option>
					</select>
				</p>
				<div id="showInputId" style="display:none"> 
				<p><label>订  单  号：<input id="expressNumber"></label></p>
				<p><label>快递公司：<input id="expressName"></label></p>
				</div>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)"
				onclick="goodsOrderManager.submit()">确定</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#ImageSubWindow').window('close');$('#uploadForm').form('clear');">取消</a>
		</div>
	</div>
</div>
<div id="yanpiaoWindow" class="easyui-window" title="验票"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="yanpiaoForm" method="post">
				<input type="hidden" id="yanpiaoId" />
					
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)"
				onclick="goodsOrderManager.yanpiaoSub()">确定</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#yanpiaoWindow').window('close');$('#yanpiaoForm').form('clear');">取消</a>
		</div>
	</div>
</div>
<div id="deleverFeeWindow" class="easyui-window" title="运费修改"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			 修改运费为：<input type="text" id="deleverFee">
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)"
				onclick="goodsOrderManager.updateDeleverFee()">确定</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#deleverFeeWindow').window('close');">取消</a>
		</div>
	</div>
</div>
<script>

	$(document).ready(function() {
		$("#orderState").combobox({
			onChange : function(n, o) {
				var inputNumber = $("#orderState").combobox('getValue')
				if(inputNumber == 3){
					$("#showInputId").attr("style", "display:block;");  
				}else{
					$("#showInputId").attr("style", "display:none;");  
				}
			}
		});
	});
</script>
<script type="text/javascript"
	src="scripts/opensource/background/goodsOrder/Manager.js?id=11"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
