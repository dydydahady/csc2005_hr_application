package com.example.csc2005_team04.ui.leaves;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc2005_team04.R;

public class LeaveApplicationStatus extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_leave_application_status,
                container, false);


        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            String LeaveType = bundle.getString("LeaveType");
            String FromDate = bundle.getString("FromDate");
            String ToDate = bundle.getString("ToDate");

            TextView receiver_LeaveType = getView().findViewById(R.id.LeaveType);
            TextView receiver_FromDate = getView().findViewById(R.id.FromDate);
            TextView receiver_ToDate = getView().findViewById(R.id.ToDate);
            receiver_LeaveType.setText(LeaveType);
            receiver_FromDate.setText(FromDate);
            receiver_ToDate.setText(ToDate);

        }

        // display the string into textView


        System.out.println("Does this work?");
        return view;
    }


}