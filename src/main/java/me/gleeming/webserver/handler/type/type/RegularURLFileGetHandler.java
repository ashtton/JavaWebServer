package me.gleeming.webserver.handler.type.type;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import me.gleeming.webserver.handler.type.APIHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public abstract class RegularURLFileGetHandler extends APIHandler {
    public RegularURLFileGetHandler(String reference) { super(reference); }

    public void handle(HttpExchange he) throws IOException {
        Headers headers = he.getResponseHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        byte[] bytes = Files.readAllBytes(requested(he.getRequestURI().toString().substring(getReference().length() + 1)).toPath());
        he.sendResponseHeaders(200, bytes.length);

        try (OutputStream os = he.getResponseBody()) { os.write(bytes); }

        he.close();
    }

    public abstract File requested(String given);
}
