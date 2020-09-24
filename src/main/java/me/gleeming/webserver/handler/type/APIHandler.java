package me.gleeming.webserver.handler.type;

import com.sun.net.httpserver.HttpHandler;
import lombok.Getter;

public abstract class APIHandler implements HttpHandler {
    @Getter private final String reference;
    public APIHandler(String reference) {
        this.reference = reference.startsWith("/") ? reference : "/" + reference;
    }
}
