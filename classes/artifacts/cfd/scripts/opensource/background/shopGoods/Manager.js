var shopGoodsManager = {
		//addUrl : "./background/shopGoodsManage/addVisitors",
		delUrl : "./background/shopGoodsManage/deleteShopGoods",
		ylUrl  : "./background/shopGoodsManage/showPage",
		toAddUrl  : "./background/shopGoodsManage/toAddPage",
		editUrl  : "./background/shopGoodsManage/toEditPage",
		//manyiduUrl :"./background/shopGoodsManage/manyidu",
		updateImgUrl :"./background/shopGoodsManage/updateImg",
		init : function(){
			$(window).load(function(){
				$('#shopGoodsGid').datagrid({
					url:'./background/shopGoodsManage/getShopGoodsList.json',
					border:false,
					fit:true,
					singleSelect:true,
					idField:'id',
					pagination:true,
					pageSize:20,
					striped:true,
					fitColumns:true,
					queryParams:{
						type : $("#typeId").val()
					},
					columns : [ [ {
						field : 'id',
						title : 'id',
						align:'center',width:100,hidden:true

					},{
						field : 'goodsName',
						title : '商品名称',
						align:'center',width:100
					},{
						field : 'goodsDescribe',
						title : '描述',
						align:'center',width:200,hidden:true
					},{
						field : 'label',
						title : '标签',
						align:'center',width:100,hidden:true
					},{
						field : 'price',
						title : '原价',
						align:'center',width:50
					},{
						field : 'newPrice',
						title : '新价',
						align:'center',width:50
					},{
						field : 'deliverFee',
						title : '配送费',
						align:'center',width:50,hidden:true
					},{
						field : 'danpin',
						title : '区别单品',
						align:'center',width:50,hidden:true
					},{
						field : 'stock',
						title : '库存',
						align:'center',width:30
					},{
						field : 'isRecomment',
						title : '是否推荐',
						align:'center',width:30,
                        formatter :function (
                            v, r, i) {
                            switch (v) {
                                case 0:
                                    return "否";
                                case 1:
                                    return "是";
                            }
                        }
					},{
						field : 'describeImg',
						title : '主图',
						align : 'center',
						width : 50,
						formatter : function(
								v, r, i) {
								var a = "";
							if (v != null){
								a = r.describeImg;
								a = a.replace(
										"\\",
										"/");
								return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
										+ "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
										+ "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
										+ 'onclick="shopGoodsManager.show('
										+ "'"
										+ r.id
										+ ","
										+ a
										+ ",1"
										+ "'"
										+ ');">查看</a>';
							}else {
								return "<a style='background-color: #2daebf;color: #fff;cursor: pointer;display: "
										+ "inline-block;font-size: 9pt;font-weight: bold;margin-right: 10px;padding: "
										+ "3px 8px;text-decoration: none;text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);' href='javascript:void(0)'"
										+ 'onclick="shopGoodsManager.show('
										+ "'"
										+ r.id
										+ ","
										+ a
										+ ",1"
										+ "'"
										+ ');">查看</a>';
							}
						}
					},{
						field : 'type',
						title : '商品类型',
						align:'center',width:50,hidden:true,
						formatter :function (
						    v, r, i) {
                            switch (v) {
                                case 1:
                                    return "酒店";
                                case 2:
                                    return "饭店";
                                case 3:
                                    return "特产";
                                case 4:
                                    return "小吃";
                            }
                        }
					}] ]
				});
			});
		},
		
		show : function(url) {
			var arr = new Array();
			arr = url.split(",");
			console.log(url);
			$('#ImgWindow').window('open');
			$('#imgId').val(arr[0]);
			$('#selectType').val(arr[2]);
			if (arr[1] != null && arr[1] != ""){
				$('#showImg').attr('src', arr[1]);
			}else {
				$('#showImg').attr('alt', '暂时还没有主图，请上传！');
			}
		},
		
		add : function(){
			var type = $("#typeId").val();
			if(type==2){
				$('#danpinwindow').window('open');
			}else{
				window.location.href = shopGoodsManager.toAddUrl+"?type="+type;
			}
		},
		danpin : function(){
			var type = $("#typeId").val();
			var a = $('input:radio:checked').val();
			window.location.href = shopGoodsManager.toAddUrl+"?type="+type+"&&danpin="+a;
		},
		edit : function() {
			var row = $('#shopGoodsGid').datagrid('getSelected');
			var danpin = $("#danpin").val();
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			window.location.href = shopGoodsManager.editUrl+"?id="+row.id+"&&danpin="+danpin;
		},
		
		updateImg : function(){
			var row = $('#shopGoodsGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			
			$('#ImageSubWindow').window('open');
			$('#uploadForm').form('clear');
			$('#ImgId').val(row.id);
			$('#imgSrcId').attr('src',row.describeImg);
		},
		
		submit : function(){
			var a = shopGoodsManager.updateImgUrl;
			/*if($('#imgFile').val() == null || $('#imgFile').val() == ''){
				a = bankListManager.subUrl1;
			}*/
			var formElement = document.getElementById("uploadForm");
			var oReq = new XMLHttpRequest();
			$.messager.progress({
				title : '提示信息',
				msg : '请稍候...',
				interval : 2000
			});
			oReq.open("POST", a, true);
			oReq.send(new FormData(formElement));
			oReq.onreadystatechange = function() {
				if (oReq.readyState == 4) {
					console.log(oReq.status);
					if (oReq.status == 200) {
						var b = oReq.responseText;
						console.log(b);
						if (b == "success") {
                            eu.showMsg("操作成功");
							$.messager.progress('close');
							$('#ImageSubWindow').window('close');
							$('#shopGoodsGid').datagrid('reload');
						} else {
							$.messager.progress('close');
							eu.showMsg("失败");
						}
					} else {
						$.messager.progress('close');
						eu.showMsg("失败");
					}
				}
			}
		},

        search : function(obj){
			var name =  $("#searchValue1").val();
			if(obj == 2){
			var danpin =  $("#searchValue").combobox("getValue");
			}
			var type = $("#typeId").val();
			var a = $("#danpin").val(danpin);
			$('#shopGoodsGid').datagrid('load',{goodsName: name , type: type,danpin:danpin});
			$('#shopGoodsGid').datagrid('clearSelections');
		},
		
		del : function(obj) {
			var row = $('#shopGoodsGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			var type = 0;
			if(obj == 2){
			type = $("#searchValue").combobox('getValue'); 
			}
			$.messager.confirm("提示信息", "您确定要将该数据删除吗？", function(r) {
				if (r) {
					$.post(shopGoodsManager.delUrl, {
						id : row.id,type:type
					}).success(function(data) {
						if (data.success) {
							eu.showMsg("操作成功！");
							$("#shopGoodsGid").datagrid('reload');
						} else {
							eu.showMsg(data);
						}
					}).error(function(data) {
						eu.showMsg("系统错误，请联系管理员！");
					})
				} else {
					$('#shopGoodsGid').datagrid('clearSelections');
				}
			});
		},
		
		yl : function(){
			var row = $("#shopGoodsGid").datagrid('getSelected');
			if(!row){
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			var type = $("#typeId").val();
			if(type==2){
				var danpin =  $("#searchValue").combobox("getValue");
				window.location.href = shopGoodsManager.ylUrl+"?id="+row.id+"&&danpin="+danpin;	
			}else{
				window.location.href = shopGoodsManager.ylUrl+"?id="+row.id;
			}
			
			
		},

};
shopGoodsManager.init();