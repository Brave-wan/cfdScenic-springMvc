/**  
* @Title: MonitorPointService.java
* @Package com.htkj.cfdScenic.app.service
* @Description: TODO(用一句话描述该文件做什么)
* @author 张伟烁 
* @date 2016年10月31日 下午4:15:00
*/ 
package com.htkj.cfdScenic.app.service;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.MonitorPointDao;
import com.htkj.cfdScenic.app.model.MonitorPoint;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import com.sun.java.swing.plaf.motif.resources.motif_zh_CN;

/**
* @ClassName: MonitorPointService
* @Description: TODO(这里用一句话描述这个类的作用)
* @author 张伟烁
* @date 2016年10月31日 下午4:15:00
*
*/
@Service
@Transactional
public class MonitorPointService {
	@Autowired
	private MonitorPointDao monitorPointDao;

	/**
	 * @param pageRequest 
	* @Title: getAllMonitor
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @author 张伟烁
	* @return Object    返回类型
	* @throws
	*/ 
	public DataGrid getAllMonitor(PageRequest<Map<String, Object>> pageRequest) {
		DataGrid data = new DataGrid();
		try {
			Page page = monitorPointDao.getAllMointor(pageRequest);
			data = DataGrid.pageToDataGrid(page);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("查询摄像头出错",e);
		}
		return data;
	}

	public DataGrid getMonitorList(PageRequest<Map<String, Object>> pageRequest) {
		return DataGrid.pageToDataGrid(monitorPointDao.getMonitorList(pageRequest));
	}

	public void saveMonitorPoint(MonitorPoint mp) {
		monitorPointDao.insertSelective(mp);
	}

	public void editMonitor(MonitorPoint mp) {
		monitorPointDao.updateByPrimaryKeySelective(mp);
	}

	public MonitorPoint getMonitorPointById(Long id) {
		return monitorPointDao.getMonitorPointById(id);
	}

	public void deleteMonitorById(Long id) {
		monitorPointDao.deleteMonitorById(id);
	}

	public void updateIP(Map map) {
		monitorPointDao.updateIP(map);
	}

	public Map getIp() {
		return monitorPointDao.getIp();
	}
	
}
