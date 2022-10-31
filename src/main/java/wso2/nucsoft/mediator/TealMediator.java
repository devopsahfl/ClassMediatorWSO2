package wso2.nucsoft.mediator;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.apache.commons.lang3.StringUtils;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class TealMediator extends AbstractMediator{

	
	
	public boolean mediate(MessageContext context) {
		
		if(!context.isResponse()) {
			String  requestJson = StringUtils.stripToEmpty((String) context.getProperty("payload"));
//			logger.info("Request Received in Class Mediator==============>"+requestJson);
//			System.out.println("Request Received in Class Mediator==============>"+requestJson);
			//context.setProperty("requestPayload", requestJson.toString());
			JSONObject inputPayload=null;
			String fileByteArrayString = null;
			JSONArray fileByteArray=null;
			inputPayload= new JSONObject(requestJson);

			String referenceNumber=inputPayload.getString("referenceNumber");
			String attachId=inputPayload.getString("attachId");
			fileByteArrayString=    inputPayload.getString("fileBytes");
			fileByteArray=new JSONArray(fileByteArrayString);
			System.out.println("Reference Number"+referenceNumber);
			System.out.println("AttachId"+attachId);
			System.out.println("fileByteArray"+fileByteArray);
			System.out.println("File Byte Array fileByteArray--"+fileByteArray);

			String filename=referenceNumber+"_"+attachId+".pdf";
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd"); 
			LocalDateTime now = LocalDateTime.now(); 
			System.out.println(dtf.format(now));
			Path path = Paths.get("E:\\Teal_Documents\\"+dtf.format(now)+"\\");
			try {
				
				Files.createDirectories(path);
				System.out.println("Directory is created!");
		 }catch (IOException e) {
			 
			 System.err.println("Failed to create directory!" + e.getMessage());
}
			try {
				FileOutputStream fos=new FileOutputStream("E:\\Teal_Documents\\"+dtf.format(now)+"\\"+filename);
				for(int i=0;i<fileByteArray.length();i++)
				{
					try {
						fos.write( (int) fileByteArray.get(i));
					} catch (JSONException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Encoder encoder =Base64.getEncoder();
			String encodedString=null;
			try {
				 encodedString=encoder.encodeToString(Files.readAllBytes(Paths.get("E:\\Teal_Documents\\"+dtf.format(now)+"\\"+filename)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONObject reponse=new JSONObject();
			reponse.put("fileName", filename);
			reponse.put("encodedFile",encodedString.toString());
			
			System.out.println("Encoded File-->"+reponse);
			context.setProperty("responsePayload", reponse.toString());
			

			return true;
		}
		
		else {
			return false;
		}
	
	}
	
	
	

}
