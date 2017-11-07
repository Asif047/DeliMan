package com.deliman_poc.rokomari.deliman_rokomari_poc.CreateUser;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.deliman_poc.rokomari.deliman_rokomari_poc.ApiCall;
import com.deliman_poc.rokomari.deliman_rokomari_poc.ListAdapter;
import com.deliman_poc.rokomari.deliman_rokomari_poc.MainActivity;
import com.deliman_poc.rokomari.deliman_rokomari_poc.R;
import com.deliman_poc.rokomari.deliman_rokomari_poc.ResponseApi;
import com.deliman_poc.rokomari.deliman_rokomari_poc.details.DetailActivity;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class CreateUserActivity extends AppCompatActivity {


    Button btnPost;
    private ProgressDialog pDialog;
    private String path = "https://rokom.herokuapp.com/api/postme";
    OkHttpClient client;
    String jsonString;

    String response;


    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private TextView tvJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);


        btnPost=findViewById(R.id.button_post);
        tvJsonString=findViewById(R.id.textview_json_string);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new PostDataToServer().execute();
            }
        });
    }


    private class PostDataToServer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(CreateUserActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try {

            client = new OkHttpClient();


            //post new starts

            //JsonArray form=new JsonArray();
            JsonObject item=new JsonObject();

            item.addProperty("order_id",26);
            item.addProperty("recipient","Mr.Asif");
            item.addProperty("order_type","Bkash");
            item.addProperty("area","Moghbazar");
            item.addProperty("district","Dhaka");


           //form.add(item);
            Gson gson;
            gson=new Gson();
            jsonString=gson.toJson(item);

//            Log.d("STRING: ",jsonString);



            RequestBody body = RequestBody.create(JSON, jsonString);

            ResponseBody responseBody;


                responseBody = ApiCall.POST(client, path,body);

                String responseBodyString=responseBody.string();


                Log.d("STRING: ",responseBodyString);

            } catch (IOException e) {
                e.printStackTrace();
            }

            //post new ends

//                response = ApiCall.GET(client, path);
//
//                Log.d("JSON: ",response);

//                Gson gson = new Gson();
//                Type type = new TypeToken<Collection<ResponseApi>>() {}.getType();
//                Collection<ResponseApi> enums = gson.fromJson(response, type);
//                responseApis = enums.toArray(new ResponseApi[enums.size()]);
//
//
//                if(data.isEmpty())
//                    for(int i=0;i<enums.size();i++)
//                        data.add(responseApis[i]);


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();



            //tvJsonString.setText(jsonString);

//            //new starts
//            listAdapter = new ListAdapter(MainActivity.this,data);
//            listView.setAdapter(listAdapter);
//
//            if(flag_list==1)
//            {
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        //Toast.makeText(MainActivity.this,data.get(i).getOrderId().toString(),Toast.LENGTH_LONG).show();
//
//                        Intent intent=new Intent(MainActivity.this, DetailActivity.class);
//                        intent.putExtra("order_id",data.get(i).getOrderId().toString());
//
//                        startActivity(intent);
//                    }
//                });
//            }
//
//
//            //new ends

        }
    }




}
