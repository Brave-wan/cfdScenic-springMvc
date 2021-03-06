package com.htkj.cfdScenic.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.PictureLibraryDao;
import com.htkj.cfdScenic.app.dao.UserCommentDao;
import com.htkj.cfdScenic.app.dao.VisitorsOrderDao;
import com.htkj.cfdScenic.app.model.PictureLibrary;
import com.htkj.cfdScenic.app.model.UserComment;
import com.htkj.cfdScenic.app.model.VisitorsOrder;
import com.htrj.common.base.BaseService;

@Service
@Transactional
public class MyTicketsService extends BaseService{

	@Autowired
	private VisitorsOrderDao visitorsOrderDao;
	@Autowired
	private UserCommentDao userCommentDao;
	@Autowired
	private PictureLibraryDao pictureLibraryDao;
	
	public List<Long> getMyTickets(Long userId) {
		return visitorsOrderDao.getMyTickets(userId);
	}

	public void deleteMyTickets(Long orderCode) {
		visitorsOrderDao.deleteMyTickets(orderCode);
	}

	public void updateMyTickets(Long orderCode) {
		visitorsOrderDao.updateMyTickets(orderCode);
	}

	public void updateOrderState(Long id) {
		visitorsOrderDao.updateOrderState(id);
	}

	public List<Map<String,Object>> getOrderDetail(Long orderCode) {
		return visitorsOrderDao.getOrderDetail(orderCode);
	}

	public List<Long> getMyTicketsByType(Map map) {
		return visitorsOrderDao.getMyTicketsByType(map);
	}


	public void saveVisitorsUserComment(UserComment userComment) {
		userCommentDao.saveVisitorsUserComment(userComment);
	}

	public void savePictureLibrary(PictureLibrary pl) {
		pictureLibraryDao.savePictureLibrary(pl);
	}

	public void updateOrderStateByOrderCode(Long orderCode) {
		visitorsOrderDao.updateOrderStateByOrderCode(orderCode);
	}

	public Integer getWaitPayByUserId(Long userId) {
		return visitorsOrderDao.getWaitPayByUserId(userId);
	}

	public Integer getAlreadyByUserId(Long userId) {
		return visitorsOrderDao.getAlreadyByUserId(userId);
	}

	public List<Map<String, Object>> getMyTicketsByOrderCode(Long orderCode) {
		return visitorsOrderDao.getMyTicketsByOrderCode(orderCode);
	}

	public void updateTicketsOverDue(String orderCode) {
		VisitorsOrder visitorsOrder = new VisitorsOrder();
		visitorsOrder.setOrderState(8);
		visitorsOrder.setOrderCode(orderCode);
		visitorsOrderDao.updateVisitorsOrder(visitorsOrder);
	}
	
}
