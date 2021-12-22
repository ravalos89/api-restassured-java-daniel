package oauthv2;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;

import static io.restassured.RestAssured.given;

public class GoogleOAuthv2 {
  @Test
  public void f() {
	  
	  // Get URL after login google account
	  
	  // 2020 SELENIUM
	  
	  String url = "https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AX4XfWiAuOT82qWrHddIZAPO0KOaPJXUIntk5-nFAwL_lqmTH4KB0ZMkukVSx_UQd4n72Q&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
	  
	  // String processing
	  String code = url.split("code=")[1].split("&scope")[0];
	  System.out.println(code);
	  
	  
//	  String random = "ABCDEFG-1234567#DANIEL";
//	  String letras = random.split("-")[0];
//	  String daniel = random.split("#")[1];
//	  String numeros = random.split("G-")[1].split("#")[0];
//	  System.out.println(letras);
//	  System.out.println(daniel);
//	  System.out.println(numeros);
	  
	  // Get access token
	  String clientID = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
	  String clientSecret = "erZOWM9g3UtwNRj340YYaK_W";
	  String redirectURI = "https://rahulshettyacademy.com/getCourse.php";
	  String grantType = "authorization_code";
	  
	  String response = given()
	  // Encoding - Para obtener codigo fuente
	  .urlEncodingEnabled(false)
	  .queryParam("code", code)
	  .queryParam("client_id", clientID)
	  .queryParam("client_secret", clientSecret)
	  .queryParam("redirect_uri", redirectURI)
	  .queryParam("grant_type", grantType)
	  .when().log().all()
	  .post("https://www.googleapis.com/oauth2/v4/token").asString();

	  String accessToken = Base.getValueFromResponseJson(response, "access_token");
	  
	  // Get course using access token
	  if(accessToken==null) {
		  accessToken="";
	  }
	  
	  String response2 = given()
			  .queryParam("access_token", accessToken)
			  .when().log().all()
			  .get("https://rahulshettyacademy.com/getCourse.php")
			  .asString();
	  System.out.println(response2);
	  
	  Assert.assertNotEquals(response2, "AUTHENTICATION FAILED !!!! PLEASE ENTER VALID ACCESS TOKEN");
	  
  }
}
