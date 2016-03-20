package it.bamboolab.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * Created by Enrico on 01/11/2015.
 */
public class AlfrescoUtils {

    public static void ldapSync() throws Exception{

        String urlToRead = ApplicationProperties.getProperty("alfrescoSync", "");
        String httpAuthUser = ApplicationProperties.getProperty("alfrescoUserName", "");
        String httpAuthPass = ApplicationProperties.getProperty("alfrescoPassword", "");

        String up = httpAuthUser + ":" + httpAuthPass;
        String encoded = Base64.getEncoder().encodeToString(up.getBytes());

        HttpURLConnection conn = null;

        URL url = new URL(urlToRead);

        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Basic " + encoded);

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        conn.disconnect();
    }
}
