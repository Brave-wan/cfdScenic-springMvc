var restaurantOrderManager = {
		delUrl : "./background/restaurantOrderManage/deleteRestaurantOrder",
        ylUrl : "./background/restaurantOrderManage/showPage",
        updateUrl : "./background/restaurantOrderManage/updateRestaurantOrder",
        fpUrl : "./background/restaurantOrderManage/tokaipiao",
        hexiaoUrl : "./background/restaurantOrderManage/toFinshOrder",
		init : function(){
			$(window).load(function(){
				$('#restaurantOrderGid').datagrid({
					url:'./background/restaurantOrderManage/getRestaurantOrderList.json',
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
                                    return '未使用';
                                case 3:
                                    return '申请退款';
                                case 4:
                                    return '已使用';
                                case 5:
                                    return '已过期';
                                case 6:
                                    return '申请退款成功';
                                case 7:
                                    return '申请退款失败';
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
					},{
						field : 'price',
						title : '原价',
						align:'center',width:50,hidden:true
					},{
						field : 'real_price',
						title : '应付价格',
						align:'center',width:50,hidden:true
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
					    field : 'pay_state',
                        title : '是否支付',
                        align : 'center',
                        width : 30,
                        formatter : function (
                                v, r, i) {
                            switch (v) {
                                case 0 :
                                    return '未支付';
                                case 1 :
                                    return '已支付';
                            }
                        }
					},{
					    field : 'is_comment',
                        title : '是否评价',
                        align : 'center',
                        width : 30,hidden:true,
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
					    field : 'goods_type',
                        title : '类型',
                        align : 'center',
                        width : 30,
                        formatter : function (
                                v, r, i) {
                            switch (v) {
                                case 0 :
                                    return '单品';
                                case 1 :
                                    return '套餐';
                            }
                        }
                    },{
					    field : 'is_balance',
                        title : '是否结算',
                        align : 'center',
                        width : 30,
                        formatter : function (
                                v, r, i) {
                            switch (v) {
                                case 0 :
                                    return '未结算';
                                case 1 :
                                    return '已结算';
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
    						title : '发票',
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
			var row = $('#restaurantOrderGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			console.log(row);
			$('#ImageSubWindow').window('open');
			$('#uploadForm').form('clear');
			$('#orderCode').val(row.order_code);
            $('#realPrice').val(row.real_price);
            $('#userId').val(row.user_id);
//			$('#searchValue').combobox('setValue',row.status);
		},
		
		submit : function(){
			var orderCode = $('#orderCode').val();
            var realPrice = $('#realPrice').val();
            var userId = $('#userId').val();
			var a = $('#orderState').combobox('getValue');
			$.post(restaurantOrderManager.updateUrl, {
				orderCode : orderCode ,
                orderState : a,
                realPrice : realPrice,
                userId :userId
			}).success(function(data){
				if(data.success){
					eu.showMsg("操作成功！");
					$("#restaurantOrderGid").datagrid('reload');
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
			$('#restaurantOrderGid').datagrid('load',{orderCode: orderCode , nickName: nickName , createTime: createTime , orderState: orderState});
			$('#restaurantOrderGid').datagrid('clearSelections');
		},
		
		del : function() {
			var row = $('#restaurantOrderGid').datagrid('getSelected');
			if (!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$.messager.confirm("提示信息", "您确定要将该数据删除吗？", function(r) {
				if (r) {
					$.post(restaurantOrderManager.delUrl, {
						id : row.id
					}).success(function(data) {
						if (data.success) {
							eu.showMsg("操作成功！");
							$("#restaurantOrderGid").datagrid('reload');
						} else {
							eu.showMsg(data);
						}
					}).error(function(data) {
						eu.showMsg("系统错误，请联系管理员！");
					})
				} else {
					$('#restaurantOrderGid').datagrid('clearSelections');
				}
			});
		},
		
		yl : function(){
			var row = $("#restaurantOrderGid").datagrid('getSelected');
			if(!row){
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			window.location.href = restaurantOrderManager.ylUrl+"?id="+row.id;
		},
		kaip : function(){
			var row = $("#restaurantOrderGid").datagrid('getSelected');
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
			window.location.href = restaurantOrderManager.fpUrl+"?id="+row.id+"&&code="+code;
		},
		yanpiao : function(){
			var row = $('#restaurantOrderGid').datagrid('getSelected');
			if(!row) {
				eu.showMsg("请选择一行再进行操作");
				return;
			}
			$('#yanpiaoWindow').window('open');
			$('#yanpiaoForm').form('clear');
			$('#yanpiaoId').val(row.order_code);
		},
		
		yanpiaoSub : function(){
			var row = $('#restaurantOrderGid').datagrid('getSelected');
			 if(confirm("订单核销后状态将不能改变，是否确定核销？")){
			$.post(restaurantOrderManager.hexiaoUrl, {
				orderCode : row.order_code
			}).success(function(data){
				if(data.success){
					eu.showMsg("操作成功！");
					$("#restaurantOrderGid").datagrid('reload');
				} else {
					eu.showMsg(data);
				}
			}).error(function(data){
				eu.showMsg("系统错误，请联系管理员！");
			})
			 }
		}
};
restaurantOrderManager.init();