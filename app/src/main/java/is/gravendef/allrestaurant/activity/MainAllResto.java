package is.gravendef.allrestaurant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import is.gravendef.allrestaurant.R;
import is.gravendef.allrestaurant.adapter.allRestoAdapter;
import is.gravendef.allrestaurant.modal.allResto;


public class MainAllResto extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ArrayList<allResto> allRestos;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_resto);

        Button button = findViewById(R.id.button);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);

        bottomNavigationView = findViewById(R.id.bottomNavViewBar);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);


        button2.setOnClickListener(v -> {
            Intent intent2 = new Intent(this, Barcode.class);
            intent2.putExtra("url", "http://10.0.2.2:5000/halavi");
            this.startActivity(intent2);
            //finish();
        });
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, specificResto.class);
            intent.putExtra("url", "http://10.0.2.2:5000/asiati");
            this.startActivity(intent);
            //finish();
        });
        button1.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, specificResto.class);
            intent1.putExtra("url", "http://10.0.2.2:5000/besari");
            this.startActivity(intent1);
            //finish();
        });

        // Lookup the recyclerview in activity layout
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        RequestQueue queue = Volley.newRequestQueue(this);
        //  RequestQueue queue1 = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:5000/allResto";

       allRestos = allResto.createAllRestoList(0);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONArray jsonarr=new JSONArray(response);
                            allRestoAdapter adapter = new allRestoAdapter(allRestos,getApplicationContext());
                            // Attach the adapter to the recyclerview to populate items
                            recyclerView.setAdapter(adapter);
                            // Set layout manager to position the items
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            for(int i=0 ; i<jsonarr.length();i++)
                            {
                                JSONObject jsonobj = jsonarr.getJSONObject(i);
                                allRestos.add(0,new allResto(jsonobj.getString("url"),jsonobj.getString("name"),true));
                                // adapter.notifyItemInserted(0);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, error -> Log.d("http error", "http error"));

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profil:
                Intent intent = new Intent(getApplicationContext(), login.class);
                getApplicationContext().startActivity(intent);
                return true;

            case R.id.home:
                Intent intent1 = new Intent(getApplicationContext(), MainAllResto.class);
                getApplicationContext().startActivity(intent1);
                return true;

            case R.id.panier:
                Intent intent2 = new Intent(getApplicationContext(), MainPaniers.class);
                getApplicationContext().startActivity(intent2);
                return true;

            case R.id.favorit:
                Intent intent3 = new Intent(getApplicationContext(), MainFavorit.class);
                getApplicationContext().startActivity(intent3);
                return true;

        }
        return false;
    }
}