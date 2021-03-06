var visitorsManager = {
		delUrl : "./background/visitorsManage/deleteVisitors",
		ylUrl  : "./visitorsOrder/weiShowPage",
		updateUrl : "./visitorsOrder/updateVisitorsOrder",
		yanpiaoUrl : "./visitorsOrder/checkVisitorsOrder",
		init : function(){
			$(window).load(function(){
				$('#visitorsGid').datagrid({
					url:'./visitorsOrder/getVisitorsOrderWeiList.json',
					border:false,
					fit:true,
					singleSelect:true,
					idField:'id',
					pagination:true,
					pageSize:20,
					striped:true,
					fitColumns:true,
					columns : [ [ {
						field : 'id',
						title : 'id',
						align:'center',width:100,hidden:true

					},{
						field : 'order_code',
						title : '订单号',
						align:'center',width:100
					},{
                        field : 'order_state',
                        title : '订单状态',
                        align:'center',width:50,
                        formatter : function(
                            v,r,i){
                            switch(v){
                                case 1:
                                    return '确认订单';
                                case 2:
                                    return '已支付';
                                case 3:
                                    return '已使用';
                                case 4:
                                    return '已完成';
                                case 5:
                                    return '申请退款';
                                case 6:
                                    return '退款失败';
                                case 7:
                                    return '退款成功';
                                case 8:
                                    return '已过期';
                                case 9:
                                    return '作废';
                            }
                        }
                    },{
						field : 'nickName',
						title : '订票人',
						align:'center',width:100
					},{
						field : 'v_name',
						title : '景区名称',
						align:'center',width:100
					},{
						field : 'price',
						title : '原价',
						align:'center',width:50
					},{
						field : 'real_price',
						title : '总价',
						align:'center',width:50
					},{
						field : 'invoice_state',
						title : '发票状态',
						align:'center',width:50,
						formatter : function(
								v,r,i) {
							switch(v) {
							case 0:
								return '未开票';
							case 1:
								return '开票中';
							case 2:
								return '已开票';
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
			$('#showImg').attr('src', arr[1]);
		},
		
		add : function(){
			$('#visitorsWindow').window('open');
			$('#visitorsForm').form('clear');
			$('#id').val(0);
		},
		
		addSub : function(){
			var a = visitorsManager.addUrl;
			/*if($('#imgFile').val() == null || $('#imgFile').val() == ''){
				a = bankListManager.subUrl1;
			}*/
			var formElement = document.getElementById("visitorsForm");
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
							$.messager.progress('close');
							$('#visitorsWindow').window('close');
							$('#visitorsGid').datagrid('reload');
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
		
		edit : function() {
			var row = $('#visitorsGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			console.log(row);
            $('#orderState option[value != ""]').remove();
			$('#ImageSubWindow').window('open');
			$('#uploadForm').form('clear');
			$('#orderCode').val(row.order_code);
			$('#realPrice').val(row.real_price);
			$('#userId').val(row.user_id);
//			$('#searchValue').combobox('setValue',row.status);
            if (row.order_state == 1){
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 2) {
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 3) {
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 4) {
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 5) {
                $('#orderState').append("<option  value='7'>退款成功</option>");
                $('#orderState').append("<option  value='6'>退款失败</option>");
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 6) {
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 7) {
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 8) {
                $('#orderState').append("<option  value='9'>作废</option>");
            } else if (row.order_state == 9) {

            }
            $('#orderState').combobox({});
		},
		
		submit : function(){
			var orderCode = $('#orderCode').val();
			var realPrice = $('#realPrice').val();
			var userId = $('#userId').val();
			var a = $('#orderState').combobox('getValue');
			$.post(visitorsManager.updateUrl, {
				orderCode : orderCode , 
				orderState : a,
                realPrice : realPrice,
                userId :userId
			}).success(function(data){
				if(data.success){
					eu.showMsg("操作成功！");
					$("#visitorsGid").datagrid('reload');
					$('#ImageSubWindow').window('close');
					$('#uploadForm').form('clear');
				} else {
					eu.showMsg(data);
				}
			}).error(function(data){
				eu.showMsg("系统错误，请联系管理员！");
			})
		},

		search : function(){
			var orderCode =  $("#searchValue1").val();
			$('#visitorsGid').datagrid('load',{orderCode: orderCode});
			$('#visitorsGid').datagrid('clearSelections');
		},
		
		del : function() {
			var row = $('#visitorsGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$.messager.confirm("提示信息", "您确定要将该数据删除吗？", function(r) {
				if (r) {
					$.post(visitorsManager.delUrl, {
						id : row.id
					}).success(function(data) {
						if (data.success) {
							eu.showMsg("操作成功！");
							$("#visitorsGid").datagrid('reload');
						} else {
							eu.showMsg(data);
						}
					}).error(function(data) {
						eu.showMsg("系统错误，请联系管理员！");
					})
				} else {
					$('#visitorsGid').datagrid('clearSelections');
				}
			});
		},
		
		yl : function(){
			var row = $("#visitorsGid").datagrid('getSelected');
			if(!row){
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			window.location.href = visitorsManager.ylUrl+"?id="+row.id;
		},
		
		yanpiao : function(){
			var row = $('#visitorsGid').datagrid('getSelected');
			if(!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$('#yanpiaoWindow').window('open');
			$('#yanpiaoForm').form('clear');
			$('#yanpiaoId').val(row.order_code);
		},
		
		yanpiaoSub : function(){
			var yanpiaoId = $('#yanpiaoId').val();
			$.post(visitorsManager.yanpiaoUrl, {
				orderCode : yanpiaoId
			}).success(function(data){
				if(data.success){
					eu.showMsg("操作成功！");
					$("#visitorsGid").datagrid('reload');
					$('#yanpiaoWindow').window('close');
					$('#yanpiaoForm').form('clear');
				} else {
					eu.showMsg(data);
				}
			}).error(function(data){
				eu.showMsg("系统错误，请联系管理员！");
			})
		}
};
visitorsManager.init();