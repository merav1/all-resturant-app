package is.gravendef.allrestaurant.modal;

import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class category {

    //server
    private String url;
    private String name_soug_menu;
    //xml
    private ImageButton imageButton;
    private TextView textView;
    boolean b;

    public category( String url,String name_soug_menu, boolean b) {
        this.url = url;
        this.name_soug_menu = name_soug_menu;
        this.b=b;
    }

    public boolean isB() {
        return b;
    }
    public void setB(boolean b) {
        this.b = b;
    }

    public String getName_soug_menu() { return name_soug_menu; }
    public ImageButton getImageButton() {
        return imageButton;
    }
    public TextView getTextView() {
        return textView;
    }
    public String getUrl() { return url; }

    public void setName_soug_menu(String name_soug_menu) { this.name_soug_menu = name_soug_menu; }
    public void setImageButton(ImageButton imageButton) {
        this.imageButton = imageButton;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setTextView(TextView textView) {
        this.textView = textView;
    }
    public void setText(TextView textView) {
        this.textView=textView;
    }

    public static int getLastrestoId() {
        return lastrestoId;
    }

    public static void setLastrestoId(int lastrestoId) {
        category.lastrestoId = lastrestoId;
    }
    private static int lastrestoId = 0;
    public static ArrayList<category> createCategoryList(int numCategories) {
        ArrayList<category> categories = new ArrayList<category>();

        for (int i = 1; i <= numCategories; i++) {
            categories.add(new category("Person " + ++lastrestoId, "url",i <= numCategories / 2));
          //  categories.add(new category("Person " + ++lastrestoId,"name", i <= numCategories / 2));
        }

        return categories;
    }
}
