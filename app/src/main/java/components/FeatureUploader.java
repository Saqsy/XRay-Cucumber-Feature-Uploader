/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package components;

import static XRayFeatureFileUploader.Home.textLog;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Saqsy
 */
public class FeatureUploader {

    private final String bearerToken = AuthenticationGenerator.authenticationToken;

    public void cucumberImportJob(String filepath) {
        File file = new File(filepath);

        RequestSpecification httpRequest;

        httpRequest = RestAssured.given();

        String endpoint = "https://xray.cloud.getxray.app/api/v2/import/feature?projectKey=" + PropertyManager.properties.getProperty("Project_ID");

        Response response = httpRequest.headers("Authorization", bearerToken, "Content-Type",
                        ContentType.MULTIPART)
                .multiPart("file", file)
                .post(endpoint);

        if (response.statusCode() == 200) {
            try {
                JSONObject uploadResponse = new JSONObject(response.asString());
                JSONArray updatedOrCreatedTests = uploadResponse.getJSONArray("updatedOrCreatedTests");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < updatedOrCreatedTests.length(); i++) {
                    JSONObject key = (JSONObject) updatedOrCreatedTests.get(i);
                    sb.append(key.get("key").toString());
                    sb.append(System.getProperty("line.separator"));
                }
                textLog.setText(sb.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
