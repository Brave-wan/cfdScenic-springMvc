<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views-commons/header.jsp"%>
<style type="text/css">
	 body{font-family: "微软雅黑";}
    .tb{width:90%;margin:20px auto;}
    .tb td{height:45px;text-align:left;}
    .tb td:first-child{width:10%;}
    .tb td:last-child{width:90%;}
    .tb td input[type=text]{width:80%;height:30px;line-height: 30px;padding-left: 10px;}
    .tb img{width:150px;height:100px;}
    .tb td textarea{padding-left:10px;}
    .tb td label{float: left;width:100px;text-align: left;}
    .l-btn-text{line-height: 30px;}
    .easyui-linkbutton{width:30%;height:30px;display: inline-block;}


    .uploader { position:relative; display:inline-block; overflow:hidden; cursor:default; padding:0; margin:10px 0px; -moz-box-shadow:0px 0px 5px #ddd; -webkit-box-shadow:0px 0px 5px #ddd; box-shadow:0px 0px 5px #ddd; -moz-border-radius:5px; -webkit-border-radius:5px; border-radius:5px; }
    #filename { float:left; display:inline-block; outline:0 none; height:32px; width:180px; margin:0; padding:8px 10px; overflow:hidden; cursor:default; border:1px solid; border-right:0; font:9pt/100% Arial, Helvetica, sans-serif; color:#777; text-shadow:1px 1px 0px #fff; text-overflow:ellipsis; white-space:nowrap; -moz-border-radius:5px 0px 0px 5px; -webkit-border-radius:5px 0px 0px 5px; border-radius:5px 0px 0px 5px; background:#f5f5f5; background:-moz-linear-gradient(top, #fafafa 0%, #eee 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #fafafa), color-stop(100%, #f5f5f5)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#fafafa', endColorstr='#f5f5f5', GradientType=0);
        border-color:#ccc; -moz-box-shadow:0px 0px 1px #fff inset; -webkit-box-shadow:0px 0px 1px #fff inset; box-shadow:0px 0px 1px #fff inset; -moz-box-sizing:border-box; -webkit-box-sizing:border-box; box-sizing:border-box; }
    .button { float:left; height:32px; display:inline-block; outline:0 none; padding:8px 12px; margin:0; cursor:pointer; border:1px solid; font:bold 9pt/100% Arial, Helvetica, sans-serif; -moz-border-radius:0px 5px 5px 0px; -webkit-border-radius:0px 5px 5px 0px; border-radius:0px 5px 5px 0px; -moz-box-shadow:0px 0px 1px #fff inset; -webkit-box-shadow:0px 0px 1px #fff inset; box-shadow:0px 0px 1px #fff inset; }
    .uploader input[type=file] { position:absolute; top:0; right:0; bottom:0; border:0; padding:0; margin:0; height:30px; cursor:pointer; filter:alpha(opacity=0); -moz-opacity:0; -khtml-opacity: 0; opacity:0; }
    input[type=button]::-moz-focus-inner {
        padding:0;
        border:0 none;
        -moz-box-sizing:content-box;
    }
    input[type=button]::-webkit-focus-inner {
        padding:0;
        border:0 none;
        -webkit-box-sizing:content-box;
    }
    input[type=text]::-moz-focus-inner {
        padding:0;
        border:0 none;
        -moz-box-sizing:content-box;
    }
    input[type=text]::-webkit-focus-inner {
        padding:0;
        border:0 none;
        -webkit-box-sizing:content-box;
    }
    /* White Color Scheme ------------------------ */

    .white .button { color:#555; text-shadow:1px 1px 0px #fff; background:#ddd; background:-moz-linear-gradient(top, #eeeeee 0%, #dddddd 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #eeeeee), color-stop(100%, #dddddd)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#eeeeee', endColorstr='#dddddd', GradientType=0);
        border-color:#ccc; }
    .white:hover .button { background:#eee; background:-moz-linear-gradient(top, #dddddd 0%, #eeeeee 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #dddddd), color-stop(100%, #eeeeee)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#dddddd', endColorstr='#eeeeee', GradientType=0);
    }
    /* Blue Color Scheme ------------------------ */

    .blue .button { color:#fff; text-shadow:1px 1px 0px #09365f; background:#064884; background:-moz-linear-gradient(top, #3b75b4 0%, #064884 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #3b75b4), color-stop(100%, #064884)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#3b75b4', endColorstr='#064884', GradientType=0);
        border-color:#09365f; }
    .blue:hover .button { background:#3b75b4; background:-moz-linear-gradient(top, #064884 0%, #3b75b4 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #064884), color-stop(100%, #3b75b4)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#064884', endColorstr='#3b75b4', GradientType=0);
    }
    #initMap {
	width: 100%;
	height: 100%;
	font-size: 12px;
	font-family: "微软雅黑";
	}

</style>


    <div style="width:98.5%;padding:10px;">
        <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:平台管理-添加新闻须知</div>
        <form id="ff" action="./monitorPoint/editMonitor" method="post" enctype="multipart/form-data">
            <table class="tb">
				<input type="hidden" name="id" value="${model.id}">
                <tr>
                    <td>监控名称：</td>
                    <td>
                    	<input type="text" name="name" id="name" value="${model.name}"/>
                    </td>
                </tr>
                <tr>
                    <td>监控编码：</td>
                    <td>
                    	<input type="text" name="code" id="code" value="${model.code }"/>
                    </td>
                </tr>
                <tr>
                    <td>监控介绍：</td>
                    <td>
                    	<input type="text" name="content" id="content" value="${model.content}"/>
                    </td>
                </tr>
                
                <tr>
                    <td>监控经度：</td>
                    <td>
                    	<input type="text" readonly="readonly" id="xPoint" name="xPoint" value="${model.xPoint}"/>
                    </td>
                </tr>
                <tr>
                    <td>监控纬度：</td>
                    <td>
                    	<input type="text" readonly="readonly" id="yPoint" name="yPoint" value="${model.yPoint}"/>
                    </td>
                </tr>
                <tr>
					<td>获取经纬度：</td>
					<td><a href="javascript:void(0)" onclick="openNew()"
						class="easyui-linkbutton" iconCls="icon-add">点击获取经纬度</a></td>
				</tr>
            </table>
            <div style="text-align:center;padding:5px">
                <a id="showPageSaveBtn" href="javascript:void(0)" onclick="$('#ff').submit()" class="easyui-linkbutton" iconCls="icon-save">确定</a>
                <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
            </div>
        </form>
    </div>
<div id="mapWindow" class="easyui-dialog" title="选择经纬度"
	data-options="closed:true,inline:true"
	style="width: 1000px; height: 500px; padding: 5px;">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center'" style="padding: 10px;">
			<div id="initMap" tabindex="0"></div>
			<div id="myPageTop" style="width: 400px; height: 40px;">
				<table style="width: 90%; margin: 0px;">
					<tr>
						<td style="width: 25%; height: 45px; text-align: left;">经度：</td>
						<td style="height: 25%; text-align: left;"><input
							style="width: 100px; height: 20px; line-height: 10px; padding-left: 10px;"
							type="text" id="lat" /></td>
						<td style="width: 25%; height: 45px; text-align: left;">纬度：</td>
						<td style="height: 25%; text-align: left;"><input
							style="width: 100px; height: 20px; line-height: 10px; padding-left: 10px;"
							type="text" id="lon" /></td>
					</tr>
				</table>
			</div>
		</div>

		<div data-options="region:'south',border:false"
			style="text-align: right; padding: 5px 0 0;">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
				href="javascript:void(0)" onclick="saveJW()">确定</a> <a
				class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"
				href="javascript:void(0)" onclick="closeJW()">关闭</a>
		</div>
	</div>
</div>
<script type="text/javascript">
function openNew(){
    var lat = $("#xPoint").val();
    var lon = $("#yPoint").val();
    if(lat == null || lat.length == 0){
        lat = 118.449347
    }
    if(lon == null || lon.length == 0){
        lon = 39.272507
    }
    var map = new AMap.Map("initMap",{
        resizeEnable: true,
        center: [lat,lon]
    });
    var marker = new AMap.Marker({
        position : [ lat, lon ]
    });
    marker.setMap(map);
    map.addControl(new AMap.ToolBar())
    map.setFitView()
    map.on('click', function(e) {
        map.clearMap();
        //经度
        document.getElementById("lat").value = e.lnglat.getLng();
        //纬度
        document.getElementById("lon").value = e.lnglat.getLat();

        var lat1 = $("#lat").val();
        var lon1 = $("#lon").val();
        var marker = new AMap.Marker({
            position : [ lat1, lon1 ]
        });
        marker.setMap(map);
        map.addControl(new AMap.ToolBar())
    });
    $('#mapWindow').window('open');
}
function saveJW() {
    var lat = $('#lat').val();
    var lon = $('#lon').val();
    if (lat == "" || lon == "") {
        alert("请选择经纬度！");
        return false;
    }
    $("#xPoint").val(lat);
    $("#yPoint").val(lon);
    $('#mapWindow').window('close');
}
function closeJW() {
    $('#mapWindow').window('close');
}
</script>

<%@ include file="/WEB-INF/views-commons/footer.jsp"%>