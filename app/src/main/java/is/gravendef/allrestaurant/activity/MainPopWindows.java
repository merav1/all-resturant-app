//package is.gravendef.allrestaurant.activity;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import is.gravendef.allrestaurant.R;
//import is.gravendef.allrestaurant.adapter.popWindowsAdapter;
//
//public class MainPopWindows extends AppCompatActivity {
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_pop_windows);
//
//// Create a button handler and call the dialog box display method in it
//            Button popupButton = findViewById(R.id.button1);
//            popupButton.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//
//                    popWindowsAdapter popUpClass = new popWindowsAdapter();
//                    popUpClass.showPopupWindow(v);
//                }
//            });
//
//            Button popupButton2 = findViewById(R.id.button2);
//            popupButton2.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//
//                    popWindowsAdapter popUpClass = new popWindowsAdapter();
//                    popUpClass.showPopupWindow(v);
//                }
//            });
//        }
//    }
//
