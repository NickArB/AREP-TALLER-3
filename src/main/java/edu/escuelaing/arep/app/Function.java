package edu.escuelaing.arep.app;

import java.net.URI;

interface Function {
    public String handle(String requestQuery, HTTPResponse response);
}
