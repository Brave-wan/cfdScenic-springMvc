package com.htrj.base.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hsqldb.lib.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.htkj.cfdScenic.app.dao.OpenDateListDao;
import com.htrj.base.dao.PermissionDao;
import com.htrj.base.model.BaseMenu;
import com.htrj.base.model.Role;
import com.htrj.common.base.BaseService;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import com.htrj.common.utils.StringUtil;

@Service
@Transactional
public class PermissionService extends BaseService{
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private OpenDateListDao openDateListDao;
	@Autowired
	private RoleService roleService;
	@Autowired
	private BaseMenuService baseMenuService;
	
	/**
	 * 张腾跃
	 * 权限设置
	 * 2016年9月29日13:13:41
	 * @param permission
	 * @return
	 */

	public DataGrid topermissionManage(PageRequest<Map<String, Object>> pageRequest) {
		DataGrid data = new DataGrid();
		try {
			Page page = permissionDao.pageGetpermission(pageRequest);
			data = DataGrid.pageToDataGrid(page);
		} catch (Exception e) {
			throw new BusinessException("查询权限列表出错",e);
		}
		return data;
	}


	public List findUserMenu(Map<String,Object> map) {
		List list = permissionDao.selectmenu(map);
		return list;
	}


	public String getAuthIdsById(Map map)throws Exception{
		String authIds=null;
		List list =  roleService.findAll(map);
		if(list.size()>0){
			Role role = (Role) list.get(0);
			authIds = role.getMenuid();
		}else{
			authIds = "";
		}
		return authIds;
	}
	public JSONArray getCheckedAuthsByParentId(String parentId,String authIds,Integer type)throws Exception{
		JSONArray jsonArray=this.getCheckedAuthByParentId(parentId,authIds,type);
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			if("0".equals(jsonObject.getString("state"))){
				continue;
			}else{
				jsonObject.put("children", getCheckedAuthsByParentId(jsonObject.getString("id"),authIds,type));
			}
		}
		return jsonArray;
	}
	public JSONArray getCheckedAuthByParentId(String parentId,String authIds,Integer type)throws Exception{
		JSONArray jsonArray=new JSONArray();
		BaseMenu baseMenu = new BaseMenu();
		baseMenu.setBmParentId(Long.parseLong(parentId));
		baseMenu.setType(type);
		if(type==1){
			baseMenu.setType(0);
		}else{
			baseMenu.setType(1);
		}
		List<BaseMenu> list = baseMenuService.finderjiMenu(baseMenu);
		for (int i = 0; i < list.size(); i++) {
			JSONObject jsonObject=new JSONObject();
			Long authId=list.get(i).getBmId();
			jsonObject.put("id", authId);
			jsonObject.put("text", list.get(i).getBmName());
			jsonObject.put("state", list.get(i).getState());
			if(StringUtil.existStrArr(authId+"", authIds.split(","))){
				jsonObject.put("checked", true);
			}
			JSONObject attributeObject=new JSONObject();
			attributeObject.put("authPath",authId);
			jsonObject.put("attributes", attributeObject);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}
}
