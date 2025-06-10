package finalmission.reservation.controller;

import finalmission.accommodation.dto.CreateAccommodationRequest;
import finalmission.dateprice.dto.AddDatePriceRequest;
import finalmission.reservation.dto.CreateReservationRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.time.LocalDate;
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
class ReservationControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 특정_기간에_대해_예약을_생성할_수_있다() {
        // given
        setAccommodation();
        setDatePrices();

        CreateReservationRequest request = new CreateReservationRequest(
                LocalDate.of(2025, 6, 11),
                LocalDate.of(2025, 6, 14),
                "예약자 이름",
                "010-1234-5678",
                "oz122@naver.com",
                1L
        );

        // when & then
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/reservations")
                .then().log().all()
                .statusCode(201)
                .body("id", Matchers.equalTo(1))
                .body("totalPrice", Matchers.equalTo(60000));
    }

    @Test
    void 특정_기간에_대해_예약_목록을_볼_수_있다() {
        // given
        setAccommodation();
        setDatePrices();
        setReservation();

        // when & then
        RestAssured.given().log().all()
                .when().get("/reservations?year=2025&month=6")
                .then().log().all()
                .statusCode(200)
                .body("size()", Matchers.is(1));
    }

    void setAccommodation() {
        CreateAccommodationRequest accommodationRequest = new CreateAccommodationRequest("숙소 이름", "숙소 설명", "숙소 주소");
        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(accommodationRequest)
                .when().post("/accommodations")
                .then().log().all()
                .statusCode(201);
    }

    void setDatePrices() {
        long accommodationId = 1;
        for (int i = 1; i <= 4; i++) { // 11일 ~ 14일에 대해 가격 등록 : 10000, 20000, 30000, 40000
            AddDatePriceRequest request = new AddDatePriceRequest(LocalDate.of(2025, 6, 10 + i), 10000L * i,
                    accommodationId);
            RestAssured.given().log().all()
                    .contentType(ContentType.JSON)
                    .body(request)
                    .when().post("/date-price")
                    .then().log().all()
                    .statusCode(201);
        }
    }

    void setReservation() {
        CreateReservationRequest request = new CreateReservationRequest(
                LocalDate.of(2025, 6, 11),
                LocalDate.of(2025, 6, 14),
                "예약자 이름",
                "010-1234-5678",
                "oz122@naver.com",
                1L
        );

        RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("/reservations")
                .then().log().all()
                .statusCode(201);
    }
}
