package Resources;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	RequestSpecification responsegiven;
	ResponseSpecification resp;
	public static RequestSpecification req;
	static Properties prop;
	
	

	@SuppressWarnings("resource")
	public RequestSpecification requestspecification() throws IOException
	{
		
		
		if (req==null)
		{
			PrintStream Stream = new PrintStream(new FileOutputStream("Logger.txt"));
			req = new RequestSpecBuilder().addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(Stream))
					.addFilter(ResponseLoggingFilter.logResponseTo(Stream))
	    			.setBaseUri(getGobaldata("baseuri")).setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
		
		
	}
	
	public ResponseSpecification responsespecification()
	{
		
		
		resp = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		return resp;
	}
	
	public static String getGobaldata(String key) throws IOException
	{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\AddPlace.properties");
		prop.load(fis);
		
		return prop.getProperty(key);
		
	}
	
	public static String getJsonValue(Response response,String key)
	{
		String output = response.asString();
		JsonPath js = new JsonPath(output);
		return js.get(key);
	}
}
