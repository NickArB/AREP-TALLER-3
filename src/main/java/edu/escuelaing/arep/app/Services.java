package edu.escuelaing.arep.app;

import static edu.escuelaing.arep.app.HTTPServer.get;
import static edu.escuelaing.arep.app.HTTPServer.post;

import java.io.IOException;

public class Services {
    public static void main(String[] args) throws IOException {
        
        get("/get-test", (req,rep) -> {
            rep.setContentType("html");
            rep.setHeader(rep.OKResponse());
            rep.setBody("<!DOCTYPE html>\r\n" + //
                        "<html>\r\n" + //
                        "    <body>\r\n" + //
                        "        <h1>The get method works!</h1>\r\n" + //
                        "    </body>\r\n" + //
                        "</html>");
            return rep.createAndGetResponse();
        });

        get("/read-query", (req,rep) -> {
            rep.setContentType("html");
            rep.setHeader(rep.OKResponse());
            rep.setBody("<!DOCTYPE html>\r\n" + //
                        "<html>\r\n" + //
                        "    <body>\r\n" + //
                        "        <h1>The query from the URL is " + req.getQuery() + "</h1>\r\n" + //
                        "    </body>\r\n" + //
                        "</html>");
            return rep.createAndGetResponse();
        });

        post("/post-test", (req, rep) -> {
            rep.setContentType("html");
            rep.setHeader(rep.createdResponse());
            rep.setBody("<!DOCTYPE html>\r\n" + //
                        "<html>\r\n" + //
                        "    <body>\r\n" + //
                        "        <h1>Posted content in the server!</h1>\r\n" + //
                        "    </body>\r\n" + //
                        "</html>");
            return rep.createAndGetResponse();
        });
        

        HTTPServer.getInstance();
        HTTPServer.runServer(args);
    }
}
