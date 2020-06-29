package Resources;

public enum APIResources {
	
		
	ADDPLACE("/maps/api/place/add/json"),
	GETPLACE("/maps/api/place/get/json"),
	DELETEPLACE("/maps/api/place/delete/json"),
	UPDATEPLACE("/maps/api/place/update/json");
	
	private String resource;
	

	APIResources(String resource) {
	
		this.resource = resource;
			
	}
	
	public String getResourcename()
	{
		return resource;
	}
	
	

}
