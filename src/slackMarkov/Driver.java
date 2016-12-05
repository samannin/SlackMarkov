package slackMarkov;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import org.json.*;

public class Driver {
	private static String UserName = System.getProperty("user.name");
	private static String testExportLocation = "C:\\Users\\"+UserName+"\\Dropbox\\SlackExport\\data\\ACM Flint Slack export Nov 30 2016";
	
	public static void main(String args[]) throws IOException{
		JSONObject obj = new JSONObject();
		ArrayList<User> userList = importUsers(testExportLocation);
		System.out.print("Users: "+userList.size());
		
		displayIt(new File(testExportLocation));
	}
	
	private static ArrayList<User> importUsers(String folder) throws IOException{
		ArrayList<User> output = new ArrayList<User>();
		
		String fullFilePath = folder+java.io.File.separator+"users.json";
		String jsonContents = FileUtils.readFileToString(new File(fullFilePath));
		
		//JSONObject userFileJson = new JSONObject(jsonContents);
		//JSONArray userArray = userFileJson.getJSONArray(null);
		
		JSONArray userArray = new JSONArray(jsonContents);
		for (int i=0; i<userArray.length();i++){
			JSONObject item = userArray.getJSONObject(i);
			output.add(new User(item.getString("id"),item.getString("name")));
		}
		
		return output;		
	}
	
	public static void displayIt(File node){

		System.out.println(node.getAbsoluteFile());

		if(node.isDirectory()){
			String[] subNote = node.list();
			for(String filename : subNote){
				displayIt(new File(node, filename));
			}
		}

	}
	

}
