package me.gleeming.webserver.pages;

import lombok.Getter;

public class Page {
    @Getter private final String address;
    @Getter private final String html;
    public Page(String address, String html) {
        this.address = address;
        this.html = html;
    }
}
