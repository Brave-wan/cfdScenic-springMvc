<%@ page contentType="text/html; charset=UTF-8"
	trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height: 220px;">
		<div class="panel_title">
			<div style="float: left; padding: 10px">
				<c:if test="${type==1}">
					<i class="curLoca"></i><font class="fontbold">当前位置:</font>景区管理-景点管理
				</c:if><c:if test="${type==2}">
					<i class="curLoca"></i><font class="fontbold">当前位置:</font>景区管理-基础活动
				</c:if><c:if test="${type==3}">
					<i class="curLoca"></i><font class="fontbold">当前位置:</font>景区管理-结伴游活动
				</c:if><c:if test="${type==4}">
					<i class="curLoca"></i><font class="fontbold">当前位置:</font>景区管理-积分商品
				</c:if><c:if test="${type==5}">
					<i class="curLoca"></i><font class="fontbold">当前位置:</font>景区管理-积分门票
				</c:if>
			</div>
		<input type="hidden" value="${type}" id="typeId"/>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
				<h2>查询条件</h2>
				<em></em>
			</div>
		</div>
		<div class="querydiv">
			<p>
				<label for="searchValueNickName">名称：</label> <input id="searchValue1"
					class="easyui-validatebox">
			</p>
			<p>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-search" onclick="visitorsManager.serch()">查询</a>
			</p>
		</div>
		<div class="titlediv">
			<div class="titleTxt">
                <c:if test="${type==1}">
                    <h2>景点设置</h2>
                </c:if><c:if test="${type==2}">
                    <h2>基础活动设置</h2>
                </c:if><c:if test="${type==3}">
                    <h2>结伴游活动设置</h2>
                </c:if><c:if test="${type==4}">
                    <h2>积分商品设置</h2>
                </c:if><c:if test="${type==5}">
                    <h2>积分门票设置</h2>
                </c:if>

				<em></em>
			</div>
		</div>
		<div class="menubtndiv">
			<a id="shareRuleAddBtn" href="javascript:void(0)"
				onclick="visitorsManager.add()" class="easyui-linkbutton"
				iconCls="icon-add">新增</a> <a id="shareRuleEditBtn"
				href="javascript:void(0)" onclick="visitorsManager.edit()"
				class="easyui-linkbutton" iconCls="icon-redo">修改</a><a
				id="shareRuleDelBtn" href="javascript:void(0)"
				onclick="visitorsManager.del()" class="easyui-linkbutton"
				iconCls="icon-remove">删除</a><a id="shareRuleDelBtn"
				href="javascript:void(0)" onclick="visitorsManager.yl()"
				class="easyui-linkbutton" iconCls="icon-add">预览</a>
				<a id="shareRuleDelBtn"
				href="javascript:void(0)" onclick="visitorsManager.manyidu()"
				class="easyui-linkbutton" iconCls="icon-add">设置满意度</a>
		</div>

	</div>
	<div data-options="region:'center',border:true">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'center'">
				<table id="visitorsGid" >
				</table>
			</div>
		</div>
	</div>
</div>

<div id="visitorsWindow" class="easyui-window" title="添加景点信息"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="visitorsForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" id="id"/>
				<p>
					<label class="bolb">名称:</label> <input id="name"
						name="name" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">描述:</label> <input id="visitorsDescribe"
						name="visitorsDescribe" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">原价:</label> <input id="price"
						name="price" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">折后价:</label> <input id="newPrice"
						name="newPrice" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">地址:</label> <input id="address"
						name="address" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">经度:</label> <input id="longitude"
						name="longitude" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">纬度:</label> <input id="latitude"
						name="latitude" type="text" class="easyui-validatebox sleek"/>
				</p>
				<p>
					<label class="bolb">上传主图:</label><input type="file" id="imgFile" name="imageFile"/>  
				</p>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="visitorsManager.addSub()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="visitorsManager.cancel()">取消</a>
		</div>
	</div>
</div>

<div id="ImgWindow" class="easyui-window" title="图片查看"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 380px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<p>
				<img alt="图片暂时无法显示" style="width: 350px; height: auto" src=""
					id="showImg">
			</p>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="$('#ImgWindow').window('close');visitorsManager.updateImg();">修改</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#ImgWindow').window('close');$('#showImg').attr('src','');">关闭</a>
		</div>
	</div>
</div>

<div id="ImageSubWindow" class="easyui-window" title="修改主图"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form id="uploadForm" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" id="ImgId"/>
				<input type="hidden" name="type" value="0"/>
				<p>
					<label class="bolb">上传图片:</label><input type="file" id="imgFile" name="imageFile"/>  
				</p>
				<p>
					<label class="bolb">图片:</label> <img id="imgSrcId" style="height: 50%;width: 50%;" src="" alt="图片暂时无法显示"/>
				</p>
					
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)"
				onclick="visitorsManager.submit()">确定</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#ImageSubWindow').window('close');$('#uploadForm').form('clear');">取消</a>
		</div>
	</div>
</div>
<div id="manyiduWindow" class="easyui-window" title="设置满意度"
	data-options="closed:true,inline:true"
	style="width: 420px; height: 300px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<form action="post" id="manyiForm">
				<input type="hidden" id="manyiId" />
				<p>
					<label class="bolb">选择满意度:</label> 
                    <select id="manyiduId" class="easyui-combobox" data-options="panelHeight: '150',editable:false">
                        <option  value='0'>0</option>
                        <option  value='10%'>10%</option>
                        <option  value='20%'>20%</option>
                        <option  value='30%'>30%</option>
                        <option  value='40%'>40%</option>
                        <option  value='50%'>50%</option>
                        <option  value='60%'>60%</option>
                        <option  value='70%'>70%</option>
                        <option  value='80%'>80%</option>
                        <option  value='90%'>90%</option>
                        <option  value='100%'>100%</option>
					</select>
				</p>
			</form>
		</div>
		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="visitorsManager.manyiSub()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)"
				onclick="$('#manyiduWindow').window('close');$('#manyiForm').form('clear');">取消</a>
		</div>
	</div>
</div>
<script type="text/javascript"
	src="scripts/opensource/background/visitors/Manager.js?id=123"></script>
<%@ include file="/WEB-INF/views-commons/footer.jsp"%>
