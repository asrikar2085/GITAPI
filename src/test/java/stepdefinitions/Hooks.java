package stepdefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	
	public void beforescenario() throws IOException
	{
		FeaturetoCode ftc = new FeaturetoCode();
		
		if (FeaturetoCode.place_id ==null)
		{		
		
		ftc.add_place_payload_using("babyboy", "Atlanta", "Telugu");
		ftc.user_calls_API_with_HTTP_method("ADDPLACE", "POST");
		ftc.verify_retrieved_from_API_matches_with_using("name", "GETPLACE", "babyboy", "place_id");
		
		}
	}

}
