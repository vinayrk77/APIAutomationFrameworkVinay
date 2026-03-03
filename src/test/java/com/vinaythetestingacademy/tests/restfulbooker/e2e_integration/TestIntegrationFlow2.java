package com.vinaythetestingacademy.tests.restfulbooker.e2e_integration;

import com.vinaythetestingacademy.base.BaseTest;
import com.vinaythetestingacademy.endpoints.APIConstants;
import com.vinaythetestingacademy.pojos.responsePOJO.BookingResponse;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestIntegrationFlow2 extends BaseTest {

    @Test
    @Owner("Vinay")
    @Description("E2E - Create Booking -> Delete Booking -> Verify Deletion")
    public void testCreateDeleteVerifyFlow() {

        // ================================
        // STEP 1 - CREATE BOOKING
        // ================================

        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);

        response = RestAssured.given(requestSpecification)
                .body(payloadManager.createPayloadBookingAsString())
                .post();

        response.then().log().all().statusCode(200);

        BookingResponse bookingResponse =
                payloadManager.bookingResponseJava(response.asString());

        Integer bookingId = bookingResponse.getBookingid();

        assertActions.verifyStringKeyNotNull(bookingId);

        System.out.println("Created Booking ID: " + bookingId);


        // ================================
        // STEP 2 - DELETE BOOKING
        // ================================

        String token = getToken();

        requestSpecification
                .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId)
                .cookie("token", token);

        response = RestAssured.given(requestSpecification)
                .delete();

        response.then().log().all().statusCode(201);

        System.out.println("Booking Deleted Successfully");


        // ================================
        // STEP 3 - VERIFY BOOKING IS DELETED
        // ================================

        requestSpecification
                .basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingId);

        response = RestAssured.given(requestSpecification)
                .get();

        response.then().log().all().statusCode(404);

        System.out.println("Verified Booking is Deleted (404 Not Found)");
    }
}