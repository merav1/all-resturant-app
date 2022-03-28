package is.gravendef.allrestaurant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import is.gravendef.allrestaurant.R;
import is.gravendef.allrestaurant.modal.allResto;
import is.gravendef.allrestaurant.adapter.allRestoAdapter;

public class specificResto extends AppCompatActivity {

    ArrayList<allResto> allRestos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bb);


        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        button2.setOnClickListener(v -> {
            Intent intent2 = new Intent(this, MainAllResto.class);
            intent2.putExtra("url", "http://10.0.2.2:5000/halavi");
            this.startActivity(intent2);
            //finish();
        });
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainAllResto.class);
            intent.putExtra("url", "http://10.0.2.2:5000/asiati");
            this.startActivity(intent);
            //finish();
        });
        button1.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, MainAllResto.class);
            intent1.putExtra("url", "http://10.0.2.2:5000/besari");
            this.startActivity(intent1);
            //finish();
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        String url = getIntent().getStringExtra("url");

        RequestQueue queue = Volley.newRequestQueue(this);
        allRestos = allResto.createAllRestoList(0);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONArray jsonarr = new JSONArray(response);
                            allRestoAdapter adapter = new allRestoAdapter(allRestos, getApplicationContext());
                            // Attach the adapter to the recyclerview to populate items
                            recyclerView.setAdapter(adapter);
                            // Set layout manager to position the items
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            for (int i = 0; i < jsonarr.length(); i++) {
                                JSONObject jsonobj = jsonarr.getJSONObject(i);
                                allRestos.add(0,new allResto(jsonobj.getString("url"),jsonobj.getString("name"),true));

                                adapter.notifyItemInserted(0);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, error -> Log.d("http error", "http error"));

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}