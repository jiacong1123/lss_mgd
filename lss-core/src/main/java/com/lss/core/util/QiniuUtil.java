/**
 * 
 */
package com.lss.core.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.lss.core.constant.SystemConstant;
import com.lss.core.exception.UploadException;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 杨恩科技有限公司
 * @author 刘红艳
 *   
 * CreateDate:  2019年5月7日
 */
public class QiniuUtil{

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private String bucketHostName;

    private String bucketName;

    private Auth auth;

    private UploadManager uploadManager = new UploadManager();
    
    private static QiniuUtil instant;
    /**
     * 构造函数
     *
     * @param bucketHostName 七牛域名
     * @param bucketName     七牛空间名
     * @param auth           七牛授权
     */
    private QiniuUtil(String bucketHostName, String bucketName, Auth auth) {
        this.bucketHostName = bucketHostName;
        this.bucketName = bucketName;
        this.auth = auth;
    }


	private static QiniuUtil createUpload(String accessKey, String secretKeySpec, String bucketHostName,
			String bucketName) {
		if (instant == null) {
			Auth auth = Auth.create(accessKey, secretKeySpec);
			instant = new QiniuUtil(bucketHostName, bucketName, auth);
		}
		return instant;
	}

	public static QiniuUtil getInstant() {
		String bucketHostName = SystemConstant.qiniuBucketHostName;
		String bucketName = SystemConstant.qiniuBucket;// 空间名
        return QiniuUtil.createUpload(SystemConstant.qiniuAccessKey, SystemConstant.qiniuSecretKey,
                bucketHostName, bucketName); 
	}

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param multipartFile spring mvc 文件接口
     * @return 文件路径
     * @throws IOException
     */
    
