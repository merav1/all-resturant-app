package is.gravendef.allrestaurant.adapter;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import is.gravendef.allrestaurant.modal.profil;
import is.gravendef.allrestaurant.modal.profil1;

public class Profil1Adapter {

        private DatabaseReference databaseReference;
        private FirebaseDatabase db;
        public Profil1Adapter()
        {
            db = FirebaseDatabase.getInstance();
            databaseReference = db.getReference(profil1.class.getSimpleName());

        }
        public Task<Void> add(profil1 emp1)
        {
            //if(emp==null)//exception
            return databaseReference.push().setValue(emp1);
        }

    }

