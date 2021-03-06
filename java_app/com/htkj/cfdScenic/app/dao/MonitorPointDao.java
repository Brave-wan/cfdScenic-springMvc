package com.htkj.cfdScenic.app.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.MonitorPoint;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
@Repository
public class MonitorPointDao extends BaseDao<MonitorPoint, Integer> {
    public int deleteByPrimaryKey(Long id){
    	return getSqlSession().delete(getStatementName("deleteByPrimaryKey"),id);
    }

    public int insertSelective(MonitorPoint record){
    	return getSqlSession().insert(getStatementName("insertSelective"),record);
    }

    public int updateByPrimaryKeySelective(MonitorPoint record){
    	return getSqlSession().update(getStatementName("updateByPrimaryKeySelective"),record);
    }

	/**
	* @Title: getAllMointor
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param pageRequest
	* @param @return    设定文件
	* @author 张伟烁
	* @return Page    返回类型
	* @throws
	*/ 
	public Page getAllMointor(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getAllMointor"), pageRequest);
	}

	public Page getMonitorList(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("getMonitorList"), pageRequest);
	}

	public MonitorPoint getMonitorPointById(Long id) {
		return getSqlSession().selectOne(getStatementName("getMonitorPointById"), id);
	}

	public void deleteMonitorById(Long id) {
		getSqlSession().delete(getStatementName("deleteByPrimaryKey"), id);
	}

	public void updateIP(Map map) {
		getSqlSession().update(getStatementName("updateIP"), map);
	}

	public Map getIp() {
		return getSqlSession().selectOne(getStatementName("getIp"));
	}
}