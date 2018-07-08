package com.example.annaxlu.currencyconvert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert (View view) {
        EditText input = findViewById(R.id.editText );
        Log.i("input", (input.getText().toString()));
        double usDollar = Double.parseDouble(input.getText().toString());
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
