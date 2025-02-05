package com.example.cheapesttransferroute;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TransferApiTest {

    @BeforeAll
    static void setUpBeforeClass(){
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.useRelaxedHTTPSValidation();
    }

    @Test
    void getTransfers() {

        String requestBody = """
        {
            "maxWeight": 15,
            "availableTransfers": [
                { "weight": 5, "cost": 10 },
                { "weight": 10, "cost": 20 },
                { "weight": 3, "cost": 5 },
                { "weight": 8, "cost": 15 }
            ]
        }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/transfer/calculate")
                .then().statusCode(200)
                .body("totalCost", is(30))
                .body("totalWeight", is(15))
                .body("selectedTransfers.size()", is(2))
        ;
    }

    @Test
    void getInvalidTransfers() {

        String requestBody = """
        {
            "maxWeight": -15,
            "availableTransfers": [
                { "weight": 5, "cost": 10 },
                { "weight": 10, "cost": 20 },
                { "weight": 3, "cost": 5 },
                { "weight": 8, "cost": 15 }
            ]
        }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/transfer/calculate")
                .then().statusCode(400)
        ;
    }

    @Test
    void SimpleUserScenario() {

        given()
                .contentType(ContentType.JSON)
                .when().get("/transfer/health-check")
                .then().statusCode(200)
                .body(equalTo("All Good"));

        String requestBody = """
        {
            "maxWeight": 15,
            "availableTransfers": [
                { "weight": 5, "cost": 10 },
                { "weight": 10, "cost": 20 },
                { "weight": 3, "cost": 5 },
                { "weight": 8, "cost": 15 }
            ]
        }
        """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/transfer/calculate")
                .then().statusCode(200)
                .body("totalCost", is(30))
                .body("totalWeight", is(15))
                .body("selectedTransfers.size()", is(2))
        ;
    }
}
