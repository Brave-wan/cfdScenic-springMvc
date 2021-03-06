package com.htkj.cfdScenic.app.service;

import com.htkj.cfdScenic.app.dao.UserAccountDao;
import com.htkj.cfdScenic.app.model.UserAccount;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class UserAccountService {
	@Autowired
	private UserAccountDao userAccountDao;

	public Integer getRemainingPoints(Long userId) {
		return userAccountDao.getRemainingPoints(userId);
	}

	public Integer updateMoney(Map<String, Object> content) {
		return userAccountDao.updateMoney(content);
	}

	public UserAccount findByUserId(Long userId2) {
		return userAccountDao.findByUserId(userId2);
	}

	public void updateBalanceMessage(UserAccount userAccountMessage) {
		userAccountDao.updateBalanceMessage(userAccountMessage);
	}
	
	public void updateCutDowmBalanceUserAccount(Map map) {
			userAccountDao.updateCutDowmBalanceUserAccount(map);
	}
	public void updateBalanceByShopUserId(UserAccount userAccount) {
		userAccountDao.updateBalanceByShopUserId(userAccount);
}
	public void updateBalanceShopToUser(Map map) {
		userAccountDao.updateBalanceShopToUser(map);
}

    public DataGrid getUserAccountList(PageRequest<Map<String, Object>> pageRequest) {
        DataGrid data = new DataGrid();
        try {
            Page page = userAccountDao.getUserAccountList(pageRequest);
            data = DataGrid.pageToDataGrid(page);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return data;
    }

    public void updateBalanceUserAccountByUID(Map map) {
		userAccountDao.updateBalanceUserAccountByUID(map);
}
    
    public UserAccount selectBalance(Long id) {
        return userAccountDao.selectBalance(id);
    }

    public void addBalance(UserAccount userAccount) {
        userAccountDao.addBalance(userAccount);
    }

    public UserAccount selectByUserId(Long userId) {
        return userAccountDao.selectByUserId(userId);
    }

    public void addBalanceByUserId(UserAccount userAccount) {
        userAccountDao.addBalanceByUserId(userAccount);
    }

	public UserAccount selectUserAccountByUserId(Long userId) {
		return userAccountDao.findByUserId(userId);
	}
}
