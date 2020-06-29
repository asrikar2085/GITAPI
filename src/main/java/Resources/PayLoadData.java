package Resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import pojo.ConstructJson;
import pojo.Locationjson;

public class PayLoadData extends Utils {
	
	Properties prop;
	
	public ConstructJson AddPlaceData(String name, String address, String language) throws IOException
	{
		    	
    	ConstructJson cj = new ConstructJson();
    	cj.setAccuracy(50);
		cj.setName(name);
		cj.setPhone_number(getGobaldata("phone_number"));
		cj.setAddress(address);
		
		cj.setWebsite(getGobaldata("website"));
		cj.setLanguage(language);
		
		ArrayList<String> mylist = new ArrayList<String>();
		mylist.add(getGobaldata("types1"));
		mylist.add(getGobaldata("types2"));
		
		Locationjson lj = new Locationjson();
		
		lj.setLng(-38.383494);		
		lj.setLat(33.427362);
		
		cj.setTypes(mylist);
		cj.setLocation(lj);
		return cj;
	}
	
	public String DeletePlaceData(String placeid)
	{
	    return "{\r\n" + 
	    		"    \"place_id\":\""+placeid+"\"\r\n" + 
	    		"}";	
	}

}
