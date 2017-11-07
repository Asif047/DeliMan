package com.deliman_poc.rokomari.deliman_rokomari_poc;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by DEVPC on 05/11/2017.
 */

public class ApiCall {

    private String TAG="#RESPONSE: ";


    //GET network request
    public static String GET(OkHttpClient client, String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        //Log.d("#RESPONSE: ","INTO THE GET METHOD");
        return response.body().string();
    }

    //POST network request
    public static ResponseBody POST(OkHttpClient client, String url, RequestBody body) throws IOException {


        ResponseBody responseBody;

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json; charset=utf-8")
                .build();
        Response response = client.newCall(request).execute();
        Log.d("#RESPONSE POST: ",response.body().string());


        responseBody=response.body();
        Log.d("RESPONSE BODY: ",responseBody.toString());
        return responseBody;

    }


}
