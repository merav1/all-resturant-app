package is.gravendef.allrestaurant.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

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
import is.gravendef.allrestaurant.activity.MainActivity;
import is.gravendef.allrestaurant.activity.MainFavorit;
import is.gravendef.allrestaurant.activity.MainPaniers;
import is.gravendef.allrestaurant.modal.favorit;
import is.gravendef.allrestaurant.modal.panier;


    public class favoritAdapter extends
            RecyclerView.Adapter<favoritAdapter.ViewHolder> {

        // ... view holder defined above...

        // Store a member variable for the contacts
        private ArrayList<favorit> favorits;
        private Context mcontext;
        private int count = 0;
        // Pass in the contact array into the constructor

        public favoritAdapter(ArrayList<favorit> favorits, Context mcontext) {
            this.favorits = favorits;
            this.mcontext = mcontext;
        }


        public void downloadImage(String url, ImageView imageview) {

            Log.i("Button", "Tapped");

            favoritAdapter.DownloadImage task = new favoritAdapter.DownloadImage();
            Bitmap result = null;
            try {
                result = task.execute(url).get();
                // result = task.execute(name).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            imageview.setImageBitmap(result);
//        imageview.setOnClickListener(v -> {
//            Intent intent = new Intent(mcontext, MainBranches.class);
//            intent.putExtra("resturantName",resturantName);
//            mcontext.startActivity(intent);
//        });

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


        @Override
        public favoritAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View v = inflater.inflate(R.layout.item_favorit, parent, false);
            // Return a new holder instance
            favoritAdapter.ViewHolder viewHolder = new favoritAdapter.ViewHolder(v);
            return viewHolder;
        }



        // Involves populating data into the item through holder
        public void onBindViewHolder(favoritAdapter.ViewHolder holder, int position) {

            // Get the data model based on position
            favorit favorit = favorits.get(position);
            favorit.setImageButton1(holder.imageButton1);
            favorit.setImageButton2(holder.imageButton2);
            favorit.setTextView1(holder.textView1);
            favorit.setTextView2(holder.textView2);
            favorit.setTextView3(holder.textView3);
            String url = favorit.getUrl();
            String nameM = favorit.getNameM();
            String nameP = favorit.getNameP();
            String nameQ = favorit.getNameQ();
            ImageButton imageButton1 = holder.imageButton1;
            ImageButton imageButton2 = holder.imageButton2;
            TextView textView1 = holder.textView1;
            TextView textView2 = holder.textView2;
            TextView textView3 = holder.textView3;
            textView1.setText(nameM);
            textView2.setText(nameP);
            textView3.setText(nameQ);

            try {
                downloadImage(url, imageButton1);
            } catch (Exception e) {
                Log.d("set Image failed", "set Image failed");
            }
        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return favorits.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageButton imageButton1, imageButton2;
            //public Button minus,plus;
            public TextView textView1, textView2, textView3;
            ImageView favorit;

            public ViewHolder(View itemView) {

                super(itemView);
                imageButton1 = itemView.findViewById(R.id.imageM);
               // shop = itemView.findViewById(R.id.shop);

                // minus= itemView.findViewById(R.id.minus);
                //plus= itemView.findViewById(R.id.plus);
                //imageButton2= itemView.findViewById(R.id.imageQ);
                textView1 = itemView.findViewById(R.id.nameM);
                textView2 = itemView.findViewById(R.id.nameP);
                textView3 = itemView.findViewById(R.id.nameQ);

                ImageView favorit = itemView.findViewById(R.id.fav);
                favorit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // set the color to relative layout
                        favorit.setImageResource(R.drawable.favorit_border);
                    }
                    });

                ImageView shop = itemView.findViewById(R.id.shop);
                shop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            MainPaniers.paniers.add(new panier(imageButton1.toString(),textView1.toString(),textView2.toString(),textView3.toString(),true));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }}
                });
            }
        }
    }

