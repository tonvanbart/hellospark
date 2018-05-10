package org.vanbart.hellospark;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

import java.util.Random;

public class Hello {

    private static final String[] messages = {
            "Hello",
            "Good day",
            "Hey there",
            "Salutations",
            "Greetings",
            "Sawadee",
            "Live long and prosper"
    };

    private static final Random random = new Random();

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger log = LoggerFactory.getLogger(Hello.class);

    public static void main(String[] args) {
        Spark.get("/hello", (req, resp) -> "Hello, World from Spark");
        Spark.get("/world", (req, resp) -> {
            resp.type("application/json");
            return objectMapper.writeValueAsString(hello()); }
        );
    }

    private static HelloDto hello() {
        log.info("hello()");
        int nextInt = random.nextInt(messages.length);
        return new HelloDto("success", messages[nextInt]);
    }
}
