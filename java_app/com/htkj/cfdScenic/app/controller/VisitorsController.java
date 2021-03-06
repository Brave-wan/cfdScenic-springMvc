package com.htkj.cfdScenic.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.dao.PictureLibraryDao;
import com.htkj.cfdScenic.app.model.PictureLibrary;
import com.htkj.cfdScenic.app.model.UserComment;
import com.htkj.cfdScenic.app.model.Visitors;
import com.htkj.cfdScenic.app.service.UserCommentService;
import com.htkj.cfdScenic.app.service.VisitorsService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.PageCount;
import com.htrj.common.page.PagerForm;

@Controller
@RequestMapping("/visitors")
public class VisitorsController extends BaseController{
	@Autowired
	private VisitorsService visitorsService;
	@Autowired
	private UserCommentService userCommentService;
	
	/**
	 * 景区-景点列表	GET
	 * http://localhost/cfdScenic/visitors/scenicSpotList?rows=6&page=1
	 * 把景区表内的信息返回
	 * 调用表 visitors
	 */
	@RequestMapping(value="/scenicSpotList",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String scenicSpotListPage(){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			List<Map<String,Object>> list = visitorsService.scenicSpotLimitPage();
			if(list.size() > 0 && list.get(0) != null){
				msg.setData(list);
			}else{
				msg.setData(new ArrayList());
			}
			msg.setHearder(0, "success");
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 景区-景点列表	GET
	 * http://localhost/cfdScenic/visitors/scenicSpotList?rows=6&page=1
	 * 把景区表内的信息返回
	 * 调用表 visitors
	 */
	@RequestMapping(value="/findVisitorsList",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String scenicSpotListPage(PagerForm page){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			Map map = new HashMap();
			DataGrid dataGrid = visitorsService.findVisitorsList(page.getPageRequest(map));
			msg.setData(dataGrid);
			msg.setHearder(0, "success");
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 景区-景点主面(景点详情) GET
	 * http://localhost/cfdScenic/visitors/scenicSpotParticulars?id=1
	 * 根据景区ID把景区的详情信息返回
	 * 调用表 
	 * visitors
	 */
	@RequestMapping(value="/scenicSpotParticulars",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String scenicSpotParticulars(Long id){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		Map<String, Object> content = new HashMap<String, Object>();
		try {
			content = visitorsService.scenicSpotParticulars(id);
			if (content != null) {
				Long visitorsId = Long.parseLong((content.get("id")+""));
				Long detailId = Long.parseLong((content.get("details_id")+""));
				List<Map<String,Object>> list = visitorsService.selectOpenDateListByVisitorsId(visitorsId);
				if(list.size()>0&&list.get(0)!=null){
					content.put("openDateList",list);
				}else{
					content.put("openDateList",new ArrayList());
				}
				Map<String,String> detailHtml = visitorsService.selectDetailHtmlById(detailId);
				if(detailHtml != null){
					content.put("detailHtml",detailHtml.get("html_url"));
					content.put("detailText",detailHtml.get("content_text"));
				}else{
					content.put("detailHtml","");
					content.put("detailText","");
				}
				msg.setData(content);
			}
			msg.setHearder(0, "success");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 景区-景点评论	GET
	 * http://localhost/cfdScenic/visitors/scenicSpotComment?id=1&rows=6&page=1
	 * 根据景区ID把景区的评论信息返回
	 * 调用表 user_comment  consumer_user
	 */
	@RequestMapping(value="/scenicSpotComment",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String scenicSpotComment(Long id,PageCount page){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("id", id);
			List<Map<String,Object>> data= userCommentService.scenicComment(map);
			if(data.size()>0&&data.get(0)!=null){
				for(int i=0;i < data.size();i++){
					System.out.println(data.get(i).get("id"));
					Long cId = Long.parseLong(data.get(i).get("id")+"");
					List<String> list = visitorsService.selectPictureLibrary(cId);
					data.get(i).put("pic",list);
				}
				msg.setData(data);
			}
			msg.setHearder(0, "success");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 景区-搜索
	 * GET
	 * http://localhost/cfdScenic/visitors/obscureSelect
	 * 把需要搜索的景区名称传过来name 查找表内的信息返回id,head_img(主图),name(名称),price(原价),new_price(折后价),details(详情)openDateId(开放时间)
	 * 调用表 visitors
	 */
	@RequestMapping(value="/obscureSelect",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String obscureSelect(String name){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		try {
			list = visitorsService.obscureSelect(name);
			msg.setHearder(0, "success");
			if(list.size()>0&&list.get(0)!=null)
			{
				msg.setData(list);
			}
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 景区-须知
	 * GET
	 * http://localhost/cfdScenic/visitors/getVisitorsNotice?notice_id=2
	 * 把需要搜索的景区名称传过来name 查找表内的信息返回id,head_img(主图),name(名称),price(原价),new_price(折后价),details(详情)openDateId(开放时间)
	 * 调用表 visitors
	 */
	@RequestMapping(value="/getVisitorsNotice",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getVisitorsNotice(Long notice_id){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		try {
			String noticeUrl = visitorsService.getNoticeByNoticeId(notice_id);
			if(noticeUrl != null)
			{
				msg.setData(noticeUrl);
			}
			msg.setHearder(0, "success");
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	/**
	 * 景区-周边
	 * GET
	 * http://localhost/cfdScenic/visitors/getSurrounding?type=1
	 * 把需要搜索的景区名称传过来name 
	 * 查找表内的信息返回 type(1景区，2酒店，3饭店，4小吃，5特产，6洗手间，7停车场，8代理充值)
	 * 调用表 visitors
	 */
	@RequestMapping(value="/getSurrounding",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getSurrounding(Integer type){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			switch (type) {
			case 1:
				list = visitorsService.findAllVisitors();
				break;
			case 2:
				list = visitorsService.findAllInformation(type);
				break;
			case 3:
				list = visitorsService.findAllInformation(type);
				break;
			case 4:
				list = visitorsService.findAllInformation(type);
				break;
			case 5:
				list = visitorsService.findAllInformation(type);
				break;
			case 6:
				list = visitorsService.findAllPublicPlaces(type);
				break;
			case 7:
				list = visitorsService.findAllPublicPlaces(type);
				break;
			case 8:
				list = visitorsService.findAllPublicPlaces(type);
				break;
			}
			if(list.size() >0&&list.get(0)!=null){
				msg.setData(list);
			}else{
				msg.setData(new ArrayList());
			}
			msg.setHearder(0, "success");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 景区 - 湿地简介，景区规划
	 * http://localhost/cfdScenic/visitors/getPlanningOrIntroduce?type=1
	 * type(1湿地简介，2景区规划)
	 * 调用表 visitors
	 */
	@RequestMapping(value="/getPlanningOrIntroduce",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getPlanningOrIntroduce(Integer type){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		String url = new String();
		try {
				url = visitorsService.getPlanningOrIntroduce(type);
			if(url!=null&&url.length()>0){
				msg.setData(url);
			}
			msg.setHearder(0, "success");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	/**
	 * 景区 - 图集
	 * GET
	 * http://localhost/cfdScenic/visitors/findAtlasByVisitorsId?visitorsId=2
	 * 调用表 visitors
	 */
	@RequestMapping(value="/findAtlasByVisitorsId",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String findAtlasByVisitorsId(Long visitorsId){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		List<String> list = new ArrayList<String>(); 
		try {
			list = visitorsService.findAtlasByVisitorsId(visitorsId);
			if(list.size()>0&&list.get(0)!=null){
				msg.setData(list);
			}
			msg.setHearder(0, "success");
		} catch (Exception e) {
			e.printStackTrace();
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}
	
	
	
	
	
	
	
	
}
