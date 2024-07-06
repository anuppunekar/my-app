package com.mycompany.app;

/*
Mod log
Created on: 04-Jul-2024
Created by: ChatGPT
PhonePe Wrapper
*/

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.http.HttpHeaders;

/*
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Map;
*/

public class PhonePeApiWrapper {
    private String baseUrl;
    private HttpClient httpClient;

    public PhonePeApiWrapper(String baseUrl) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClient.newHttpClient();
    }

    //Methods for different API endpoints
    public String postRequestToken(String endpointPath, String requestBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + endpointPath))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null; 
        }
    }

    public String postRequestSetup(String endpointPath, String requestBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + endpointPath))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "O-Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcmVzT24iOjE3MjA4MTEyOTQ2ODUsIm1lcmNoYW50SWQiOiJBVkFOU0VTVUJVQVQifQ.r42TcOD4c7EqvU8wVBMIyU7OM4uwE71wd6j6fmQniag")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null; 
        }
    }

    public String postRequestRedeem(String endpointPath, String requestBody) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + endpointPath))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "O-Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHBpcmVzT24iOjE3MjA4MTEyOTQ2ODUsIm1lcmNoYW50SWQiOiJBVkFOU0VTVUJVQVQifQ.r42TcOD4c7EqvU8wVBMIyU7OM4uwE71wd6j6fmQniag")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null; 
        }
    }

    public static void main(String[] args) {
        String baseUrl = "https://api-preprod.phonepe.com/apis/pg-sandbox";
        PhonePeApiWrapper apiWrapper = new PhonePeApiWrapper(baseUrl);

        String endpointPath = "/v1/oauth/token";
		String client_credentials = "{ \"MerchantID\": \"AVANSESUBUAT\", \"Salt Key\": \"e22f8806-e244-40d2-9788-06a66b3019a0\", \"Key Index\": 1}";
        String requestBody = "{ \"client_id\": \"AVANSESUBUAT_2406211117363065519826\", \"client_version\": 1, \"client_secret\": \"ZmVlNTFjNjAtNTcyMC00ZmMxLTliZmItMDA0Yzg3MDRkYzRk\", \"grant_type\": \" + client_credentials + \" }";
	
        String response = apiWrapper.postRequestToken(endpointPath, requestBody);
        System.out.println("Response from API: " + response);

        String baseUrlSetup = "https://api-preprod.phonepe.com/apis/pg-sandbox";
        PhonePeApiWrapper apiWrapperSetup = new PhonePeApiWrapper(baseUrlSetup);
		String endpointPathSetup = "/subscriptions/v2/setup";
		String requestBodySetup = "{ \"merchantOrderId\": \"MO1709025658932\", \"amount\": 200, \"expireAt\": 1709058548000, \"paymentFlow\": {\"type\": \"SUBSCRIPTION_SETUP\", \"merchantSubscriptionId\": \"MS1709025658932\", \"authWorkflowType\": \"TRANSACTION\", \"amountType\": \"FIXED\", \"maxAmount\": 200, \"frequency\": \"ON_DEMAND\", \"expireAt\": 1737278524000, \"deviceContext\": { \"deviceOS\": \"ANDROID\" }, \"paymentMode\": { \"type\": \"UPI_INTENT\", \"targetApp\" : \"com.Phonepe.app\" }}}";
		String responseSetup = apiWrapperSetup.postRequestSetup(endpointPathSetup, requestBodySetup);
        System.out.println("Response from API: " + responseSetup);

        String baseUrlRedeem = "https://api-preprod.phonepe.com/apis/pg-sandbox";
        PhonePeApiWrapper apiWrapperRedeem = new PhonePeApiWrapper(baseUrlRedeem);
		String endpointPathRedeem = "/subscriptions/v2/redeem";
		String requestBodyRedeem = "{ \"merchantOrderId\": \"MO1709025658932\" }";
		String responseRedeem = apiWrapperRedeem.postRequestRedeem(endpointPathRedeem, requestBodyRedeem);
        System.out.println("Response from API: " + responseRedeem);

		/*
		ObjectMapper objectMapper = new ObjectMapper();
		try {
		    // Parse JSON response into a map or a custom class representing the response structure
		    Map<String, Object> jsonResponse = objectMapper.readValue(response, new TypeReference<Map<String, Object>>() {});
	    
		    // Extract relevant fields from the JSON response
		    boolean success = (boolean) jsonResponse.get("success");
		    String code = (String) jsonResponse.get("code");
		    String message = (String) jsonResponse.get("message");
	
		    // Handle the 'data' object if needed
		    // Map<String, Object> data = (Map<String, Object>) jsonResponse.get("data");
	
		    // Print or use the extracted fields
		    System.out.println("Success: " + success);
		    System.out.println("Error Code: " + code);
		    System.out.println("Error Message: " + message);
	
			if (!success) {
			    System.out.println("Request failed with error code: " + code);
			    System.out.println("Error message: " + message);
			    // Implement error handling logic based on the error code and message
			} else {
			    // Process successful response
			    // Handle data if required
			}
		} catch (IOException e) {
		    e.printStackTrace();
		    // Handle JSON parsing exception
		}
		*/
    }

}

