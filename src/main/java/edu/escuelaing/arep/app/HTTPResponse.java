package edu.escuelaing.arep.app;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The `HTTPResponse` class provides methods for generating HTML pages and JSON error messages
 * to be sent as responses in an HTTP server. It includes a method to create the index page, a method
 * for the "not found" page, and a method to return a JSON error message.
 * @author Nicolas Ariza Barbosa
 */
public class HTTPResponse {

    private String CONTENT_TYPE;
    private String[] mediaFilesLst = {"jpeg", "png", "ico"};
    private Socket socketClient = null;
    private String STATIC_PATH = "web-files";
    private String RESPONSE = "";
    private String HEADER = "";
    private String BODY = "";

    /**
     * Constructs an `HTTPResponse` object with the specified content type.
     * @param contentType The content type for the HTTP response.
     */
    public HTTPResponse(String contentType, Socket client){
        this.CONTENT_TYPE = contentType;
        this.socketClient = client;
    }

    public void setClient(Socket client){
        this.socketClient = client;
    }

    /**
     * Generates the HTTP response headers for a successful (OK) response with the specified content type.
     * @return The HTTP response headers for a successful response.
     */
    public String OKResponse(){
        String reponse = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/" + this.CONTENT_TYPE + "\r\n" +
                        "\r\n";

        for(String contentType: this.mediaFilesLst){
            if(CONTENT_TYPE.equals(contentType)){
                return "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: image/" + this.CONTENT_TYPE + "\r\n" +
                "\r\n";
            }
        }
        return reponse;
    }

    public String createdResponse(){
        String response = "HTTP/1.1 201 Created\r\n"
                    + "Content-Type: text/" + this.CONTENT_TYPE +"\r\n"
                    + "\r\n";
        return response;
    }

    /**
     * Generates the HTTP response headers for a "not found" response with the specified content type.
     * @return The HTTP response headers for a "not found" response.
     */
    public String NotFoundResponse(){
        return "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/" + this.CONTENT_TYPE + "\r\n" +
                "\r\n";
    }

    /**
     * Sets the content type for the HTTP response headers.
     * @param newContentType The new content type to be set.
     */
    public void setContentType(String newContentType) {
        this.CONTENT_TYPE = newContentType;
    }

    /**
     * Generates the HTML content for the index page, which includes a form to query movies and a
     * div to display the API response as an HTML table.
     * @param requestedfile The URI of the requested file
     * @param client The socket used to communicate with the client
     * @throws IOException 
     */
    public void sendFile(URI requestedfile){
        try{
            OutputStream out = socketClient.getOutputStream();
            Path imagePath = Paths.get("web-files", requestedfile.getPath());
            byte[] imageData = Files.readAllBytes(imagePath);
            out.write(imageData);
            out.flush();
            out.close();
        }catch (IOException e){
            System.err.println(e);
        }
    }

    /**
     * Calls the HTML content for the "not found" page, indicating that the requested resource does not exist.
     * @throws URISyntaxException 
     * @throws IOException 
     */
    public void sendNotFoundPage() throws IOException, URISyntaxException{
        sendFile(new URI("/not-found.html"));
    }

    public void internalServerError(){
        try {
            sendFile(new URI("/internal-server-error.html"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void setStaticPath(String path){
        this.STATIC_PATH = path;
    }

    public void setBody(String newBody){
        this.BODY = newBody;
    }

    public void setHeader(String newHeader){
        this.HEADER = newHeader;
    }

    public String createAndGetResponse(){
        RESPONSE += HEADER;
        RESPONSE += BODY;
        return RESPONSE;
    }

    
}