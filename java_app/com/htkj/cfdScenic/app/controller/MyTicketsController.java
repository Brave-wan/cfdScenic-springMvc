package com.htkj.cfdScenic.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.PictureLibrary;
import com.htkj.cfdScenic.app.model.UserComment;
import com.htkj.cfdScenic.app.service.MyTicketsService;
import com.htkj.cfdScenic.app.service.ShopInformationService;
import com.htkj.cfdScenic.app.service.ShopUserService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.upload.UploadFile;
import com.htrj.common.utils.GenerateSequenceUtil;

@Controller
@RequestMapping("/interFace/myTickets")
public class MyTicketsController extends BaseController{
	
	@Autowired
	private MyTicketsService myTicketsService;
	@Autowired
	private ShopInformationService consumerUserService;
	@Autowired
	private ShopUserService shopUserService;
	
	/**
	 * Title:我的所有门票（未付款，未使用，已使用，已过期）
	 * http://localhost/cfdScenic/interFace/myTickets/getMyTickets
	 * @author:lishilong
	 * @date:2016年9月9日
	 * type(0全部1待支付2未使用3待评价4已过期5退款)
	 */
	@RequestMapping(value="getMyTickets",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getMyTickets(Integer type){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//获取token
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				//获取userId
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId != null) {
					switch (type) {
					case 0:
						// 获取我的门票列表
						List<Long> list = myTicketsService.getMyTickets(userId);
						//通过orderCode查询门票列表
						List returnlist = new ArrayList();
						List<Long> ids = new ArrayList<Long>();
						for(int i=0;i<list.size();i++){
							List<Map<String,Object>> ticketslist = myTicketsService.getMyTicketsByOrderCode(list.get(i));
							if(ticketslist.size()>0 && ticketslist.get(0)!=null){
								for(int j=0;j<ticketslist.size();j++){
									Integer orderState = Integer.parseInt(ticketslist.get(j).get("order_state")+"");
									Long voId = Long.parseLong((ticketslist.get(j).get("id")+""));
									if(orderState != 8 && (orderState == 2 || orderState == 1)){
									//判断我的门票有没有过期
									String dateTime = ticketslist.get(j).get("end_valid")+"";
									Long dateLong = sdf.parse(dateTime).getTime();
									Long nowLong = (sdf.parse(sdf.format(new Date()))).getTime();
									if(dateLong <= nowLong){
										ticketslist.get(j).put("order_state","8");
										String orderCode = ticketslist.get(j).get("order_code")+"";
										myTicketsService.updateTicketsOverDue(orderCode);
										ticketslist.remove(j);
									}else{
										ids.add(voId);
									}
									}
								}
								Map ticketsMap = new HashMap();
								ticketsMap.put("ticketslist",ticketslist);
								ticketsMap.put("ids",ids);
								returnlist.add(ticketsMap);
							}
						}
						// 门票列表不为空的话就返回list，否则返回date为空list，防止报错
						if (returnlist.size() > 0 && returnlist.get(0) != null) {
							// 返回门票列表
							msg.setData(returnlist);
						}else{
							msg.setData(new ArrayList());
						}
						break;
					case 1:
						Map map = new HashMap();
						map.put("order_state","1");
						map.put("userId",userId);
						// 获取我的门票列表
						List<Long> wite = myTicketsService.getMyTicketsByType(map);
						List returnlist1 = new ArrayList();
						List<Long> ids1 = new ArrayList<Long>();
						for(int i=0;i<wite.size();i++){
							List<Map<String,Object>> ticketslist = myTicketsService.getMyTicketsByOrderCode(wite.get(i));
							if(ticketslist.size()>0 && ticketslist.get(0)!=null){
								for(int j=0;j<ticketslist.size();j++){
									Integer orderState = Integer.parseInt(ticketslist.get(j).get("order_state")+"");
									Long voId = Long.parseLong((ticketslist.get(j).get("id")+""));
									//判断我的门票有没有过期
									if(orderState != 8){
										//判断我的门票有没有过期 
										String dateTime = ticketslist.get(j).get("end_valid")+"";
										Long dateLong = sdf.parse(dateTime).getTime();
										Long nowLong = (sdf.parse(sdf.format(new Date()))).getTime();
										if(orderState == 2 || orderState == 1){
										if(dateLong <= nowLong){
											ticketslist.get(j).put("order_state","8");
											String orderCode = ticketslist.get(j).get("order_code")+"";
											myTicketsService.updateTicketsOverDue(orderCode);
											ticketslist.remove(j);
										}else{
											ids1.add(voId);
										}
										}
										}
								}
								Map ticketsMap = new HashMap();
								ticketsMap.put("ticketslist",ticketslist);
								ticketsMap.put("ids",ids1);
								returnlist1.add(ticketsMap);
							}
						}
						// 门票列表不为空的话就返回list，否则返回date为空list，防止报错
						if (returnlist1.size() > 0 && returnlist1.get(0) != null) {
							// 返回门票列表
							msg.setData(returnlist1);
						}else{
							msg.setData(new ArrayList());
						}
						break;
					case 2:
						Map map1 = new HashMap();
						map1.put("order_state","2,6");
						map1.put("userId",userId);
						// 获取我的门票列表
						List<Long> noUse = myTicketsService.getMyTicketsByType(map1);
						// 门票列表不为空的话就返回list，否则返回date为空list，防止报错
						List returnlist2 = new ArrayList();
						List<Long> ids2 = new ArrayList<Long>();
						for(int i=0;i<noUse.size();i++){
							List<Map<String,Object>> ticketslist = myTicketsService.getMyTicketsByOrderCode(noUse.get(i));
							if(ticketslist.size()>0 && ticketslist.get(0)!=null){
								
								for(int j=0;j<ticketslist.size();j++){
									Integer orderState = Integer.parseInt(ticketslist.get(j).get("order_state")+"");
									Long voId = Long.parseLong((ticketslist.get(j).get("id")+""));
									if(orderState != 8 && (orderState == 2 || orderState == 1)){
									//判断我的门票有没有过期
									String dateTime = ticketslist.get(j).get("end_valid")+"";
									Long dateLong = sdf.parse(dateTime).getTime();
									Long nowLong = (sdf.parse(sdf.format(new Date()))).getTime();
									if(dateLong <= nowLong){
										ticketslist.get(j).put("order_state","8");
										String orderCode = ticketslist.get(j).get("order_code")+"";
										myTicketsService.updateTicketsOverDue(orderCode);
										ticketslist.remove(j);
									}else{
										ids2.add(voId);
									}
									}
								}
								Map ticketsMap = new HashMap();
								ticketsMap.put("ticketslist",ticketslist);
								ticketsMap.put("ids",ids2);
								returnlist2.add(ticketsMap);
							}
						}
						// 门票列表不为空的话就返回list，否则返回date为空list，防止报错
						if (returnlist2.size() > 0 && returnlist2.get(0) != null) {
							// 返回门票列表
							msg.setData(returnlist2);
						}else{
							msg.setData(new ArrayList());
						}
						break;
						
					case 3:
						Map map2 = new HashMap();
						map2.put("order_state","3");
						map2.put("userId",userId);
						// 获取我的门票列表
						List<Long> use = myTicketsService.getMyTicketsByType(map2);
						// 门票列表不为空的话就返回list，否则返回date为空list，防止报错
						List returnlist3 = new ArrayList();
						List<Long> ids3 = new ArrayList<Long>();
						for(int i=0;i<use.size();i++){
							List<Map<String,Object>> ticketslist = myTicketsService.getMyTicketsByOrderCode(use.get(i));
							if(ticketslist.size()>0 && ticketslist.get(0)!=null){
								for(int j=0;j<ticketslist.size();j++){
									Long voId = Long.parseLong((ticketslist.get(j).get("id")+""));
									ids3.add(voId);
								}
								Map ticketsMap = new HashMap();
								ticketsMap.put("ticketslist",ticketslist);
								ticketsMap.put("ids",ids3);
								returnlist3.add(ticketsMap);
							}
						}
						// 门票列表不为空的话就返回list，否则返回date为空list，防止报错
						if (returnlist3.size() > 0 && returnlist3.get(0) != null) {
							// 返回门票列表
							msg.setData(returnlist3);
						}else{
							msg.setData(new ArrayList());
						}
						break;
						
					case 4:
						Map map3 = new HashMap();
						map3.put("order_state","8");
						map3.put("userId",userId);
						// 获取我的门票列表
						List<Long> finsh = myTicketsService.getMyTicketsByType(map3);
						// 门票列表不为空的话就返回list，否则返回date为空list，防止报错
						List returnlist4 = new ArrayList();
						List<Long> ids4 = new ArrayList<Long>();
						for(int i=0;i<finsh.size();i++){
							List<Map<String,Object>> ticketslist = myTicketsService.getMyTicketsByOrderCode(finsh.get(i));
							if(ticketslist.size()>0 && ticketslist.get(0)!=null){
								for(int j=0;j<ticketslist.size();j++){
									Long voId = Long.parseLong((ticketslist.get(j).get("id")+""));
									ids4.add(voId);
								}
								Map ticketsMap = new HashMap();
								ticketsMap.put("ticketslist",ticketslist);
								ticketsMap.put("ids",ids4);
								returnlist4.add(ticketsMap);
							}
						}
						// 门票列表不为空的话就返回list，否则返回date为空list，防止报错
						if (returnlist4.size() > 0 && returnlist4.get(0) != null) {
							// 返回门票列表
							msg.setData(returnlist4);
						}else{
							msg.setData(new ArrayList());
						}
						break;
						
					case 5:
						Map map4 = new HashMap();
						map4.put("order_state","5,6,7");
						map4.put("userId",userId);
						// 获取我的门票列表
						List<Long> overdue = myTicketsService.getMyTicketsByType(map4);
						// 门票列表不为空的话就返回list，否则返回date为空list，防止报错
						List returnlist5 = new ArrayList();
						List<Long> ids5 = new ArrayList<Long>();
						for(int i=0;i<overdue.size();i++){
							List<Map<String,Object>> ticketslist = myTicketsService.getMyTicketsByOrderCode(overdue.get(i));
							if(ticketslist.size()>0 && ticketslist.get(0)!=null){
								for(int j=0;j<ticketslist.size();j++){
									Long voId = Long.parseLong((ticketslist.get(j).get("id")+""));
									ids5.add(voId);
								}
								Map ticketsMap = new HashMap();
								ticketsMap.put("ticketslist",ticketslist);
								ticketsMap.put("ids",ids5);
								returnlist5.add(ticketsMap);
							}
						}
						// 门票列表不为空的话就返回list，否则返回date为空list，防止报错
						if (returnlist5.size() > 0 && returnlist5.get(0) != null) {
							// 返回门票列表
							msg.setData(returnlist5);
						}else{
							msg.setData(new ArrayList());
						}
						break;
					}
					// 设置msg状态值
					msg.setHearder(0, "ok");
				} else {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1,"error");
		}
		json = JSONObject.toJSONString(msg);
		System.out.println(json);
		return json;
	}
	
	/**
	 * Title:删除我的门票
	 * http://localhost/cfdScenic/interFace/myTickets/deleteMyTickets?id=&orderState=1
	 * 未使用的删除，已使用的更改状态值
	 * @author:lishilong
	 * @date:2016年9月9日
	 */
	@RequestMapping(value="deleteMyTickets",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deleteMyTickets(Long orderCode,Integer orderState){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			//获取token
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				//获取userId
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId != null) {
					//未使用的门票是删除，已使用的门票是修改状态值
					// type值
					// 1直接删除，其他的修改状态值
					if(orderState == 1){
						myTicketsService.deleteMyTickets(orderCode);
					}else{
						myTicketsService.updateMyTickets(orderCode);
					}
					msg.setHearder(0, "ok");
				} else {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1,"error");
		}
		json = JSONObject.toJSONString(msg);
		System.out.println(json);
		return json;
	}
	/**
	 * Title:申请退款
	 * http://localhost/cfdScenic/interFace/myTickets/refund?id=&endValid="2016-08-09 23:12:59"
	 * @author:lishilong
	 * @date:2016年9月10日
	 */
	@RequestMapping(value="refund",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String refund(Long orderCode,String endValid){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//获取token
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				//获取userId
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId != null) {
					//判断是否在退款时间中，修改订单状态
					Long nowDate = sdf.parse(sdf.format(new Date())).getTime();
					Long endDate = sdf.parse(endValid).getTime();
					if(nowDate > endDate){
						msg.setHearder(4,"dont refund");
					}else{
						myTicketsService.updateOrderState(orderCode);
						msg.setHearder(0, "ok");
					}
				} else {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1,"error");
		}
		json = JSONObject.toJSONString(msg);
		System.out.println(json);
		return json;
	}
	
	/**
	 * Title:订单详情
	 * http://localhost/cfdScenic/interFace/myTickets/orderDetail?orderCode=111
	 * @author:lishilong
	 * @date:2016年9月10日
	 */
	@RequestMapping(value="orderDetail",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String orderDetail(Long orderCode){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			//获取token
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				//获取userId
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId != null) {
					//查询订单详情
					List<Map<String,Object>> list = myTicketsService.getOrderDetail(orderCode);
					if(list.size() >0 && list.get(0) != null){
						msg.setData(list);
					}else{
						msg.setData(new ArrayList());
					}
					msg.setHearder(0,"ok");
				} else {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1,"error");
		}
		json = JSONObject.toJSONString(msg);
		System.out.println(json);
		return json;
	}
	/**
	 * Title:评价
	 * http://localhost/cfdScenic/interFace/myTickets/evaluate
	 * @author:lishilong
	 * @date:2016年9月10日
	 * 
	 * linkId	  //评论关联
	 * content,		//评论内容
	 * haveImg,		//是否有图片
	 * 
	 * imgUrl //上传的图片
	 */
	@RequestMapping(value="evaluate",produces="text/html;charset=UTF-8")
	@ResponseBody 
	public String evaluate(UserComment userComment,@RequestParam(value = "imgUrl", required = false)String[] imgUrl,Long orderCode){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//获取token
			String token = webContext.getRequest().getHeader("Authorization");
			if (token != null) {
				//获取userId
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId != null) {
					//保存评论
					Long commentId = GenerateSequenceUtil.getUniqueId();
					userComment.setId(commentId);
					userComment.setUserId(userId);
					userComment.setCreateTime(sdf.format(new Date()));
					userComment.setCommentType(1);
					userComment.setContentType(0);
					//没用的参数,不需要改动
					userComment.setFromUserId(0L);
					userComment.setIsTravels(0);
					userComment.setMemo("备注");
					userComment.setSatisfyState(0);
					myTicketsService.saveVisitorsUserComment(userComment);
					//修改订单状态
					myTicketsService.updateOrderStateByOrderCode(orderCode);
					//保存照片
					if (imgUrl != null && imgUrl.length > 0) {
						for (int i = 0; i < imgUrl.length; i++) {
							PictureLibrary pl = new PictureLibrary();
							pl.setId(GenerateSequenceUtil.getUniqueId());
							pl.setImgRootUrl(imgUrl[i]);
							pl.setImgUrl(imgUrl[i]);
							pl.setLinkId(commentId);
							pl.setMemo("备注");
							pl.setName("景区评价");
							pl.setPicDescribe("景区评价描述");
							pl.setType(2);
							pl.setCreateTime(sdf.format(new Date()));
							myTicketsService.savePictureLibrary(pl);
						}
					}
					msg.setHearder(0, "ok");
				} else {
					msg.setHearder(3, "认证信息错误，请重新登录！");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1,"error");
		}
		json = JSONObject.toJSONString(msg);
		System.out.println(json);
		return json;
	}
	
	/** 
	 * 上传图片视频等
	 * interFace/myTickets/upload
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "upload", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadImage(@RequestParam("file") MultipartFile[] imageFiles,HttpServletRequest request,HttpServletResponse response,Integer type) {
		ResponseMsg msg = new ResponseMsg();
		String token = webContext.getRequest().getHeader("Authorization");
		try {

			if (token != null) {
				Long userId = 0L;
				if(type == 0){
					userId = consumerUserService.getUserIdByToken(token);
				}else{
					userId = shopUserService.getShopUserIdByToken(token);
				}
				if (userId != null) {
					UploadFile uploadFile = new UploadFile();
					List<Map<String, String>> uploadImage = uploadFile.uploadImage(imageFiles, userId+"", request,response);
					List<String> imgUrl = new ArrayList<String>();
 					for (Map<String, String> img : uploadImage) {
						imgUrl.add(img.get("contextUrl"));
					}
					msg.setData(imgUrl);
					msg.setHearder(0, "ok");
 				} else {
					msg.setHearder(3, "请重新登录！");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		return JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * Title:获取未支付订单数量
	 * @author:lishilong
	 * @date:2016年9月20日
	 */
	@RequestMapping(value="getWaitPay",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getWaitPay(Integer type){
		ResponseMsg msg = new ResponseMsg();
		String token = webContext.getRequest().getHeader("Authorization");
		try {
			if (token != null) {
				Long userId = consumerUserService.getUserIdByToken(token);
				if (userId != null) {
					Integer waitPay = myTicketsService.getWaitPayByUserId(userId);
					Integer already = myTicketsService.getAlreadyByUserId(userId);
					Map map = new HashMap();
					map.put("waitPay",waitPay);
					map.put("noUse",already);
					msg.setData(map);
					msg.setHearder(0,"ok");
				} else {
					msg.setHearder(3, "请重新登录！");
				}
			} else {
				msg.setHearder(2, "token is null");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		return JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
	}
	
}
