package com.xlinyu.web.shiro;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import com.xlinyu.web.constant.SystemConstant;
import com.xlinyu.web.dao.SysUserMapper;
import com.xlinyu.web.domain.SysUser;

@Component("shiroDbRealm")
public class ShiroDbRealm extends AuthorizingRealm {

	private static final Logger logger = Logger.getLogger(ShiroDbRealm.class);
	
	@Resource
	private SysUserMapper sysUserMapper;
	
	public ShiroDbRealm(){
		//访问量小的情况下  
		// 认证
		super.setAuthenticationCacheName(SystemConstant.AUTHENTICATIONCACHENAME);
		super.setAuthenticationCachingEnabled(false);	//如果为true密码会被缓存
		// 授权
		super.setAuthorizationCacheName(SystemConstant.AUTHORIZATIONCACHENAME);
		super.setAuthorizationCachingEnabled(false);
		// super.setAuthorizationCachingEnabled(false); //测试的时候先关闭缓存
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 因为非正常退出，即没有显式调用 SecurityUtils.getSubject().logout()
		// (可能是关闭浏览器，或超时)，但此时缓存依旧存在(principals)，所以会自己跑到授权方法里。
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			doClearCache(principalCollection);
			SecurityUtils.getSubject().logout();
			return null;
		}
		SysUser sysUser = (SysUser)principalCollection.getPrimaryPrincipal();
		if(sysUser == null){
			return null;
		}
		
		int userId = sysUser.getId();
		//添加角色及权限信息
		SimpleAuthorizationInfo sazi = new SimpleAuthorizationInfo();
		
		try {
			sazi.addRoles(sysUserMapper.getRolesAsString(userId)); //获取当前用户所有的角色,
			// //用于依据角色判断权限的shiro过滤器
			Set<String> sp = sysUserMapper.getPermissionsAsString(userId);
			sazi.addStringPermissions(sp); // 获取当前用户的所有权限,
			// System.out.println("我的权限:" + sp);
			// 权限就是url,一个url的集合
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return sazi;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		return null;
	}


}
