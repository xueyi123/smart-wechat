/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.starbuy.wechat.sdk.kit;
import com.starbuy.wechat.sdk.api.ApiConfigKit;
import com.starbuy.wechat.sdk.encrypt.AesException;
import com.starbuy.wechat.sdk.encrypt.SHA1;

import java.util.Arrays;

/**
 * 测试用的账号：
 * appID = wx9803d1188fa5fbda
 * appsecret = db859c968763c582794e7c3d003c3d87
 * url = http://www.jfinal.com/weixin
 * token = __my__token__
 */
public class SignatureCheckKit {
	
	public static final SignatureCheckKit me = new SignatureCheckKit();

	/**
	 * @param signature 微信加密签名
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @return {boolean}
	 */
	public boolean checkSignature(String signature, String timestamp, String nonce) throws AesException {
		String TOKEN = ApiConfigKit.getApiConfig().getToken();
		String array[] = {TOKEN, timestamp, nonce};
		Arrays.sort(array);
		String tempStr = SHA1.getSHA1(TOKEN,timestamp,nonce,signature);
		return tempStr.equalsIgnoreCase(signature);
	}

}



