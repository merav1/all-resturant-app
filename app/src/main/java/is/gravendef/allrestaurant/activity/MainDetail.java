package is.gravendef.allrestaurant.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import is.gravendef.allrestaurant.R;
import is.gravendef.allrestaurant.modal.detail;
import is.gravendef.allrestaurant.modal.favorit;
import is.gravendef.allrestaurant.modal.panier;


public class MainDetail extends AppCompatActivity {

    private ArrayList<detail> details;
    public static String resname;
    public static String cityt;
    public static String categori;
    public static String manat;
    public static Boolean b;
    TextView timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView favorit_border = findViewById(R.id.fav_b);
        favorit_border.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // set the color to relative layout
                 favorit_border.setImageResource(R.drawable.favorit);
            }
        });

//        ImageView favorit = findViewById(R.id.fav_b);
//        favorit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // set the color to relative layout
//                favorit.setImageResource(R.drawable.favorit_border);
//            }
//        });

            //timer
        timer = findViewById(R.id.nameT);
        new CountDownTimer(3000,1000){
            public void onTick(long secondsLeft){
                timer.setText("זמן המתנה" +secondsLeft/1000);
            }

            @Override
            public void onFinish() {
                timer.setText("בדרך אליך");
            }
        }.start();

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        // recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        // Lookup the recyclerview in activity layout
        String resturantName = getIntent().getStringExtra("resturantName");
        String city = getIntent().getStringExtra("city");
        String category = getIntent().getStringExtra("category");
        String mana = getIntent().getStringExtra("mana");
        resname=resturantName;
        cityt=city;
        categori=category;
        manat=mana;

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:5000/detail/"+resturantName+"/"+city+"/"+category+"/"+mana;

        details = detail.createDetailList (0);
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {

                                JSONObject jsonobj = new JSONObject(response);
                                //details.add(0,new detail(jsonobj.getString("url"),jsonobj.getString("name"),jsonobj.getString("description"),jsonobj.getString("price"),true));
                                TextView name=(TextView) findViewById(R.id.nameN);
                                TextView description=(TextView) findViewById(R.id.nameD);
                                TextView price=(TextView) findViewById(R.id.nameP);
                                name.setText(jsonobj.getString("name"));
                                description.setText(jsonobj.getString("description"));
                                price.setText(jsonobj.getString("price"));
                               downloadImage(jsonobj.getString("url"),findViewById(R.id.imagey));
                                //downloadImage(jsonobj.getString("url"),findViewById(R.id.image));



                            ImageView favorit_border = findViewById(R.id.fav_b);
                            favorit_border.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // set the color to relative layout
                                    favorit_border.setImageResource(R.drawable.favorit);
                                    //Intent intent1 = new Intent(getApplicationContext(), MainFavorit.class);
                                    //intent.putExtra("url", url);
                                    try {
                                        MainFavorit.favorits.add(new favorit(jsonobj.getString("url"), jsonobj.getString("name"), jsonobj.getString("description"), jsonobj.getString("price"), true));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                   // getApplicationContext().startActivity(intent1);
                                }});


                            Button addTC=(Button) findViewById(R.id.badd);
                                addTC.setOnClickListener(v -> {
                                    Intent intent = new Intent(getApplicationContext(), MainAllResto.class);
                                    //intent.putExtra("url", url);
                                    try {
                                        MainPaniers.paniers.add(new panier(jsonobj.getString("url"),jsonobj.getString("name"),jsonobj.getString("description"),jsonobj.getString("price"),true));
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    getApplicationContext().startActivity(intent);
                                });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, error -> Log.d("http error", "http error"));

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    public void downloadImage(String url, ImageView imageview) {

        Log.i("Button", "Tapped");

        DownloadImage task = new DownloadImage();
        Bitmap result = null;
        try {
            result = task.execute(url).get();
            // result = task.execute(name).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        imageview.setImageBitmap(result);


    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... imageurls) {
            URL url;
            HttpURLConnection httpURLConnection;

            try {
                url = new URL(imageurls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream in = httpURLConnection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}




