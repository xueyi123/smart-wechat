package com.iih5.wechat.sdk.api;

import com.iih5.wechat.sdk.cache.DefaultAccessTokenCache;
import com.iih5.wechat.sdk.cache.IAccessTokenCache;

public class ApiConfigKit {
	
	static ApiConfig result = null;
	public static void initApiConfig(ApiConfig apiConfig) {
		result = apiConfig;
	}
	public static ApiConfig getApiConfig() {
		if (result == null)
			throw new IllegalStateException("需要事先使用 ApiConfigKit.setThreadLocalApiConfig(apiConfig) 将 ApiConfig对象存入，才可以调用 ApiConfigKit.getApiConfig() 方法");
		return result;
	}
	
	static IAccessTokenCache accessTokenCache = new DefaultAccessTokenCache();
	
	public static void setAccessTokenCache(IAccessTokenCache accessTokenCache) {
		ApiConfigKit.accessTokenCache = accessTokenCache;
	}
	
	public static IAccessTokenCache getAccessTokenCache() {
		return ApiConfigKit.accessTokenCache;
	}

}