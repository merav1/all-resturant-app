//package is.gravendef.allrestaurant.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.concurrent.ExecutionException;
//
//import is.gravendef.allrestaurant.R;
//import is.gravendef.allrestaurant.activity.MainCategory;
//import is.gravendef.allrestaurant.activity.MainDetail;
//import is.gravendef.allrestaurant.modal.detail;
//
//
//public class detailAdapter extends
//        RecyclerView.Adapter<detailAdapter.ViewHolder> {
//
//    // ... view holder defined above...
//
//    // Store a member variable for the contacts
//    private ArrayList<detail> details;
//    private Context mcontext;
//
//    // Pass in the contact array into the constructor
//
//    public detailAdapter(ArrayList<detail> details, Context mcontext) {
//        this.details = details;
//        this.mcontext = mcontext;
//    }
//
//
//    public void downloadImage(String url, ImageView imageview) {
//
//        Log.i("Button", "Tapped");
//
//        detailAdapter.DownloadImage task = new detailAdapter.DownloadImage();
//        Bitmap result = null;
//        try {
//            result = task.execute(url).get();
//            // result = task.execute(name).get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//        imageview.setImageBitmap(result);
//        imageview.setOnClickListener(v -> {
//            Intent intent = new Intent(mcontext, MainCategory.class);
//            //intent.putExtra("url", url);
//            intent.putExtra("resturantName", MainDetail.resname);
//            intent.putExtra("city",MainDetail.cityt);
//            intent.putExtra("category",MainDetail.categori);
//            mcontext.startActivity(intent);
//        });
//
//    }
//
//    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
//
//        @Override
//        protected Bitmap doInBackground(String... imageurls) {
//            URL url;
//            HttpURLConnection httpURLConnection;
//
//            try {
//                url = new URL(imageurls[0]);
//                httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.connect();
//                InputStream in = httpURLConnection.getInputStream();
//                Bitmap myBitmap = BitmapFactory.decodeStream(in);
//                return myBitmap;
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//                return null;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;
//            }
//        }
//    }
//
//    @Override
//    public detailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(R.layout.item_detail, parent, false);
//        detailAdapter.ViewHolder viewHolder = new detailAdapter.ViewHolder(v);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//
//        detail detail = details.get(position);
//        detail.setImageView(holder.imageView);
//        detail.setTextViewN(holder.textViewN);
//        detail.setTextViewD(holder.textViewD);
//        detail.setTextViewP(holder.textViewP);
//        String nameN = detail.getNameN();
//        String nameD = detail.getNameD();
//        String nameP = detail.getNameP();
//        String url = detail.getUrl();
//        ImageView imageView = holder.imageView;
//        TextView textViewN = holder.textViewN;
//        TextView textViewD = holder.textViewD;
//        TextView textViewP = holder.textViewP;
//        textViewN.setText(nameN);
//        textViewD.setText(nameD);
//        textViewP.setText(nameP);
//
//        try {
//            downloadImage(url, imageView);
//        } catch (Exception e) {
//            Log.d("set Image failed", "set Image failed");
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return details.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//        public ImageView imageView;
//        public Button button;
//        public TextView textViewN,textViewD,textViewP;
//
//        public ViewHolder(View itemView) {
//
//            super(itemView);
//            imageView = itemView.findViewById(R.id.image);
//            button = itemView.findViewById(R.id.button);
//            textViewN = itemView.findViewById(R.id.nameN);
//            textViewD = itemView.findViewById(R.id.nameD);
//            textViewP = itemView.findViewById(R.id.nameP);
//        }
//    }
//}
//
//
//
