import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xml.sax.helpers.DefaultHandler;


/**
 * findEmails goes through a number of URL's and captures each email found from a pattern group in line 45.
 * @author mcaird22
 *
 */
public class findEmails extends DefaultHandler {
	
	
	static HashMap<String,Boolean> map = new HashMap<String,Boolean>(); // saves the map and determines if they are visited.
	static HashSet<String> emails = new HashSet<String>(); 				// Saves the emails.
	static int i = 0;
	
	public static void main(String[] args) throws IOException {
		
		String urlString = "https://www.kxly.com/station/contact-kxly/171599929";
		map.put(urlString, true);
		
			try {
				URL url = new URL(urlString); // sets the urlString to a type of URL
			
			    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			    String line = "";
			    
			    Pattern email = Pattern.compile("mailto:(.*?)\""); // Email pattern group 1
			    Pattern furl = Pattern.compile("www.(.*?)\""); // URL pattern group 1
			    while ((line = reader.readLine()) != null) {
			    	
				    Matcher Ematcher = email.matcher(line); // Reads the line and sees if it matches
				    Matcher Umatcher = furl.matcher(line);
				    
				    if (Ematcher.find()) {  // if it matches the email pattern, add that to Hashset
				    	
				    	emails.add(Ematcher.group(1));
				    }
				    
				    else if (Umatcher.find()) { // if the line matches URL pattern, add that to map.
				    	System.out.println("URL " + i++ + ": " + urlString);  // prints the url to System
				    	urlString = ("https://" + Umatcher.group(1).toString()); 
				    	if ( i > 100) 
				    		break;
				    	else if (!map.containsKey(urlString)) { // if the hashmap doesnt have the urlString already, add it with false.
					    	map.put(urlString,false);
				    	}
				    }
				    
			    }
			    
			    reader.close();
			    
			    for (String u: map.keySet()) { // for every string in map, if its false set it to urlString to open.
			    	if (map.get(u) == false) {
			    		urlString = u;
			    		map.put(u, true);
			    	}
			    }
			
			} catch (MalformedURLException e) {
				
				System.out.println("Could not read file" + urlString);
				e.printStackTrace();
			}
	
			System.out.printf("Emails: " + emails.toString() + "\n"); // prints the emails
	}
}