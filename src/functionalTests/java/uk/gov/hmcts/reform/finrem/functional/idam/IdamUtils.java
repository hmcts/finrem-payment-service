package uk.gov.hmcts.reform.finrem.functional.idam;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.gov.hmcts.reform.finrem.functional.util.FunctionalTestUtils;

import java.util.Base64;

@Slf4j
@Component
public class IdamUtils implements IdamUserClient {

    @Autowired
    private FunctionalTestUtils utils;

    @Value("${idam.api.url}")
    private String idamUserBaseUrl;

    @Value("${idam.whitelist.url}")
    private String idamRedirectUri;

    @Value("${idam.username}")
    private String idamUserName;
    @Value("${idam.userpassword}")
    private String idamUserPassword;

    @Value("${idam.api.secret}")
    private String idamSecret;


    public String generateUserTokenWithNoRoles(String username, String password) {

        String userLoginDetails = String.join(":", username, password);
        final String authHeader = "Basic " + new String(Base64.getEncoder().encode((userLoginDetails).getBytes()));


        Response response = RestAssured.given()
            .header("Authorization", authHeader)
            .relaxedHTTPSValidation()
            .post(idamCodeUrl());

        if (response.getStatusCode() >= 300) {
            throw new IllegalStateException("Token generation failed with code: " + response.getStatusCode()
                + " body: " + response.getBody().prettyPrint());
        }

        response = RestAssured.given()
            .relaxedHTTPSValidation()
            .post(idamTokenUrl(response.getBody().path("code")));

        String token = response.getBody().path("access_token");
        System.out.println("Printing autho Token :" + token);

        return token;
    }

    private String idamCodeUrl() {
        String myUrl = idamUserBaseUrl + "/oauth2/authorize"
            + "?response_type=code"
            + "&client_id=finrem"
            + "&redirect_uri=" + idamRedirectUri;

        return myUrl;
    }

    private String idamTokenUrl(String code) {
        String myUrl = idamUserBaseUrl + "/oauth2/token"
            + "?code=" + code
            + "&client_id=finrem"
            + "&client_secret=" + idamSecret
            + "&redirect_uri=" + idamRedirectUri
            + "&grant_type=authorization_code";

        return myUrl;
    }

}
