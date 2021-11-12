package com.example.csc2005_team04.ui.leaves;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.csc2005_team04.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class LeaveApplicationStatus extends AppCompatActivity {
    String LeaveDetails;
    ArrayList<Leaves> LeaveArrayList;

    LeavesAdapter mLeaveAdapter;

    TextView mHeaderview;
    ListView mLeavesListView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_application_status);

        mHeaderview = (TextView)findViewById(R.id.LeavesHeader);
        mLeavesListView = (ListView) findViewById(R.id.LeaveListView);

        mHeaderview.setText("Status of Leaves");

        LeaveArrayList = new ArrayList<Leaves>();

        mLeaveAdapter = new LeavesAdapter(this,LeaveArrayList);

        mLeavesListView.setAdapter(mLeaveAdapter);



        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("leaves")
                .whereEqualTo("EmployeeID", "user1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        List<Leaves> arrayList = new ArrayList<>();
                        if (task.isSuccessful())
                        {
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                System.out.println("OnComplete, task.is.Succuessful");



                                Leaves leave = document.toObject(Leaves.class);
                                arrayList.add(leave);



                            }
                            ListView mLeavesView = (ListView) findViewById(R.id.LeaveListView);
                            LeavesAdapter mLeavesAdapter = new LeavesAdapter(LeaveApplicationStatus.this, arrayList);



                            mLeavesView.setAdapter(mLeavesAdapter);
                            System.out.println(arrayList);

                            }



                    }


                });













        System.out.println("Does this work?");
    }
}