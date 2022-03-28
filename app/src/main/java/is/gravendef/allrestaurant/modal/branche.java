package is.gravendef.allrestaurant.modal;

import android.widget.TextView;

import java.util.ArrayList;

public class branche {
    //server
    private String city;
    private String address; // include street & num
    private String phone;
    private String openIn;
    boolean b;

    // constructor
    public branche(String city,String street,String phone, String openIn , boolean b){
        this.city = city;
        this.address = street;
        this.phone = phone;
        this.openIn = openIn;
        this.b = b;
    }


    public void setCity(String city) {this.city = city;}
    public void setAddress(String address) {this.address = address;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setOpenIn(String openIn) {this.openIn = openIn;}

    public String getCity() { return city; }
    public String getAddress(){ return address; }
    public String getPhone(){ return phone; }
    public String getOpenIn(){ return openIn; }





    public static int getLastrestoId() {
        return lastrestoId;
    }

    public static void setLastrestoId(int lastrestoId) {
        branche.lastrestoId = lastrestoId;
    }
    private static int lastrestoId = 0;
    public static ArrayList<branche> createBrancheList(int numCategories) {
        ArrayList<branche> branches = new ArrayList<branche>();

        for (int i = 1; i <= numCategories; i++) {
            branches.add(new branche("city " + ++lastrestoId,"address", "phone","open",i <= numCategories / 2));
        }

        return branches;
    }
}


