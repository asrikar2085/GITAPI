package stepdefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.runner.RunWith;

import Resources.APIResources;
import Resources.PayLoadData;
import Resources.Utils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@RunWith(Cucumber.class)
public class FeaturetoCode extends Utils {

	RequestSpecification responsegiven;
	Response responsewhen;
	PayLoadData add = new PayLoadData();
	static String place_id;
	
	@Given("Add place payload using {string},{string},{string}")
	public void add_place_payload_using(String name,String address,String language) throws IOException {
    	   	
    	responsegiven = given().spec(requestspecification()).body(add.AddPlaceData(name,address,language));
    }

	@When("User calls {string} API with {string} HTTP method")
	public void user_calls_API_with_HTTP_method(String inputresource, String method) {
	    	
    	APIResources resource = APIResources.valueOf(inputresource);
    	String resourcepath = resource.getResourcename();
    	
    	if(method.equalsIgnoreCase("POST"))
    		responsewhen =responsegiven.when().post(resourcepath)
    				.then().spec(responsespecification()).extract().response();
    	if(method.equalsIgnoreCase("GET"))	       
    	responsewhen =responsegiven.when().get(resourcepath)
    			.then().spec(responsespecification()).extract().response();
    	if(method.equalsIgnoreCase("DELETE"))	       
        	responsewhen =responsegiven.when().delete(resourcepath)
        			.then().spec(responsespecification()).extract().response();
    	if(method.equalsIgnoreCase("PUT"))	       
        	responsewhen =responsegiven.when().put(resourcepath)
        			.then().spec(responsespecification()).extract().response();
    	
    	System.out.println("responsewhen is " + responsewhen.asString());
    }

    @Then("^the API call should be successfull with status code (\\d+)$")
    public void the_api_call_should_be_successfull_with_status_code_something(int statuscode) throws Throwable {
    
        
    	assertEquals(responsewhen.getStatusCode(),statuscode);
    	System.out.println("inside the_api_call_should_be_successfull_with_status_code_something method");
    }

    @And("^\"([^\"]*)\" in response is \"([^\"]*)\"$")
    public void something_in_response_is_something(String key, String Expectedvalue) throws Throwable {
        
    	assertEquals(getJsonValue(responsewhen,key),Expectedvalue);  
    	System.out.println("something_in_response_is_something");
    	}
    
    @And("Verify {string} retrieved from {string} API matches with {string} using {string}")
	public void verify_retrieved_from_API_matches_with_using(String outputkey, 
			String GETPLACE, String expectedname, String inputkey) throws IOException {
    	
    	place_id = getJsonValue(responsewhen,inputkey);
    	responsegiven = given().spec(requestspecification()).queryParam(inputkey, getJsonValue(responsewhen,inputkey));
    	user_calls_API_with_HTTP_method(GETPLACE,"GET");
    	String actualname = getJsonValue(responsewhen,outputkey);
    	assertEquals(expectedname, actualname);  	
    }
    
    @Given("Delete place payload")
    public void delete_place_payload() throws IOException {
    	
    	System.out.println("place_id is " + place_id);
     
    	responsegiven = given().spec(requestspecification()).body(add.DeletePlaceData(place_id));
    	System.out.println("responsegiven is " + responsegiven);
    	
    }

}