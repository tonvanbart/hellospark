package org.vanbart.hellospark;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import spark.Spark;
import spark.utils.IOUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class HelloTest {

    @BeforeClass
    public static void staticSetup() {
        Hello.main(null);
        Spark.awaitInitialization();
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    @Test
    public void responseAlwaysSuccess() throws Exception {
        // when a request is made to "/world"
        TestResponse testResponse = request("GET", "/world");

        // the request should succeed
        assertThat(testResponse.status, is(200));

        // and the response DTO always has a status "success"
        ObjectMapper objectMapper = new ObjectMapper();
        HelloDto helloDto = objectMapper.readValue(testResponse.body, HelloDto.class);
        assertThat(helloDto.getStatus(), is("success"));
        assertNotNull(helloDto.getMessage());
    }

    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }
}
