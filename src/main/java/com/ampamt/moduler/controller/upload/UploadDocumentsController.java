package com.ampamt.moduler.controller.upload;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ampamt.moduler.constant.ApplicationConstant;
import com.ampamt.moduler.constant.CommonConstant;
import com.ampamt.moduler.model.BusinessImageUploadModel;
import com.ampamt.moduler.model.DoctorsImageUploadModel;
import com.ampamt.moduler.model.EventUploadModel;
import com.ampamt.moduler.model.ImageSearchModel;
import com.ampamt.moduler.model.SuccessModel;

@RestController
@RequestMapping(ApplicationConstant.CONTEXT_PATH+"/upload")
public class UploadDocumentsController {

	private final Logger logger = LoggerFactory.getLogger(UploadDocumentsController.class);

	@Autowired
	private Environment environment;
	
//	@Autowired
//	private AccountService accountService;

	@RequestMapping(value = "/upload-doctor-image", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> uploadDocImage(@RequestBody DoctorsImageUploadModel doctorsImageUploadModel) {
		logger.debug("*******************start uploadDocImage***************************");
		SuccessModel successModel = new SuccessModel();
		String base64String = doctorsImageUploadModel.getImageBase64();
		String[] strings = base64String.split(",");

		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
		String filename = doctorsImageUploadModel.getImageName() + ".jpeg";
		String path = environment.getProperty("home.file.path") + "//DOCTOR//IMAGES//"+doctorsImageUploadModel.getUserId();
		String path2 = environment.getProperty("physical.file.image.storage.path") + "//DOCTOR//IMAGES//"+doctorsImageUploadModel.getUserId();

		File file = new File(path);
		
		if (!file.exists()) {
			logger.debug("making directory");
			file.mkdirs();
			file = new File(path + "//" + filename);
		} else {
			file = new File(path + "//" + filename);
		}
		
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
			outputStream.write(data);
			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
		} catch (IOException e) {
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();
		}
		
		File file2 = new File(path2);
		if (!file2.exists()) {
			logger.debug("making directory");
			file2.mkdirs();
			file2 = new File(path2 + "//" + filename);
		} else {
			file2 = new File(path2 + "//" + filename);
		}

		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file2))) {
			outputStream.write(data);
			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
		} catch (IOException e) {
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();
		}

		logger.debug("*******************end uploadDocImage***************************");

		return new ResponseEntity<>(successModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/upload-business-image", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> uploadBusinessImage(@RequestBody BusinessImageUploadModel businessImageUploadModel) {
		logger.debug("*******************start uploadBusinessImage***************************");
		SuccessModel successModel = new SuccessModel();
		String base64String = businessImageUploadModel.getImageBase64();
		String[] strings = base64String.split(",");

		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
		String filename = businessImageUploadModel.getImageName() + ".jpeg";
		String path = environment.getProperty("home.file.path") + "//BUSINESS//IMAGES//"+businessImageUploadModel.getUserId();
		String path2 = environment.getProperty("physical.file.image.storage.path") + "//BUSINESS//IMAGES//"+businessImageUploadModel.getUserId();

		File file = new File(path);
		
		if (!file.exists()) {
			logger.debug("making directory");
			file.mkdirs();
			file = new File(path + "//" + filename);
		} else {
			file = new File(path + "//" + filename);
		}
		
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
			outputStream.write(data);
			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
		} catch (IOException e) {
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();
		}
		
		File file2 = new File(path2);
		if (!file2.exists()) {
			logger.debug("making directory");
			file2.mkdirs();
			file2 = new File(path2 + "//" + filename);
		} else {
			file2 = new File(path2 + "//" + filename);
		}
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file2))) {
			outputStream.write(data);
			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
		} catch (IOException e) {
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();
		}

		logger.debug("*******************end uploadBusinessImage***************************");

		return new ResponseEntity<>(successModel, HttpStatus.OK);
	}
	@RequestMapping(value = "/get-doctor-image", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> getDoctorImage(@RequestBody BusinessImageUploadModel businessImageUploadModel) {
		logger.debug("*******************start getDoctorImage***************************");
		SuccessModel successModel = new SuccessModel();
//		AES.setKey("");
//		AES.decrypt(businessImageUploadModel.getUserId());
	
		String userId=businessImageUploadModel.getUserId();
		String base64String = businessImageUploadModel.getImageBase64();
		String[] strings = base64String.split(",");

		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
		String filename = businessImageUploadModel.getImageName() + ".jpeg";
		String path = environment.getProperty("home.file.path") + "//BUSINESS//IMAGES//"+userId;
		String path2 = environment.getProperty("physical.file.image.storage.path") + "//BUSINESS//IMAGES//"+userId;

		File file = new File(path);
		
		if (!file.exists()) {
			logger.debug("making directory");
			file.mkdirs();
			file = new File(path + "//" + filename);
		} else {
			file = new File(path + "//" + filename);
		}
		
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
			outputStream.write(data);
			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
		} catch (IOException e) {
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();
		}
		
		File file2 = new File(path2);
		if (!file2.exists()) {
			logger.debug("making directory");
			file2.mkdirs();
			file2 = new File(path2 + "//" + filename);
		} else {
			file2 = new File(path2 + "//" + filename);
		}
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file2))) {
			outputStream.write(data);
			successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
		} catch (IOException e) {
			successModel.setStatus(ApplicationConstant.STATUS_FAILED);
			e.printStackTrace();
		}

		logger.debug("*******************end getDoctorImage***************************");

		return new ResponseEntity<>(successModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/upload-event-image", method = RequestMethod.POST)
	public ResponseEntity<SuccessModel> uploadEventImage(@RequestBody EventUploadModel eventUploadModel) {
		logger.debug("*******************start uploadEventImage***************************");
//		AES.setKey("");
//		AES.decrypt(eventUploadModel.getAccountId());
		eventUploadModel.setAccountId(eventUploadModel.getAccountId());
		SuccessModel successModel = new SuccessModel();
		String base64String = eventUploadModel.getEventImgBase64();
		String[] strings = base64String.split(",");

		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
		String filename = eventUploadModel.getEventImgName() + ".jpeg";
		String path = environment.getProperty("home.file.path") + "//EVENTS_IMAGES//"+eventUploadModel.getAccountId();
		String path2 = environment.getProperty("physical.file.image.storage.path") + "//EVENTS_IMAGES//"+eventUploadModel.getAccountId();
		
		if(path!=null)
		{
			
		
			File file = new File(path);
			
			if (!file.exists()) {
				logger.debug("making directory");
				file.mkdirs();
				file = new File(path + "//" + filename);
			} else {
				file = new File(path + "//" + filename);
			}
			
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
				outputStream.write(data);
				successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
			} catch (IOException e) {
				successModel.setStatus(ApplicationConstant.STATUS_FAILED);
				e.printStackTrace();
			}
		}
		if(path2!=null) 
		{
			
			File file2 = new File(path2);
			if (!file2.exists()) {
				logger.debug("making directory");
				file2.mkdirs();
				file2 = new File(path2 + "//" + filename);
			} else {
				file2 = new File(path2 + "//" + filename);
			}
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file2))) {
				outputStream.write(data);
				successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
			} catch (IOException e) {
				successModel.setStatus(ApplicationConstant.STATUS_FAILED);
				e.printStackTrace();
			}
		}
		logger.debug("*******************end uploadEventImage***************************");

		return new ResponseEntity<>(successModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/upload-image", method = RequestMethod.POST)
	public SuccessModel uploadImage(boolean pathFlag,boolean path2Flag,String userId,String imageName,String imagebase64,String folderName,String path,String path2) {
		
		logger.debug("*******************start uploadImage***************************");
		
		logger.debug("path2Flag="+path2Flag+" pathFlag="+pathFlag+" userId="+userId+" imageName"+imageName+" imagebase64="+imagebase64);
		
		logger.debug(" folderName="+folderName+" path="+path+" path2="+path2);
		SuccessModel successModel = new SuccessModel();
		String base64String =imagebase64;
		String[] strings = base64String.split(",");

		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
		String filename = imageName + ".jpeg";
//		String pathStr1= null;
//		String pathStr2= null;
//		if(pathFlag) {
//			pathStr1 = path;
//		}
//		if(path2Flag) {
//			pathStr2 = path2;
//		}
		
//		logger.debug("pathStr1="+pathStr1+" pathStr2="+pathStr2);
		if(pathFlag && path!=null)
		{
			
		
			File file = new File(path);
			
			if (!file.exists()) {
				logger.debug("making directory");
				file.mkdirs();
				file = new File(path + "//" + filename);
			} else {
				file = new File(path + "//" + filename);
			}
			
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
				outputStream.write(data);
				successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
			} catch (IOException e) {
				successModel.setStatus(ApplicationConstant.STATUS_FAILED);
				e.printStackTrace();
			}
		}
		if(path2Flag && path2!=null) 
		{
			
			File file2 = new File(path2);
			if (!file2.exists()) {
				logger.debug("making directory");
				file2.mkdirs();
				file2 = new File(path2 + "//" + filename);
			} else {
				file2 = new File(path2 + "//" + filename);
			}
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file2))) {
				outputStream.write(data);
				successModel.setStatus(ApplicationConstant.STATUS_SUCCESS);
			} catch (IOException e) {
				successModel.setStatus(ApplicationConstant.STATUS_FAILED);
				e.printStackTrace();
			}
		}
		logger.debug("*******************end uploadImage***************************");

		return successModel;
	}
	
	public String getImages(String imagePath) throws IOException {
		logger.debug("*******************start getEventImages***************************");
			String base64Base=null;
		
			logger.debug("imagePath="+imagePath);

			File file = new File(imagePath);
			logger.debug("file="+file);
			if(file.exists()) {
				logger.debug("file exists");
				FileInputStream fileInputStreamReader = new FileInputStream(file);
				
				byte[] bytes = new byte[(int) file.length()];
                fileInputStreamReader.read(bytes);
                base64Base=CommonConstant.START_OF_IMAGE_BASE64+DatatypeConverter.printBase64Binary(bytes);
			}else {
				logger.debug("file does not exists");
			}

	
		
		logger.debug("*******************end getEventImages***************************");

		return base64Base;
	}
	
	@RequestMapping(value = "/get-user-images", method = RequestMethod.POST)
	public ResponseEntity<String> getUserImages(@RequestBody ImageSearchModel imageSearchModel) throws IOException {
		logger.debug("*******************start getUserImages***************************");
			String base64Base=null;
			String userId=imageSearchModel.getUserId();
			
			String folder="";
			if(imageSearchModel.getUserType() !=null && ApplicationConstant.ACCOUNT_BUSINESS.equalsIgnoreCase(imageSearchModel.getUserType())) {
				folder="BUSINESS//IMAGES";
			}
			
			if(imageSearchModel.getUserType() !=null && ApplicationConstant.ACCOUNT_DOCTOR.equalsIgnoreCase(imageSearchModel.getUserType())) {
				folder="DOCTOR//IMAGES";
			}
			
			if(imageSearchModel.getUserType() !=null && "EVENTS".equalsIgnoreCase(imageSearchModel.getUserType())) {
				folder="EVENTS_IMAGES";
			}
			String path = environment.getProperty("home.file.path") + "//"+folder+"//"+userId+"//"+imageSearchModel.getImageName();
			File file = new File(path);
			logger.debug("file="+file);
			FileInputStream fileInputStreamReader= new FileInputStream(file);
			try {
				if(file.exists()) {
					logger.debug("file exists");
					
					byte[] bytes = new byte[(int) file.length()];
	                fileInputStreamReader.read(bytes);
	                base64Base=CommonConstant.START_OF_IMAGE_BASE64+DatatypeConverter.printBase64Binary(bytes);
				}else {
					logger.debug("file does not exists");
				}
			}
			catch(Exception e) 
			{
				logger.debug("Exception=>",e);
			}
			finally 
			{
				fileInputStreamReader.close();
			}
		
		logger.debug("*******************end getUserImages***************************");

		return new ResponseEntity<>(base64Base,HttpStatus.OK);
	}
}
