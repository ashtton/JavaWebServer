package me.gleeming.webserver.website;

import lombok.Getter;
import lombok.Setter;
import me.gleeming.webserver.WebServer;
import me.gleeming.webserver.handler.Handler;
import me.gleeming.webserver.pages.Page;
import me.gleeming.webserver.replacer.Replacer;

public abstract class Website {
    @Getter @Setter private int port;
    public Website() { }
    public Website(int port) { this.port = port; }

    public void addPage(Page page) {
        if(WebServer.getInstance() == null) {
            System.out.println("[Website] You must enable your web server before adding pages!");
        } else {
            WebServer.getInstance().getServer().createContext(page.getAddress(), new Handler(page.getHtml()));
        }
    }

    public void addReplacer(String toReplace, Replacer replacer) {
        WebServer.getInstance().getReplacers().put(toReplace, replacer);
    }


    public void enable() {
        if(port == 0) {
            System.out.println("[Website] You cannot enable the website until you have set a port!");
        } else {
            new WebServer(port);
        }
    }
}
