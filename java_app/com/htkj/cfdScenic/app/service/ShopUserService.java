package com.htkj.cfdScenic.app.service;

import com.htkj.cfdScenic.app.dao.AlipayInfoDao;
import com.htkj.cfdScenic.app.dao.HtmlTextDao;
import com.htkj.cfdScenic.app.dao.PushMessageDao;
import com.htkj.cfdScenic.app.dao.ShopUserDao;
import com.htkj.cfdScenic.app.model.AlipayInfo;
import com.htkj.cfdScenic.app.model.PushMessage;
import com.htkj.cfdScenic.app.model.ShopUser;
import com.htrj.common.base.BaseService;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopUserService extends BaseService{
	
	@Autowired
	private ShopUserDao shopUserDao;
	
	@Autowired
	private PushMessageDao pushMessageDao;
	
	@Autowired
	private HtmlTextDao htmlTextDao;
	
	@Autowired
	private AlipayInfoDao alipayInfoDao;

	public Integer selectById(Long userId) {
		return shopUserDao.selectById(userId);
	}
	
	
	public Long getShopUserIdByToken(String token)
	{
		return shopUserDao.getShopUserIdByToken(token);
	}

	public ShopUser selectByPhone(String telPhone) {
		return shopUserDao.selectByPhone(telPhone);
	}
	
	public ShopUser selectByUserId(Long id) {
		return shopUserDao.selectByUserId(id);
	}

	public void updateUUID(ShopUser userMessage) {
		shopUserDao.updateUUID(userMessage);
	}

	public void update(ShopUser userMessage) {
		shopUserDao.updatePassWord(userMessage);
	}
	
	public void updateInformationId(ShopUser userMessage) {
		shopUserDao.updateInformationId(userMessage);
	}

	public void insertMessage(ShopUser user) {
		shopUserDao.insertMessage(user);
	}

	public Map<String, Object> shopUserMessage(Long id) {
		return shopUserDao.shopUserMessage(id);
	}

	public Map<String, Object> shopAutonymMessage(Long id) {
		return shopUserDao.shopAutonymMessage(id);
	}


    public ShopUser login(String telephone, String password) throws BusinessException{
    	ShopUser user =new ShopUser();
    	try {
    		user = shopUserDao.selectByPhone(telephone);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        if(user==null){
            throw new BusinessException("用户账户不存在！");
        }
        if(user.getState()==1){
            throw new BusinessException("您的账户已被禁用！");
        }

        String pas = new Md5Hash(password).toHex();

        String userPas = user.getPassWord();

        if(!userPas.equals(pas)){
            throw new BusinessException("密码错误！");
        }
        return user;

    }

    public Boolean selectByTelPhone(String telphone) {
        ShopUser user = shopUserDao.selectByTelPhone(telphone);
        boolean flag = true;
        if (user != null) {
            flag = false ;
        }
        return flag;
    }

    public void register(ShopUser shopUser) {
        shopUserDao.register(shopUser);
    }


	public DataGrid getMyMessage(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = pushMessageDao.getMyMessage(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}


	public String getAboutUs() {
		return htmlTextDao.getAboutUs();
	}


	public String getDetailUrlbyId(Long detailId) {
		return htmlTextDao.getDetailUrlbyId(detailId);
	}
	public DataGrid getByid(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = shopUserDao.getById(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}
	
	public DataGrid getShopUserList(PageRequest<Map<String, Object>> pageRequest) {
		Page returnPage = shopUserDao.getShopUserList(pageRequest);
		return DataGrid.pageToDataGrid(returnPage);
	}


	public AlipayInfo getAlipayInfoBySiId(Long siId) {
		return alipayInfoDao.getAlipayInfoBySiId(siId);
	}
}
