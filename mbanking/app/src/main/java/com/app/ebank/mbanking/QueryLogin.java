package com.app.ebank.mbanking;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Hichem Himovic on 07/06/2017.
 */

public class QueryLogin {
    private static final String LOG_TAG=QueryLogin.class.getName();
    /**
     * Return the List that contains the client that he done the authentification
     * */
    public static ClientModel fetchClientData(String requestUrl){
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        ClientModel client = extractFeaturesFromJson(jsonResponse);
        return client;
    }
    /**
     * Returns new URL object from the given string URL.
     */
    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            Log.e(LOG_TAG,"AFTER GET METHOD");
            urlConnection.connect();
            Log.e(LOG_TAG,"AFTER CONNECTING");

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the client JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
    /**
     * Extraction the data from database
     * */
    public static ClientModel extractFeaturesFromJson(String dataJson){
        ClientModel client =null;
        if(TextUtils.isEmpty(dataJson)){
            return null;
        }
        if(dataJson == null){
            return client;
        }
        try{
            Log.e(LOG_TAG,dataJson);
            JSONObject jsonResponse = new JSONObject(dataJson);
            String NOM = jsonResponse.getString("Nom");
            String PRENOM = jsonResponse.getString("Prenom");
            String DATE_REC = jsonResponse.getString("DateN");
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date DATEN = format.parse(DATE_REC);
            String DATEEN_REC = jsonResponse.getString("DateEn");
            Date DATEEN = format.parse(DATEEN_REC);
            String EMAIL = jsonResponse.getString("Email");
            String NUMCOMPTE = jsonResponse.getString("NumCompte");
            double SOLDE = jsonResponse.getDouble("Solde");
            String ADRESSE = jsonResponse.getString("Adresse");
            int IDCLIENT = jsonResponse.getInt("idClient");
            client = new ClientModel(IDCLIENT,NOM,PRENOM,DATEN,EMAIL,DATEEN,SOLDE,ADRESSE,NUMCOMPTE);
        }catch (Exception e){
            Log.e(LOG_TAG,"Problem with parsin json",e);
        }
        return client;
    }
}
