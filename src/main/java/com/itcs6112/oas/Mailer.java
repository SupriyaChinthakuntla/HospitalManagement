package com.itcs6112.oas;
import java.text.SimpleDateFormat;
import java.util.Properties;  

import javax.mail.*;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;

import com.itcs6112.oas.model.AppointmentInfo;
import com.itcs6112.oas.model.UserInfo; 

public class Mailer {
	public static void send(UserInfo ui, AppointmentInfo ai) {
		// TODO Auto-generated method stub
		String to = ui.getEmail();
		String startDate = new SimpleDateFormat("yyyy-MM-dd hh:mm a").format(ai.getStartDate());
		
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
		 message.setSubject("Appointment Confirmation");  
		 message.setText(String.format("Dear %s,\n\nYour appointment has been confirmed for %s.\n\nThank you and we look forward to seeing you soon.", ui.getFname(), startDate));  
		     

		 //3rd step)send message  
		 Transport.send(message); 
		  
		 System.out.println("Done");  
		  
		 } catch (MessagingException e) {  
		    throw new RuntimeException(e);  
		 }  
		
	}

}
