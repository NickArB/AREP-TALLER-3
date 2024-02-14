package edu.escuelaing.arep.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HTTPResponseTest {
    
    @Test
    public void shouldgetOKResponse(){
        HTTPResponse server = new HTTPResponse("html", null);
        String correctString = "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/html\r\n" +
                                "\r\n";
        assertEquals(correctString, server.OKResponse());
    }

    @Test
    public void shouldgetNotFoundResponse(){
        HTTPResponse server = new HTTPResponse("html",null);
        String correctString = "HTTP/1.1 404 Not Found\r\n" +
                                "Content-Type: text/html\r\n" +
                                "\r\n";
        assertEquals(correctString, server.NotFoundResponse());
    }

    @Test
    public void shouldModifyContentType(){
        HTTPResponse server = new HTTPResponse("html", null);
        // Get request
        server.setContentType("json");
        String correctString = "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/json\r\n" +
                                "\r\n";
        assertEquals(correctString, server.OKResponse());
        // Not found request
        server.setContentType("html");
        correctString = "HTTP/1.1 404 Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "\r\n";
        assertEquals(correctString, server.NotFoundResponse());
    }

    @Test
    public void shouldChangeContentTypeToImg(){
        HTTPResponse server = new HTTPResponse("html",null);
        // Get request
        server.setContentType("json");
        String correctString = "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: text/json\r\n" +
                                "\r\n";
        assertEquals(correctString, server.OKResponse());
        // Get image
        server.setContentType("jpeg");
        correctString = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: image/jpeg\r\n" +
                        "\r\n";
        assertEquals(correctString, server.OKResponse());
    }

    @Test
    public void shouldReturnCreatedResponse(){
        HTTPResponse server = new HTTPResponse("html",null);
        // Post request
        String correctString = "HTTP/1.1 201 Created\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n";
        assertEquals(correctString, server.createdResponse());
    }

    @Test
    public void shouldSetAndGetStaticPath(){
        HTTPResponse server = new HTTPResponse("html",null);
        // Setting Static Path
        server.setStaticPath("target");
        // Getting Static Path
        assertEquals("target", server.getStaticPath());
    }

    @Test
    public void shouldSetAndGetBody(){
        HTTPResponse server = new HTTPResponse("html",null);
        // Setting Static Path
        String correctString = "<!DOCTYPE html>\r\n" + //
                        "<html>\r\n" + //
                        "    <body>\r\n" + //
                        "        <h1>This is an example</h1>\r\n" + //
                        "    </body>\r\n" + //
                        "</html>\r\n";
        server.setBody(correctString);
        // Getting Static Path
        assertEquals(correctString, server.getBody());
    }

    @Test
    public void shouldSetAndGetHeader(){
        HTTPResponse server = new HTTPResponse("html",null);
        // Setting Static Path
        String correctString = "HTTP/1.1 201 Created\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n";
        server.setHeader(correctString);
        // Getting Static Path
        assertEquals(correctString, server.getHeader());
    }

    @Test
    public void shouldCreateAndReturnAResponse(){
        HTTPResponse server = new HTTPResponse("html",null);
        // Correct answer
        String correctString = "HTTP/1.1 201 Created\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>\r\n" 
                    + "<html>\r\n" 
                    + "    <body>\r\n"
                    + "        <h1>This is an example</h1>\r\n" 
                    + "    </body>\r\n"
                    + "</html>\r\n";
        // Setting headers and body
        server.setHeader(server.createdResponse());
        server.setBody("<!DOCTYPE html>\r\n" 
                        + "<html>\r\n" 
                        + "    <body>\r\n"
                        + "        <h1>This is an example</h1>\r\n" 
                        + "    </body>\r\n"
                        + "</html>\r\n");
        // Comparing
        assertEquals(correctString, server.createAndGetResponse());
    }
}
