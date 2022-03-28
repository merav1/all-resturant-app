package is.gravendef.allrestaurant.modal;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class allResto {

    private String name,url;
    private ImageButton imageButton;
    private TextView textView;
    private boolean mOnline;

    public allResto(String name, String url, boolean online) {
        this.name = name;
        this.url = url;
        mOnline = online;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageButton getImageButton() {
        return imageButton;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }
    public void setText(TextView textView) {
        this.textView=textView;
    }
    public boolean ismOnline() {
        return mOnline;
    }

    public void setmOnline(boolean mOnline) {
        this.mOnline = mOnline;
    }

    public static int getLastrestoId() {
        return lastrestoId;
    }

    public static void setLastrestoId(int lastrestoId) {
        allResto.lastrestoId = lastrestoId;
    }

    private static int lastrestoId = 0;
    public static ArrayList<allResto> createAllRestoList(int numResto) {
        ArrayList<allResto> allRestos = new ArrayList<allResto>();

        for (int i = 1; i <= numResto; i++) {
            allRestos.add(new allResto("Person " + ++lastrestoId,"name", i <= numResto / 2));
        }

        return allRestos;
    }
}