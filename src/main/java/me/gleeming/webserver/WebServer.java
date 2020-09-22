package me.gleeming.webserver;

import com.sun.net.httpserver.HttpServer;
import lombok.Getter;
import me.gleeming.webserver.handler.Handler;
import me.gleeming.webserver.pages.Page;
import me.gleeming.webserver.replacer.Replacer;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;

public class WebServer {
    @Getter private static WebServer instance;
    @Getter private final HashMap<String, Replacer> replacers = new HashMap<>();
    @Getter private HttpServer server;
    public WebServer(int port) {
        try {
            instance = this;
            server = HttpServer.create(new InetSocketAddress(port), 0);

            server.setExecutor(null);
            server.start();
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("[WebServer] Error creating web socket on port " + port);
        }
    }
}
