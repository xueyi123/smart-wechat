/**
 * ---------------------------------------------------------------------------
 * 类名称   ：WeChatKit
 * 类描述   ：
 * 创建人   ： xue.yi
 * 创建时间： 2016/7/13 10:43
 * 版权拥有：银信网银科技
 * ---------------------------------------------------------------------------
 */
package com.iih5.wechat.sdk.kit;

import com.iih5.wechat.sdk.api.ApiConfig;
import com.iih5.wechat.sdk.api.ApiConfigKit;

public class WeChatKit {

    public WeChatKit(String appId,String appSecret,String token){
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setAppId(appId);
        apiConfig.setAppSecret(appSecret);
        apiConfig.setToken(token);
        ApiConfigKit.initApiConfig(apiConfig);
    }

}
