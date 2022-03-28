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

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import is.gravendef.allrestaurant.R;
import is.gravendef.allrestaurant.activity.MainActivity;
import is.gravendef.allrestaurant.activity.MainBranches;
import is.gravendef.allrestaurant.activity.MainCategory;
import is.gravendef.allrestaurant.activity.MainTafrits;
import is.gravendef.allrestaurant.modal.category;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {

     private ArrayList<category> categories;
        private Context mcontext;

    public categoryAdapter(ArrayList<category> categories, Context mcontext) {
            this.categories = categories;
            this.mcontext = mcontext; }

        public void downloadImage(String url, ImageView imageview,String categori) {

            Log.i("Button", "Tapped");

            categoryAdapter.DownloadImage task = new categoryAdapter.DownloadImage();
            Bitmap result = null;
            try {
                result = task.execute(url).get();
                // result = task.execute(name).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            imageview.setImageBitmap(result);
            imageview.setOnClickListener(v -> {
                Intent intent = new Intent(mcontext, MainTafrits.class);
                intent.putExtra("resturantName", MainCategory.resturantname);
                intent.putExtra("city",MainCategory.cityt);
                intent.putExtra("category",categori);
                mcontext.startActivity(intent);
            });
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
        public categoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.item_category, parent, false);
            categoryAdapter.ViewHolder viewHolder = new categoryAdapter.ViewHolder(v);
            return viewHolder;
        }
        @Override
        public void onBindViewHolder(categoryAdapter.ViewHolder holder, int position) {

            category category = categories.get(position);
            category.setImageButton(holder.imageButton);
            category.setTextView(holder.textView);
            String name_soug_menu = category.getName_soug_menu();
            String url = category.getUrl();
            ImageButton imageButton = holder.imageButton;
            TextView textView = holder.textView;
            textView.setText(name_soug_menu);

            try {
                downloadImage(url, imageButton,name_soug_menu);
            } catch (Exception e) {
                Log.d("set Image failed", "set Image failed");
            }

            //essai hetssssss
            holder.cardView.setOnClickListener(
                    v -> {
                        Intent intent = new Intent(mcontext, MainTafrits.class);
                        intent.putExtra("resturantName", MainCategory.resturantname);
                        intent.putExtra("city",MainCategory.cityt);
                        intent.putExtra("category",MainCategory.categoryt);
                        mcontext.startActivity(intent);
                    });
            //////mntttttttttt

        }

        @Override
        public int getItemCount() {
            return categories.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public ImageButton imageButton,imageButton1;
            public TextView textView;
            public CardView cardView;

            public ViewHolder(View itemView) {

                super(itemView);
                imageButton = itemView.findViewById(R.id.category_photo);
                imageButton1 = itemView.findViewById(R.id.hets);
                textView = itemView.findViewById(R.id.category_name);
                cardView=itemView.findViewById(R.id.cardView);

            }
        }
    }



