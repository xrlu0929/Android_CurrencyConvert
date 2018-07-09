package com.example.annaxlu.currencyconvert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public void convert (View view) {
        EditText input = findViewById(R.id.editText );
        Log.i("input", (input.getText().toString()));
        double usDollar = Double.parseDouble(input.getText().toString());
        /**
         * API get rate
         */

        //String apiCall = "http://data.fixer.io/api/latest?access_key=d78043c1da73bc2b33d74bbec6b67d01&symbols=USD,CNY";
        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<RetroFixData> call = service.getAllrate();

        Log.i("API call", call.toString());
        call.enqueue(new Callback<RetroFixData>() {

            @Override
            public void onResponse(Call<RetroFixData> call, Response<RetroFixData> response) {
                Log.i("response success", response.toString());
                Log.i("response success", response.body().getRate().toString());
                /**
                 * handle rate
                 */
                
            }

            @Override
            public void onFailure(Call<RetroFixData> call, Throwable t) {
                Log.i("response fail - call", call.toString());
                Log.i("response fail - throw", t.getMessage());
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!" + t +"CALL" + call, Toast.LENGTH_LONG).show();
            }


        });


        double us_rmb = 6.64;
        double rmb = usDollar * us_rmb;
        String result = Double.toString(rmb);

        TextView result_view = findViewById(R.id.result);
        result_view.setText("RMB "+result + " Yuan");
        Log.i("result", result);

        Toast.makeText(MainActivity.this, "Converted", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
