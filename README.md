Quick testdrive of Spark-Java.

To build an executable jar and run it:

    mvn clean compile assembly:single
    java -jar target/hello-spark-1.0-SNAPSHOT-jar-with-dependencies.jar
    
To get a response (I'm using the excellent [HTTPie](https://httpie.org/) tool):

    http :4567/world
    
Which should give you something like

    HTTP/1.1 200 OK
    Content-Type: application/json
    Date: Thu, 10 May 2018 21:19:51 GMT
    Server: Jetty(9.4.8.v20171121)
    Transfer-Encoding: chunked
    
    {
        "message": "Good day",
        "status": "success"
    }

Apart from Spark-Java, the only dependencies are slf4j and project Lombok.

    
    