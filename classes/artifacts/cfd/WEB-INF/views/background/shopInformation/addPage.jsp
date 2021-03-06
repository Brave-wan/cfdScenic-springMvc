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


</style>

<div style="width:98.5%;padding:10px;">
    <div style="padding:8px 2px;border-bottom:1px solid #ccc">当前位置:商家管理-添加店铺信息</div>
    <form id="ff" action="./background/shopInformationManager/addShopInformation" method="post" enctype="multipart/form-data">
        <table class="tb">
            <tr>
                <td>店铺名称：</td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>地址：</td>
                <td><input type="text" name="address" /></td>
            </tr>
            <tr>
                <td>经度：</td>
                <td><input type="text" name="latitude" /></td>
            </tr>
            <tr>
                <td>纬度：</td>
                <td><input type="text" name="longitude" /></td>
            </tr>
            <tr>
                <td>电话：</td>
                <td><input type="text" name="phone" /></td>
            </tr>

            <tr>
                <td>内容介绍：</td>
                <td><input type="text" name="content" /></td>
            </tr>

            <%--<tr>
                <td>店铺分类：</td>
                <td>
                    <select name="shopId">
                        <c:forEach items="${list}" var="list">
                            <option value="${list.id}">${list.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>--%>

            <tr>
                <td>年龄：</td>
                <td><input type="text" name="age" /></td>
            </tr>
            <tr>
                <td>真是姓名：</td>
                <td><input type="text" name="realName" /></td>
            </tr>
            <tr>
                <td>身份证：</td>
                <td><input type="text" name="idCard" /></td>
            </tr>

            <tr>
                <td>经营范围：</td>
                <td><input type="text" name="businessScope" /></td>
            </tr>

            <tr>
                <td>账户名称：</td>
                <td><input type="text" name="accountName" /></td>
            </tr>
            <tr>
                <td>银行卡号：</td>
                <td><input type="text" name="bankCard" /></td>
            </tr>
            <tr>
                <td>开户行：</td>
                <td><input type="text" name="accountBank" /></td>
            </tr>
            <tr>
                <td>提现密码：</td>
                <td><input type="text" name="cashPassWord" /></td>
            </tr>
            <tr>
                <td>人均消费：</td>
                <td><input type="text" name="consumption" /></td>
            </tr>
            <tr>
                <td>账户类型：</td>
                <td>
                    <input type="radio" checked="checked" name="accountType" value="0">对公
                    <input type="radio" name="accountType" value="1">个人
                </td>
            </tr>
            <tr>
                <td>性别：</td>
                <td>
                    <input type="radio" checked="checked" name="sex" value="0">男
                    <input type="radio" name="sex" value="1">女
                </td>
            </tr>
            <tr>
                <td>是否有营业执照：</td>
                <td>
                    <input type="radio" checked="checked" name="isLicense" value="0">否
                    <input type="radio" name="isLicense" value="1">是
                </td>
            </tr>
            <tr>
                <td>是否有无线：</td>
                <td>
                    <input type="radio" checked="checked" name="isWifi" value="0">否
                    <input type="radio" name="isWifi" value="1">是
                </td>
            </tr>
            <tr>
                <td>是否有浴室：</td>
                <td>
                    <input type="radio" checked="checked" name="isYushi" value="0">否
                    <input type="radio" name="isYushi" value="1">是
                </td>
            </tr>
            <tr>
                <td>是否有便利设施：</td>
                <td>
                    <input type="radio" checked="checked" name="isBlss" value="0">否
                    <input type="radio" name="isBlss" value="1">是
                </td>
            </tr>
            <tr>
                <td>是否有媒体或科技：</td>
                <td>
                    <input type="radio" checked="checked" name="isMedia" value="0">否
                    <input type="radio" name="isMedia" value="1">是
                </td>
            </tr>
            <tr>
                <td>是否有食品或饮品：</td>
                <td>
                    <input type="radio" checked="checked" name="isFood" value="0">否
                    <input type="radio" name="isFood" value="1">是
                </td>
            </tr>
            <tr>
                <td>就餐开始时间：</td>
                <td><input type="text" name="startDate" class="easyui-datebox" style="width: 150px;" required="required" /></td>
            </tr>
            <tr>
                <td>就餐结束时间：</td>
                <td><input type="text" name="endDate" class="easyui-datebox" style="width: 150px;" required="required" /></td>
            </tr>
            <tr>
                <td>头像：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile1" class="button" value="上传...">
                        <input type="file" size="30" name="imageFile1">
                    </div>
                </td>
            </tr>
            <tr>
                <td>背景图：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile2" class="button" value="上传...">
                        <input type="file" size="30" name="imageFile2">
                    </div>
                    <%--<input type="file" name="imageFile2" />--%>
                </td>
            </tr>
            <tr>
                <td>手持证件照：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile3" class="button" value="上传...">
                        <input type="file" size="30" name="imageFile3">
                    </div>
                </td>
            </tr>
            <tr>
                <td>身份证正面照：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile4" class="button" value="上传...">
                        <input type="file" size="30" name="imageFile4">
                    </div>
                </td>
            </tr>
            <tr>
                <td>身份证反面照：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile5" class="button" value="上传...">
                        <input type="file" size="30" name="imageFile5">
                    </div>
                </td>
            </tr>
            <tr>
                <td>营业执照照片：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile6" class="button" value="上传...">
                        <input type="file" size="30" name="imageFile6">
                    </div>
                </td>
            </tr>
            <tr>
                <td>其他证件照1：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile7" class="button" value="上传...">
                        <input type="file" size="30" name="imageFile7">
                    </div>
                </td>
            </tr>
            <tr>
                <td>其他证件照2：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile8" class="button" value="上传...">
                        <input type="file" size="30" name="imageFile8">
                    </div>
                </td>
            </tr>
            <tr>
                <td>商家logo：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile9" class="button" value="上传...">
                        <input type="file" size="30" name="imageFile9">
                    </div>
                </td>
            </tr>
            <tr>
                <td>上传图集：</td>
                <td>
                    <div class="uploader blue">
                        <input type="text" id="filename" class="filename" readonly="">
                        <input type="button" name="imageFile" class="button" value="上传...">
                        <input type="file" size="30" id="imgFile" multiple="true" name="imageFile1" onchange="uploadImg()">
                    </div>
                </td>
            </tr>
            <tr>
                <td>图集展示：</td>
                <td>
                    <div id="pictureId"></div>
                    <input type="hidden" id="pictureUrl" name="pictureUrl">
                </td>
            </tr>
            <tr>
                <td>店铺信息介绍：</td>
                <td>
                    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
                    <input type="hidden" name="detailId" id="detailId" />

                </td>
            </tr>

        </table>
        <div style="text-align:center;padding:5px">
            <a id="showPageSaveBtn" href="javascript:void(0)" onclick="save()" class="easyui-linkbutton" iconCls="icon-save">确定</a>
            <%--<a id="showPageCancleBtn" href="javascript:void(0)" onclick="window.location.href ='./background/visitorsManage/toVisitorsManage'" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>--%>
            <a id="showPageCancleBtn" href="javascript:void(0)" onclick="history.go(-1)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
        </div>
    </form>
</div>

<script type="text/javascript" src="${ctx}/scripts/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript">

    $(function(){

        $("input[type=file]").change(function(){
            $(this).parents(".uploader").find(".filename").val($(this).val());
        });

        $("input[type=file]").each(function(){
            if($(this).val()==""){
                $(this).parents(".uploader").find(".filename").val("请选择图片...");
            }
        });

    });

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');


    //获取内容
    function getAllHtml() {
        alert(UE.getEditor('editor').getContent())
    }

    //获取纯文本内容
    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }

    function save() {

        var arr = [];
        arr.push(UE.getEditor('editor').getContentTxt());

        $.post("${basePath}/htmlText/addHtmlText",{
            contentText : arr.join("\n"),
            contentHtml : UE.getEditor('editor').getContent(),
            activityDetail : UE.getEditor('editor').getContent(),
            type : 0,
            state : 0
        }).success(function (data) {
        	alert(data);
            if (data.success) {
                $("#detailId").val(data.obj);
                var imgUrl = "";
                $(".imgUrl").each(function () {
                    imgUrl += $(this).attr("src")+",";
                })
                $("#pictureUrl").val(imgUrl);
                $('#ff').submit();
            } else {
                eu.showMsg(data);
            }
        }).error(function (data) {
            eu.showMsg("系统错误，请联系管理员！");
        })

    }
    function uploadImg() {
        var formElement = document.getElementById("ff");
        var oRep = new XMLHttpRequest();
        oRep.open("POST","${basePath}/pictureLibrary/uploadPicture",true);
        oRep.send(new FormData(formElement));
        oRep.onreadystatechange = function() {
            if (oRep.readyState == 4) {
                var b = oRep.responseText;
                var a = eval("("+b+")");
                if (oRep.status == 200) {
                    if (a.success == true) {
                        for (var i = 0 ; i < a.obj.length ; i++){
                            var position = a.obj[i];
                            for(var pro in position){
                                $("#pictureId").append("<div style='float: left;'>" +
                                        "<img class='imgUrl' src='"+position[pro]+"'>" +
                                        "<img src='${ctx}/images/xhao.png' style='width: 10px;height: 10px;vertical-align: top; margin-left: -10px;' onclick='deleteImg(this)'>" +
                                        "</div>");
                            }
                        }
                    }
                }
            }
        }
    }

    function deleteImg(obj) {
        $(obj).parent().remove();
    }

</script>


<%@ include file="/WEB-INF/views-commons/footer.jsp"%>