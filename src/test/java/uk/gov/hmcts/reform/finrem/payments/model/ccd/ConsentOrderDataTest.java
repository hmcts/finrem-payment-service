package uk.gov.hmcts.reform.finrem.payments.model.ccd;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConsentOrderDataTest extends ConsentOrderTest {
    ConsentOrderData orderData;

    @Before
    public void setUp() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        orderData = mapper.readValue(new File(getClass()
                .getResource("/fixtures/model/consent-order-data.json").toURI()), ConsentOrderData.class);
        order = orderData.getConsentOrder();
    }

    @Test
    public void shouldCreateConsentOrderDataFromJson() {
        assertThat(orderData.getId(), is("1"));
    }
}