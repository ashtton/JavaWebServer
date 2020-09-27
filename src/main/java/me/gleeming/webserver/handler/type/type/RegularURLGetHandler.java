package me.gleeming.webserver.handler.type.type;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import me.gleeming.webserver.handler.type.APIHandler;
import org.bson.Document;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

public abstract class RegularURLGetHandler extends APIHandler {
    public RegularURLGetHandler(String reference) { super(reference); }

    public void handle(HttpExchange he) throws IOException {
        Headers headers = he.getResponseHeaders();
        headers.add("Content-type", "application/json");
//        headers.add("NIGGER", "NIGGER");
        headers.add("Access-Control-Allow-Origin", "*");

        String response = requested(he.getRequestURI().toString().substring(getReference().length() + 1)).toJson();
        he.sendResponseHeaders(200, response.length());

        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();

        he.close();
    }

    public abstract Document requested(String given);
}
