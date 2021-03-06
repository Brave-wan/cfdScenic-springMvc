package com.htkj.cfdScenic.app.controller;

import com.htkj.cfdScenic.app.model.GoodsOrder;
import com.htkj.cfdScenic.app.model.HotelOrder;
import com.htkj.cfdScenic.app.model.RefundCause;
import com.htkj.cfdScenic.app.model.RestaurantOrder;
import com.htkj.cfdScenic.app.model.ShopUser;
import com.htkj.cfdScenic.app.service.GoodsOrderService;
import com.htkj.cfdScenic.app.service.HotelOrderService;
import com.htkj.cfdScenic.app.service.OrderDetailService;
import com.htkj.cfdScenic.app.service.RefundCauseService;
import com.htkj.cfdScenic.app.service.RestaurantOrderService;
import com.htkj.cfdScenic.app.service.UserAccountService;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 
 * @date 
 */
@RequestMapping("/background/refundOrderManage")
@Controller
public class RefundOrderManageController extends BaseController {


    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private RefundCauseService refundCauseService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private GoodsOrderService goodsOrderService;
    @Autowired
    private HotelOrderService hotelOrderService;
    @Autowired
    private RestaurantOrderService restaurantOrderService;

    /**
     * 订单首页
     * @return
     */
    @RequestMapping("/toRefundOrderManage")
    public String toRefundOrderManage() {
        return "/background/Order/refundOrderManager";
    }


    /**
     * 订单 - 列表
     * @param page
     * @param 
     * @param 
     * @return
     */
    @RequestMapping("/getRefundOrderList")
    @ResponseBody
    public DataGrid getRefundOrderList(PagerForm page,String orderCode, String nickName,String mobileNo,int orderType){
        Map<String , Object> map = new HashMap();
        ShopUser user =  (ShopUser) webContext.getSessionShopUser();
        DataGrid dataGrid = new DataGrid();
        try {
        	if(null!= user && null != user.getShopInformationId()){
        		map.put("shopId",user.getShopInformationId());
        	}
        	map.put("nickName",nickName);
        	map.put("mobileNo",mobileNo);
        	Integer type = refundCauseService.getShopInformationIdTypeById(user.getShopInformationId());
        	if(type == 1){//酒店
        		dataGrid = refundCauseService.getRefundHotelOrderList(page.getPageRequest(map));
        	}else if(type==2){//饭店
        		dataGrid = refundCauseService.getRefundRestaurantOrderList(page.getPageRequest(map));
        	}else{//商品
        		dataGrid = refundCauseService.getRefundGoodsOrderList(page.getPageRequest(map));
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return dataGrid;
    }


    

    /**
     * 修改订单状态
     * @param visitorsOrder
     * @return
     */
    @RequestMapping("/updateState")
    @ResponseBody
    public Json updateState(Long id,Long uId,Long oId,Long sId,String name,String mobile,int state,String cause,int orderType,Double price){
        Json json = new Json();
        try {
            if(state == 2){//审核未通过
            	//新增退款审核表数据
            	RefundCause refundCause = new RefundCause();
            	refundCause.setId(GenerateSequenceUtil.getUniqueId());
            	refundCause.setUserId(uId);
            	refundCause.setOrderCode(oId);
            	refundCause.setShopInformationId(sId);
            	refundCause.setUserName(name);
            	refundCause.setUserPhone(mobile);
            	refundCause.setCause(cause);
            	refundCause.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            	orderDetailService.saveRefundCause(refundCause);
            	//更新原订单状态
            	switch (orderType) {
				case 1://商品
					GoodsOrder goodsOrder =  new GoodsOrder();
					goodsOrder.setId(id);
					goodsOrder.setOrderState(10);
					goodsOrderService.updateByOrder(goodsOrder);
					break;
				case 2://酒店
					HotelOrder hotelOrder = new HotelOrder();
					hotelOrder.setOrderCode(oId.toString());
					hotelOrder.setOrderState(7);
					hotelOrderService.updateOrderState(hotelOrder);
					break;
				case 3://饭店
					RestaurantOrder restaurantOrder = new RestaurantOrder();
					restaurantOrder.setOrderState(7);
					restaurantOrder.setOrderCode(oId.toString());
					restaurantOrderService.updateByOrderCode(restaurantOrder);
					break;
				}
            	
            }else{//审核通过
            	switch (orderType) {
				case 1://商品
					GoodsOrder goodsOrder =  new GoodsOrder();
					goodsOrder.setId(id);
					goodsOrder.setOrderState(9);
					goodsOrderService.updateByOrder(goodsOrder);
					break;
				case 2://酒店
					HotelOrder hotelOrder = new HotelOrder();
					hotelOrder.setOrderCode(oId.toString());
					hotelOrder.setOrderState(6);
					hotelOrderService.updateOrderState(hotelOrder);
					break;
				case 3://饭店
					RestaurantOrder restaurantOrder = new RestaurantOrder();
					restaurantOrder.setOrderState(6);
					restaurantOrder.setOrderCode(oId.toString());
					restaurantOrderService.updateByOrderCode(restaurantOrder);
					break;
				}
            	
            	Map map = new HashMap();
				map.put("balance", price);
				map.put("userId", uId);
				userAccountService.updateBalanceUserAccountByUID(map);
            }
            json.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

}
