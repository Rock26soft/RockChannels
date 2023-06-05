package com.rock.tvchannel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class M3UParser {
	
	public static List<Channel> parse(String filepath) {
		List<Channel> channels = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
			String line;
			Channel currentChannel = null;
			
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.startsWith("#KODIPROP")) {
					// Extract license type and key from #KODIPROP lines
					String property = line.substring(line.indexOf(':') + 1).trim();
					String[] propertyParts = property.split("=");
					
					String propertyName = propertyParts[0].trim();
					String propertyValue = propertyParts[1].trim();
					
					currentChannel = new Channel( null,null, null, null, null,null);
					System.out.println("name: " + propertyName +"  " +propertyValue);
					
					if (propertyName.equals("inputstream.adaptive.license_type")) {
						currentChannel.licenseType = propertyValue;
						
					}
					if (propertyName.equals("inputstream.adaptive.license_key")) {
						currentChannel.licenseKey = propertyValue;
					}
					
					
				} else if (line.startsWith("#EXTINF")) {
					// Extract channel name and logo from #EXTINF line
					String[] channelInfo = line.split(",");
					currentChannel.name = channelInfo[1];
					currentChannel.logo = extractValue(line, "tvg-logo=\"([^\"]+)\"");
					currentChannel.id = extractValue(line, "tvg-id=\"([^\"]+)\"");
					
					
				} else if (line.startsWith("http")) {
					// Store channel URL
					if (currentChannel != null) {
						currentChannel.url = line;
						channels.add(currentChannel);
						currentChannel = null;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return channels;
	}
	
	private static String extractValue(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		
		if (matcher.find()) {
			return matcher.group(1);
		}
		
		return null;
	}
	
	public static List<Channel> getParsedList(String playlistFile) {
		
		return parse(playlistFile);
	}
}
