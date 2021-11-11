package com.example.csc2005_team04.ui.leaves;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csc2005_team04.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LeaveInfoPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_info_page);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String email = user.getEmail();
            Log.i("EMAIL: ", email);
        }

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                System.out.println("OnComplete, task.is.Succuessful");
                                System.out.println(document.getId());
//                                if(document.getId().equals("user1"))
                                if(document.getId().equals(user.getEmail().replaceAll("@.*","").trim()))

                                {
                                    System.out.print("Setting user1 Leave Info retrieval worked");
                                    TextView txtAnnualLeave = findViewById(R.id.Annual_Leave);
                                    TextView txtMedicalLeave = findViewById(R.id.Medical_Leave);
                                    TextView txtEmergencyLeave = findViewById(R.id.Emergency_Leave_Re);
                                    int AnnualLeave = document.getLong("AnnualLeave").intValue();
                                    int MedicalLeave = document.getLong("MedicalLeave").intValue();
                                    int EmergencyLeave = document.getLong("EmergencyLeave").intValue();



                                    txtAnnualLeave.setText(String.valueOf(AnnualLeave));
                                    txtMedicalLeave.setText(String.valueOf(MedicalLeave));
                                    txtEmergencyLeave.setText(String.valueOf(EmergencyLeave));
                                }
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

                    }

                });

    }
}