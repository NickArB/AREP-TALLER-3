package edu.escuelaing.arep.app;

import static edu.escuelaing.arep.app.HTTPServer.get;

import java.io.IOException;

public class Services {
    public static void main(String[] args) throws IOException {
        get("/hello", () -> "Hello world");
    }
}
