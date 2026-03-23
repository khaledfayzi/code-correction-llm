package service;

import config.EnvLoader;
import com.google.gson.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class LLMService {

    //Endpoint von Google Gemini
    private static final String API_URL =
            "https://generativelanguage.googleapis.com/v1/models/gemini-2.5-flash:generateContent?key=";

    private final String apikey = EnvLoader.getApiKey();

    public String generate(String prompt) throws IOException {
        //Verbindung aufbauen

        URL url = new URL(API_URL + apikey);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        //Json  Body bauen (mit Gson )
        String jsonInput = buildJson(prompt);

        //body senden
        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonInput.getBytes());


            //status prüfen
            int status = conn.getResponseCode();

            InputStream is = (status < 400)
                    ? conn.getInputStream()
                    : conn.getErrorStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            String responseString = response.toString();

            // fehler prüfen

            if (status != 200) {
                return handleHttpError(status, responseString);
            }
            //Antwort parsen
            return parseResponse(responseString);

        }catch (IOException e){
            return "Network Error "+e.getMessage();


        }catch (Exception e){
            return "General Error "+e.getMessage();
        }





    }

    private String handleHttpError(int status, String responseString) {
        if (status == 401) {
            return "API key falsch";

        }
        if (status == 429) {
            return "Rate Limit erreicht";
        }
        if (status == 500) {
            return "Internal Server Error";
        }

        return "HTTP fehler : " + status + "\n" + responseString;
    }

    private String parseResponse(String responseString) {
        JsonObject obj =  JsonParser.parseString(responseString).getAsJsonObject();

        try {
            return obj
                    .getAsJsonArray("candidates")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("content")
                    .getAsJsonArray("parts")
                    .get(0).getAsJsonObject()
                    .get("text").getAsString();


        }catch (Exception e){
            return " Fehler beim lesen der Antwort";
        }
    }

    //Json bauen
    public String buildJson(String prompt ){
        JsonObject root = new JsonObject();

        JsonArray contents = new JsonArray();
        JsonObject content = new JsonObject();

        JsonArray parts = new JsonArray();
        JsonObject part = new JsonObject();

        part.addProperty("text", prompt);
        parts.add(part);

        content.add("parts", parts);
        contents.add(content);

        root.add("contents", contents);

        return root.toString();

    }




}