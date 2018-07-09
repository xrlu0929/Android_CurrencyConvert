package com.example.annaxlu.currencyconvert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import com.google.gson.JsonObject;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.json.*;

public class MainActivity extends AppCompatActivity {

    public void convert (View view) {
        EditText input = findViewById(R.id.editText );
        Log.i("input", (input.getText().toString()));
        final double usDollar = Double.parseDouble(input.getText().toString());

        /**
         * API get rate
         */

        //String apiCall = "http://data.fixer.io/api/latest?access_key=d78043c1da73bc2b33d74bbec6b67d01&symbols=USD,CNY";
        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<RetroFixData> call = service.getAllrate();

        Log.i("API call", call.toString());

        call.enqueue(new Callback<RetroFixData>() {

            double usRate = 1.0;
            double cnyRate = 6.64;

            @Override
            public void onResponse(Call<RetroFixData> call, Response<RetroFixData> response) {
                Log.i("response success", response.toString());
//                Log.i("response success", response.body().getRate().toString());
                /**
                 * handle rate
                 */
                Gson gson = new Gson();
                String gsonRates = gson.toJson(response.body().getRate());

                Log.i("response success-GSON",  gsonRates);

                JsonObject jsonObject = gson.fromJson( gsonRates, JsonObject.class);
                //jsonObject.get("USD"); // returns a JsonElement for that name


                usRate = Double.parseDouble(jsonObject.get("USD").toString());
                cnyRate = Double.parseDouble(jsonObject.get("CNY").toString());
                Log.i("response success-USD",  jsonObject.get("USD").toString());
                Log.i("response success-CNY",  jsonObject.get("CNY").toString());

                double us_rmb = cnyRate / usRate;
                Log.i("response success-rate", Double.toString(us_rmb));
                double rmb = usDollar * us_rmb;
                DecimalFormat fourDForm = new DecimalFormat("#.0000");

                String result = fourDForm.format(rmb);

                TextView result_view = findViewById(R.id.result);
                result_view.setText("RMB "+result + " Yuan");
                Log.i("result", result);

                Toast.makeText(MainActivity.this, "Converted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RetroFixData> call, Throwable t) {
                Log.i("response fail - call", call.toString());
                Log.i("response fail - throw", t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_LONG).show();
            }


        });



    }
    private double passInput (double input){
        return input;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
