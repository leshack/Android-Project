package com.example.studentgpa;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText cgpa,iq,profile_score;
    Button predict;
    TextView result;
    String url = "https://students-bxrp.onrender.com/predict";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cgpa = findViewById(R.id.cgpa);
        iq = findViewById(R.id.iq);
        profile_score = findViewById(R.id.profile_score);
        predict = findViewById(R.id.predict);
        result = findViewById(R.id.result);
        predict.setOnClickListener(new View.OnClickListener() {
            private void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClick(View view) {
                // hit the API -> Volley
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        (Response.Listener<String>) response -> {
                            //JSONObject jsonObject = new JSONObject((String) response);
                            String data = response;
                            if(data.equals("1")){
                                result.setText("Placement Hoga");
                            }else{
                                result.setText("Placement Nahi Hoga");
                            }
                        },
                        this::onErrorResponse){
                    @Override
                    protected Map getParams(){
                        Map<String, String> params = new HashMap<>();
                        params.put("cgpa",cgpa.getText().toString());
                        params.put("iq",iq.getText().toString());
                        params.put("profile_score",profile_score.getText().toString());
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(stringRequest);

            }
        });
    }
}