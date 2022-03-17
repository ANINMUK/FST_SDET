package RestAssuredProjectWork.RestAssuredProjectWork;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJO.userpojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class userMethods {
	@Test (enabled = false)
	public void createUser() throws JsonProcessingException
	{
		userpojo pojoobj = new userpojo();
		pojoobj.setId("1");
		pojoobj.setUsername("User1");
		pojoobj.setFirstname("Larry");
		pojoobj.setLastname("Johnson");
		pojoobj.setEmail("ljohn@abc.com");
		pojoobj.setPassword("pass1");
		pojoobj.setPhone("9023456789");
		pojoobj.setStatus("0");
		
		ObjectMapper objmap = new ObjectMapper();
		
		String body = objmap.writerWithDefaultPrettyPrinter().writeValueAsString(pojoobj);
		
		System.out.println(body);
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		Response respobj = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(body)
			.when()
				.post("/user")
			.then()
				.statusCode(200)
				.log().all().extract().response();
		
	}
	 @Test (enabled = false)
	public void modifyUser() throws JsonProcessingException
	{
		userpojo pojoobj = new userpojo();
		pojoobj.setId("1");
		pojoobj.setUsername("ModifiedUser1");
		pojoobj.setFirstname("Larry");
		pojoobj.setLastname("Johnson");
		pojoobj.setEmail("ljohn@abc.com");
		pojoobj.setPassword("Modifedpass1");
		pojoobj.setPhone("9023456789");
		pojoobj.setStatus("0");
		
ObjectMapper objmap = new ObjectMapper();
		
		String body = objmap.writerWithDefaultPrettyPrinter().writeValueAsString(pojoobj);
		
		System.out.println(body);
		
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		Response respobj = RestAssured
				.given()
				.contentType(ContentType.JSON).queryParam("username", "User1")
				.body(body)
			.when()
				.put("/user")
			.then()
				.statusCode(200)
				.log().all().extract().response();
	}
	 @Test (enabled = false)
	public void loginUser()
	{
		 RestAssured.baseURI = "https://petstore.swagger.io/v2";
			Response respobj = RestAssured
					.given()
					.contentType(ContentType.JSON).queryParam("username", "ModifiedUser1").queryParam("password", "Modifiedpass1")
					
				.when()
					.get("/user/login")
				.then()
					.statusCode(200)
					.log().all().extract().response();
	}
	@Test(enabled = false)
	public void logoutUser()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		Response respobj = RestAssured
				.given()
				.contentType(ContentType.JSON)
			.when()
				.get("/user/logout")
			.then()
				.statusCode(200)
				.log().all().extract().response();
	}
	@Test
	public void deleteUser()
	{
		RestAssured.baseURI = "https://petstore.swagger.io/v2";
		Response respobj = RestAssured
				.given()
				.contentType(ContentType.JSON).queryParam("username", "ModifiedUser1")
				
			.when()
				.delete("/user")
			.then()
				.statusCode(405)
				.log().all().extract().response();
	}

}
