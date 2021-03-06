<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/WEB-INF/views-commons/taglibs.jsp" %>
    <%@ include file="/WEB-INF/views-commons/meta.jsp" %>

    <title>华腾后台管理系统</title>
    <c:set var="viewmodel" value="${ctx}/scripts/zckj"/>
    <c:set var="controller" value="${ctx}/"/>
    <%-- 设置当前控制器的路径，简化路径操作 --%>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/styles/sysHtkj/home/login.css"/>

    <script src="${basePath}/scripts/jquery/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="${basePath}/scripts/sysHtkj/home/login.js" type="text/javascript" charset="utf-8"></script>

    <script type="text/javascript">
        if (self != top) {
            window.top.location.href = "logout";
        }

        function changeImg() {
            var imgSrc = $("#imgObj");
            var src = imgSrc.attr("src");
            imgSrc.attr("src", chgUrl(src));
        }

        //时间戳
        //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
        function chgUrl(url) {
            var timestamp = (new Date()).valueOf();
            url = url.substring(0, 17);
            if ((url.indexOf("&") >= 0)) {
                url = url + "×tamp=" + timestamp;
            } else {
                url = url + "?timestamp=" + timestamp;
            }
            return url;
        }
    </script>
</head>
<body style="background:#141c46 url(images/sysHtkj/home/beijing.jpg) no-repeat;background-size: cover;">

<div class="centerDiv">
    <p class="mb20"><img src="images/sysHtkj/home/logo_03.png" width="100%"/></p>
    <div class="login_bg"></div>
    <form method="post" action="login" id="subLogin" style="float: none;">
        <div class="login_ct">
            <div class="ct_box">
                <label class="username_lab"></label>
                <input type="text" name="name" placeholder="请输入账号"/>
            </div>
            <div class="ct_box">
                <label class="password_lab"></label>
                <input type="password" name="password" maxlength="6" placeholder="请输入密码"/>
            </div>


            <div class="ct_box">
                <label class="yz_lab"></label>
                <input type="text" name="yz" width="50px" maxlength="4" id="index_code" placeholder="请输入验证码"/>
                <img class="yz_pic" style="vertical-align:bottom;" id="imgObj" alt="验证码" src="img/code1"
                     onclick="changeImg()"/>
            </div>


            <div class="ct_box  mt35 border0">
                <a href="javaScript:void(0);" id="subButton" class="btn">登 陆</a>
            </div>
            <c:if test="${not empty showMsg}">
                <p class="tips"><img src="images/sysHtkj/home/tishi_11.png"/><span>登录失败：${showMsg}</span></p>
            </c:if>
            <c:if test="${empty showMsg}">
                <p class="tips undis"><img src="images/sysHtkj/home/tishi_11.png"/><span>登录失败：${showMsg}</span></p>
            </c:if>
        </div>
    </form>
</div>
<div class="hiddenDiv"></div>
<%@ include file="/WEB-INF/views-commons/footer.jsp" %>