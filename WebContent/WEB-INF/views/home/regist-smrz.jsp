<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/views-commons/taglibs.jsp"%>
<%@ include file="/WEB-INF/views-commons/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/styles/sysHtkj/home/css.css"/>
<script src="${basePath}/scripts/jquery/jquery-1.9.1.js" type="text/javascript" charset="utf-8"></script>
		<title>商家注册-实名认证</title>
		<style type="text/css">
			.yzm{
				width: 25%;
			}
			.bg-img{
				background: url(images/index-bg.jpg) no-repeat top center;
				width: 120px;
				height: 120px;
				display: none;
			}
		</style>
<script>
	function imgf1() {
		var docObj = document.getElementById("imageFile1");
		var imgObjPreview = document.getElementById("ima1");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性  
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '120px';
			imgObjPreview.style.height = '120px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL();
			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式     
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜    
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("imageFile1");
			//必须设置初始大小 
			localImagId.style.width = "120px";    
			localImagId.style.height = "120px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片 
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';

			document.selection.empty();
		}
		return true;
	}
	function imgf2() {
		var docObj = document.getElementById("imageFile2");
		var imgObjPreview = document.getElementById("ima2");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性  
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '120px';
			imgObjPreview.style.height = '120px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL();
			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式     
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜    
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("imageFile2");
			//必须设置初始大小 
			localImagId.style.width = "120px";    
			localImagId.style.height = "120px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片 
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';

			document.selection.empty();
		}
		return true;
	}
	function imgf3() {
		var docObj = document.getElementById("imageFile3");
		var imgObjPreview = document.getElementById("ima3");
		if (docObj.files && docObj.files[0]) {
			//火狐下，直接设img属性  
			imgObjPreview.style.display = 'block';
			imgObjPreview.style.width = '120px';
			imgObjPreview.style.height = '120px';
			//imgObjPreview.src = docObj.files[0].getAsDataURL();
			//火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式     
			imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);
		} else {
			//IE下，使用滤镜    
			docObj.select();
			var imgSrc = document.selection.createRange().text;
			var localImagId = document.getElementById("imageFile3");
			//必须设置初始大小 
			localImagId.style.width = "120px";    
			localImagId.style.height = "120px";
			//图片异常的捕捉，防止用户修改后缀来伪造图片 
			try {
				localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";
				localImagId.filters
						.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;
			} catch (e) {
				alert("您上传的图片格式不正确，请重新选择!");
				return false;
			}
			imgObjPreview.style.display = 'none';

			document.selection.empty();
		}
		return true;
	}
	function tijiao(){
		 var realname = $("#realname").val();
		 if(realname==""||realname==null){
			 alert("店主姓名不能为空");
			 return false;
		 }
		 var idcard = $("#idcard").val();
		 if(idcard==""||idcard==null){
			 alert("身份证号码不能为空");
			 return false;
		 }else{
			 if(!(/^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/.test(idcard))){
				 alert("身份证格式错误");
				 return false;
			 }
		 }
		 var imageFile1 = $("#imageFile1").val();
		 if(imageFile1==""||imageFile1==null){
			 alert("请上传手持身份证照");
			 return false;
		 }
		 var imageFile2 = $("#imageFile2").val();
		 if(imageFile2==""||imageFile2==null){
			 alert("请上传身份证正面照");
			 return false;
		 }
		 var imageFile3 = $("#imageFile3").val();
		 if(imageFile3 =""||imageFile3==null){
			 alert("请上传身份证背面照");
			 return false;
		 }else {
			 return true;
		 }
		 /* $.post("${ctx}/background/shopInformationManager/renzShopInformation",$("#form").serialize()).success(function(data){
				if (data.success) {
					window.location.href = "${ctx}/ShopUserPcController/login";
				} else {
					alert(data.message);
				}
			}).error(function(ex){
				alert("系统错误！");
			}); */
	}
</script
</head>
<body>
	<div class="box">
			<p class="ready-yy">已有账号?<span style="border: 1px solid #fff;padding: 5px;margin-left: 10px;"><a href="${ctx}/ShopUserPcController/login" style="color: #fff;">立即登录</a></span></p>
			<div class="regis-rz" style="height: 460px;">
			<h3>商家注册-实名认证</h3>
				<form method="post" id="form" onsubmit="return tijiao();" action="${ctx}/background/shopInformationManager/renzShopInformation" enctype="multipart/form-data">
				<div class="buss-dz">
					<p>店主姓名</p>
					<input type="text" placeholder="请输入店主姓名" name="realname" id="realname" class="regist"/>
				</div>
				<div class="buss-dz">
					<p style="padding-bottom: 10px;">店主性别</p>
					<input type="radio" value="0" name="sex" checked="checked" style="margin-left: 25px;font-size: 14px;width: 20px;vertical-align:top;margin-top: 2px;"/>男 
					<input type="radio" value="1" name="sex"   style="margin-left:10px;width: 20px;vertical-align: top;margin-top: 2px;"/>女
				</div>
				<div class="buss-dz">
					<p>身份证号</p>
					<input type="text" placeholder="请输入身份证号" name="idcard" id="idcard" class="regist"/>
					<!-- <span class="tishi"><img src="images/tishi.jpg" style="margin-right: 5px;"/>请输入正确的身份证号 </span> -->
				</div>
				<div class="upload-photos">
					<p>请上传证件照片</p>
					<div class="uplo">
						<p class="bg-img"></p>
						<img src="" id="ima1" style="width: 120px;height: 120px;"/>
						<p style="">手持身份证</p>
						<p>
							<a href="javascript:void(0)" class="file" class="sczp-btn" >请点击添加
								   <input type="file" name="imageFile1" id="imageFile1" onchange="imgf1()">		   				 
							</a>
						</p>
					</div>
					<div class="uplo">
						<img src="images/1.jpg" style="width: 120px;height: 120px;"/>
						<p style="">手持身份证示例</p>
					</div>
					<div class="uplo">
						<p class="bg-img"></p>
						<img src="" id="ima2" style="width: 120px;height: 120px;"/>
						<p style="">正面身份证</p>
						<p>
							<a href="javascript:void(0)" class="file" class="sczp-btn">请点击添加
								   <input type="file" name="imageFile2" id="imageFile2" onchange="imgf2()">		   	   				 
							</a>
						</p>
					</div>
					<div class="uplo">
						<img src="images/2.jpg" style="width: 120px;height: 120px;"/>
						<p style="">正面身份证示例</p>
					</div>
					<div class="uplo">
						<p class="bg-img"></p>
						<img src="" id="ima3" style="width: 120px;height: 120px;"/>
						<p style="">背面身份证</p>
						<p>
							<a href="javascript:void(0)" class="file" class="sczp-btn">请点击添加
								    <input type="file" name="imageFile3" id="imageFile3" onchange="imgf3()">		   		   				 
							</a>
						</p>
					</div>
					<div class="uplo">
						<img src="images/2.jpg" style="width: 120px;height: 120px;"/>
						<p style="">背面身份证示例</p>
					</div>
					<p style="color: #999;width: 1200px;float: left;font-size:16px;margin-top: 70px;margin-left: 30px;">隐私声明：我们承诺此信息仅用于核实商家信息，不做任何其他用途！</p>
				</div>
				<p style="border: 0;text-align: center;float: left;width: 800px;margin-top: px;"><input type="submit" class="dl-btn" style="width: 400px;height: 40px;cursor:pointer" value="下一步"></p>
			</div>
		</div>
	</body>
</html>