package is.gravendef.allrestaurant.adapter;

import is.gravendef.allrestaurant.modal.tafrit;

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
import is.gravendef.allrestaurant.activity.MainCategory;
import is.gravendef.allrestaurant.activity.MainDetail;
import is.gravendef.allrestaurant.activity.MainTafrits;
import is.gravendef.allrestaurant.modal.detail;
import is.gravendef.allrestaurant.modal.tafrit;


public class tafritAdapter extends
        RecyclerView.Adapter<tafritAdapter.ViewHolder> {

    // ... view holder defined above...

    // Store a member variable for the contacts
    private ArrayList<tafrit> tafrits;
    private Context mcontext;

    // Pass in the contact array into the constructor

    public tafritAdapter(ArrayList<tafrit> tafrits, Context mcontext) {
        this.tafrits = tafrits;
        this.mcontext = mcontext;
    }


    public void downloadImage(String url, ImageView imageview,CardView cardView) {

        Log.i("Button", "Tapped");

        tafritAdapter.DownloadImage task = new tafritAdapter.DownloadImage();
        Bitmap result = null;
        try {
            result = task.execute(url).get();
            // result = task.execute(name).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        imageview.setImageBitmap(result);
        cardView.setOnClickListener(v -> {
            Intent intent = new Intent(mcontext, MainDetail.class);
           // intent.putExtra("name", MainDetail.mana);
           // intent.putExtra("mana",tafrit.getName());
            TextView txtview=(TextView)cardView.findViewById(R.id.name_t);
            intent.putExtra("mana",txtview.getText());
            intent.putExtra("resturantName", MainTafrits.resname);
            intent.putExtra("city",MainTafrits.cityt);
            intent.putExtra("category",MainTafrits.categori);
            //  intent.putExtra("url", url);
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
    public tafritAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View v = inflater.inflate(R.layout.item_tafrit, parent, false);
        // Return a new holder instance
        tafritAdapter.ViewHolder viewHolder = new tafritAdapter.ViewHolder(v);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        tafrit tafrit = tafrits.get(position);
        tafrit.setImageButton(holder.imageButton);
        tafrit.setTextView(holder.textView);
        String url = tafrit.getUrl();
        String name = tafrit.getName();
        ImageButton imageButton = holder.imageButton;
        //Button button = holder.button;
        TextView textView = holder.textView;
        textView.setText(name);
        CardView cardView= holder.cardview;

        try {
           downloadImage(url, imageButton,cardView);
        } catch (Exception e) {
            Log.d("set Image failed", "set Image failed");
        }
    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return tafrits.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageButton imageButton;
        public TextView textView;
        public CardView cardview;
        //public Button button;

        public ViewHolder(View itemView) {

            super(itemView);
            imageButton = itemView.findViewById(R.id.image_t);
            textView = itemView.findViewById(R.id.name_t);
            cardview=itemView.findViewById(R.id.tafrit);
          //  button = itemView.findViewById(R.id.button);
        }
    }
}



