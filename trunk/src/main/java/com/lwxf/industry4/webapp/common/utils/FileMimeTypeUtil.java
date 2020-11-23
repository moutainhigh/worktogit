package com.lwxf.industry4.webapp.common.utils;

import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.exception.LwxfIOException;
import com.lwxf.commons.exception.RequestParamError;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.commons.utils.file.MimeTypeUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.FileMimeType;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.common.result.FileMimeType;

/**
 * 功能：文件的mime type的处理工具
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 13:10:51
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class FileMimeTypeUtil extends MimeTypeUtil {
	private static Configuration configuration;
	static {
		configuration = AppBeanInjector.configuration;
	}
	/**
	 * 处理文件的mime type
	 * 对于image类的mime，如果不在Configuration.UPLOAD_IMG_MIME_RANGE配置范围内的，则统一为"application/octet-stream"
	 * @param mimeType
	 * @return
	 */
	public static final String doFileMimeType(String mimeType){
		Assert.isTrue(LwxfStringUtils.isNotEmpty(mimeType),"mimeType为空");

		if(!mimeType.startsWith("image/")){
			return mimeType;
		}

		if(!isLegalImageType(mimeType)){
			return "application/octet-stream";
		}

		return mimeType;
	}

	public static final boolean isImageType(String mimeType) {
		Assert.isTrue(LwxfStringUtils.isNotEmpty(mimeType), "mimeType不能为null");
		return mimeType.toLowerCase().startsWith("image/");
	}

	public static final boolean isAvatarType(String avatar) {
		if (LwxfStringUtils.isEmpty(avatar)) {
			return false;
		}

		if (LwxfStringUtils.isEmpty(configuration.getUploadAvatarRange())) {
			throw new RequestParamError("未能找到配置的上传头像类型");
		}

		return isLegalFileMimeType(configuration.getUploadAvatarRange(),avatar);
	}

	public static final boolean isLegalImageType(String imageType) {
		if (LwxfStringUtils.isEmpty(imageType)) {
			return false;
		}

		if (LwxfStringUtils.isEmpty(configuration.getUploadImgMimeRange())) {
			throw new RequestParamError("未能找到合法的上传图片类型范围的配置");
		}

		return isLegalFileMimeType(configuration.getUploadImgMimeRange(),imageType);
	}
	//页面是否正确2
	public static final boolean isLegalHtmlType(String htmlType) {
		if (LwxfStringUtils.isEmpty(htmlType)) {
			return false;
		}

		if (LwxfStringUtils.isEmpty(configuration.getUploadHtmlMimeRange())) {
			throw new RequestParamError("未能找到合法的网页类型范围的配置");
		}

		return isLegalFileMimeType(configuration.getUploadHtmlMimeRange(),htmlType);
	}

	public static final boolean isLegalImageType(MultipartFile file) {
		FileMimeType fileMimeType = doFileMimeType(file);
		return isLegalImageType(fileMimeType.getOriginalType());
	}
	//判断页面是否格式正确
	public static final boolean isLegalHtmlType(MultipartFile file){
		FileMimeType fileMimeType = doFileMimeType(file);
		return isLegalHtmlType(fileMimeType.getOriginalType());
	}
	//判断APK是否格式正确
	public static final boolean isLegalApkType(String apkType){
		if (LwxfStringUtils.isEmpty(apkType)) {
			return false;
		}

		if (LwxfStringUtils.isEmpty(configuration.getUploadHtmlMimeRange())) {
			throw new RequestParamError("未能找到合法的APK类型范围的配置");
		}
		return isLegalFileMimeType(configuration.getUploadApkMimeRange(),apkType);
	}
	//判断APK是否格式正确
	public static final boolean isLegalApkType(MultipartFile file){
		FileMimeType fileMimeType = doFileMimeType(file);
		return isLegalApkType(fileMimeType.getOriginalType());
	}

	public static final boolean isLegalVideoFileType(MultipartFile file){
		FileMimeType fileMimeType = doFileMimeType(file);
		return isLegalVideoFileType(fileMimeType.getOriginalType());
	}

	public static FileMimeType doFileMimeType(MultipartFile mpf){
		String originalType;
		try {
			byte[] bytes= mpf.getBytes();
			originalType = findMagicMimeType(bytes).toString().toLowerCase();
		} catch (Exception e) {
			throw new LwxfIOException(e);
		}
		String realType = originalType;

		if(isImageType(originalType)){ // 图片类文件处理
			if(!isLegalImageType(originalType)){
				realType = "application/octet-stream";
			}
		}

		FileMimeType result = new FileMimeType();
		result.setOriginalType(originalType);
		result.setRealType(realType);
		return result;
	}

	public static final boolean isLegalFileMimeType(MultipartFile file) {
		FileMimeType fileMimeType = doFileMimeType(file);
		return isLegalFileMimeType(fileMimeType.getOriginalType());
	}
	/**
	 * 验证是否是合法的文件类型
	 * @param mimeType
	 * @return
	 */
	public static final boolean isLegalFileMimeType(String mimeType){
		return isLegalFileMimeType(configuration.getUploadFileMimeRange() , mimeType);
	}

	private static final boolean isLegalFileMimeType(String mimeRange,String mimeType){
		if(LwxfStringUtils.isBlank(mimeRange) || WebConstant.STRING_ALL.equals(mimeRange)){
			return true;
		}
		String mimeTypes = ",".concat(mimeRange).concat(",");
		String curMimeType = ",".concat(mimeType.toLowerCase()).concat(",");
		return mimeTypes.indexOf(curMimeType) >= 0;
	}
	/**
	 * 验证是否是合法的视频类型
	 * @param videoType
	 * @return
	 */

	public static final boolean isLegalVideoFileType(String videoType){
		if (LwxfStringUtils.isEmpty(videoType)) {
			return false;
		}
		if (LwxfStringUtils.isEmpty(configuration.getUploadVideoMimeRange())) {
			throw new RequestParamError("未能找到合法的上传视频类型范围的配置");
		}

		return isLegalFileMimeType(configuration.getUploadVideoMimeRange(),videoType);
	}

	public static boolean isLegalExcelWordType(MultipartFile multipartFile) {
		FileMimeType fileMimeType = doFileMimeType(multipartFile);
		return isLegalExcelWordType(fileMimeType.getOriginalType());
	}

	public static boolean isLegalExcelWordType(String excelWordType){
		if(LwxfStringUtils.isBlank(excelWordType)){

		}
		return false;
	}
}
