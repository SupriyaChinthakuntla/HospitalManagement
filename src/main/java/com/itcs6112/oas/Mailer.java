package com.itcs6112.oas;
import java.util.Properties;  

import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage; 

public class Mailer {
	public static void send(String to) {
		// TODO Auto-generated method stub
		
		
		final String user="hospitalmanagementssdi@gmail.com";//change accordingly  
		final String pass="Test@12345";  
		  
		//1st step) Get the session object    
		Properties props = new Properties();  
		props.put("mail.smtp.host", "smtp.gmail.com");//change accordingly  
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.auth", "true");

		  
		Session session = Session.getDefaultInstance(props,  
		 new javax.mail.Authenticator() {  
		  protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication(user,pass);  
		   }  
		});  
		//2nd step)compose message  
		try {  
		 MimeMessage message = new MimeMessage(session);  
		 message.setFrom(new InternetAddress(user));  
		 message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
		 message.setSubject("Doctor Appointment Confirmed");  
		 message.setText("Your appointment has been confirmed");  
		     

		 //3rd step)send message  
		 Transport.send(message); 
		  
		 System.out.println("Done");  
		  
		 } catch (MessagingException e) {  
		    throw new RuntimeException(e);  
		 }  
		
	}

}
