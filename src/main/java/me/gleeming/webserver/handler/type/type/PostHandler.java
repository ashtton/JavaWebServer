package me.gleeming.webserver.handler.type.type;

import com.sun.net.httpserver.HttpExchange;
import me.gleeming.webserver.handler.type.APIHandler;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public abstract class PostHandler extends APIHandler {
    public PostHandler(String reference) { super(reference); }

    public void handle(HttpExchange he) throws IOException {
        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), StandardCharsets.UTF_8);

        Document document = Document.parse(URLDecoder.decode(new BufferedReader(isr).readLine(), System.getProperty("file.encoding")));
        if(APIHandler.getToken().equals("") || document.getString("TOKEN").equals(APIHandler.getToken())) posted(document);
    }

    public abstract void posted(Document document);
}
