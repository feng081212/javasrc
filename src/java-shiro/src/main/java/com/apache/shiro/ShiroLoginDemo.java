package com.apache.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

import com.log.LogFactory;

public class ShiroLoginDemo {

	public static void main(String[] args) {
		/** 获取安全管理器 */
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini") ;
		SecurityManager securityManager = factory.getInstance() ;
		/** 设置安全管理器 */
		SecurityUtils.setSecurityManager(securityManager);
		/** 获取Subject，即将登陆的用户 */
		Subject currentUser = SecurityUtils.getSubject() ;
		/** 获取Session */
		Session session = currentUser.getSession() ;
		session.setAttribute("name", "姓名");
		LogFactory.info("获取Session中Key为name的值：" + session.getAttribute("name"));
		/** 用户是否登录 */
		if(!currentUser.isAuthenticated()){
			UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123") ;
			try{
				currentUser.login(token);
				LogFactory.info("用户登录成功");
			}catch(UnknownAccountException e){
				LogFactory.info("账户不存在");
			}catch(IncorrectCredentialsException e){
				LogFactory.info("密码错误");
			}catch(LockedAccountException e){
				LogFactory.info("用户已经锁死");
			}catch(AuthenticationException e){
				LogFactory.info("认证异常");
			}
		}
		
		/** 用户是否有指定的角色 */
		if(currentUser.hasRole("role1")){
			LogFactory.info("用户拥有指定的角色");
		} else {
			LogFactory.info("用户不拥有指定的角色");
		}
		
		/** 用户是否有指定的权限 */
		if(currentUser.isPermitted("role1")){
			LogFactory.info("用户拥有指定的权限");
		} else {
			LogFactory.info("用户不拥有指定的权限");
		}
		
		/** 用户退出 */
		currentUser.logout();
	}
}
