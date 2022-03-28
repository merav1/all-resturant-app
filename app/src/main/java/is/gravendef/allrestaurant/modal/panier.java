package is.gravendef.allrestaurant.modal;


import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

    public class panier {
        //server
        private String url,nameM,nameP,nameQ;
        //xml
        private ImageButton imageButton1;
        private ImageButton imageButton2;
        private TextView textView1,textView2,textView3;
        private boolean b;

        public panier(String url,String nameM,String nameP,String nameQ,boolean b) {
            this.url = url;
            this.nameM = nameM;
            this.nameP = nameP;
            this.nameQ = nameQ;
            this.b = b;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getNameM() {
            return nameM;
        }

        public void setNameM(String nameM) {
            this.nameM = nameM;
        }

        public String getNameP() {
            return nameP;
        }

        public void setNameP(String nameP) {
            this.nameP = nameP;
        }

        public String getNameQ() {
            return nameQ;
        }

        public void setNameQ(String nameQ) {
            this.nameQ = nameQ;
        }

        public ImageButton getImageButton1() {
            return imageButton1;
        }

        public void setImageButton1(ImageButton imageButton1) {
            this.imageButton1 = imageButton1;
        }

        public ImageButton getImageButton2() {
            return imageButton2;
        }

        public void setImageButton2(ImageButton imageButton2) {
            this.imageButton2 = imageButton2;
        }

        public TextView getTextView1() {
            return textView1;
        }

        public void setTextView1(TextView textView1) {
            this.textView1 = textView1;
        }

        public TextView getTextView2() {
            return textView2;
        }

        public void setTextView2(TextView textView2) {
            this.textView2 = textView2;
        }

        public TextView getTextView3() {
            return textView3;
        }

        public void setTextView3(TextView textView3) {
            this.textView3 = textView3;
        }

        public boolean isB() {
            return b;
        }

        public void setB(boolean b) {
            this.b = b;
        }
        public void setText(TextView textView) {
            this.textView3=textView;
        }

        public static int getLastrestoId() {
            return lastrestoId;
        }

        public static void setLastrestoId(int lastrestoId) {
            panier.lastrestoId = lastrestoId;
        }

        private static int lastrestoId = 0;
        public static ArrayList<panier> createPanierList(int numResto) {
            ArrayList<panier> paniers = new ArrayList<panier>();

            for (int i = 1; i <= numResto; i++) {
                paniers.add(new panier("Person " + ++lastrestoId,"name","price","quantity", i <= numResto / 2));
            }

            return paniers;
        }
    }