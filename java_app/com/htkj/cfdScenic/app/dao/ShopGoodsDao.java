package com.htkj.cfdScenic.app.dao;

import com.htkj.cfdScenic.app.model.ShopGoods;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ShopGoodsDao extends BaseDao<ShopGoods, Integer>{
	
	/**
	 * 根据关键字模糊查询
	 */
	public List<Map<String,String>> linkSelect(String keyWord){
		return getSqlSession().selectList(getStatementName("linkSelect"),keyWord);
	}
	/**
	 * 查询 为你推荐 列表
	 */
	public List<Map<String,Object>> selectRecommend(){
		return getSqlSession().selectList(getStatementName("selectRecommend"));
	}
	public Page selectShopGoodsByInformationId(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("selectShopGoodsByInformationId"),pageRequest);
	}
	/**
	 * 新分页查询
	 */
	public List<Map<String,Object>> selectShopGoodsByInformationIdPage(Map parameter) {
		return getSqlSession().selectList(getStatementName("selectShopGoodsByInformationIdPage"),parameter);
	}
	/**
	 * 通过商品Id获取商品
	 */
	public Map findDetailByGoodsId(Long goodsId) {
		return getSqlSession().selectOne(getStatementName("findDetailByGoodsId"),goodsId);
	}
	/**
	 * 通过商品Id和用户获取商品及关注状态
	 */
	public Map findDetailByGoodsIdAndUid(Map map) {
		return getSqlSession().selectOne(getStatementName("findDetailByGoodsIdAndUid"),map);
	}
	/**
	 * 通过店铺Id获取商品
	 */
	public List<Map<String,Object>> findHotelGoodsById(Long id) {
		return getSqlSession().selectList(getStatementName("findHotelGoodsById"),id);
	}
	public Map findHotelGoodsDetail(Long goodsId) {
		return getSqlSession().selectOne(getStatementName("findHotelGoodsDetail"),goodsId);
	}
	public int getstockNumber(Long shopGoodsId) {
		return getSqlSession().selectOne(getStatementName("getstockNumber"),shopGoodsId);
	}
	public List<Map<String, String>> selectMessage(String name) {
		return getSqlSession().selectList(getStatementName("selectMessage"),name);
	}
	public List<Map<String, String>> recommendShop() {
		return getSqlSession().selectList(getStatementName("recommendShop"));
	}
	/**
	 * 获取热门商品
	 */
	public List<Map> getHotGoods() {
		return getSqlSession().selectList(getStatementName("getHotGoods"));
	}
	/**
	 * 判断这个商品能不能预定
	 */
	public Integer getOrderNumberByGoodsId(Map paraMap) {
		return getSqlSession().selectOne(getStatementName("getOrderNumberByGoodsId"),paraMap);
	}
	public int getGoodsStock(Long id) {
		return getSqlSession().selectOne(getStatementName("getGoodsStock"),id);
	}
	public Map findPackageDetailByPackageId(Long packageId) {
		return getSqlSession().selectOne(getStatementName("findPackageDetailByPackageId"),packageId);
	}
	public List<Map<String,Object>> findRestaurantGoodsById(Long id) {
		return getSqlSession().selectList(getStatementName("findRestaurantGoodsById"),id);
	}

    /**
     * 后台页面列表
     * @param pageRequest
     * @return
     */
	public Page getShopGoodsList(PageRequest<Map<String,Object>> pageRequest){
	    return pageQuery(getStatementName("getShopGoodsList"),pageRequest);
    }
	 /**
     * 商户页面列表
     * @param pageRequest
     * @return
     */
	public Page getShopShopGoodsList(PageRequest<Map<String,Object>> pageRequest){
	    return pageQuery(getStatementName("getShopShopGoodsList"),pageRequest);
    }
    /**
     * 添加商品详情
     * @param shopGoods
     * @return
     */
    public int insert(ShopGoods shopGoods) {
        return getSqlSession().insert(getStatementName("insertSelective"),shopGoods);
    }

    /**
     * 删除商品信息
     * @param id
     * @return
     */
    public int deleteShopGoods(Long id) {
        return getSqlSession().delete(getStatementName("deleteShopGoods"),id);
    }

    /**
     * 通过id查询信息
     * @param id
     * @return
     */
    public Map<String , Object> selectByPrimaryKey(Long id) {
        return getSqlSession().selectOne(getStatementName("selectByPrimaryKey"),id);
    }

    /**
     * 修改商品信息
     * @param shopGoods
     * @return
     */
    public int updateByPrimaryKeySelective(ShopGoods shopGoods) {
        return getSqlSession().update(getStatementName("updateByPrimaryKeySelective"),shopGoods);
    }
	public List getShopGoodsByIds(Map sg) {
		return getSqlSession().selectList(getStatementName("getShopGoodsByIds"),sg);
	}
	public List<Map<String, Object>> searchGoodsByName(Map para) {
		return getSqlSession().selectList(getStatementName("searchGoodsByName"),para);
	}
	public Long getShopUserIdBySiId(Long siId) {
		return getSqlSession().selectOne(getStatementName("getShopUserIdBySiId"),siId);
	}
	public List getGoodsByInformationId(Long id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(getStatementName("getGoodsByInformationId"),id);
	}
}
