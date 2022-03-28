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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

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
import is.gravendef.allrestaurant.activity.MainFavorit;
import is.gravendef.allrestaurant.activity.MainPaniers;
import is.gravendef.allrestaurant.modal.allResto;
import is.gravendef.allrestaurant.modal.favorit;
import is.gravendef.allrestaurant.modal.panier;

public class panierAdapter extends
        RecyclerView.Adapter<panierAdapter.ViewHolder> {

    // ... view holder defined above...

    // Store a member variable for the contacts
    private ArrayList<panier> paniers;
    private Context mcontext;
    private int count = 0 ;
    // Pass in the contact array into the constructor

    public panierAdapter(ArrayList<panier> paniers, Context mcontext) {
        this.paniers = paniers;
        this.mcontext=mcontext;
    }


    public void downloadImage(String url, ImageView imageview)
    {

        Log.i("Button","Tapped");

        panierAdapter.DownloadImage task = new panierAdapter.DownloadImage();
        Bitmap result = null;
        try {
            result = task.execute(url).get();
            // result = task.execute(name).get();
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        imageview.setImageBitmap(result);
//        imageview.setOnClickListener(v -> {
//            Intent intent = new Intent(mcontext, MainBranches.class);
//            intent.putExtra("resturantName",resturantName);
//            mcontext.startActivity(intent);
//        });

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View v = inflater.inflate(R.layout.item_paniers, parent, false);
        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Get the data model based on position
        panier panier = paniers.get(position);
        panier.setImageButton1(holder.imageButton1);
        panier.setImageButton2(holder.imageButton2);
        panier.setTextView1(holder.textView1);
        panier.setTextView2(holder.textView2);
        panier.setTextView3(holder.textView3);
        String url=panier.getUrl();
        String nameM=panier.getNameM();
        String nameP=panier.getNameP();
        String nameQ=panier.getNameQ();
        ImageButton imageButton1 = holder.imageButton1;
        ImageButton imageButton2 = holder.imageButton2;
        TextView textView1 = holder.textView1;
        TextView textView2 = holder.textView2;
        TextView textView3 = holder.textView3;
        textView1.setText(nameM);
        textView2.setText(nameP);
        textView3.setText(nameQ);

        try{
            downloadImage(url,imageButton1);
        }
        catch (Exception e){
            Log.d("set Image failed","set Image failed");
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return paniers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton imageButton1,imageButton2;
        //public Button minus,plus;
        public TextView textView1,textView2,textView3;
        ElegantNumberButton btn;
        public ImageButton fav,tin;
        public ViewHolder(View itemView) {

            super(itemView);
            imageButton1= itemView.findViewById(R.id.imageM);
           // minus= itemView.findViewById(R.id.minus);
            //plus= itemView.findViewById(R.id.plus);
            //imageButton2= itemView.findViewById(R.id.imageQ);
            textView1= itemView.findViewById(R.id.nameM);
            textView2= itemView.findViewById(R.id.nameP);
            textView3= itemView.findViewById(R.id.nameQ);
            fav= itemView.findViewById(R.id.fav);
            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // set the color to relative layout
                    fav.setImageResource(R.drawable.favorit);
                    try {
                        MainFavorit.favorits.add(new favorit(imageButton1.toString(),textView1.toString(),textView2.toString(),textView3.toString(),true));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }}
            });

            tin= itemView.findViewById(R.id.tin);
            tin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        MainPaniers.paniers.remove(new panier(imageButton1.toString(),textView1.toString(),textView2.toString(),textView3.toString(),true));
                        panierAdapter.this.notifyDataSetChanged();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }}
            });

            //ElegantNumberButton
            btn = itemView.findViewById(R.id.plus_minus);
            btn.setOnClickListener(new ElegantNumberButton.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String num = btn.getNumber();
                    Log.e("num",num);
                }
            });
//            minus.setOnClickListener(v -> {
//                count--;
//            });
//            plus.setOnClickListener(v -> {
//                count++;
//            });
//        }
//        public void onClick(View v)
//        {
//            if(v.getId() == R.id.minus)
//            {
//                minus.setText(count--);
//            }
//            if(v.getId() == R.id.plus)
//            {
//                plus.setText(count++);
//            }
        }
    }
}