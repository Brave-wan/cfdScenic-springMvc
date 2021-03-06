var refundManager = {
	init : function(){
		$(window).load(function(){
			$('#refundManagerGid').datagrid({
				url:'./census/censuslist.json',
				border:false,
				fit:true,
				singleSelect:true,
				idField:'id',
				pagination:true,
				pageSize:20,
				striped:true,
				fitColumns:true,
				queryParams : {
					type : 1,
					status : 4
				},
				columns : [ [ {
					field : 'id',
					title : 'id',
					align:'center',width:100,hidden:true

				}, 
				{
					field : 'goodsname',
					title : '商品',
					align:'center',width:100
				},
				{
					field : 'name',
					title : '订单',
					align:'center',width:100,hidden:true
				},
//				{
//					field : 'price',
//					title : '原价',
//					align:'center',width:100
//				},
				{
					field : 'quantity',
					title : '数量',
					align:'center',width:100
				}, {
					field : 'payWay',
					title : '支付方式',
					align:'center',width:100,hidden:true
				},{
					field : 'payState',
					title : '支付状态',
					align:'center',width:100,hidden:true
					/*formatter : function(v, r, i) {
						switch (v) {
						case 0:
							return "未支付";
						case 1:
							return "已支付";
						}
					}*/
				},{
					field : 'orderState',
					title : '订单状态',
					align:'center',width:100,hidden:true
				},{
					field : 'createTime',
					title : '订单生成时间',
					align:'center',width:100,hidden:true
				}, {
					field : 'payTime',
					title : '支付时间',
					align:'center',width:100,hidden:true
				}, {
					field : 'refundTime',
					title : '退付时间',
					align:'center',width:100,hidden:true
				},{
					field : 'realPrice',
					title : '售卖金额',
					align:'center',width:100
				},{
					field : 'orderCode',
					title : '订单号',
					align:'center',width:100,hidden:true
				},
//				{
//					field : 'isComment',
//					title : '是否评价',
//					align:'center',width:100,hidden:true
//				},
				{
					field : 'shopname',
					title : '商户id',
					align:'center',width:100,hidden:true
				},
//				{
//					field : 'addressId',
//					title : '收货地址id',
//					align:'center',width:100,hidden:true
//				},
//				{
//					field : 'deliverFee',
//					title : '配送费',
//					align:'center',width:100
//				},
//				{
//					field : 'isPickup',
//					title : '是否自提',
//					align:'center',width:100,hidden:true
//				},
//				{
//					field : 'isUpdatePrice',
//					title : '是否修改过价格',
//					align:'center',width:100,hidden:true
//				},{
//					field : 'isDeliverFee',
//					title : '是否是配送费',
//					align:'center',width:100,hidden:true
//				},
				{
					field : 'isDelete',
					title : '是否删除',
					align:'center',width:100,hidden:true
				}] ]
			});
		});
	},
	serch : function () {
 		var state = $('#searchValue').combobox('getValue');
 		var dateTime =$('#dateTimeId').datebox('getValue');
		$('#refundManagerGid').datagrid('load',{state: state,dateTime:dateTime});
		$('#refundManagerGid').datagrid('clearSelections');
	}
};
refundManager.init();