package com.app.ebank.mbanking;

import android.text.TextUtils;
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
import java.util.ArrayList;

/**
 * Created by Hichem Himovic on 10/06/2017.
 */

public class QueryExchange {
    //public  static final String LOG_TAG=QueryExchange.class.getName();
    public static final String []ArrayRates = {"NZD","RON","CZK","GBP","USD","PLN","JPY","AUD","BRL","TRY"};
    public static ArrayList<ExchangeModel> fetchExchangeData(String requestURL){
        URL url = createUrl(requestURL);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("QueryExchange", "Problem making the HTTP request.", e);
        }
        ArrayList<ExchangeModel> AllExchanges = extractFeatureFromJson(jsonResponse);
        return AllExchanges;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    public static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("QueryExchange","Problem building the URL ", e);
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
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("QueryExchange","Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("QueryExchange","Problem retrieving the earthquake JSON results.", e);
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
     * Return a list of {@link ExchangeModel} objects that has been built up from
     * parsing the given JSON response.
     */
    public static ArrayList<ExchangeModel> extractFeatureFromJson(String exchangeJson){
        String base="",date="",rateName="";
        double rateDouble=0.0;
        if(TextUtils.isEmpty(exchangeJson)){
            return null;
        }
        ArrayList<ExchangeModel> AllExchanges = new ArrayList<ExchangeModel>();
        try{
            JSONObject jsonObject = new JSONObject(exchangeJson);
            base = jsonObject.getString("base");
            date = jsonObject.getString("date");
            for(int i=0;i<ArrayRates.length;i++){
                rateName = ArrayRates[i];
                JSONObject rateObject = jsonObject.getJSONObject("rates");
                rateDouble = rateObject.getDouble(rateName);
                ExchangeModel exchange = new ExchangeModel(base,date,rateName,rateDouble);
                AllExchanges.add(exchange);
            }
        }catch (Exception ex){
            Log.e("QueryExchange","Problem parsing the exchange informatin",ex);
        }
        return AllExchanges;
    }
}
