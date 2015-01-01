package MailJet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EmailDemo {
	public static void main(String[] args) {
		/*ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println("Hello");
			}
		};

		new Timer (1000, taskPerformer).start();*/

		//SendEmailAction.sendMail("60101", "matthewly@gmail.com", "20", "120");

		try {
			URL url = new URL("http://api.wunderground.com/api/0ec92fe577458c9e/conditions/q/60101.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			JSONParser jp = new JSONParser();
			Object jo = jp.parse(rd);

			JSONArray currObv = new JSONArray(); // = (JSONArray) jo.get("current_observation");

			currObv.put( ((JSONObject) jo).get("current_observation") );
			double temp = 0;
			String s = currObv.get(0).toString();
			System.out.println(s);
			int i = s.indexOf("temp_f\":");
			int j = s.indexOf(",\"wind_kph", i);
			temp = Double.parseDouble(s.substring(i+8,j));

			if(temp < 0){
				System.out.println("Less than desired temp.");
			}
			else if(temp > 40){
				System.out.println("Greater than desired temp.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		} catch (ParseException e) { 
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
