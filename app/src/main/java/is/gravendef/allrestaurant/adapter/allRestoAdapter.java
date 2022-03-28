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
import is.gravendef.allrestaurant.modal.allResto;

public class allRestoAdapter extends
        RecyclerView.Adapter<allRestoAdapter.ViewHolder> {

    // ... view holder defined above...

    // Store a member variable for the contacts
    private ArrayList<allResto> mallRestos;
    private Context mcontext;

    // Pass in the contact array into the constructor

    public allRestoAdapter(ArrayList<allResto> allRestos, Context mcontext) {
        this.mallRestos = allRestos;
        this.mcontext=mcontext;
    }


    public void downloadImage(String url, ImageView imageview,String resturantName)
    {

        Log.i("Button","Tapped");

        allRestoAdapter.DownloadImage task = new allRestoAdapter.DownloadImage();
        Bitmap result = null;
        try {
            result = task.execute(url).get();
            // result = task.execute(name).get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        imageview.setImageBitmap(result);
        imageview.setOnClickListener(v -> {
            Intent intent = new Intent(mcontext, MainBranches.class);
            intent.putExtra("resturantName",resturantName);
            mcontext.startActivity(intent);
        });

    }


    public class DownloadImage extends AsyncTask<String, Void, Bitmap>
    {


        @Override
        protected Bitmap doInBackground(String... imageurls) {


            URL url;
            HttpURLConnection httpURLConnection;

            try {
                url = new URL(imageurls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                InputStream in =httpURLConnection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }


        }
    }


    @Override
    public allRestoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View v = inflater.inflate(R.layout.item_all_restos, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Get the data model based on position
        allResto allRestos = mallRestos.get(position);
        allRestos.setImageButton(holder.imageButton);
        allRestos.setTextView(holder.textView);
        String name=allRestos.getUrl();
        String url=allRestos.getName();
        ImageButton imageButton = holder.imageButton;
        TextView textView = holder.textView;
        textView.setText(name);

        try{
            downloadImage(url,imageButton,name);
        }
        catch (Exception e){
            Log.d("set Image failed","set Image failed");
        }
    }


    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mallRestos.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageButton imageButton;
        public TextView textView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            imageButton= itemView.findViewById(R.id.image);
            textView= itemView.findViewById(R.id.name_resto);
        }
    }
}