package is.gravendef.allrestaurant.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.ArrayList;

import is.gravendef.allrestaurant.R;
import is.gravendef.allrestaurant.adapter.panierAdapter;
import is.gravendef.allrestaurant.modal.panier;

public class MainPaniers extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static ArrayList<panier> paniers=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        TextView textView = findViewById(R.id.name);
        //CardView cardView = findViewById(R.id.name1);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        panierAdapter adapter = new panierAdapter(paniers,getApplicationContext());

        recyclerView.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        // Request a string response from the provided URL.

    }

}
