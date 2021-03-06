var goodsOrderManager = {
		delUrl : "./background/goodsOrderManage/deleteGoodsOrder",
		updateDeleverFeeUrl : "./background/goodsOrderManage/updateDeleverFee",
        ylUrl : "./background/goodsOrderManage/showPage",
        updateUrl : "./background/goodsOrderManage/updateGoodsOrder",
        fpurl : "./background/goodsOrderManage/kaipiao",
		init : function(){
			$(window).load(function(){
				$('#goodsOrderGid').datagrid({
					url:'./background/goodsOrderManage/getGoodsOrderList.json',
					border:false,
					fit:true,
					idField:'id',
					pagination:true,
					singleSelect:true,
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
                                    return '待支付';
                                case 2:
                                    return '待发货';
                                case 3:
                                    return '已发货';
                                case 4:
                                    return '已收货';
                                case 5:
                                    return '订单完成';
                                case 6:
                                    return '申请退款';
                                case 7:
                                    return '审核通过';
                                case 11:
                                    return '待收货';
                                case 8:
                                    return '退款中';
                                case 9:
                                    return '退款成功';
                                case 10:
                                    return '已驳回';
                            }
                        }
                    },{
						field : 'nickName',
						title : '下单人',
						align:'center',width:50
					},{
						field : 'goodsName',
						title : '商品名称',
						align:'center',width:100
					},
//					{
//						field : 'price',
//						title : '原价',
//						align:'center',width:50
//					},
					{
						field : 'real_price',
						title : '应付价格',
						align:'center',width:50
					},{
						field : 'create_time',
						title : '下单时间',
						align:'center',width:50,
                        formatter : function (
                                v, r, i) {
                            var date = new Date(v);
                            var year = date.getFullYear().toString();
                            var month = (date.getMonth() + 1);
                            var day = date.getDate().toString();
                            var hour = date.getHours().toString();
                            var minutes = date.getMinutes().toString();
                            var seconds = date.getSeconds().toString();
                            if (month < 10) {
                                month = "0" + month;
                            }
                            if (day < 10) {
                                day = "0" + day;
                            }
                            if (hour < 10) {
                                hour = "0" + hour;
                            }
                            if (minutes < 10) {
                                minutes = "0" + minutes;
                            }
                            if (seconds < 10) {
                                seconds = "0" + seconds;
                            }
                            return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
                        }
					},{
					    field : 'is_comment',
                        title : '是否评价',
                        align : 'center',
                        width : 30,
                        formatter : function (
                                v, r, i) {
                            switch (v) {
                                case 0 :
                                    return '否';
                                case 1 :
                                    return '是';
                            }
                        }
                    },{
					    field : 'is_pickup',
                        title : '是否自提',
                        align : 'center',
                        width : 30,
                        formatter : function (
                                v, r, i) {
                            switch (v) {
                                case 0 :
                                    return '否';
                                case 1 :
                                    return '是';
                            }
                        }
                    },{
					    field : 'is_delete',
                        title : '是否删除',
                        align : 'center',
                        width : 30,
                        formatter : function (
                                v, r, i) {
                            switch (v) {
                                case 0 :
                                    return '否';
                                case 1 :
                                    return '是';
                            }
                        }
                    },{
                    	field : 'billing',
						title : '发票状态',
						align:'center',width:50,
						formatter : function (
                                v, r, i) {
                            switch (v) {
                                case 0 :
                                    return '未开';
                                case 1 :
                                    return '已开';
                            }
                        }
                    }] ]
				});
			});
		},
		
		edit : function() {
			var row = $('#goodsOrderGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			console.log(row);
//            $('#orderState option[value != ""]').remove();
			$('#ImageSubWindow').window('open');
//			$('#uploadForm').form('clear');
			$('#orderCode').val(row.order_code);
            $('#realPrice').val(row.real_price);
            $('#userId').val(row.user_id);
            $('#orderStateNumber').val(row.order_state);

//            if (row.order_state == 1){
//
//            } else if (row.order_state == 2) {
//
//            } else if (row.order_state == 3) {
//            	
//            } else if (row.order_state == 4) {
//
//            } else if (row.order_state == 5) {
//
//            } else if (row.order_state == 6) {
//                $('#orderState').append("<option  value='7'>审核通过</option>");
//                $('#orderState').append("<option  value='10'>已驳回</option>");
//            } else if (row.order_state == 7) {
//                $('#orderState').append("<option  value='3'>已发货</option>");
//            } else if (row.order_state == 8) {
//                $('#orderState').append("<option  value='9'>确认退款</option>");
//            } else if (row.order_state == 9) {
//
//            } else if (row.order_state == 10) {
//
//            }else if (row.order_state == 11) {
//
//            }
//            $('#orderState').combobox({});
//            /*<option  value="3">已发货</option>
//                <option  value="4">已收货</option>
//                <option  value="5">订单完成</option>
//                <option  value="6">申请退款</option>
//                <option  value="7">审核通过</option>
//                <option  value="11">待收货</option>
//                <option  value="8">退款中</option>
//                <option  value="9">退款成功</option>
//                <option  value="10">已驳回</option>*/
		},
		
		submit : function(){
			var inputNumber = $("#orderState").combobox('getValue')
			if(inputNumber == 0){
				alert("请选择要修改的状态！");
				return false;
			}
			var orderState = $('#orderStateNumber').val();
			switch (orderState) {
			case 2:
				if(inputNumber != 3){
					alert("只能选择已发货状态！");
					return false;
				}
				break;
			case 6:
				if(inputNumber != 3 && inputNumber != 10){
					alert("只能选择审核通过或已驳回状态！");
					return false;
				}
				break;
			case 8:
				if(inputNumber != 9){
					alert("只能选择退款成功状态！");
					return false;
				}
				break;
			default:
				if (confirm("操作状态有误，修改之后将无法回退，确定继续吗？"))
				{
					break;
				}else{
					return false;
				}
			}
			var orderCode = $('#orderCode').val();
            var realPrice = $('#realPrice').val();
            var userId = $('#userId').val();
            var expressCode = $('#expressNumber').val();
            var expressName = $('#expressName').val();
			var a = $('#orderState').combobox('getValue');
			$.post(goodsOrderManager.updateUrl, {
				orderCode : orderCode ,
                orderState : a,
                realPrice : realPrice,
                userId :userId,
                expressCode : expressCode,
                expressName : expressName
			}).success(function(data){
				if(data.success){
					eu.showMsg("操作成功！");
					$("#goodsOrderGid").datagrid('reload');
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
			var nickName =  $("#searchValue2").val();
			var createTime =  $("#searchValue3").val();
			var orderState =  $("#searchValue4").combobox("getValue");
			$('#goodsOrderGid').datagrid('load',{orderCode: orderCode , nickName: nickName , createTime: createTime , orderState: orderState});
			$('#goodsOrderGid').datagrid('clearSelections');
		},
		
		del : function() {
			var row = $('#goodsOrderGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$.messager.confirm("提示信息", "您确定要将该数据删除吗？", function(r) {
				if (r) {
					$.post(goodsOrderManager.delUrl, {
						id : row.id
					}).success(function(data) {
						if (data.success) {
							eu.showMsg("操作成功！");
							$("#goodsOrderGid").datagrid('reload');
						} else {
							eu.showMsg(data);
						}
					}).error(function(data) {
						eu.showMsg("系统错误，请联系管理员！");
					})
				} else {
					$('#goodsOrderGid').datagrid('clearSelections');
				}
			});
		},
		
		yl : function(){
			var row = $("#goodsOrderGid").datagrid('getSelected');
			if(!row){
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			window.location.href = goodsOrderManager.ylUrl+"?id="+row.id;
		},
		fp : function(){
			var row = $("#goodsOrderGid").datagrid('getSelected');
			if(!row){
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			var billing = row.billing;
			if(billing==1){
				eu.showMsg("已经开过发票了，请重新选择");
				return;
			}
			var code = row.order_code;
			window.location.href = goodsOrderManager.fpurl+"?id="+row.id+"&&code="+code;
		},
		yanpiao : function(){
			var row = $('#goodsOrderGid').datagrid('getSelected');
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
			$.post(goodsOrderManager.yanpiaoUrl, {
				orderCode : yanpiaoId
			}).success(function(data){
				if(data.success){
					eu.showMsg("操作成功！");
					$("#goodsOrderGid").datagrid('reload');
					$('#yanpiaoWindow').window('close');
					$('#yanpiaoForm').form('clear');
				} else {
					eu.showMsg(data);
				}
			}).error(function(data){
				eu.showMsg("系统错误，请联系管理员！");
			})
		},
		
	//显示运费窗口	
	showDeleverFee : function(){
		$('#deleverFeeWindow').window('open');
	},
	// 修改订单状态
	updateDeleverFee : function() {
		var row = $('#goodsOrderGid').datagrid('getSelected');
		var deleverFee = $('#deleverFee').val();
		if(row == null || row == ""){
			alert("至少选择一条数据！");
			return false;
		}
		if(deleverFee == null || deleverFee == "")
		{
			alert("运费不能为空！");
			return false;
		}	
		$.messager.confirm("提示信息", "是否确定修改运费？", function(r) {
		$.post(goodsOrderManager.updateDeleverFeeUrl, {
			orderCode : row.order_code,
			deleverFee : deleverFee
		}).success(function(data){
			if(data.success){
				eu.showMsg("操作成功！");
				$("#goodsOrderGid").datagrid('reload');
				$('#deleverFeeWindow').window('close');
			} else {
				eu.showMsg(data);
			}
		}).error(function(data){
			eu.showMsg("系统错误，请联系管理员！");
		})
		});
	}
};
goodsOrderManager.init();