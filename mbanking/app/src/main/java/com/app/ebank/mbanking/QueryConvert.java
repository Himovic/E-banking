package com.app.ebank.mbanking;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Hichem Himovic on 11/06/2017.
 */

public class QueryConvert {
    /**
     * Fetching Json Data
     * */
    public static double FetchCurrencyData(double EditTextValue,String requestUrl,String To){
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("QueryExchange", "Problem making the HTTP request.", e);
        }
        double ValueCurrency = extractFeaturesFromJson(jsonResponse,To);
        return ValueCurrency*EditTextValue;
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
                Log.e("QueryConvert","JsonResponse in makehttprequest is : "+jsonResponse);
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

    public static double extractFeaturesFromJson(String exchangeJson,String To){
        if(TextUtils.isEmpty(exchangeJson)){
            return 0.0;
        }
        double ExchangeRate=0.0;
        try{
            JSONObject jsonObject = new JSONObject(exchangeJson);
            JSONObject currencyValue = jsonObject.getJSONObject("rates");
            ExchangeRate = currencyValue.getDouble(To);
        }catch (Exception ex){
            Log.e("QueryExchange","Problem parsing the exchange informatin"+exchangeJson,ex);
        }
        Log.e("QueryConvert","Exchange rate is :"+ExchangeRate);
        return ExchangeRate;
    }
}
