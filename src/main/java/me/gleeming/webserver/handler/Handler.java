package me.gleeming.webserver.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.Getter;
import me.gleeming.webserver.util.FileUtil;

import java.io.*;

public class Handler implements HttpHandler {
    private final String html;
    public Handler(String html) {
        this.html = html;
    }
    public void handle(HttpExchange httpExchange) throws IOException {
        byte[] bytes = FileUtil.getFile(html).getBytes();
        httpExchange.sendResponseHeaders(200, bytes.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(bytes);
        os.close();
    }
}
