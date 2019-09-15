/**
 * https://www.jianshu.com/p/0b1131be7ace 参考
 */

package com.cwg.bootstrap.system.config;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cwg.bootstrap.system.auth.JwtAuthFilter;
import com.cwg.bootstrap.system.auth.JwtCredentialsMatcher;
import com.cwg.bootstrap.system.auth.JwtRealm;
import com.cwg.bootstrap.system.auth.DBRealm;

@Configuration
public class ShiroConfig {
    
	@Bean
	public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(authenticator());
        // securityManager.setRealms(Arrays.asList(jwtShiroRealm(), dbShiroRealm()));
        return securityManager;
	}
	
	
	/**
     * 注册shiro的Filter，拦截请求
     */ 
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean(SecurityManager securityManager) throws Exception{
        FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean<Filter>();
        filterRegistration.setFilter((Filter)shiroFilter(securityManager).getObject());
        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
        filterRegistration.setAsyncSupported(true);
        filterRegistration.setEnabled(true);
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);

        return filterRegistration;
    }

	  /**
	     * 初始化Authenticator
	     */
    
    protected Authenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        //设置两个Realm，一个用于用户登录验证和访问权限获取；一个用于jwt token的认证
        authenticator.setRealms(Arrays.asList(jwtShiroRealm(), dbShiroRealm()));
        //设置多个realm认证策略，一个成功即跳过其它的
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }

    /**
    * 禁用session, 不保存用户登录状态。保证每次请求都重新认证。
    * 需要注意的是，如果用户代码里调用Subject.getSession()还是可以用session，如果要完全禁用，要配合下面的noSessionCreation的Filter来实现
    */
    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }
    /**
    * 用于用户名密码登录时认证的realm
    */
    @Bean("dbRealm")
    public Realm dbShiroRealm() {
        DBRealm myShiroRealm = new DBRealm();
        myShiroRealm.setAuthenticationCachingEnabled(false);
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }
    /**
    * 用于JWT token认证的realm
    */
    @Bean("jwtRealm")
    public Realm jwtShiroRealm() {
    	JwtRealm jwtRealm = new JwtRealm();
    	jwtRealm.setCredentialsMatcher(new JwtCredentialsMatcher());
        return jwtRealm;
    }

    /**
     * 设置过滤器，将自定义的Filter加入
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = factoryBean.getFilters();
        filterMap.put("authcToken", createAuthFilter());
        //filterMap.put("anyRole", createRolesFilter());
        factoryBean.setFilters(filterMap);
        factoryBean.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());

        return factoryBean;
    }
    
    @Bean
    protected ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // chainDefinition.addPathDefinition("/**", "anon");
        chainDefinition.addPathDefinition("/login", "noSessionCreation,anon");  //login不做认证，noSessionCreation的作用是用户在操作session时会抛异常
        chainDefinition.addPathDefinition("/logout", "noSessionCreation,authcToken[permissive]"); //做用户认证，permissive参数的作用是当token无效时也允许请求访问，不会返回鉴权未通过的错误
        chainDefinition.addPathDefinition("/static/**", "anon");
        chainDefinition.addPathDefinition("/**", "noSessionCreation,authcToken"); // 默认进行用户鉴权
        return chainDefinition;
    }

    protected JwtAuthFilter createAuthFilter(){
        return new JwtAuthFilter();
    }
    // 任意一个角色验证通过，则通过验证
//    protected AnyRolesAuthorizationFilter createRolesFilter(){
//        return new AnyRolesAuthorizationFilter();
//    }

    /**
	     * 密码校验规则HashedCredentialsMatcher
	     * 这个类是为了对密码进行编码的 ,
	     * 防止密码在数据库里明码保存 , 当然在登陆认证的时候 ,
	     * 这个类也负责对form里输入的密码进行编码
	     * 处理认证匹配处理器：如果自定义需要实现继承HashedCredentialsMatcher
	 */
	protected HashedCredentialsMatcher hashedCredentialsMatcher() {
	    HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
	    //指定加密方式为MD5
	    credentialsMatcher.setHashAlgorithmName("MD5");
	    //加密次数
	    credentialsMatcher.setHashIterations(2);
	    credentialsMatcher.setStoredCredentialsHexEncoded(true);
	    return credentialsMatcher;
	}

}