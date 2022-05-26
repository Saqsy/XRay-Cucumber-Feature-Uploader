/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 *
 * @author Saqsy
 */
public class AuthenticationGenerator {
    
    public static String authenticationToken = null;

    public static boolean generateBearerToken(){
        
        boolean flag = false;

        RequestSpecification httpRequest;

        httpRequest = RestAssured.given();
        
        ObjectMapper mapper = new ObjectMapper();
        
        ObjectNode authBody = mapper.createObjectNode();
        
        authBody.put("client_id", PropertyManager.properties.getProperty("Xray_Client_Id"));
       
        authBody.put("client_secret", PropertyManager.properties.getProperty("Xray_Client_Secret_Key"));

        try {
            Response authResponse = httpRequest.headers("Content-Type", ContentType.JSON)
                    .body(authBody)
                    .post("https://xray.cloud.getxray.app/api/v1/authenticate");

            if (authResponse.statusCode() == 200) {
                flag = true;
                authenticationToken = "Bearer " + authResponse.getBody().asString().replaceAll("^\"+|\"+$", "");
            }
        } catch (Exception e){
            System.exit(0);
        }
        return flag;
}
}
