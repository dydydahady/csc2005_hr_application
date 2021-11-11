package com.example.csc2005_team04.ui.leaves;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc2005_team04.R;


public class LeaveMainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_leaves);
    }
    public void LeaveAppOnClick(View v)
    {
        Button button = (Button) findViewById(R.id.LeaveApp);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                openLeaveAppPage();
                System.out.println("openLeavePage() is done!");
            }
        });
    }
    public void LeaveInfoOnClick(View v)
    {
        Button button = (Button) findViewById(R.id.LeaveInfo);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                openLeaveInfoPage();
            }
        });
    }
    public void LeaveStatOnClick(View v)
    {
        Button button = (Button) findViewById(R.id.LeaveStat);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                openLeaveStatPage();
            }
        });

    }
    public void openLeaveAppPage()
    {
        Intent intent = new Intent(this,com.example.csc2005_team04.ui.leaves.LeaveApplicationPage.class);
        startActivity(intent);
    }
    public void openLeaveInfoPage()
    {
        Intent intent = new Intent(this, com.example.csc2005_team04.ui.leaves.LeaveInfoPage.class);
        startActivity(intent);
    }
    public void openLeaveStatPage()
    {
        Intent intent = new Intent(this,com.example.csc2005_team04.ui.leaves.LeaveApplicationStatus.class);
        startActivity(intent);
    }



}