package com.dssathe.referralsapp;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MainActivity extends AppCompatActivity {

    Button Query;
    SeekBar CookingQSB;
    SeekBar HouseRepairQSB;
    SeekBar EducationQSB;
    SeekBar EntertainmentQSB;
    SeekBar CookingAnsSB;
    SeekBar HouseRepairAnsSB;
    SeekBar EducationAnsSB;
    SeekBar EntertainmentAnsSB;

    TextView CookingQTV;
    TextView HouseRepairQTV;
    TextView EducationQTV;
    TextView EntertainmentQTV;
    TextView CookingAnsTV;
    TextView HouseRepairAnsTV;
    TextView EducationAnsTV;
    TextView EntertainmentAnsTV;
    Boolean isSucess=false;
    RequestQueue requestQueue;
    String URL = "http://10.0.2.2:9000/default/query/";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Query = (Button) findViewById(R.id.Query);
        
        CookingQSB=(SeekBar)findViewById(R.id.CookingQSB);
        HouseRepairQSB=(SeekBar)findViewById(R.id.HouseRepairQSB);
        EducationQSB=(SeekBar)findViewById(R.id.EducationQSB);
        EntertainmentQSB=(SeekBar)findViewById(R.id.EntertainmentQSB);
        CookingAnsSB=(SeekBar)findViewById(R.id.CookingAnsSB);
        HouseRepairAnsSB=(SeekBar)findViewById(R.id.HouseRepairAnsSB);
        EducationAnsSB=(SeekBar)findViewById(R.id.EducationAnsSB);
        EntertainmentAnsSB=(SeekBar)findViewById(R.id.EntertainmentAnsSB);

        CookingQTV=(TextView)findViewById(R.id.CookingQTV);
        HouseRepairQTV=(TextView)findViewById(R.id.HouseRepairQTV);
        EducationQTV=(TextView)findViewById(R.id.EducationQTV);
        EntertainmentQTV=(TextView)findViewById(R.id.EntertainmentQTV);
        CookingAnsTV=(TextView)findViewById(R.id.CookingAnsTV);
        HouseRepairAnsTV=(TextView)findViewById(R.id.HouseRepairAnsTV);
        EducationAnsTV=(TextView)findViewById(R.id.EducationAnsTV);
        EntertainmentAnsTV=(TextView)findViewById(R.id.EntertainmentAnsTV);

        CookingQSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                CookingQTV.setText(""+(progress/10));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //CookingQTV.setText(progress);
                CookingQTV.setText(""+(progress/10));

            }
        });

        HouseRepairQSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                HouseRepairQTV.setText(""+(progress/10));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //CookingQTV.setText(progress);
                HouseRepairQTV.setText(""+(progress/10));

            }
        });
        EducationQSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                EducationQTV.setText(""+(progress/10));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //CookingQTV.setText(progress);
                EducationQTV.setText(""+(progress/10));

            }
        });
        EntertainmentQSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            float progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                EntertainmentQTV.setText(""+(progress/10));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //CookingQTV.setText(progress);
                EntertainmentQTV.setText(""+(progress/10));

            }
        });
        Query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                getAnswer(CookingQTV.getText()+","+HouseRepairQTV.getText()+","+EducationQTV.getText()+","+EntertainmentQTV.getText());
            }
        });

    }
    private void getAnswer(String query) {
        requestQueue=Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL+query, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    Reply r=mapper.readValue(response, Reply.class);
                    if(r.getStatus().equals("success")){
                        CookingAnsTV.setText(""+r.getAnswer()[0]);
                        CookingAnsSB.setProgress((int)(r.getAnswer()[0]*10));
                        HouseRepairAnsTV.setText(""+r.getAnswer()[1]);
                        HouseRepairAnsSB.setProgress((int)(r.getAnswer()[1]*10));
                        EducationAnsTV.setText(""+r.getAnswer()[2]);
                        EducationAnsSB.setProgress((int)(r.getAnswer()[2]*10));
                        EntertainmentAnsTV.setText(""+r.getAnswer()[3]);
                        EntertainmentAnsSB.setProgress((int)(r.getAnswer()[3]*10));
                        isSucess= true;
                    }else{
                        Toast.makeText(getApplicationContext(), "Unable to get results for the given Query" , Toast.LENGTH_SHORT).show();
                        CookingAnsTV.setText(""+0.0);
                        CookingAnsSB.setProgress(0);
                        HouseRepairAnsTV.setText(""+0.0);
                        HouseRepairAnsSB.setProgress(0);
                        EducationAnsTV.setText(""+0.0);
                        EducationAnsSB.setProgress(0);
                        EntertainmentAnsTV.setText(""+0.0);
                        EntertainmentAnsSB.setProgress(0);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Unable to get results for the given Query" , Toast.LENGTH_SHORT).show();
                CookingAnsTV.setText(""+0.0);
                CookingAnsSB.setProgress(0);
                HouseRepairAnsTV.setText(""+0.0);
                HouseRepairAnsSB.setProgress(0);
                EducationAnsTV.setText(""+0.0);
                EducationAnsSB.setProgress(0);
                EntertainmentAnsTV.setText(""+0.0);
                EntertainmentAnsSB.setProgress(0);
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }
}
