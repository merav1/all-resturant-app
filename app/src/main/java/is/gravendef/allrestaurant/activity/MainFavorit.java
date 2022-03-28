package is.gravendef.allrestaurant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import is.gravendef.allrestaurant.R;
import is.gravendef.allrestaurant.adapter.favoritAdapter;
import is.gravendef.allrestaurant.modal.detail;
import is.gravendef.allrestaurant.modal.favorit;
import is.gravendef.allrestaurant.modal.panier;

public class MainFavorit extends AppCompatActivity {


    private RecyclerView recyclerView;
    public static ArrayList<favorit> favorits=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        favoritAdapter adapter = new favoritAdapter(favorits,getApplicationContext());

        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        // Request a string response from the provided URL.

}

}

