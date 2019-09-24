package com.github.wxpay.sdk;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MyConfig extends WXPayConfig {

	private byte[] certData;

	public MyConfig() throws Exception {
		// 不是沙箱环境要要下载证书，开出来
		//String certPath = "D:/cert/apiclient_cert.p12";
		String certPath = "/usr/local/cert/apiclient_cert.p12";
		File file = new File(certPath);
		InputStream certStream = new FileInputStream(file);
		this.certData = new byte[(int) file.length()];
		certStream.read(this.certData);
		certStream.close();
	}

	@Override
	String getAppID() {
		return "wxb88b44b094c7950e";
	}

	@Override
	InputStream getCertStream() {
		ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
		return certBis;
	}

	@Override
	String getKey() {
		return "QT6zG6MXSD2w5pYTOKCCnNnm9cuEuTTK";
	}

	@Override
	String getMchID() {
		return "1521763091";
	}

	@Override
	IWXPayDomain getWXPayDomain() {
		return WXPayDomainSimpleImpl.instance();
	}

}
