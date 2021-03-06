package com.htkj.cfdScenic.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.RefundCauseDao;
import com.htkj.cfdScenic.app.dao.ShopInformationDao;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Service
@Transactional
public class RefundCauseService {

	@Autowired
	private RefundCauseDao refundCauseDao;
	
	@Autowired
	private ShopInformationDao ShopInformationDao;
	
	public DataGrid getRefundGoodsOrderList(PageRequest<Map<String, Object>> pageRequest) {
		DataGrid data = new DataGrid();
		try {
			Page page = refundCauseDao.getRefundGoodsOrderList(pageRequest);
			data = DataGrid.pageToDataGrid(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public DataGrid getRefundHotelOrderList(PageRequest<Map<String, Object>> pageRequest) {
		DataGrid data = new DataGrid();
		try {
			Page page = refundCauseDao.getRefundHotelOrderList(pageRequest);
			data = DataGrid.pageToDataGrid(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public DataGrid getRefundRestaurantOrderList(PageRequest<Map<String, Object>> pageRequest) {
		DataGrid data = new DataGrid();
		try {
			Page page = refundCauseDao.getRefundRestaurantOrderList(pageRequest);
			data = DataGrid.pageToDataGrid(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public Integer getShopInformationIdTypeById(Long shopInformationId) {
		return ShopInformationDao.getShopInformationIdTypeById(shopInformationId);
	}
	
}
