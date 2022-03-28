package is.gravendef.allrestaurant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import is.gravendef.allrestaurant.R;
import is.gravendef.allrestaurant.adapter.ProfilAdapter;
import is.gravendef.allrestaurant.modal.profil;

public class MainProfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_profil_login);
        final EditText edit_name = findViewById(R.id.name);
        final EditText edit_position = findViewById(R.id.password);
        Button btn = findViewById(R.id.btn_submit);
        Button btn1 = findViewById(R.id.sign_in);
        btn1.setOnClickListener(v ->
                {
                    Intent intent2 = new Intent(this, MainProfil1.class);
                    this.startActivity(intent2);
                });

        ProfilAdapter dao =new ProfilAdapter();
        btn.setOnClickListener(v ->
        {
            profil emp = new profil(edit_name.getText().toString(),edit_position.getText().toString());
            dao.add(emp).addOnSuccessListener(suc->
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
