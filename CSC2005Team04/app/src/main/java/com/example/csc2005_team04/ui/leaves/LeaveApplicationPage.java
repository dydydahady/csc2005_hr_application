package com.example.csc2005_team04.ui.leaves;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csc2005_team04.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class LeaveApplicationPage extends Fragment {


    Spinner spinner1;


    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    AlertDialog.Builder builder;
    Object item;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_leave_application_page,
                container, false);


        //setContentView(R.layout.activity_leave_application_page);
        Button selectDateFrom = getView().findViewById(R.id.btnDateFrom);
        TextView dateFrom = getView().findViewById(R.id.FromDate);


        Spinner spinner = getView().findViewById(R.id.spinner1);


        Button selectDateTo = getView().findViewById(R.id.btnDateTo);
        TextView dateTo = getView().findViewById(R.id.ToDate);

        Button submitBtn = getView().findViewById(R.id.SubmitBtn);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());



        spinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                        item = parent.getItemAtPosition(pos);
                        System.out.println(item.toString());     //prints the text in spinner item.

                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                    }

                });



        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                builder.setMessage("Do you want to submit Leave Application?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //String LeaveType = spinner1.getSelectedItem().toString();
                                String LeaveType = item.toString();
                                String FromDate = dateFrom.getText().toString();
                                String ToDate = dateTo.getText().toString();
                                System.out.println(LeaveType);
                                System.out.println(FromDate);
                                System.out.println(ToDate);
                                //Dates appeared weirdly

                                /*
                                Bundle bundle = new Bundle();
                                bundle.putString("FromDate",FromDate);
                                bundle.putString("ToDate",ToDate);
                                bundle.putString("LeaveType",LeaveType);


                                Intent intent = new Intent(getApplicationContext(), LeaveApplicationStatus.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                */

                                Map<String, Object> data = new HashMap<>();
                                data.put("LeaveType", LeaveType);
                                data.put("StartDate", FromDate);
                                data.put("EndDate", ToDate);
                                data.put("Approver:", "manager0");
                                //Long tsLong = System.currentTimeMillis()/1000;
                                //String ts = tsLong.toString();

                                data.put("DateApplied", FieldValue.serverTimestamp());
                                data.put("EmployeeID", "user1");
                                FirebaseFirestore db = FirebaseFirestore.getInstance();

                                db.collection("leaves")
                                        .add(data)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "Error adding document", e);
                                            }
                                        });


                                //finish();
                                Toast.makeText(getActivity(), "Leave Applied",
                                        Toast.LENGTH_LONG).show();

                                System.out.println("Bruh where is my toast message??");

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getActivity(), "Cancelled",
                                        Toast.LENGTH_SHORT).show();


                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("Leave Application Submission");
                alert.show();

            }

        });













        selectDateFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dateFrom.setText(day + "/" + (month + 1) + "/" + year);
                                System.out.println(dateFrom.toString());
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }



        });

        selectDateTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dateTo.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        return view;
    }

}









