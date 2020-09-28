package me.gleeming.webserver.util;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HTTPCreator {
    @Getter @Setter private static String token = "";

    @Getter private final String address;
    @Getter private final Document requesting;
    public HTTPCreator(String address, Document requesting) {
        this.address = address;
        this.requesting = requesting;

        if(!token.equals("")) requesting.append("TOKEN", token);
    }

    public Document performGET() {
        try {

            URL obj = new URL(!address.startsWith("http") ? "http://" : "" + address);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            if(requesting != null) {
                con.setDoOutput(true);
                DataOutputStream out = new DataOutputStream(con.getOutputStream());
                out.writeBytes(URLEncoder.encode(requesting.toJson()));
                out.flush();
                out.close();
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            return Document.parse(response.toString());
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public void performPOST() {
        try {
            URL obj = new URL(!address.startsWith("http") ? "http://" : "" + address);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(URLEncoder.encode(requesting.toJson()));
            out.flush();
            out.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
