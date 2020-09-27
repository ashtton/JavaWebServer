package me.gleeming.webserver.handler.type.type;

import com.sun.net.httpserver.HttpExchange;
import me.gleeming.webserver.handler.type.APIHandler;

import java.io.IOException;

public abstract class RegularURLPostHandler extends APIHandler {
    public RegularURLPostHandler(String reference) { super(reference); }

    public void handle(HttpExchange he) throws IOException {
        called(he.getRequestURI().toString().substring(getReference().length() + 1));

        he.close();
    }

    public abstract void called(String value);
}
