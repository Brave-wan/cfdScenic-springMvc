package com.htkj.cfdScenic.app.service;

import com.htkj.cfdScenic.app.dao.AlipayInfoDao;
import com.htkj.cfdScenic.app.dao.ConsumerUserDao;
import com.htkj.cfdScenic.app.dao.ShopInformationDao;
import com.htkj.cfdScenic.app.dao.ShopsGroupDao;
import com.htkj.cfdScenic.app.dao.UserAccountDao;
import com.htkj.cfdScenic.app.model.AlipayInfo;
import com.htkj.cfdScenic.app.model.ConsumerUser;
import com.htkj.cfdScenic.app.model.ShopInformation;
import com.htkj.cfdScenic.app.model.ShopUser;
import com.htkj.cfdScenic.app.model.SmsSendRecord;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Json;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import com.htrj.common.utils.GenerateSequenceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopInformationService {
	
	@Autowired
	private ConsumerUserDao consumerUserDao;
	@Autowired
	private ShopInformationDao shopInformationDao;
    @Autowired
    private ShopsGroupDao shopsGroupDao;
    @Autowired
    private ShopUserService shopUserService ;
    @Autowired
  	private SmsSendService smsSendService;
    @Autowired
    private UserAccountDao userAccountDao; 
    @Autowired
    private AlipayInfoDao alipayInfoDao;
	
	/**
	 * @Title: getUserIdByToken
	 * @Description: TODO(跟据token查询用户ID)
	 * @param token
	 * @return
	 */
	public Long getUserIdByToken(String token){
		return consumerUserDao.getUserIdByToken(token);
	}
	
	public void insertShopInformation(ShopInformation shopInformation)
	{
		shopInformationDao.insertShopInformation(shopInformation);
	}


	public void editDatum(ConsumerUser consumerUser) {
		consumerUserDao.editDatum(consumerUser);
	}


	public Integer selectById(Long userId) {
		return consumerUserDao.selectById(userId);
	}
	
	public ShopInformation getInforMationByid(Long id)
	{
		return shopInformationDao.findDetailInformationByID(id);
	}

	public ConsumerUser selectByPhone(String mobileNo) {
		return consumerUserDao.selectByPhone(mobileNo);
	}

	public ConsumerUser selectByUserId(Long id) {
		return consumerUserDao.selectByUserId(id);
	}
	public ConsumerUser selectByUserIdTwo(Long id) {
		return consumerUserDao.selectByUserIdTwo(id);
	}

	public Integer updateUUID(ConsumerUser userMessage) {
		return consumerUserDao.updateUUID(userMessage);
	}

	public void updateInformation(ShopInformation shopInformation) {
	       shopInformationDao.updateInformation(shopInformation);
	}


	public Integer insertMessage(ConsumerUser user) {
		return consumerUserDao.insertMessage(user);
	}


	public ConsumerUser findUserByOpenId(String openId) {
		return consumerUserDao.findUserByOpenId(openId);
	}


	public void updateMessage(ConsumerUser user) {
		consumerUserDao.updateMessage(user);
	}


	public Integer update(ConsumerUser consumerUser) {
		return consumerUserDao.updateMessages(consumerUser);
	}


	public Map<String, Object> storeMessage(Map<String, Object> map) {
		return shopInformationDao.storeMessage(map);
	}
	
	public Map<String, Object> findUserMessage(String telphone) {
		return consumerUserDao.findUserMessage(telphone);
	}

	public String getUserPassWordByUserId(Long userId) {
		return consumerUserDao.getUserPassWordByUserId(userId);
	}

    public ShopInformation selectAllShopInformation(Long id){
        return shopInformationDao.selectAllShopInformation(id);
    }
//admin查看全部的
    public DataGrid getShopInformationList(PageRequest<Map<String, Object>> pageRequest) {
        DataGrid data = new DataGrid();
        try {
            Page page = shopInformationDao.getShopInformationList(pageRequest);
            data = DataGrid.pageToDataGrid(page);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return data;
    }
    /**
     * 获取商家自己的信息
     * @param pageRequest
     * @return
     */
    public DataGrid getShopList(PageRequest<Map<String, Object>> pageRequest) {
        DataGrid data = new DataGrid();
        try {
            Page page = shopInformationDao.getShopList(pageRequest);
            data = DataGrid.pageToDataGrid(page);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return data;
    }
    public List<Map<String, Object>> getType() {
        return shopsGroupDao.getType();
    }

    public int insert(ShopInformation shopInformation) {
        return shopInformationDao.insert(shopInformation);
    }

    public int updateByPrimaryKeySelective(ShopInformation shopInformation) {
        return shopInformationDao.updateByPrimaryKeySelective(shopInformation);
    }

    public Map<String, Object> selectByPrimaryKey(Long id) {
        return shopInformationDao.selectByPrimaryKey(id);
    }
    
    public int deleteByPrimaryKey(Long id) {
        return shopInformationDao.deleteByPrimaryKey(id);
    }
    public ShopInformation selectByShopUserPrimaryKey(Long id) {
        return shopInformationDao.selectByShopUserPrimaryKey(id);
    }
    public void audit(ShopInformation shopInformation) {
    	  String str = "";
    	  ShopInformation s = shopInformationDao.findDetailInformationByID(shopInformation.getId());
          try {
              if (shopInformation.getIsAudit() == 1) {
                  shopInformation.setAuditFail("");
                  str = "您的店铺有新的审核信息：名称为《"+s.getName()+"》的店铺审核通过";
              }else{
              	str = "您的店铺有新的审核信息：名称为《"+s.getName()+"》的店铺审核未通过;失败原因："+shopInformation.getAuditFail();
              }
              /**
               * 审核成功后，进行短信通知
               * 张腾跃
               * 2016年10月9日14:41:46
               */
              shopInformationDao.audit(shopInformation);
              SmsSendRecord sms = new SmsSendRecord();
              ShopUser shopUser = shopUserService.selectByUserId(s.getShopUserId());
  			sms.setMobiles(shopUser.getTelPhone());
  			sms.setNeedstatus(false);
  			sms.setContent(str.toString());
  			Map<String, String> map = smsSendService.SmsSend(sms);
          }catch(Exception e){
        	 e.printStackTrace(); 
          }
    }

	public UserAccount getUserAccountByUserId(Long id) {
		return userAccountDao.findByUserId(id);
	}

	public Json editAlipayInfo(AlipayInfo ai) {
		Json json = new Json();
		try {
			if(ai.getId() != null){
				alipayInfoDao.updateAlipayInfo(ai);
			}else{
				ai.setId(GenerateSequenceUtil.getUniqueId());
				alipayInfoDao.saveAlipayInfo(ai);
			}
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMessage("系统繁忙！");
			e.printStackTrace();
		}
		return json;
	}

	public AlipayInfo getAlipayInfo(Long id) {
		return alipayInfoDao.getAlipayInfo(id);
	}
}
