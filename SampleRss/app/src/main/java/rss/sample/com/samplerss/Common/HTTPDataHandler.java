package rss.sample.com.samplerss.Common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Admin on 7/10/2017.
 */

public class HTTPDataHandler {

    static String stream="";

    public HTTPDataHandler(){}

    public String GetHTTPDataHandler(String urlString) throws IOException {


        try {
                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

                if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader r  = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder sb = new StringBuilder();
                    String line="";
                    while((line = r.readLine()) != null)
                        sb.append(line);
                        stream = sb.toString();
                    urlConnection.disconnect();
                }

            } catch (Exception e) {
                return  null;
            }
            return  stream;

    }
}
