<%@ page contentType="text/html; charset=UTF-8"
         trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'north',border:false" style="height: 220px;">
        <div class="panel_title">
            <div style="float: left; padding: 10px">
                <i class="curLoca"></i><font>当前位置:</font>系统管理-轮播图管理
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
                <label for="searchValueNickName">名称:</label> <input id="searchValue1"
                    class="easyui-validatebox" />
            </p>
            <p>
	            <label for="searchValueNickName">来源：</label> 
	           	<select id="searchValue2" class="easyui-combobox" data-options="panelHeight:'auto',editable:false">
                    <option checked value="">请选择来源</option>
                    <option  value="1">景点</option>
                    <option  value="2">酒店</option>
                    <option  value="3">饭店</option>
                    <option  value="4">小吃</option>
                    <option  value="5">特产</option>
                    <option  value="6">活动</option>
                    <option  value="7">结伴游</option>
                    <option  value="8">app首页轮播图</option>
                    <option  value="9">商城轮播图</option>
	            </select>
            </p>
            <p>
                <a href="javascript:void(0)" class="easyui-linkbutton"
                    iconCls="icon-search" onclick="advertisementManager.search()">查询</a>
            </p>
        </div>
        <div class="titlediv">
            <div class="titleTxt">
                <h2>轮播图设置</h2>
                <em></em>
            </div>
        </div>
        <div class="menubtndiv">
            <a id="shareRuleAddBtn" href="javascript:void(0)" onclick="advertisementManager.add()" class="easyui-linkbutton" iconCls="icon-add">新增</a>
            <a id="shareRuleEditBtn" href="javascript:void(0)" onclick="advertisementManager.edit()" class="easyui-linkbutton" iconCls="icon-redo">修改</a>
            <a id="shareRuleDelBtn" href="javascript:void(0)" onclick="advertisementManager.del()" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
        </div>
    </div>
    <div data-options="region:'center',border:true">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'center'">
                <table id="advertisementGid">
                </table>
            </div>
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
               href="javascript:void(0)" onclick="$('#ImgWindow').window('close');advertisementManager.updateImg();">修改</a>
            <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
                href="javascript:void(0)"
                onclick="closeImgWindow()">关闭</a>
        </div>
    </div>
</div>
<script>
	function closeImgWindow(){
		$('#showImg').attr('src','');
		$('#ImgWindow').window('close');
	}
</script>
<div id="ImageSubWindow" class="easyui-window" title="修改"
     data-options="closed:true,inline:true"
     style="width: 420px; height: 300px; padding: 5px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center'" style="padding: 10px;">
            <form id="uploadForm" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" id="ImgId"/>
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
               onclick="advertisementManager.submit()">确定</a> <a
                class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
                href="javascript:void(0)"
                onclick="$('#ImageSubWindow').window('close');$('#uploadForm').form('clear');">取消</a>
        </div>
    </div>
</div>

<script type="text/javascript" src="scripts/opensource/background/advertisement/Manager.js"></script>
<%@include file="/WEB-INF/views-commons/footer.jsp"%>