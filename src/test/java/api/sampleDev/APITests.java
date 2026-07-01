package api.sampleDev;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.example.automation.base.BaseFactory;
import com.example.automation.utils.commonUtils.APIUtils;

import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * APITests is a TestNG test class for performing CRUD operations on a phone API using RestAssured.
 * <p>
 * This class includes tests for:
 * <ul>
 *   <li>Creating a phone entry via POST</li>
 *   <li>Retrieving a phone by ID via GET</li>
 *   <li>Updating a phone's details via PUT</li>
 *   <li>Deleting a phone entry via DELETE</li>
 * </ul>
 * <p>
 * The tests use a shared deviceId property to chain operations and validate the API's behavior.
 * <p>
 * Group: api
 *
 * @author Reetanshu Kumar
 * @since 2025-08-16
 */
@Test(groups = {"api"})
public class APITests extends BaseFactory {

    private static final Logger log = LogManager.getLogger(APITests.class);
    APIUtils apiUtils = new APIUtils();
    String expectedPhoneName = "Apple IPhone 13-RK";
    Double price = 899.99d;
    String template = "{ \"name\": \"%s\",\"data\": {\"year\": 2021,\"price\": %f, \"CPU model\": \"Google Tensor\", \"Hard disk size\": \"128 GB\"} }";

    @Test(priority = 1)
    void postPhoneAPI() {
        String url = prop.getProperty("apiBaseUrl");
        String body = String.format(template, expectedPhoneName, price);
        Response postResponse = apiUtils.sendPostRequest(url, body, "application/json");
        log.info("POST Response to phone creation : " + postResponse.asString());
        
        assertTrue(postResponse.getStatusCode() == 200, "Phone creation failed with status code: " + postResponse.getStatusCode());
        
        JsonPath jsonPath = postResponse.jsonPath();
        Object deviceID  = jsonPath.get("id");
        log.info("Device ID: " + deviceID);
        System.setProperty("deviceId", deviceID.toString());
        Allure.step("Post Phone API method passed");
    }

    @Test(priority = 2)
    void getPhoneAPI() {
        String deviceId = System.getProperty("deviceId");
        String url = prop.getProperty("apiBaseUrl") + "?id=" + deviceId;
        Response getResponse = apiUtils.sendGetRequest(url);

        assertTrue(getResponse.getStatusCode() == 200, "Get phone by ID failed with status code: " + getResponse.getStatusCode());

        JsonPath jsonPath = getResponse.jsonPath();
        String actualPhoneName = jsonPath.getList("name").get(0).toString();

        assertEquals(actualPhoneName, expectedPhoneName, "Actual phone name "+actualPhoneName+" does not match expected "+ expectedPhoneName);
        log.info("GET API Test Passed with phone name: " + actualPhoneName);
        Allure.step("Get Phone API method passed");
    }

    //@Test(priority = 3)
    void getAllPhonesAPI(){
       Response getResponse = apiUtils.sendGetRequest(prop.getProperty("apiBaseUrl"));
       JsonPath jsonPath = getResponse.jsonPath();
       int phoneCount = jsonPath.getInt("phones.size()");

       System.out.println("getReponse: " + getResponse.asString());
       System.out.println("Path: " + getResponse.asString().contains("Google Pixel 6 Pro"));
       //Search Google Pixel 6 Pro in the response
       List<Map<String,String>> phone = jsonPath.getList("findAll { it.name == 'Google Pixel 6 Pro' }");
       log.info("Phone ID for 'Google Pixel 6 Pro': " + phone.get(0).get("id"));
       log.info("Number of phones: " + phoneCount);
    }

    @Test(priority = 3)
    void putPhoneAPI() {
        String deviceId = System.getProperty("deviceId");
        price = 799.99;
        String body = String.format(template, expectedPhoneName, price);
        System.out.println("Body: " + body);
        String url = prop.getProperty("apiBaseUrl") + "/" + deviceId;
       
        Response putResponse = apiUtils.sendPutRequest(url, body, "application/json");
        log.info("PUT Response to phone update: " + putResponse.asString());

        assertTrue(putResponse.getStatusCode() == 200, "Phone put call failed with status code: " + putResponse.getStatusCode());
        
        JsonPath jsonPath = putResponse.jsonPath();
        Double currentPhonePrice = jsonPath.getDouble("data.price");
        System.out.println("Current phone price: " + currentPhonePrice);
        assertTrue(currentPhonePrice.equals(price), "Current price is not set to:"+ price);
        log.info("PUT API Test Passed with phone price: " + currentPhonePrice);
        Allure.step("Put Phone API method passed");
    }
    
    @Test(priority = 4)
    void deletePhoneAPI() {
        String deviceId = System.getProperty("deviceId");
        String url = prop.getProperty("apiBaseUrl") + "/" + deviceId;
        
        Response deleteResponse = apiUtils.sendDeleteRequest(url);
        log.info("DELETE Response: " + deleteResponse.asString());
        
        assertTrue(deleteResponse.getStatusCode() == 200, "Phone delete call failed with status code: " + deleteResponse.getStatusCode());

        Response getResponse = apiUtils.sendGetRequest(url);
        
        assertTrue(getResponse.getStatusCode() == 404, "Phone was not deleted successfully, status code: " + getResponse.getStatusCode());
        log.info("DELETE API Test Passed, phone deleted successfully");
        Allure.step("Delete Phone API method passed");
    }    
}
