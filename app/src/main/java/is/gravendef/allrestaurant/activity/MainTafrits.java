package is.gravendef.allrestaurant.activity;

import android.os.Bundle;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
import is.gravendef.allrestaurant.adapter.categoryAdapter;
import is.gravendef.allrestaurant.adapter.tafritAdapter;
import is.gravendef.allrestaurant.modal.category;
import is.gravendef.allrestaurant.modal.tafrit;


public class MainTafrits extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<tafrit> tafrits;
    public static String resname;
    public static String cityt;
    public static String categori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tafrit);



        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
            // recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        // Lookup the recyclerview in activity layout
        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        String resturantName = getIntent().getStringExtra("resturantName");
        String city = getIntent().getStringExtra("city");
        String category = getIntent().getStringExtra("category");
        resname=resturantName;
        cityt=city;
        categori=category;

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:5000/taf/"+resturantName+"/"+city+"/"+category;

        tafrits = tafrit.createTafritList (0);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONArray jsonarr=new JSONArray(response);
                            tafritAdapter adapter = new tafritAdapter(tafrits,getApplicationContext());
                            // Attach the adapter to the recyclerview to populate items
                            recyclerView.setAdapter(adapter);
                            // Set layout manager to position the items
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                           // recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                            for(int i=0 ; i<jsonarr.length();i++)
                            {
                                JSONObject jsonobj = jsonarr.getJSONObject(i);
                                tafrits.add(0,new tafrit(jsonobj.getString("url"),jsonobj.getString("name"),true));
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

}




