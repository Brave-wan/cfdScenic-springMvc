package com.htkj.cfdScenic.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.Invoice;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htkj.cfdScenic.app.model.UserAccountLog;
import com.htkj.cfdScenic.app.model.VisitorsOrder;
import com.htkj.cfdScenic.app.service.*;
import com.htkj.cfdScenic.app.util.MD5;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/visitorsOrder")
public class VisitorsOrderController extends BaseController {
	@Autowired
	private VisitorsOrderService visitorsOrderService;
	@Autowired
	private ShopInformationService consumerUserService;
	@Autowired
	private UserAccountService userAccountService;
	@Autowired
	private UserAccountLogService userAccountLogService;
    @Autowired
    private InvoiceService invoiceService;

	/**
	 * 景区-确认订单 
	 * http://localhost/cfdScenic/visitorsOrder/affirmOrder?name=订单名称&orderDescribe=订单描述&price=80&startValid=2016-09-05&endValid=2016-09-09&quantity=3&realPrice=240&visitorsId=1&isMention=1&addressId=
	 * 把景区传过来的值存进表
	 *  name 订单名称 
	 *  orderDescribe 订单描述
	 *   price 原价 
	 *   startValid 开始有效期 
	 *   endValid 结束有效期 
	 *   quantity 数量 
	 * 	  应付价格 realPrice
	 * 	 景区Id visitorsId
	 * 	 是否自提 isMention
	 * 	收货地址Id	 addressId
	 */
	@RequestMapping(value = "/affirmOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String affirmOrder(VisitorsOrder visitorsOrder) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					if (visitorsOrder.getQuantity() != null && visitorsOrder.getQuantity() == 1) {
						
						Long nowLong = (sdf.parse(sdf.format(new Date()))).getTime();
						Long visitorsLong = (sdf.parse(visitorsOrder.getEndValid())).getTime();
						if(nowLong >= visitorsLong){
							msg.setHearder(4,"date error");
							json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
							return json;
						}else{
						Long temCode = GenerateSequenceUtil.getUniqueId();
						visitorsOrder.setId(GenerateSequenceUtil.getUniqueId());
						visitorsOrder.setPayState(0);
						visitorsOrder.setOrderState(1);
						visitorsOrder.setUserId(userId);
						visitorsOrder.setOrderCode(temCode.toString());
						visitorsOrder.setIsComment(0);
						visitorsOrder.setType(0);
						visitorsOrder.setPayWay(0);
						visitorsOrder.setIsDelete(0);
						visitorsOrder.setIsInvoice(0);
						visitorsOrderService.insertMessage(visitorsOrder);
						msg.setData(temCode);
						}
					} else {
						Long nowLong = (sdf.parse(sdf.format(new Date()))).getTime();
						Long visitorsLong = (sdf.parse(visitorsOrder.getEndValid())).getTime();
						if(nowLong >= visitorsLong){
							msg.setHearder(4,"date error");
							json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
							return json;
						}else{
						Long temCode = GenerateSequenceUtil.getUniqueId();
						int quantity =  visitorsOrder.getQuantity();
						System.out.println(quantity);
						for (int i = 0; i < quantity; i++) {
							visitorsOrder.setId(GenerateSequenceUtil.getUniqueId());
							visitorsOrder.setPayState(0);
							visitorsOrder.setOrderState(1);
							visitorsOrder.setUserId(userId);
							visitorsOrder.setOrderCode(temCode.toString());
							visitorsOrder.setQuantity(1);
							visitorsOrder.setIsComment(0);
							visitorsOrder.setType(0);
							visitorsOrder.setPayWay(0);
							visitorsOrder.setIsDelete(0);
							visitorsOrder.setIsInvoice(0);
							visitorsOrderService.insertMessage(visitorsOrder);
						}
						msg.setData(temCode);
						}
					}
					msg.setHearder(0, "success");
				}

			} else {
				msg.setHearder(2, "token is null");
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject
				.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * Title:景区 - 订单回显
	 * http://localhost/cfdScenic/visitorsOrder/getVisitorsOrderById?orderCode=1609060917446830
	 * @author:lishilong
	 * @date:2016年9月5日
	 */
	@RequestMapping(value="getVisitorsOrderById",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getVisitorsOrderById(Long orderCode){
		String json = new String();
		ResponseMsg msg = new ResponseMsg();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if(token != null){
				Long userId = consumerUserService.getUserIdByToken(token);
				if(userId != null){
					Map<String,Object> map = visitorsOrderService.getVisitorsOrderById(orderCode);
					if(map != null){
						msg.setData(map);
					}
					msg.setHearder(0, "ok");
				}else{
					msg.setHearder(3,"请重新登录！");
				}
			}else{
				msg.setHearder(2,"token is null");
			}
		} catch (Exception e) {
			msg.setHearder(1,"error");
			e.printStackTrace();
		}
		json = JSONObject.toJSONString(msg);
		return json;
	}	
	
	
	/**
	 * 景区-支付订单  
	 * http://localhost/cfdScenic/visitorsOrder/payOrder?orderCode=1609060917446830&balance=100&price=10&payType=1
	 * 把景区传过来的值存进表
	 * 
	 * 支付方式是余额的话就减少自身的余额，其他不变，但是得更新表的积分
	 * 
	 * 更新商户表的余额
	 * 
	 * 更新订单的状态
	 * 
	 * visitorsId //景点订单号 payType //支付方式（0余额1支付宝2微信） balance //余额 price //价格
	 */
	@RequestMapping(value = "/payOrder", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String payOrder(Long orderCode, Double balance, Double price, Integer payType, String passWord) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("orderCode", orderCode);
					if (payType == 0) {
						// 获取支付密码
						String userPassWord = consumerUserService.getUserPassWordByUserId(userId);
						MD5 getMD5 = new MD5();
						if(passWord != null){
						if (getMD5.GetMD5Code(passWord).equals(userPassWord)) {
							if (balance.compareTo(price) == 1) {
								// 卖家-钱
								UserAccount userAccountMessage = new UserAccount();
								userAccountMessage.setUserId(userId);
								userAccountMessage.setBalance(balance - price);
								userAccountMessage.setIntegration(price.intValue());
								
								UserAccount ua = userAccountService.selectUserAccountByUserId(userId);
								
								userAccountService.updateBalanceMessage(userAccountMessage);
								UserAccountLog userAccountLog = new UserAccountLog();
								userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
								userAccountLog.setBalance(balance - price);
								userAccountLog.setPrice(price);
								userAccountLog.setType(2);
								userAccountLog.setName("购买门票");
								userAccountLog.setIntegration(ua.getIntegration());
								String str = price+"";
								str = str.substring(0,str.indexOf("."));
								userAccountLog.setTradeIntegration(Integer.parseInt(str));
								userAccountLog.setUserId(userId);
								userAccountLog.setExtractType(0);
								userAccountLogService.insertMessage(userAccountLog);

								map.put("payWay", 1);
								visitorsOrderService.updatePaystate(map);
							} else {
								msg.setHearder(4, "余额不足");
								json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
								System.out.println(json);
								return json;
							}
						} else {
							msg.setHearder(5, "passWord error");
							json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
							System.out.println(json);
							return json;
						}
					}else{
						msg.setHearder(5, "passWord error");
						json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
						System.out.println(json);
						return json;
					}
					} else if (payType == 1) {
						UserAccount userAccountMessage = new UserAccount();
						userAccountMessage.setUserId(userId);
						userAccountMessage.setBalance(balance);
						userAccountMessage.setIntegration(price.intValue());
						userAccountService.updateBalanceMessage(userAccountMessage);
						map.put("payWay", 2);
						visitorsOrderService.updatePaystate(map);

					} else if (payType == 2) {
						UserAccount userAccountMessage = new UserAccount();
						userAccountMessage.setUserId(userId);
						userAccountMessage.setBalance(balance);
						userAccountMessage.setIntegration(price.intValue());
						userAccountService.updateBalanceMessage(userAccountMessage);
						map.put("payWay", 3);
						visitorsOrderService.updatePaystate(map);
					}
					msg.setData(orderCode);
					msg.setHearder(0, "success");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * Title:未使用门票页面
	 * @author wangfenglong
	 * @date 2016年9月5日
	 */
	@RequestMapping("/toVisitorsOrderWei")
	public String toVisitorsOrderWei(){
		return "/background/visitorsOrder/weiManager";
	}
	
	/**
	 * Title:
	 * @author wangfenglong
	 * @date 2016年9月5日
	 */
	@RequestMapping("/getVisitorsOrderWeiList")
	@ResponseBody
	public DataGrid getVisitorsOrderWeiList(PagerForm pagerForm , VisitorsOrder visitorsOrder){
		return visitorsOrderService.getVisitorsOrderWeiList(pagerForm.getPageRequest(visitorsOrder.toMap()));
	}
	
	/**
	 * Title:已使用门票页面
	 * @author wangfenglong
	 * @date 2016年9月5日
	 */
	@RequestMapping("/toVisitorsOrderYi")
	public String toVisitorsOrderYi(){
		return "/background/visitorsOrder/yiManager";
	}
	
	/**
	 * Title:
	 * @author wangfenglong
	 * @date 2016年9月5日
	 */
	@RequestMapping("/getVisitorsOrderYiList")
	@ResponseBody
	public DataGrid getVisitorsOrderYiList(PagerForm pagerForm , VisitorsOrder visitorsOrder){
		return visitorsOrderService.getVisitorsOrderYiList(pagerForm.getPageRequest(visitorsOrder.toMap()));
	}
	/**
	 * Title:景区 - 订单支付成功回显
	 * http://localhost/cfdScenic/visitorsOrder/payOrderFinsh?orderCode=1609060917446830
	 * @author:lishilong
	 * @date:2016年9月5日
	 */
	@RequestMapping(value = "/payOrderFinsh", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String payOrderFinsh(Long orderCode) {
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId == null) {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				} else {
					List<Map<String,Object>> map = visitorsOrderService.findOrderByOrderId(orderCode);
					if(map != null){
						msg.setData(map);
					}
					msg.setHearder(0,"ok");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject
				.toJSONString(msg, SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * Title:未使用门票详情预览
	 * @author wangfenglong
	 * @date 2016年9月5日
	 */
	@RequestMapping("/weiShowPage")
	public String weiShowPage(Long id , ModelMap model){
		try {
			Map<String, Object> map = visitorsOrderService.selectByPrimaryKey(id);
			model.addAttribute("model", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/background/visitorsOrder/weiShowPage";
	}
	/**
	 * Title:已使用门票详情预览
	 * @author wangfenglong
	 * @date 2016年9月5日
	 */
	@RequestMapping("/yiShowPage")
	public String yiShowPage(Long id , ModelMap model){
		try {
			Map<String, Object> map = visitorsOrderService.selectByPrimaryKey(id);
			model.addAttribute("model", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/background/visitorsOrder/yiShowPage";
	}
	
	/**
	 * Title：修改订单状态
	 * @author wangfenglong
	 * @date 2016年9月6日
	 */
	@RequestMapping("/updateVisitorsOrder")
	@ResponseBody
	public Json updateVisitorsOrder(VisitorsOrder visitorsOrder){
		Json json = new Json();
        UserAccountLog userAccountLog = new UserAccountLog();
		try {

		    if (visitorsOrder.getOrderState() == 7) {
                UserAccount userAccount = userAccountService.selectByUserId(visitorsOrder.getUserId());
                BigDecimal b1 = new BigDecimal(userAccount.getBalance());
                BigDecimal b2 = new BigDecimal(visitorsOrder.getRealPrice());
                userAccount.setUserId(visitorsOrder.getUserId());
                userAccount.setBalance((b1.add(b2)).doubleValue());
                userAccountService.addBalanceByUserId(userAccount);

                userAccountLog.setId(GenerateSequenceUtil.getUniqueId());
                userAccountLog.setType(4);
                userAccountLog.setTradeIntegration(0);
                userAccountLog.setIntegration(0);
                userAccountLog.setPrice(visitorsOrder.getRealPrice());
                userAccountLog.setBalance((b1.add(b2)).doubleValue());
                userAccountLog.setUserId(visitorsOrder.getUserId());
                userAccountLog.setExtractType(0);
                userAccountLogService.insertMessage(userAccountLog);
            }
			visitorsOrderService.updateVisitorsOrder(visitorsOrder);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Title：验票
	 * @author wangfenglong
	 * @date 2016年9月6日
	 */
	@RequestMapping("/checkVisitorsOrder")
	@ResponseBody
	public Json checkVisitorsOrder(VisitorsOrder visitorsOrder){
		Json json = new Json();
		try {
			visitorsOrder.setOrderState(3);
			visitorsOrderService.updateVisitorsOrder(visitorsOrder);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Title:删除已使用门票信息
	 * @author wangfenglong
	 * @date 2016年9月6日
	 */
	@RequestMapping("/deleteVisitorsOrderYi")
	@ResponseBody
	public Json deleteVisitorsOrderYi(Long id){
		Json json = new Json();
		try {
			visitorsOrderService.deleteVisitorsOrder(id);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	/**
	 * Title:开发票
	 * @author wangfenglong
	 * @date 2016年9月6日
	 */
	@RequestMapping("/changeInvoice")
	@ResponseBody
	public Json changeInvoice( Invoice invoice , String orderCodes ,String linkIds){
		Json json = new Json();
        String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        try {
            String[] orderCode = orderCodes.split(",");
            String[] linkId = linkIds.split(",");
            for (int i = 0 ; i < orderCode.length ; i ++) {
                VisitorsOrder visitorsOrder = visitorsOrderService.selectByOrderCode(orderCode[i]);
                //if(visitorsOrder.getIsInvoice() == 0){
                    visitorsOrder.setOrderCode(orderCode[i]);
                    visitorsOrder.setInvoiceState(1);
                    visitorsOrder.setIsInvoice(1);
                    visitorsOrderService.updateVisitorsOrder(visitorsOrder);

                    invoice.setId(GenerateSequenceUtil.getUniqueId());
                    invoice.setInvoiceTime(now);
                    invoice.setInvoiceState(2);
                    invoice.setOrderCode(orderCode[i]);
                    invoice.setLinkId(Long.parseLong(linkId[i]));
                    invoice.setCreateTime(now);
                    invoiceService.insertSelective(invoice);
               // } else {
                  //  json.setCode(0);
               // }
               // json.setCode(1);
            }
            json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
            json.setSuccess(false);
            json.setMessage("开发票失败！");
		}
		
		return json;
	}

}
