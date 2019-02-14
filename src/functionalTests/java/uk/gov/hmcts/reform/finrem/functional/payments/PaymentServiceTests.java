package uk.gov.hmcts.reform.finrem.functional.payments;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import uk.gov.hmcts.reform.finrem.functional.IntegrationTestBase;

import static org.junit.Assert.assertTrue;

@RunWith(SerenityRunner.class)
public class PaymentServiceTests extends IntegrationTestBase {

    private static String FEE_LOOKUP = "/payments/fee-lookup";
    private static String PBA_VALIDATE = "/payments/pba-validate/";
    private static String PBA_PAYMENT = "/payments/pba-payment";


    @Value("${payment.service.uri}")
    private String paymenturl;

    @Value("${idam.s2s-auth.microservice}")
    private String microservice;

    @Value("${idam.s2s-auth.secret}")
    private String authClientSecret;


    @Test
    public void verifyGetFeeLoopUpTest() {

        validatePostSuccess(FEE_LOOKUP);

    }

    @Test
    public void verifyPBAValidationTest() {
        validatePostSuccessForPBAValidation(PBA_VALIDATE);
    }

    @Test
    public void verifyPBAPaymentTest() {
        validatePostSuccessForPBAPayment(PBA_PAYMENT);

    }


    private void validatePostSuccess(String url) {
        SerenityRest.given()
                .relaxedHTTPSValidation()
                .when().get(paymenturl + url)
                .then()
                .assertThat().statusCode(200);
    }



    public void validatePostSuccessForPBAValidation(String url) {
        SerenityRest.given()
                .relaxedHTTPSValidation()
                .headers(utils.getHeader())
                .param("pbaNumber", "PBA123")
                .when().get(paymenturl + url)
                .then()
                .assertThat().statusCode(200);
    }


    public void validatePostSuccessForPBAPayment(String url) {
        SerenityRest.given()
                .relaxedHTTPSValidation()
                .headers(utils.getHeader())
                .body(utils.getJsonFromFile("paymentRequestPayload.json"))
                .when().post(paymenturl + url)
                .then()
                .assertThat().statusCode(200);
    }

}