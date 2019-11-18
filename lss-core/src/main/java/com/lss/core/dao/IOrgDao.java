package com.lss.core.dao;

import java.util.List;

import com.lss.core.dto.OrgDto;
import com.lss.core.dto.FindOrgPage;
import com.lss.core.pojo.Org;

public interface IOrgDao {
	int deleteByPrimaryKey(Integer id);

	int insert(Org record);

	int insertSelective(Org record);

	Org selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Org record);

	int updateByPrimaryKey(Org record);

	List<OrgDto> findOrgs(FindOrgPage findOrgPage);

	List<OrgDto> findOrgPage(FindOrgPage findOrgPage);

	int findOrgPageCount(FindOrgPage findOrgPage);
}