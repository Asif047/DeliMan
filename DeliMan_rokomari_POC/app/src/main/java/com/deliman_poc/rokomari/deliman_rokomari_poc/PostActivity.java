package com.deliman_poc.rokomari.deliman_rokomari_poc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.deliman_poc.rokomari.deliman_rokomari_poc.details.DetailActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostActivity extends AppCompatActivity {



    private ProgressDialog pDialog;

    private Button btnPost;

    private String responsePost="";
    private String responseFromPost="";

    private EditText etOrderId,etRecipient,etOrderType,etArea,etDistrict;
    private TextView tvMessage;

    List<PostResponseApi> data = new ArrayList<>();

    private PostResponseApi postResponseApis;

    private String responseData="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        etOrderId=findViewById(R.id.edittext_orderId);
        etRecipient=findViewById(R.id.edittext_recipient);
        etOrderType=findViewById(R.id.edittext_orderType);
        etArea=findViewById(R.id.edittext_area);
        etDistrict=findViewById(R.id.edittext_district);

        tvMessage=findViewById(R.id.textview_message);

        btnPost=findViewById(R.id.button_post);

       // etOrderId.setText("");etRecipient.setText("");etOrderType.setText("");etArea.setText("");etDistrict.setText("");

        //new PostActivity.GetDataFromServer().execute();



        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String check_order_id=etOrderId.getText().toString();
                if(check_order_id.isEmpty())
                    etOrderId.setError("Please enter id");

                else
                responseData= postData();


               // Toast.makeText(PostActivity.this,x,Toast.LENGTH_LONG).show();

                //tvMessage.setText(responseData);


            }
        });




    }






    public String postData()
    {

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Map<String, String> params = new HashMap<String, String>();
        params.put("order_id", etOrderId.getText().toString());
        params.put("recipient", etRecipient.getText().toString());
        params.put("order_type", etOrderType.getText().toString());
        params.put("area", etArea.getText().toString());
        params.put("district", etDistrict.getText().toString());
        JSONObject parameter = new JSONObject(params);
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, parameter.toString());
        Request request = new Request.Builder()
                .url("https://rokom.herokuapp.com/api/postme")
                .post(body)
                .addHeader("content-type", "application/json; charset=utf-8")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Log.e("response", call.request().body().toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                responsePost=response.body().string();
                Log.e("response",responsePost);

                Gson gson = new Gson();
                Type type = new TypeToken<PostResponseApi>() {}.getType();
//                    Collection<PostResponseApi> enums = gson.fromJson(responsePost, type);
//                    postResponseApis = enums.toArray(new PostResponseApi[enums.size()]);
//
//
//                    if(data.isEmpty())
//                        for(int i=0;i<enums.size();i++)
//                            data.add(postResponseApis[i]);

                // Toast.makeText(PostActivity.this,postResponseApis.getMessage().toString(),Toast.LENGTH_LONG).show();

                postResponseApis=gson.fromJson(responsePost,type);
                responseFromPost=String.format(""+postResponseApis.getCode());

                Log.d("RESPONSE POST: ",responseFromPost);


                Intent intent=new Intent(PostActivity.this,ShowResponseActivity.class);
                intent.putExtra("response",responseFromPost);
                startActivity(intent);



            }


        });

        return responseFromPost;

    }



    private class GetDataFromServer extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(PostActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            Map<String, String> params = new HashMap<String, String>();
            params.put("order_id", etOrderId.getText().toString());
            params.put("recipient", etRecipient.getText().toString());
            params.put("order_type", etOrderType.getText().toString());
            params.put("area", etArea.getText().toString());
            params.put("district", etDistrict.getText().toString());
            JSONObject parameter = new JSONObject(params);
            OkHttpClient client = new OkHttpClient();

            RequestBody body = RequestBody.create(JSON, parameter.toString());
            Request request = new Request.Builder()
                    .url("https://rokom.herokuapp.com/api/postme")
                    .post(body)
                    .addHeader("content-type", "application/json; charset=utf-8")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    //Log.e("response", call.request().body().toString());

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    responsePost=response.body().string();
                    Log.e("response",responsePost);

                    Gson gson = new Gson();
                    Type type = new TypeToken<PostResponseApi>() {}.getType();
//                    Collection<PostResponseApi> enums = gson.fromJson(responsePost, type);
//                    postResponseApis = enums.toArray(new PostResponseApi[enums.size()]);
//
//
//                    if(data.isEmpty())
//                        for(int i=0;i<enums.size();i++)
//                            data.add(postResponseApis[i]);

                   // Toast.makeText(PostActivity.this,postResponseApis.getMessage().toString(),Toast.LENGTH_LONG).show();

                    postResponseApis=gson.fromJson(responsePost,type);
                    responseFromPost=postResponseApis.getMessage().toString();



                }


            });


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();



           // if(responseFromPost!=null)
            Toast.makeText(PostActivity.this,responseFromPost,Toast.LENGTH_LONG).show();

            tvMessage.setText(responseFromPost);
            //new ends

        }
    }





}
