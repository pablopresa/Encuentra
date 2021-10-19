
import java.io.File;

import java.util.List;


import dataTypes.DataRowSincAnalizer;

import persistencia.MSSQL;

import java.io.FileInputStream;
import com.jcraft.jsch.*;

public class SincAnalitics 
{
	
	
	public static void main(String[] args) 
	{
		MSSQL ms = new MSSQL();
		
		
		List<DataRowSincAnalizer> rows = MSSQL.darRowsSincAnalizer("20160101","20160430");
		
		
		
		
			
		
		
		
		
		
		
	}
	
	
	public void upload(String fileToFTP) 
	{
		String SFTPHOST = "204.90.181.223";
		int    SFTPPORT = 22;
		String SFTPUSER = "5050406905121";
		String SFTPPASS = "Clarks321prod";
		String SFTPWORKINGDIR = "/5050406905121/5050406000000/SALESO";
		 
		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;
		
		try 
		{
			JSch jsch = new JSch();
			session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
			
			session.setPassword(SFTPPASS);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp) channel;
			channelSftp.cd(SFTPWORKINGDIR);
	
			String localDirectory = "C:/SALESO/";
			
			String filepath = localDirectory +  fileToFTP;
			File file = new File(filepath);
			
			channelSftp.put(new FileInputStream(file), file.getName());
			
			
			channelSftp.exit();
			session.disconnect();
		} 
		catch (Exception ex) 
		{
		  ex.printStackTrace();
		}		
	}
	
	/*
	public boolean startFTP(String fileToFTP){
		 
		  
		  StandardFileSystemManager manager = new StandardFileSystemManager();
		 
		  try {
		 
		   
		   String serverAddress = "204.90.181.225:22";
		   String userId = "5050406905121";
		   String password = "Clarks123test";
		   String remoteDirectory = "/5050406905121/CLARKSTEST/SALESO/";
		   String localDirectory = "C:/";
		   //check if the file exists
		   String filepath = localDirectory +  fileToFTP;
		   File file = new File(filepath);
		   if (!file.exists())
		   throw new RuntimeException("Error. Local file not found");
		 
		   //Initializes the file manager
		   manager.init();
		    
		   //Setup our SFTP configuration
		   FileSystemOptions opts = new FileSystemOptions();
		   SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(
		     opts, "no");
		   SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
		   SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);
		    
		   //Create the SFTP URI using the host name, userid, password,  remote path and file name
		   String sftpUri = "sftp://" + userId + ":" + password +  "@" + serverAddress + "/" +
		     remoteDirectory + fileToFTP;
		    
		   // Create local file object
		   FileObject localFile = manager.resolveFile(file.getAbsolutePath());
		 
		   // Create remote file object
		   FileObject remoteFile = manager.resolveFile(sftpUri, opts);
		 
		   // Copy local file to sftp server
		   remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);
		   System.out.println("File upload successful");
		 
		  }
		  catch (Exception ex) {
		   ex.printStackTrace();
		   return false;
		  }
		  finally {
		   manager.close();
		  }
		 
		  return true;
		 }
	
	
	*/
	
	
		
}
