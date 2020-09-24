package me.gleeming.webserver.website;

import lombok.Getter;
import lombok.Setter;
import me.gleeming.webserver.WebServer;
import me.gleeming.webserver.handler.type.APIHandler;

public abstract class WebAPI {
    @Getter @Setter private int port;
    public WebAPI() { }
    public WebAPI(int port) { this.port = port; }

    public void addHandler(APIHandler page) {
        if(WebServer.getInstance() == null) {
            System.out.println("[Website] You must enable your webapi before adding pages!");
        } else {
            WebServer.getInstance().getServer().createContext(page.getReference(), page);
        }
    }

    public void setToken(String token) {
        APIHandler.setToken(token);
    }

    public void enable() {
        if(port == 0) {
            System.out.println("[Website] You cannot enable the webapi until you have set a port!");
        } else {
            new WebServer(port, true);
        }
    }
}
