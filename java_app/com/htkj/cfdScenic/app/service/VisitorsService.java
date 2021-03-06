package com.htkj.cfdScenic.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.OpenDateListDao;
import com.htkj.cfdScenic.app.dao.PictureLibraryDao;
import com.htkj.cfdScenic.app.dao.PublicPlacesDao;
import com.htkj.cfdScenic.app.dao.ShopInformationDao;
import com.htkj.cfdScenic.app.dao.VisitorsDao;
import com.htkj.cfdScenic.app.dao.HtmlTextDao;
import com.htkj.cfdScenic.app.model.OpenDateList;
import com.htkj.cfdScenic.app.model.PictureLibrary;
import com.htkj.cfdScenic.app.model.Visitors;
import com.htrj.common.base.BaseService;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class VisitorsService extends BaseService{
	@Autowired
	private VisitorsDao visitorsDao;
	@Autowired
	private OpenDateListDao openDateListDao;
	@Autowired
	private HtmlTextDao htmlTextDao;
	@Autowired
	private ShopInformationDao shopInformationDao;
	@Autowired
	private PublicPlacesDao publicPlacesDao;
	@Autowired
	private PictureLibraryDao pictureLibraryDao;
	
	public List<Map<String, String>> scenicSpotList() {
		return visitorsDao.scenicSpotList();
	}

	public Map<String, Object> scenicSpotParticulars(Long id) {
		return visitorsDao.scenicSpotParticulars(id);
	}

	public List<Map<String, String>> obscureSelect(String name) {
		return visitorsDao.obscureSelect(name);
	}

	public List<Map<String, String>> selectMessage(String name) {
		return visitorsDao.selectMessage(name);
	}

	public List<Map<String, String>> tagsVisitors() {
		return visitorsDao.tagsVisitors();
	}
	public List<Map<String, String>> indexVisitors() {
		return visitorsDao.indexVisitors();
	}

	public List<Map<String,Object>> scenicSpotLimitPage() {
		return visitorsDao.scenicSpotLimitPage();
	}

	public DataGrid shopListLimit(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = visitorsDao.shopListLimit(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}

	public Map<String, String> oneShopMessage(Long id) {
		return visitorsDao.oneShopMessage(id);
	}

	public List<Map<String, Object>> selectOpenDateListByVisitorsId(Long visitorsId) {
		return openDateListDao.selectOpenDateListByVisitorsId(visitorsId);
	}
	
	/**
	 * Title:景点-添加
	 * @author wangfenglong
	 * @date 2016年9月2日
	 */
	public int insert(Visitors visitors) {
		return visitorsDao.insert(visitors);
	}

	public Map<String,String> selectDetailHtmlById(Long noticeId) {
		return htmlTextDao.selectDetailHtmlById(noticeId);
	}

	public int updateByPrimaryKeySelective(Visitors visitors) {
		return visitorsDao.updateByPrimaryKeySelective(visitors);
	}

	public DataGrid toVisitorsManage(PageRequest<Map<String, Object>> pageRequest) {
		DataGrid data = new DataGrid();
		try {
			Page page = visitorsDao.pageGetVisitors(pageRequest);
			data = DataGrid.pageToDataGrid(page);
		} catch (Exception e) {
			throw new BusinessException("查询景点信息列表出错",e);
		}
		return data;
	}

	public int deleteByPrimaryKey(Long id) {
		return visitorsDao.deleteByPrimaryKey(id);
	}

	public Visitors selectByPrimaryKey(Long id) {
		return visitorsDao.selectByPrimanrKey(id);
	}

	public String getNoticeByNoticeId(Long notice_id) {
		return htmlTextDao.getNoticeByNoticeId(notice_id);
	}

	public List<Map<String, Object>> findAllVisitors() {
		return visitorsDao.findAllVisitors();
	}

	public List<Map<String, Object>> findAllInformation(Integer type) {
		return shopInformationDao.findAllInformation(type);
	}

	public List<Map<String, Object>> findAllPublicPlaces(Integer type) {
		return publicPlacesDao.findAllPublicPlaces(type);
	}

	public String getPlanningOrIntroduce(Integer type) {
		return htmlTextDao.getPlanningOrIntroduce(type);
	}

	public List<String> findAtlasByVisitorsId(Long visitorsId) {
		return pictureLibraryDao.findAtlasByVisitorsId(visitorsId);
	}

	public List<String> selectPictureLibrary(Long id) {
		return pictureLibraryDao.selectPictureLibrary(id);
	}

	public void saveOpenDateList(OpenDateList odl) {
		openDateListDao.saveOpenDateList(odl);
	}

	public DataGrid findVisitorsList(PageRequest<Map<String, Object>> pageRequest) {
		Page page = visitorsDao.findVisitorsList(pageRequest);
		return DataGrid.pageToDataGrid(page);
	}

	public void updateOpenDateList(OpenDateList openDateList) {
		openDateListDao.updateOpenDateList(openDateList);
	}

	public void deletePictureLibraryByVisitorsId(Long id) {
		pictureLibraryDao.deleteByLinkId(id);
	}

	public void savePictureLibrary(PictureLibrary pictureLibrary) {
		pictureLibraryDao.savePictureLibrary(pictureLibrary);
	}

	public List<String> selectPictureLibraryByVisitorsId(Long id) {
		return pictureLibraryDao.selectPictureLibraryByVisitorsId(id);
	}

}
