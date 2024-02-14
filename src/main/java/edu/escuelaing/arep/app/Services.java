package edu.escuelaing.arep.app;

import static edu.escuelaing.arep.app.HTTPServer.get;
import static edu.escuelaing.arep.app.HTTPServer.post;

import java.io.IOException;

public class Services {
    public static void main(String[] args) throws IOException {
        HTTPServer server = HTTPServer.getInstance();
        
        get("/get-test", (req,rep) -> {
            String response = "";
            rep.setContentType("html");
            response += rep.OKResponse();
            response += "<!DOCTYPE html>\r\n" + //
                                "<html>\r\n" + //
                                "    <body>\r\n" + //
                                "        <h1>The get method works!</h1>\r\n" + //
                                "    </body>\r\n" + //
                                "</html>";
            System.out.println(response);
            return response;
        });

        get("/get-test1", (req,rep) -> {
            String response = "";
            rep.setContentType("html");
            response += rep.OKResponse();
            response += "<!DOCTYPE html>\r\n" + //
                                "<html>\r\n" + //
                                "    <body>\r\n" + //
                                "        <h1>The get method works!</h1>\r\n" + //
                                "    </body>\r\n" + //
                                "</html>";
            System.out.println(response);
            return response;
        });

        post("/post-test", (req, rep) -> {
            String response = "";
            rep.setContentType("html");
            response += rep.createdResponse();
            response += "<!DOCTYPE html>\r\n" + //
                                "<html>\r\n" + //
                                "    <body>\r\n" + //
                                "        <h1>Posted content in the server!</h1>\r\n" + //
                                "    </body>\r\n" + //
                                "</html>";
            System.out.println(response);
            return response;
        });
        

        HTTPServer.getInstance();
        HTTPServer.runServer(args);
    }
}
