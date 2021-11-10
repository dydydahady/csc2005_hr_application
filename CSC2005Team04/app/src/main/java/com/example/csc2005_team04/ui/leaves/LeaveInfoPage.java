package com.example.csc2005_team04.ui.leaves;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.csc2005_team04.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LeaveInfoPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_leave_info_page,
                container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

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
                                if(document.getId().equals("user1"))

                                {
                                    System.out.print("Setting user1 Leave Info retrieval worked");
                                    TextView txtAnnualLeave = getView().findViewById(R.id.Annual_Leave);
                                    TextView txtMedicalLeave = getView().findViewById(R.id.Medical_Leave);
                                    TextView txtEmergencyLeave = getView().findViewById(R.id.Emergency_Leave_Re);
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
        return view;
    }

}