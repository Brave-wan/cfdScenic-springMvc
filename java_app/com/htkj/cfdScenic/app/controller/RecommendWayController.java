/**  
* @Title: RecommendWayController.java
* @Package com.htkj.cfdScenic.app.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author 张伟烁 
* @date 2016年10月28日 上午10:40:54
*/ 
package com.htkj.cfdScenic.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.RecommendWay;
import com.htkj.cfdScenic.app.service.RecommendWayService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.PagerForm;
import com.htrj.common.utils.GenerateSequenceUtil;

/**
* @ClassName: RecommendWayController
* @Description: TODO(推荐路线查询)
* @author 张伟烁
* @date 2016年10月28日 上午10:40:54
*
*/
@Controller
@RequestMapping("/way")
public class RecommendWayController extends BaseController {
	
	@Autowired
	private RecommendWayService recommendWayService;
	
	/**
	* @Title: getAllRecommendWay
	* @Description: TODO(获取推荐路线)
	* @param @return    设定文件
	* @author 张伟烁
	* @return String    返回类型
	* @throws
	*/ 
	@RequestMapping(value="getWay",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getAllRecommendWay(PagerForm form){
		ResponseMsg responseMsg = new ResponseMsg();
		try {
			responseMsg.setData(recommendWayService.findWayList(form.getPageRequest(new HashMap<String,Object>())));
			responseMsg.setHearder(0, "成功");
		} catch (Exception e) {
			e.printStackTrace();
			responseMsg.setHearder(1, "ckeck error");
		}
		return JSONObject.toJSONString(responseMsg,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	* @Title: getAllPoint
	* @Description: TODO(获取点)
	* @param @param type 0 中心点 1多边形点
	* @param @return    设定文件
	* @author 张伟烁
	* @return String    返回类型
	* @throws
	*/ 
	@RequestMapping(value="getAllPoint",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getAllPoint(){
		ResponseMsg responseMsg = new ResponseMsg();
		try {
			List<String> points = new ArrayList<String>();
			 points.add("38.019988,114.60799");
			 points.add("38.01987,114.607352");
			 points.add("38.01976,114.607089");
			 points.add("38.019591,114.607212");
			 points.add("38.019464,114.607041");
			 points.add("38.019324,114.606724");
			 points.add("38.019109,114.607035");
			 points.add("38.018902,114.606939");
			 points.add("38.018783,114.606794");
			 points.add("38.018572,114.607035");
			 points.add("38.018344,114.606901");
			 points.add("38.018175,114.607169");
			 points.add("38.018018,114.607073");
			 points.add("38.017845,114.607379"); 
			 points.add("38.017748,114.607593");
			 points.add("38.017642,114.608039");
			 points.add("38.017782,114.608468");
			 points.add("38.017955,114.608704");
			 points.add("38.01834,114.609213"); 
			 points.add("38.018716,114.609095"); 
			 points.add("38.019134,114.609235"); 
			 points.add("38.019434,114.608972"); 
			 points.add("38.019789,114.609079"); 
			 points.add("38.019908,114.608382"); 
			 points.add("38.019612,114.608301"); 
			 points.add("38.019992,114.60799");
			 Map<String,Object> map = new HashMap<String,Object>();
			 map.put("points", points);
			 map.put("point", "38.018799,114.608119");
			 map.put("count", points.size());
			 responseMsg.setData(map);
			responseMsg.setHearder(0, "成功");
		} catch (Exception e) {
			responseMsg.setHearder(1, "check error");
		}
		return JSONObject.toJSONString(responseMsg,SerializerFeature.WriteMapNullValue);
	}
	
	/**
	 * 获取所有景点的信息
	 */
	@RequestMapping(value="getVisitorsInfo",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getVisitorsInfo(){
		ResponseMsg msg = new ResponseMsg();
		try {
			msg.setData(recommendWayService.getVisitorsInfo());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = JSONObject.toJSONString(msg);
		return json;
	}
	/**
	 * 跳转导航列表页面
	 */
	@RequestMapping("toWayPage")
	public String toWayPage(){
		return "/background/recommendWay/Manager";
	}
	/**
	 * 导航列表
	 */
	@RequestMapping("toWayList")
	@ResponseBody
	public DataGrid toWayList(PagerForm page){
		DataGrid dg = new DataGrid();
		try {
			dg = recommendWayService.getWayList(page.getPageRequest(new HashMap()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dg;
	}
	/**
	 * 跳转详情
	 */
	@RequestMapping("toWayDetail")
	public String toWayDetail(Long groupId,ModelMap model){
		model.put("list",recommendWayService.getWayDetail(groupId));
		return "/background/recommendWay/detailPage";
	}
	
	/**
	 * 跳转添加
	 */
	@RequestMapping("toAddPage")
	public String toAddPage(){
		return "/background/recommendWay/addPage";
	}
	
	/**
	 * 添加导航
	 */
	@RequestMapping("toAddWay")
	@ResponseBody
	public Json toAddWay(String xPoint,String yPoint,String groupName,String name,String info,String type){
		Json json = new Json();
		try {
			String[] xPointArr = xPoint.split("&");
			String[] yPointArr = yPoint.split("&");
			String[] groupNameArr = groupName.split("&");
			String[] nameArr = name.split("&");
			String[] infoArr = info.split("&");
			String[] typeArr = type.split("&");
			Long groupById = GenerateSequenceUtil.getUniqueId();
			for(int i=0;i<xPointArr.length;i++){
				RecommendWay rw = new RecommendWay();
				rw.setxPoint(xPointArr[i]);
				rw.setyPoint(yPointArr[i]);
				rw.setGroupId(groupById);
				rw.setId(GenerateSequenceUtil.getUniqueId());
				rw.setInfo(infoArr[i]);
				rw.setName(nameArr[i]);
				rw.setType(Integer.parseInt(typeArr[i]));
				rw.setSoft(i+1);
				rw.setGroupName(groupNameArr[i]);
				recommendWayService.saveRecommendWay(rw);
				json.setSuccess(true);
			}
		} catch (Exception e) {
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 删除列表
	 */
	@RequestMapping("toDeleteWay")
	@ResponseBody
	public Json toDeleteWay(Long groupId){
		Json json = new Json();
		try {
			recommendWayService.toDeleteWay(groupId);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
