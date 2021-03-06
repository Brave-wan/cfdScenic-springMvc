package com.htkj.cfdScenic.app.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.htkj.cfdScenic.app.model.HtmlText;
import com.htkj.cfdScenic.app.service.HtmlTextService;
import com.htkj.cfdScenic.app.util.ResponseMsg;
import com.htrj.common.base.BaseController;
import com.htrj.common.page.Json;
import com.htrj.common.upload.UploadFile;
import com.htrj.common.utils.GenerateSequenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/htmlText")
public class HtmlTextController extends BaseController{
	@Autowired
	private HtmlTextService htmlTextService;
	
	/*
	 * 湿地简介(规划)
	 * GET
	 * http://localhost:8080/cfdScenic/htmlText/wetLandSynopsis
	 * 根据类型type类型(0简介1规化)          
	 * 把湿地简介的信息返回id(湿地简介id),name(名称),html_url(生成html的url),create_time(创建时间)
	 * 调用表 visitors_profiles
	 */
	@RequestMapping(value="/wetLandSynopsis",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String wetLandSynopsis(Integer type){
		ResponseMsg msg = new ResponseMsg();
		String json = new String();
		Map<String, String> content = new HashMap<String, String>();
		try {
			content = htmlTextService.wetLandSynopsis(type);
			msg.setHearder(0, "success");
			msg.setData(content);
		} catch (Exception e) {
			msg.setHearder(1, "error");
		}
		json = JSONObject.toJSONString(msg,SerializerFeature.WriteMapNullValue);
		System.out.println(json);
		return json;
	}


    /**
     * 添加、修改   须知
     * @param request
     * @param response
     * @param activityDetail
     * @param htmlText
     * @return
     */
	@RequestMapping("/addHtmlText1")
    @ResponseBody
	public Json addHtmlText1(HttpServletRequest request, HttpServletResponse response,String activityDetail,HtmlText htmlText){
	    Json json = new Json();
        try {
            String html = new UploadFile().saveHtml(request, response, activityDetail);
            htmlText.setHtmlUrl(html);
            if(htmlText.getId() == null){
                htmlText.setId(GenerateSequenceUtil.getUniqueId());
                htmlText.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                htmlTextService.addHtmlText(htmlText);
                json.setObj(htmlText.getId());
            } else {
                htmlTextService.updateHtmlTest(htmlText);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }

        json.setSuccess(true);
        return json;
    }
	@RequestMapping("/addHtmlText")
    @ResponseBody
	public Json addHtmlText(HttpServletRequest request, HttpServletResponse response,String activityDetail,HtmlText htmlText){
	    Json json = new Json();
        try {
            String html = new UploadFile().saveHtml(request, response, activityDetail);
            htmlText.setHtmlUrl(html);
            if(htmlText.getId() == null){
                htmlText.setId(GenerateSequenceUtil.getUniqueId());
                htmlText.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                htmlTextService.addHtmlText(htmlText);
                json.setObj(htmlText.getId());
            } else {
                htmlTextService.updateHtmlTest(htmlText);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }

        json.setSuccess(true);
        return json;
    }
    /**
     * 添加、修改   详情
     * @param request
     * @param response
     * @param activityDetail
     * @param htmlText
     * @return
     */
	@RequestMapping("/addHtmlText2")
    @ResponseBody
	public Json addHtmlText2(HttpServletRequest request, HttpServletResponse response,String activityDetail,HtmlText htmlText){
	    Json json = new Json();
        try {
            String html = new UploadFile().saveHtml(request, response, activityDetail);
            htmlText.setHtmlUrl(html);
            if(htmlText.getId() == null){
                htmlText.setId(GenerateSequenceUtil.getUniqueId());
                htmlText.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                htmlTextService.addHtmlText(htmlText);
                json.setObj(htmlText.getId());
            } else {
                htmlTextService.updateHtmlTest(htmlText);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }

        json.setSuccess(true);
        return json;
    }
}
