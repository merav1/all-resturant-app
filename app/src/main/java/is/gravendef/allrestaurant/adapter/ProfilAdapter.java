package is.gravendef.allrestaurant.adapter;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import is.gravendef.allrestaurant.modal.profil;

public class ProfilAdapter
    {
        private DatabaseReference databaseReference;
        private FirebaseDatabase db;
        public ProfilAdapter()
        {
            db = FirebaseDatabase.getInstance();
            databaseReference = db.getReference(profil.class.getSimpleName());

        }
        public Task<Void> add(profil emp)
        {
            //if(emp==null)//exception
            return databaseReference.push().setValue(emp);
        }

    }

