package com.vinaythetestingacademy.tests.restfulbooker.crud;

import com.vinaythetestingacademy.base.BaseTest;
import com.vinaythetestingacademy.endpoints.APIConstants;
import com.vinaythetestingacademy.pojos.responsePOJO.LoginResponse;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestVwoLogin extends BaseTest {

    @Test
    public void test_VWO_Login_Positive() {
        // Setup will first and making the request - Part - 1
        requestSpecification.baseUri(APIConstants.APP_VWO_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(payloadManager.setLoginData()).log().all()
                .post();

        //Extraction Part - 2
        LoginResponse loginResponse = payloadManager.getLoginData(response.asString());

        // Validation and verification via the AssertJ, TestNG Part - 3
        assertActions.verifyStatusCode(response, 200);

        System.out.println(loginResponse.getAccountId());
        assertActions.verifyStringKeyNotNull(loginResponse.getAccountId());
        assertActions.verifyStringKey(loginResponse.getName(), "clay maker");


    }
}
