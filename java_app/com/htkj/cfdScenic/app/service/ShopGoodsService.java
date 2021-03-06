package com.htkj.cfdScenic.app.service;

import com.htkj.cfdScenic.app.dao.ShopGoodsDao;
import com.htkj.cfdScenic.app.model.ShopGoods;
import com.htrj.common.exception.BusinessException;
import com.htrj.common.page.DataGrid;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ShopGoodsService {
	@Autowired
	private ShopGoodsDao shopGoodsDao;

	public List<Map<String, String>> selectMessage(String name) {
		return shopGoodsDao.selectMessage(name);
	}

	public List<Map<String, String>> recommendShop() {
		return shopGoodsDao.recommendShop();
	}

	public DataGrid getShopGoodsList(PageRequest<Map<String,Object>> pageRequest){
	    DataGrid data = new DataGrid();
	    try {
            Page page = shopGoodsDao.getShopGoodsList(pageRequest);
            data = DataGrid.pageToDataGrid(page);
	    } catch (Exception e) {
	    	throw new BusinessException("商品查询出错",e);
	    }
	    return data;
    }
	//商户查看自己的
	public DataGrid getShopShopGoodsList(PageRequest<Map<String,Object>> pageRequest){
	    DataGrid data = new DataGrid();
	    try {
            Page page = shopGoodsDao.getShopShopGoodsList(pageRequest);
            data = DataGrid.pageToDataGrid(page);
	    } catch (Exception e) {
	    	throw new BusinessException("商品查询出错",e);
	    }
	    return data;
    }
    /**
     * 添加商品信息
     * @param shopGoods
     * @return
     */
    public int insert(ShopGoods shopGoods) {
        return shopGoodsDao.insert(shopGoods);
    }

    /**
     * 删除信息
     * @param id
     * @return
     */
    public int deleteShopGoods(Long id) {
        return shopGoodsDao.deleteShopGoods(id);
    }

    /**
     * 通过id查询信息
     * @param id
     * @return
     */
    public Map<String , Object> selectByPrimaryKey(Long id) {
        return shopGoodsDao.selectByPrimaryKey(id);
    }

    /**
     * 修改商品信息
     * @param shopGoods
     * @return
     */
    public int updateByPrimaryKeySelective(ShopGoods shopGoods) {
        return shopGoodsDao.updateByPrimaryKeySelective(shopGoods);
    }

	public List<ShopGoods> getGoodsByInformationId(Long id) {
		// TODO Auto-generated method stub
		return shopGoodsDao.getGoodsByInformationId(id);
	}
}
