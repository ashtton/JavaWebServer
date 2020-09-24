package me.gleeming.webserver.handler.type.type;

import com.sun.net.httpserver.HttpExchange;
import me.gleeming.webserver.handler.type.APIHandler;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public abstract class GetHandler extends APIHandler {
    public GetHandler(String reference) { super(reference); }

    public void handle(HttpExchange he) throws IOException {
        InputStreamReader isr = new InputStreamReader(he.getRequestBody(), StandardCharsets.UTF_8);

        String response;
        Document document = Document.parse(URLDecoder.decode(new BufferedReader(isr).readLine(), System.getProperty("file.encoding")));
        if(!APIHandler.getToken().equals("") && !document.getString("TOKEN").equals(APIHandler.getToken())) {
            response = "Invalid authentication token";
            he.sendResponseHeaders(403, response.length());
        } else {
            response = requested(document).toJson();
            he.sendResponseHeaders(200, response.length());
        }

        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public abstract Document requested(Document document);
}
