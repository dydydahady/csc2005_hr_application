package com.example.csc2005_team04.ui.leaves;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.csc2005_team04.R;

import java.util.List;


public class LeavesAdapter extends ArrayAdapter<Leaves> {
    public LeavesAdapter(Context context, List<Leaves> object){
        super(context,0, object);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView =  ((Activity)getContext()).getLayoutInflater().inflate(R.layout.itemleaves,parent,false);
        }

        TextView tvLeaveType = (TextView) convertView.findViewById(R.id.LeaveType);
        TextView tvFromDate = (TextView) convertView.findViewById(R.id.FromDate);
        TextView tvToDate = (TextView) convertView.findViewById(R.id.ToDate);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.LeaveStatus);

        Leaves leaves = getItem(position);
        System.out.println("GetItem: "+getItem(position));

        tvLeaveType.setText(leaves.getStartdate());
        tvFromDate.setText(leaves.getEnddate());
        tvToDate.setText(leaves.getLeavetype());

        System.out.println("GetStartDate: "+leaves.getStartdate());
        System.out.println("GetEndDate: "+leaves.getEnddate());
        System.out.println("GetLeaveType: "+leaves.getLeavetype());



        return convertView;
    }

}
