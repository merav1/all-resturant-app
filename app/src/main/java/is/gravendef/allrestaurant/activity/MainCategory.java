package is.gravendef.allrestaurant.activity;

import android.os.Bundle;

import android.util.Log;
import android.widget.RelativeLayout;

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
import is.gravendef.allrestaurant.modal.category;


public class MainCategory extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<category> categories;
    public static String resturantname;
    public static String cityt;
    public static String categoryt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerView=findViewById(R.id.recycler_view);
        RelativeLayout rel=(RelativeLayout)findViewById(R.id.container) ;
        rel.setBackgroundColor(getResources().getColor(R.color.white));
        // Lookup the recyclerview in activity layout
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        String resname=getIntent().getStringExtra("resturantName");
        resturantname=resname;
        String city=getIntent().getStringExtra("city");
        cityt=city;
        ///mnttttt
        String categoryi=getIntent().getStringExtra("category");
        categoryt=categoryi;
/////
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.0.2.2:5000/category/"+resname+"/"+city;

        categories = category.createCategoryList (0);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONArray jsonarr=new JSONArray(response);
                            categoryAdapter adapter = new categoryAdapter(categories,getApplicationContext());
                            // Attach the adapter to the recyclerview to populate items
                            recyclerView.setAdapter(adapter);
                            // Set layout manager to position the items
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            for(int i=0 ; i<jsonarr.length();i++)
                            {
                               JSONObject jsonobj = jsonarr.getJSONObject(i);
                               // categories.add(0,new category(jsonobj.getString("url"),jsonobj.getString("name_soug_menu"),true));
                                categories.add(0,new category(jsonobj.getString("icon"),jsonobj.getString("name_soug_menu"),true));
                                // adapter.notifyItemInserted(0);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, error -> Log.d("http error", "http error"));
        url="";
        StringRequest backgroundpic = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                    }
                }, error -> Log.d("http error", "http error"));
        // Add the request to the RequestQueue.
        queue.add(backgroundpic);
        queue.add(stringRequest);


    }

    }




