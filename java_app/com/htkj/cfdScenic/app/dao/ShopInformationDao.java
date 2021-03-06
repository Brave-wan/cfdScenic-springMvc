package com.htkj.cfdScenic.app.dao;

import com.htkj.cfdScenic.app.model.ShopInformation;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ShopInformationDao extends BaseDao<ShopInformation, Integer>{
	
	/**
	 * 根据关键字模糊查询
	 */
	public List<Map<String,String>> linkSelect(String keyWord){
		return getSqlSession().selectList(getStatementName("linkSelect"),keyWord);
	}
	/**
	 * 按照分类Id查询店铺
	 * 
	 */
	public List<Map<String, Object>> selectByShopId() {
		return getSqlSession().selectList(getStatementName("selectByShopId"));
	}
	/**
	 * 根据id查询店铺信息
	 */
	
	public void insertShopInformation(ShopInformation shopInformation)
	{
		getSqlSession().insert(getStatementName("insertShopInformation"),shopInformation);
	}
	
	public void updateInformation(ShopInformation shopInformation)
	{
		getSqlSession().update(getStatementName("updateInformation"),shopInformation);
	}
	
	
	
	public ShopInformation findDetailInformationByID(Long id )
	{
		return getSqlSession().selectOne(getStatementName("findInformationDetailById"),id);
	}
	
	public Map selectById(Long id) {
		return getSqlSession().selectOne(getStatementName("selectById"),id);
	}
	public List<Map<String, Object>> selectShopInformation(Long shopId) {
		return getSqlSession().selectList(getStatementName("selectShopInformation"),shopId);
	}
	public Page selectShopInformation(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("selectShopInformation"),pageRequest);
	}
	public Page findHotel(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findHotel"),pageRequest);
	}
	public Page findHotelPage(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findHotelPage"),pageRequest);
	}
	public Page findAllRestaurant(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findAllRestaurant"),pageRequest);
	}
	public Page findAllRestaurantPage(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findAllRestaurantPage"),pageRequest);
	}
	public Map findAllRestaurantDetail(Long id) {
		return getSqlSession().selectOne(getStatementName("selectById"),id);
	}
	public Page findAllSnack(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("findAllSnack"),pageRequest);
	}
	public List<Map<String,Object>> findAllSnackPage(Map map) {
		return getSqlSession().selectList(getStatementName("findAllSnackPage"),map);
	}
	public Map<String, Object> storeMessage(Map<String, Object> map) {
		return getSqlSession().selectOne(getStatementName("storeMessage"),map);
	}
	public List<Map<String,Object>> selectShopInformationPage(Map parameter) {
		return getSqlSession().selectList(getStatementName("selectShopInformationPage"),parameter);
	}
	public List<Map<String, Object>> findAllInformation(Integer type) {
		return getSqlSession().selectList(getStatementName("findAllInformation"),type);
	}

	public ShopInformation selectAllShopInformation(Long id){
	    return getSqlSession().selectOne(getStatementName("selectAllShopInformation"),id);
    }

    public Page getShopInformationList(PageRequest<Map<String, Object>> pageRequest) {
        return pageQuery(getStatementName("getShopInformationList"),pageRequest);
    }
    public Page getShopList(PageRequest<Map<String, Object>> pageRequest) {
        return pageQuery(getStatementName("getShopList"),pageRequest);
    }
    public int insert(ShopInformation shopInformation) {
        return getSqlSession().insert(getStatementName("insertSelective"),shopInformation);
    }

    public int updateByPrimaryKeySelective(ShopInformation shopInformation) {
        return getSqlSession().update(getStatementName("updateSelective"),shopInformation);
    }

    public Map<String, Object> selectByPrimaryKey(Long id) {
        return getSqlSession().selectOne(getStatementName("selectByPrimaryKey"),id);
    }

    public int deleteByPrimaryKey(Long id) {
        return getSqlSession().delete(getStatementName("deleteByPrimaryKey"),id);
    }
    public ShopInformation selectByShopUserPrimaryKey(Long id) {
        return getSqlSession().selectOne(getStatementName("selectByShopUserPrimaryKey"),id);
    }
    public void audit(ShopInformation shopInformation) {
        getSqlSession().update(getStatementName("updateSelective"),shopInformation);
    }
	public Integer getShopInformationIdTypeById(Long id) {
		return getSqlSession().selectOne(getStatementName("getShopInformationIdTypeById"),id);
	}
}
