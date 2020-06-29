Feature: Place API Validation
@AddPlace
Scenario Outline: Verify if place is successfully added using ADD PLACE API
Given Add place payload using "<name>","<address>","<language>"
When User calls "ADDPLACE" API with "POST" HTTP method
Then the API call should be successfull with status code 200
And "status" in response is "OK"
And "scope" in response is "APP"
And Verify "name" retrieved from "GETPLACE" API matches with "<name>" using "place_id"

Examples:
|name	|address			   |language|
|Srikar |1209 charter hourse ln|English-IN|
|Medha	|1209 deer trl		   |French-IN |
@DeletePlace
Scenario: Verify if place is successfully deleted using Delete Place API
Given Delete place payload
When User calls "DELETEPLACE" API with "DELETE" HTTP method
Then the API call should be successfull with status code 200
And "status" in response is "OK"
