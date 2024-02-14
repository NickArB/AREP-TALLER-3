package edu.escuelaing.arep.app;

import java.net.URI;
/**
 * Interface that defines a method to handle a request and generate a response.
 */
interface Function {

    /**
     * Handles a request and generates a response.
     *
     * @param requestQuery The URI of the request.
     * @param response     The HTTP response to generate.
     * @return The generated response as a String.
     */
    public String handle(URI requestQuery, HTTPResponse response);
}
