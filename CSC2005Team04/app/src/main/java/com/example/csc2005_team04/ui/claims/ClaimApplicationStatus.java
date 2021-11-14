package com.example.csc2005_team04.ui.claims;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csc2005_team04.LoginActivity;
import com.example.csc2005_team04.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class ClaimApplicationStatus extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    String employeeID = currentFirebaseUser.getUid();
    String ClaimNo;
    Map<String, Object> ClaimData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_application_status);

        db = FirebaseFirestore.getInstance();

        db.collection("claims").whereEqualTo("Employee ID", employeeID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    int i = 0;

                    for (QueryDocumentSnapshot document : task.getResult()){
                        int resID = getResources().getIdentifier("app" + i, "id", getPackageName());
                        TextView txtView = (TextView) findViewById(resID);

                        ClaimNo = document.getId();
                        ClaimData = document.getData();

                        txtView.setText("Claim Application Number: " + ClaimNo + "\nApplication Date: " + ClaimData.get("Application Date")
                                + "\nNumber of Items: " + ClaimData.get("No. of Items") + "\nSTATUS: PENDING");
                        txtView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                        txtView.setBackgroundColor(Color.parseColor("#FFAD33"));
                        i++;
                    }
                }
            }
        });
    }
}