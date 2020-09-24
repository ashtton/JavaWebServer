package me.gleeming.webserver.util;

import me.gleeming.webserver.WebServer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileUtil {
    public static String getFile(String path) {
        ClassLoader classLoader = WebServer.getInstance().getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            if(inputStream != null) {
                String s = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
                for (String string : WebServer.getInstance().getReplacers().keySet()) {
                    s = s.replaceAll("\\{" + string + "\\}", WebServer.getInstance().getReplacers().get(string).replace(string));
                }


                return s;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "kys";
    }
}
