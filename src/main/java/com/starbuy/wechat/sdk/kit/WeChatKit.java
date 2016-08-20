/**
 * ---------------------------------------------------------------------------
 * 类名称   ：WeChatKit
 * 类描述   ：
 * 创建人   ： xue.yi
 * 创建时间： 2016/7/13 10:43
 * 版权拥有：银信网银科技
 * ---------------------------------------------------------------------------
 */
package com.starbuy.wechat.sdk.kit;

import com.starbuy.wechat.sdk.api.ApiConfig;
import com.starbuy.wechat.sdk.api.ApiConfigKit;

public class WeChatKit {

    public WeChatKit(String appId,String appSecret,String token){
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setAppId(appId);
        apiConfig.setAppSecret(appSecret);
        apiConfig.setToken(token);
        ApiConfigKit.initApiConfig(apiConfig);
    }

}
