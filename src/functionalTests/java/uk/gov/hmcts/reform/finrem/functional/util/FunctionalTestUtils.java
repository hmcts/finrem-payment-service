package uk.gov.hmcts.reform.finrem.functional.util;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.pdfbox.cos.COSDocument;
import org.pdfbox.pdfparser.PDFParser;
import org.pdfbox.pdmodel.PDDocument;
import org.pdfbox.util.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.ResourceUtils;
import uk.gov.hmcts.reform.finrem.functional.SolCCDServiceAuthTokenGenerator;
import uk.gov.hmcts.reform.finrem.functional.TestContextConfiguration;
import uk.gov.hmcts.reform.finrem.functional.idam.IdamUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;


@ContextConfiguration(classes = TestContextConfiguration.class)
@Component
public class FunctionalTestUtils {

    @Value("${idam.api.url}")
    public String baseServiceOauth2Url = "";
    @Autowired
    protected SolCCDServiceAuthTokenGenerator serviceAuthTokenGenerator;
    @Value("${user.id.url}")
    private String userId;
    @Value("${idam.username}")
    private String idamUserName;
    @Value("${idam.userpassword}")
    private String idamUserPassword;
    @Value("${idam.s2s-auth.microservice}")
    private String microservice;
    @Autowired
    private IdamUtils idamUtils;


    public String getJsonFromFile(String fileName) {
        try {
            File file = ResourceUtils.getFile(this.getClass().getResource("/json/" + fileName));
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Headers getHeadersWithUserId() {

        return Headers.headers(
            new Header("ServiceAuthorization", serviceAuthTokenGenerator.generateServiceToken()),
            new Header("user-roles", "caseworker-divorce"),
            new Header("user-id", userId));
    }

    public Headers getHeader() {
        return Headers.headers(
                new Header("Authorization", "Bearer "
                        + idamUtils.generateUserTokenWithNoRoles(idamUserName, idamUserPassword)));
    }

    public Headers getHeaders() {
        return Headers.headers(
            new Header("Authorization", "Bearer "
                + idamUtils.generateUserTokenWithNoRoles(idamUserName, idamUserPassword)),
            new Header("Content-Type", ContentType.JSON.toString()));
    }


}
