package MailJet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.swing.Timer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SendEmailAction {
	private static Timer t;

	public static void sendMail(final String zipCode, final String emailID, final String highTemp, final String lowTemp){

		ActionListener taskPerformer = new ActionListener() {
			int iter = 3;
			public void actionPerformed(ActionEvent evt) {
				if(emailID == null)
					return;
				
				if (iter==0){
					System.out.println("Done sending emails");
					t.stop();
					return;
				}
				/*
				try {
					MimeMessage msg = new MimeMessage(session);
					msg.setFrom("mmly2@illinois.edu");
					msg.setRecipients(Message.RecipientType.TO,
							emailID);
					msg.setSubject("JavaMail hello world example");
					msg.setSentDate(new Date());
					msg.setText("Zip Code: " + zipCode + "\nEmail: " + emailID + "\nLow Temp: " + lowTemp + "\nHigh Temp: " + highTemp);
					Transport.send(msg, "09fa5b08f5566bcecce0b9d1b3949b05", "d75514d83d57a029553f66ab4cf4e7f1");
					System.out.println("done");
				} catch (MessagingException mex) {
					System.out.println("send failed, exception: " + mex);
				}*/

				try {
					URL url = new URL("http://api.wunderground.com/api/0ec92fe577458c9e/conditions/q/" + zipCode + ".json");
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					JSONParser jp = new JSONParser();
					Object jo = jp.parse(rd);

					JSONArray currObv = new JSONArray(); // = (JSONArray) jo.get("current_observation");

					currObv.put( ((JSONObject) jo).get("current_observation") );
					double temp = 0;
					String s = currObv.get(0).toString();
					//System.out.println(s);
					int i = s.indexOf("temp_f\":");
					int j = s.indexOf(",\"wind_kph", i);
					temp = Double.parseDouble(s.substring(i+8,j));

					if(temp < Integer.parseInt(lowTemp)){
						sendEmail(zipCode, emailID, lowTemp, temp, false);
					}
					else if(temp > Integer.parseInt(highTemp)){
						sendEmail(zipCode, emailID, highTemp, temp, true);
					}
				} catch (FileNotFoundException e) { e.printStackTrace();
				} catch (IOException e) {  e.printStackTrace();
				} catch (ParseException e) {  e.printStackTrace();
				} catch (JSONException e) { e.printStackTrace(); 
				} catch (MessagingException e) { e.printStackTrace(); }
				
				iter--;
			}
		};

		t = new Timer (15000, taskPerformer);
		t.start();

	}

	private static void sendEmail(String zipCode, String emailID, String temperature, double currTemp, boolean high) throws MessagingException{
		String text = "";
		if(high){
			text = "Hello, " + emailID + ".  The current temperature, " + currTemp + " degrees, is HIGHER than the temperature your supplied: " + temperature;
		}
		else {
			text = "Hello, " + emailID + ".  The current temperature, " + currTemp + " degrees, is LOWER than the temperature your supplied: " + temperature;
		}
		
		Properties props = new Properties();
			props.put("mail.smtp.host", "in-v3.mailjet.com"); //in-v3.mailjet.com
			Session session = Session.getInstance(props, null);

			MimeMessage msg = new MimeMessage(session);
			/* To receiver */
			msg.setFrom("mmly2@illinois.edu");
			msg.setRecipients(Message.RecipientType.TO,
					emailID);
			msg.setSubject("Your Electronic Weather Alert");
			msg.setSentDate(new Date());
			msg.setText(text);
			Transport.send(msg, "09fa5b08f5566bcecce0b9d1b3949b05", "d75514d83d57a029553f66ab4cf4e7f1");
			
			
			/* To me */
			
			
			msg.setRecipients(Message.RecipientType.TO,
					"matthewly@gmail.com");

			Transport.send(msg, "09fa5b08f5566bcecce0b9d1b3949b05", "d75514d83d57a029553f66ab4cf4e7f1");
			
			System.out.println("email sent");
	}
}
