package com.xlinyu.web.dao;

import java.util.List;
import java.util.Set;

import com.xlinyu.web.domain.SysMenu;

public interface SysUserMapper {

	public Set<String> getRolesAsString(int userId) throws Exception ;
	
	public Set<String> getPermissionsAsString(int userId) throws Exception ;
	
	public List<SysMenu> findAllMenuByUserId(int userId);
	
}