    public String uploadFile(MultipartFile multipartFile) throws UploadException {
        byte[] bytes = getBytesWithMultipartFile(multipartFile);
        return this.uploadFile(bytes);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param filePath      文件前缀,例如:/test或者/test/
     * @param multipartFile spring mvc 文件接口
     * @return 文件路径
     * @throws IOException
     */
    
    public String uploadFile(String filePath, MultipartFile multipartFile) throws UploadException {
        byte[] bytes = getBytesWithMultipartFile(multipartFile);
        return this.uploadFile(filePath, bytes);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param multipartFile spring mvc 文件接口
     * @param fileName      文件名
     * @return 文件路径
     * @throws IOException
     */
    
    public String uploadFile(MultipartFile multipartFile, String fileName) throws UploadException {
        byte[] bytes = getBytesWithMultipartFile(multipartFile);
        return this.uploadFile(bytes, fileName);
    }


    /**
     * 根据spring mvc 文件接口上传
     *
     * @param multipartFile spring mvc 文件接口
     * @param fileName      文件名
     * @param filePath      文件前缀,例如:/test或者/test/
     * @return 文件路径
     * @throws IOException
     */
    
    public String uploadFile(MultipartFile multipartFile, String fileName, String filePath) throws UploadException {
        byte[] bytes = getBytesWithMultipartFile(multipartFile);
        return this.uploadFile(bytes, fileName, filePath);
    }


    /**
     * 根据spring mvc 文件接口上传
     *
     * @param file 文件
     * @return 文件路径
     * @throws UploadException
     */
    
    public String uploadFile(File file) throws UploadException {
        return this.uploadFile(file, null, null);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param file     文件
     * @param filePath 文件前缀,例如:/test或者/test/
     * @return 文件路径
     * @throws UploadException
     */
    
    public String uploadFile(String filePath, File file) throws UploadException {
        return this.uploadFile(file, null, filePath);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param file     文件
     * @param fileName 文件名
     * @return 文件路径
     * @throws UploadException
     */
    
    public String uploadFile(File file, String fileName) throws UploadException {
        return this.uploadFile(file, fileName, null);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param file     文件
     * @param fileName 文件名
     * @param filePath 文件前缀,例如:/test或者/test/
     * @return 文件路径
     * @throws UploadException
     */
    
    public String uploadFile(File file, String fileName, String filePath) throws UploadException {
        String key = preHandle(fileName, filePath);
        Response response = null;
        try {
            response = this.uploadManager.put(file, key, this.generateToken());
        } catch (QiniuException e) {
        	Response r = e.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        	e.printStackTrace();
            LOGGER.warn("QiniuException:", e);
            throw new UploadException(e.getMessage());
        }
        return this.getUrlPath(response);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param data 文件
     * @return 文件路径
     * @throws UploadException
     */
    
    public String uploadFile(byte[] data) throws UploadException {
        return this.uploadFile(data, null, null);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param data     文件
     * @param filePath 文件前缀,例如:/test或者/test/
     * @return 文件路径
     * @throws UploadException
     */
    
    public String uploadFile(String filePath, byte[] data) throws UploadException {
        return this.uploadFile(data, null, filePath);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param data     文件
     * @param fileName 文件名
     * @return 文件路径
     * @throws UploadException
     */
    
    public String uploadFile(byte[] data, String fileName) throws UploadException {
        return this.uploadFile(data, fileName, null);
    }

    /**
     * 根据spring mvc 文件接口上传
     *
     * @param data     文件
     * @param fileName 文件名
     * @param filePath 文件前缀,例如:/test或者/test/
     * @return 文件路径
     * @throws UploadException
     */
    
    public String uploadFile(byte[] data, String fileName, String filePath) throws UploadException {
        String key = preHandle(fileName, filePath);
        Response response;
        try {
            response = this.uploadManager.put(data, key, generateToken());
        } catch (QiniuException e) {
            LOGGER.error("QiniuException:", e);
            throw new UploadException(e.getMessage(),e);
        }
        return this.getUrlPath(response);
    }

    private byte[] getBytesWithMultipartFile(MultipartFile multipartFile) {
        try {
            return multipartFile.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String preHandle(String fileName, String filePath) throws UploadException {
        if (StringUtils.isNotBlank(fileName) && !fileName.contains(".")) {
            throw new UploadException("文件名必须包含尾缀");
        }
        if (StringUtils.isNotBlank(filePath) && !filePath.startsWith("/")) {
            throw new UploadException("前缀必须以'/'开头");
        }
        String name = StringUtils.isBlank(fileName) ? RandomStringUtils.randomAlphanumeric(32) : fileName;
        if (StringUtils.isBlank(filePath)) {
            return name;
        }
        String prefix = filePath.replaceFirst("/", "");
        return (prefix.endsWith("/") ? prefix : prefix.concat("/")).concat(name);
    }

    private String generateToken() {
        return this.auth.uploadToken(bucketName);
    }


    private String getUrlPath(Response response) throws UploadException {
        if (!response.isOK()) {
            throw new UploadException("文件上传失败");
        }
        DefaultPutRet defaultPutRet;
        try {
            defaultPutRet = response.jsonToObject(DefaultPutRet.class);
        } catch (QiniuException e) {
            LOGGER.warn("QiniuException", e);
            throw new UploadException(e.getMessage());
        }
        String key = defaultPutRet.key;
        if (key.startsWith(bucketHostName)) {
            return key;
        }
        return bucketHostName + (key.startsWith("/") ? key : "/" + key);
    }
    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public String  uploadFromUrl(String urlStr,String fileName,String filePath){
        URL url=null;
        InputStream inputStream =null;
		try {
			url = new URL(urlStr);
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
	        //设置超时间为3秒
	        conn.setConnectTimeout(3*1000);
	        //防止屏蔽程序抓取而返回403错误
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	
	        //得到输入流
	        inputStream = conn.getInputStream();  
	        //获取自己数组
	        byte[] getData = readInputStream(inputStream);    
	        //上传到七牛
	        return uploadFile(getData, fileName, filePath);
		} catch (IOException e) {
			  LOGGER.error("IOException", e);
	          throw new UploadException(e.getMessage(),e);
		}finally {
			 if(inputStream!=null){
		            try {
						inputStream.close();
					} catch (IOException e) {
						 LOGGER.error("IOException", e);
					}
		      }
		}
    }

    public String uploadFromUrl(String url) {
    	return uploadFromUrl(url, null, null);
    }


    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    private  byte[] readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        return bos.toByteArray();  
    }  

 
    public static void main(String[] args) {
		String url = QiniuUtil.getInstant().uploadFile(new File("C:/workspace/zhixing.png"));
		System.out.println(url);
		url = QiniuUtil.getInstant().uploadFile(new File("C:/workspace/zhixing.png"));
		System.out.println(url);
		url = QiniuUtil.getInstant().uploadFromUrl("http://images.lesasa.com/FteBs9E6OeymMOwOs2fN1xuG0c7t");
		System.out.println(url);
		
	}
    
}

 