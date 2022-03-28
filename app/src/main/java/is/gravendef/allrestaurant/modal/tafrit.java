package is.gravendef.allrestaurant.modal;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class tafrit {

    //server
    private String url;
    private String name;
    boolean b;
    //xml
    private ImageView imageView;
    private TextView textView;

    public tafrit(String url, String name, boolean b) {
        this.url = url;
        this.name = name;
        this.b = b;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageButton(ImageButton imageButton) {
        this.imageView = imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public static int getLastrestoId() {
        return lastrestoId;
    }

    public static void setLastrestoId(int lastrestoId) {
        tafrit.lastrestoId = lastrestoId;
    }

    private static int lastrestoId = 0;
    public static ArrayList<tafrit> createTafritList(int numCategories) {
        ArrayList<tafrit> tafrits = new ArrayList<tafrit>();

        for (int i = 1; i <= numCategories; i++) {
            tafrits.add(new tafrit("Person " + ++lastrestoId,"name", i <= numCategories / 2));
        }

        return tafrits;
    }
}
