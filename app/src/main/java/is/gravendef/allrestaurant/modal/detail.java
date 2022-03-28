package is.gravendef.allrestaurant.modal;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class detail {

    //server
    private String url;
    private String nameN,nameD,nameP;
    boolean b;

    //xml
    private ImageView imageView;
    private TextView textViewN,textViewD,textViewP;
    public detail(String url, String nameN, String nameD, String nameP, boolean b) {
        this.url = url;
        this.nameN = nameN;
        this.nameD = nameD;
        this.nameP = nameP;
        this.b = b;
    }
//public detail(String url, String nameN, boolean b) {
//    this.url = url;
//    this.nameN = nameN;
//    this.b = b;
//}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNameN() {
        return nameN;
    }

    public void setNameN(String nameN) {
        this.nameN = nameN;
    }

    public String getNameD() {
        return nameD;
    }

    public void setNameD(String nameD) {
        this.nameD = nameD;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
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

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextViewN() {
        return textViewN;
    }

    public void setTextViewN(TextView textViewN) {
        this.textViewN = textViewN;
    }

    public TextView getTextViewD() {
        return textViewD;
    }

    public void setTextViewD(TextView textViewD) {
        this.textViewD = textViewD;
    }

    public TextView getTextViewP() {
        return textViewP;
    }

    public void setTextViewP(TextView textViewP) {
        this.textViewP = textViewP;
    }

    public void setTextN(TextView textViewN) {
        this.textViewN=textViewN;
    }
    public void setTextD(TextView textViewD) { this.textViewD=textViewD; }
    public void setTextP(TextView textViewP) { this.textViewP=textViewP; }


    public static int getLastrestoId() {
        return lastrestoId;
    }
    public static void setLastrestoId(int lastrestoId) {
        detail.lastrestoId = lastrestoId;
    }
    private static int lastrestoId = 0;

    public static ArrayList<detail> createDetailList(int numCategories) {
        ArrayList<detail> details = new ArrayList<detail>();

        for (int i = 1; i <= numCategories; i++) {
            details.add(new detail("Person " + ++lastrestoId,"name","description","price", i <= numCategories / 2));

            //  details.add(new detail("Person " + ++lastrestoId,"name","description","price", i <= numCategories / 2));
        }

        return details;
    }
}
