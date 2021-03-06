package com.htkj.cfdScenic.app.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.htkj.cfdScenic.app.model.PublicPlaces;
import com.htrj.common.base.BaseDao;
import com.htrj.common.page.Page;
import com.htrj.common.page.PageRequest;

@Repository
public class PublicPlacesDao extends BaseDao<PublicPlaces, Integer>{

	public List<Map<String, Object>> findAllPublicPlaces(Integer type) {
		return getSqlSession().selectList(getStatementName("findAllPublicPlaces"),type);
	}

	public Page checkPublicPlaces(PageRequest<Map<String, Object>> pageRequest) {
		return pageQuery(getStatementName("checkPublicPlaces"),pageRequest);
	}

	public void savePublicPlaces(PublicPlaces publicPlaces) {
		getSqlSession().insert(getStatementName("savePublicPlaces"),publicPlaces);
	}

	public void updatePublicPlaces(PublicPlaces publicPlaces) {
		getSqlSession().update(getStatementName("updatePublicPlaces"),publicPlaces);
	}

	public PublicPlaces selectPublicPlaces(Long id) {
		return getSqlSession().selectOne(getStatementName("selectPublicPlaces"),id);
	}

	public void deletePublicPlaces(Long id) {
		getSqlSession().delete(getStatementName("deletePublicPlaces"),id);
	}
	
}
