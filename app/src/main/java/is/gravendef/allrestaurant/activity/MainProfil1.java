package is.gravendef.allrestaurant.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import is.gravendef.allrestaurant.R;
import is.gravendef.allrestaurant.adapter.Profil1Adapter;
import is.gravendef.allrestaurant.modal.profil1;

public class MainProfil1 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.item_profil_signin);
            final EditText name = findViewById(R.id.name);
            final EditText phone = findViewById(R.id.phone);
            final EditText password = findViewById(R.id.password);
            Button btn = findViewById(R.id.btn);


            Profil1Adapter dao =new Profil1Adapter();
            btn.setOnClickListener(v ->
            {
                profil1 emp1 = new profil1(name.getText().toString(),phone.getText().toString(),password.getText().toString());
                dao.add(emp1).addOnSuccessListener(suc->
                {
                    Toast.makeText(this,"record is inserted",Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(er->
                {
                    Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();

                });
            });
        }
//
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.item_bar_user, container, false);
//    }
    }
