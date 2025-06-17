package finalmission.accommodation.controller;

import finalmission.accommodation.dto.CreateAccommodationRequest;
import finalmission.auth.dto.LoginRequest;
import finalmission.member.domain.Role;
import finalmission.member.dto.RegisterRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class AccommodationControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 숙소를_생성할_수_있다() {
        // given
        CreateAccommodationRequest request = new CreateAccommodationRequest("숙소 이름", "숙소 설명", "숙소 주소");

        RegisterRequest registerRequest = new RegisterRequest("test@email.com", "password", Role.ADMIN);
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(registerRequest)
                .when().post("/members")
                .then().log().all()
                .statusCode(201);

        LoginRequest loginRequest = new LoginRequest("test@email.com", "password");
        String token = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(loginRequest)
                .when().post("/login")
                .then().log().all()
                .extract().cookie("token");
        
        // when & then
        RestAssured.given().log().all()
                .cookie("token", token)
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/accommodations")
                .then().log().all()
                .statusCode(201)
                .body("id", Matchers.equalTo(1));
    }
}
