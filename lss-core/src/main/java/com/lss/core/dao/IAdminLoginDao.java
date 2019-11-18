package com.lss.core.dao;

import java.util.List;

import com.lss.core.dto.FindAdminLoginPage;
import com.lss.core.dto.AdminLoginDto;
import com.lss.core.pojo.AdminLogin;

public interface IAdminLoginDao {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminLogin record);

    int insertSelective(AdminLogin record);

    AdminLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdminLogin record);

    int updateByPrimaryKey(AdminLogin record);
    
	List<AdminLoginDto> findAdminLogins(FindAdminLoginPage findAdminLoginPage);

	List<AdminLoginDto> findAdminLoginPage(FindAdminLoginPage findAdminLoginPage);

	int findAdminLoginPageCount(FindAdminLoginPage findAdminLoginPage);
}