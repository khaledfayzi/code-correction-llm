package org.example.code_correction_llm.config;
import io.github.cdimascio.dotenv.Dotenv;
public class EnvLoader {

    //.env wird genau einmal geladen
    private static final Dotenv dotenv = Dotenv.configure()
            .directory(".")
            .load();

    // API key holen
    public static String getApiKey() {
        String apikey = dotenv.get("GEMINI_API_KEY");

        //fehler wenn key fehlt
        if (apikey == null || apikey.isEmpty()) {
            throw new RuntimeException("GEMINI_API_KEY fehlt in .env Datei");

        }
        return apikey;
    }




}