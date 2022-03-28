package is.gravendef.allrestaurant.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import is.gravendef.allrestaurant.activity.MainBranches;
import is.gravendef.allrestaurant.activity.MainCategory;
import is.gravendef.allrestaurant.activity.MainTafrits;
import is.gravendef.allrestaurant.modal.branche;
import is.gravendef.allrestaurant.modal.category;

public class branchAdapter extends RecyclerView.Adapter<branchAdapter.ViewHolder>{

    private ArrayList<branche> branches;
    private Context context;


    // constructor.
    public branchAdapter(ArrayList<branche> branches, Context context){
        this.branches = branches;
        this.context = context;
    }

//    cardView.setOnClickListener(v -> {
//                Intent intent = new Intent(context, MainCategory.class);
//                intent.putExtra("nameRestaurant", nameRestaurant);
//                context.startActivity(intent);
//            };


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflating our layout file on below line.
        View view = LayoutInflater.from(context).inflate(R.layout.item_branche, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // getting data from our array list in our modal class.
        branche branche = branches.get(position);

        // on below line we are setting data to our text view.
        holder.city.setText(branche.getCity());
        holder.address.setText(branche.getAddress());
        holder.phone.setText(branche.getPhone());
        holder.phone.setOnClickListener(
                v -> {
                    //String number = editText.getText().toString();
                        String number = branche.getPhone().toString();
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:"+number));
                        context.startActivity(callIntent);

                });




        holder.openIn.setText(branche.getOpenIn());
        holder.hets.setOnClickListener(
                v -> {

                    popWindowsAdapter popUpClass = new popWindowsAdapter();
                    popUpClass.showPopupWindow(v);
                   Intent intent = new Intent(context, MainCategory.class);
                   intent.putExtra("city", branche.getCity());
                   intent.putExtra("resturantName",MainBranches.resturantname);
                   context.startActivity(intent);
                }
        );
        holder.cardView.setOnClickListener(
                v -> {

                    popWindowsAdapter popUpClass = new popWindowsAdapter();
                    popUpClass.showPopupWindow(v);
                }
        );
    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return branches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating a variable for our text view.
        public TextView city;
        public TextView address;
        public Button phone;
        public TextView openIn;
        public CardView cardView;
        public ImageButton hets;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our variables.
            city = itemView.findViewById(R.id.City);
            address = itemView.findViewById(R.id.Address);
            phone = itemView.findViewById(R.id.Phone);
            openIn = itemView.findViewById(R.id.OpenIn);
            cardView=itemView.findViewById(R.id.cardBranche);
            hets=itemView.findViewById(R.id.hets);
        }
    }
}



