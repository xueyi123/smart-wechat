/**
 * Copyright (c) 2011-2014, James Zhan 詹波 (jfinal@126.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.starbuy.wechat.sdk.msg.in;

/**
 * <pre>
	接收视频消息
 &lt;xml&gt;
 &lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
 &lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
 &lt;CreateTime&gt;1357290913&lt;/CreateTime&gt;
 &lt;MsgType&gt;&lt;![CDATA[video]]&gt;&lt;/MsgType&gt;
 &lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;
 &lt;ThumbMediaId&gt;&lt;![CDATA[thumb_media_id]]&gt;&lt;/ThumbMediaId&gt;
 &lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;
 &lt;/xml&gt;
 </pre>
*/
public class InVideoMsg extends InMsg {
	
	private String mediaId;
	private String thumbMediaId;
	private String msgId;
	
	public InVideoMsg(String toUserName, String fromUserName, Integer createTime, String msgType) {
		super(toUserName, fromUserName, createTime, msgType);
	}
	
	public String getMediaId() {
		return mediaId;
	}
	
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}



