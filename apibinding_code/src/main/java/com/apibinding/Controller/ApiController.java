package com.apibinding.Controller;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiController {

    public String getImageUrl(String Category)  {
        
        String Link="https://api.unsplash.com/photos/random?client_id=GuNFIAp7OJaPNakC06t6f4VptgoMCQNm_NGQEldoshc&query=" + Category;
        String imageUrl;

        try {
            URL url = new URL(Link);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection(); 
            httpURLConnection.setRequestMethod("GET");
            
            BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String line;
            StringBuilder response = new StringBuilder();

            while ((line=br.readLine())!=null) {
                response.append(line);
            }

            JSONObject obj = new JSONObject(response.toString());
            imageUrl = obj.getJSONObject("urls").getString("small");
            System.out.println(imageUrl);
            return imageUrl;
        } catch (Exception e) {
        }
        return null;

    }
    
}
