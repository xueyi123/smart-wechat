package com.iih5.wechat.sdk.api;

import com.iih5.wechat.sdk.utils.HttpUtils;

/**
 * 获取自动回复规则
 * @author L.cm
 * email: 596392912@qq.com
 * site:http://www.dreamlu.net
 * date: 2016年2月15日 下午10:08:50
 */
public class AutoReplyInfoApi {

	private static String getCurrentAutoreplyInfoUrl = "https://api.weixin.qq.com/cgi-bin/get_current_autoreply_info?access_token=";
	
	public static ApiResult getCurrent() {
		String jsonResult = HttpUtils.get(getCurrentAutoreplyInfoUrl + AccessTokenApi.getAccessTokenStr());
		return new ApiResult(jsonResult);
	}
	
}