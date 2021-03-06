package com.htkj.cfdScenic.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.htkj.cfdScenic.app.dao.GoodsOrderDao;
import com.htkj.cfdScenic.app.dao.UserAddressDao;
import com.htkj.cfdScenic.app.model.UserAddress;

@Service
@Transactional
public class UserAddressService {
	@Autowired
	private UserAddressDao userAddressDao;
	@Autowired
	private GoodsOrderDao goodsOrderDao;

	public Integer selectByUser(Long userId) {
		return userAddressDao.selectByUser(userId);
	}

	public void insertMessage(UserAddress userAddress) {
		userAddressDao.userAddress(userAddress);
	}

	public UserAddress selectById(Long id) {
		return userAddressDao.selectById(id);
	}

	public void updateMessage(UserAddress userAddress) {
		userAddressDao.updateMessage(userAddress);
	}

	public void editAllDefault(Long userId) {
		userAddressDao.editAllDefault(userId);
	}

	public void updateDefault(Long id) {
		userAddressDao.updateDefault(id);
	}

	public void deleteAddress(Long id) {
		userAddressDao.deleteAddress(id);
	}

	public List<Map<String, String>> addressList(Long userId) {
		return userAddressDao.addressList(userId);
	}

	public Map<String, String> selectDefaultAddress(Long userId) {
		return userAddressDao.selectDefaultAddress(userId);
	}

	public void deleteAddressById(Long id) {
		userAddressDao.deleteAddressById(id);
	}

	public Integer getOrderCountByAddressId(Long id) {
		return goodsOrderDao.getOrderCountByAddressId(id);
	}

	public void updateAddressById(Long id) {
		userAddressDao.updateAddressById(id);
	}
	

}
