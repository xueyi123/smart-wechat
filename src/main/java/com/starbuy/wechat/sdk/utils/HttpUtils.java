package com.starbuy.wechat.sdk.utils;

import com.starbuy.wechat.sdk.api.MediaFile;
import com.squareup.okhttp.*;
import org.apache.commons.lang3.StringUtils;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public final class HttpUtils {
	static final MediaType CONTENT_TYPE_FORM = MediaType.parse("application/x-www-form-urlencoded");
	static Lock lock = new ReentrantLock();
	static OkHttpClient httpClient = new OkHttpClient();

	public static String get(String url) {
		return HttpRequest.sendGet(url);
	}
	
	public static String get(String url, Map<String, String> queryParas) {
		Map<String,Object> map = new HashMap<String, Object>();
		for (String key:queryParas.keySet()) {
			map.put(key,queryParas.get(key));
		}
		return HttpRequest.sendGet(url,  map);
	}
	public static String post(String url, String data) {
		return HttpRequest.sendPost(url, data);
	}
	public static String post(String url, Object data) {
		return HttpRequest.sendPost(url, data);
	}

	public static String upload(String url, File file, String params) {
		RequestBody fileBody = RequestBody.create(CONTENT_TYPE_FORM, file);
		MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM).addFormDataPart("media", file.getName(), fileBody);
		if (!StringUtils.isBlank(params)) {
			builder.addFormDataPart("description", params);
		}
		RequestBody requestBody = builder.build();
		Request request = new Request.Builder()
				.url(url)
				.post(requestBody)
				.build();
		try {
			com.squareup.okhttp.Response response = httpClient.newCall(request).execute();

			if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);

			return response.body().string();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

}

	public static String postSSL(String url, String data, String certPath, String certPass) {
		MediaType CONTENT_TYPE_FORM = MediaType.parse("application/x-www-form-urlencoded");
		RequestBody body = RequestBody.create(CONTENT_TYPE_FORM, data);
		Request request = new Request.Builder().url(url).post(body).build();
		InputStream inputStream = null;
		try {
			// 移动到最开始，certPath io异常unlock会报错
			lock.lock();
			KeyStore clientStore = KeyStore.getInstance("PKCS12");
			inputStream = new FileInputStream(certPath);
			clientStore.load(inputStream, certPass.toCharArray());

			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(clientStore, certPass.toCharArray());
			KeyManager[] kms = kmf.getKeyManagers();
			SSLContext sslContext = SSLContext.getInstance("TLSv1");

			sslContext.init(kms, null, new SecureRandom());
			httpClient.setSslSocketFactory(sslContext.getSocketFactory());
			com.squareup.okhttp.Response response = httpClient.newCall(request).execute();
			if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
			return response.body().string();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			IOUtils.closeQuietly(inputStream);
			lock.unlock();
		}
	}
	public static MediaFile download(String url) {
		com.squareup.okhttp.Request request = new com.squareup.okhttp.Request.Builder().url(url).get().build();
		try {
			com.squareup.okhttp.Response response = httpClient.newCall(request).execute();
			if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
			com.squareup.okhttp.ResponseBody body = response.body();
			com.squareup.okhttp.MediaType mediaType = body.contentType();
			MediaFile mediaFile = new MediaFile();
			if (mediaType.type().equals("text")) {
				mediaFile.setError(body.string());
			} else {
				BufferedInputStream bis = new BufferedInputStream(body.byteStream());
				String ds = response.header("Content-disposition");
				String fullName = ds.substring(ds.indexOf("filename=\"") + 10, ds.length() - 1);
				String relName = fullName.substring(0, fullName.lastIndexOf("."));
				String suffix = fullName.substring(relName.length()+1);
				mediaFile.setFullName(fullName);
				mediaFile.setFileName(relName);
				mediaFile.setSuffix(suffix);
				mediaFile.setContentLength(body.contentLength() + "");
				mediaFile.setContentType(body.contentType().toString());
				mediaFile.setFileStream(bis);
			}
			return mediaFile;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static InputStream download(String url, String params) {
		com.squareup.okhttp.Request request;
		if (!StringUtils.isBlank(params)) {
			com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(CONTENT_TYPE_FORM, params);
			request = new com.squareup.okhttp.Request.Builder().url(url).post(body).build();
		} else {
			request = new com.squareup.okhttp.Request.Builder().url(url).get().build();
		}
		try {
			com.squareup.okhttp.Response response = httpClient.newCall(request).execute();
			if (!response.isSuccessful()) throw new RuntimeException("Unexpected code " + response);
			return response.body().byteStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
