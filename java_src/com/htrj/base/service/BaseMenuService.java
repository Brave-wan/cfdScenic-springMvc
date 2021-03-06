package com.htrj.base.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htrj.base.dao.BaseMenuDao;
import com.htrj.base.dao.RoleDao;
import com.htrj.base.model.BaseMenu;
import com.htrj.base.model.Role;
import com.htrj.base.model.SysUser;
import com.htrj.common.page.DataTree;

@Service
@Transactional
public class BaseMenuService {
	
	@Autowired(required=false)
	private BaseMenuDao baseMenuDao;
	@Autowired(required=false)
	private RoleDao roleDao;
	
	
	/**
	 * 
	 * @Title: finUserMenu 
	 * @Description: TODO(根据参数查询菜单信息，用于登录后进入主页面左侧的列表显示。) 
	 * @param: @param baseMenu 封装的参数信息
	 * @param: @return 
	 * @return: List<BaseMenu> 查询到的菜单列表   
	 * @throws
	 */
	public List<BaseMenu> findUserMenu(BaseMenu BaseMenu){
		return baseMenuDao.findUserMenu(BaseMenu);
	}
	public List<BaseMenu> findUMenu(BaseMenu BaseMenu){
		return baseMenuDao.findUMenu(BaseMenu);
	}
	public List<BaseMenu> finderjiMenu(BaseMenu BaseMenu){
		return baseMenuDao.finderjiMenu(BaseMenu);
	}
	/**
	 * 
	 * @Title: getMenuTree 
	 * @Description: TODO(将从数据库中取出的BaseMenu列表转化成DataTree列表) 
	 * @param: @param baseMenuParam
	 * @param: @return    
	 * @return: List<DataTree>    
	 * @throws
	 */
	
	public List<DataTree> getMenuTree(BaseMenu baseMenuParam){
		List<BaseMenu> baseMenuList = new ArrayList<BaseMenu>();
		if(baseMenuParam.getBrId()!=1){//判断是不是超级管理员的
			if(baseMenuParam.getBmParentId()==null){
				Map rolemap = new HashMap();
				rolemap.put("brid", baseMenuParam.getBrId());
				List list = roleDao.findAll(rolemap);
				if(list.size()>0){
					Role role = (Role) list.get(0);
					String[] roles = role.getMenuid().split(",");
					for (int i = 0; i < roles.length; i++) {
						BaseMenu b = new BaseMenu();
						b.setBmId(Long.parseLong(roles[i]));
						b.setBmParentId(baseMenuParam.getBmParentId());
						b.setBrId(baseMenuParam.getBrId());
						List<BaseMenu> a = baseMenuDao.findUserMenu(b);
						if(a.size()>0){
							BaseMenu b1 = a.get(0);
							baseMenuList.add(b1);
						}
					}
				}else{
				}
			}else{
				Map rolemap = new HashMap();
				rolemap.put("brid", baseMenuParam.getBrId());
				Role role = (Role) roleDao.findAll(rolemap).get(0);
				String[] roles = role.getMenuid().split(",");
				List<BaseMenu> base = finderjiMenu(baseMenuParam);
				for (int i = 0; i < roles.length; i++) {
					for (int j = 0; j < base.size(); j++) {
						Long pid = base.get(j).getBmId();
						String a1 = pid.toString();
						String a2 = roles[i];
						if(a2.equals(a1)){
							BaseMenu b = new BaseMenu();
							b.setBmId(Long.parseLong(roles[i]));
							b.setBmParentId(baseMenuParam.getBmParentId());
							b.setBrId(baseMenuParam.getBrId());
							List<BaseMenu> a = baseMenuDao.findUserMenu(b);
							System.out.println(1);
							if(a.size()>0){
								BaseMenu b1 = a.get(0);
								baseMenuList.add(b1);
							}
						}
					}
				}
			}
		}else{
			baseMenuList = findUMenu(baseMenuParam);
		}
		List<DataTree> dataTreeList = new ArrayList<DataTree>();
		for(BaseMenu baseMenu : baseMenuList){
			DataTree dt = new DataTree();
			Map<String, Object> map = new HashMap<String, Object>();
			dt.setId(baseMenu.getBmId());
			dt.setPid(baseMenu.getBmParentId());
			dt.setIconCls(baseMenu.getBmIcon());
			dt.setText(baseMenu.getBmName());
			dt.setState(baseMenu.getState()==0? DataTree.STATE_OPEN:DataTree.STATE_CLOASED);
			map.put("url", baseMenu.getBmUrl());
			map.put("menudes", baseMenu.getBmRemark());
			dt.setAttributes(map);
			dt.setChildren(new ArrayList());
			dataTreeList.add(dt);
		}
		return dataTreeList;
	}
}
