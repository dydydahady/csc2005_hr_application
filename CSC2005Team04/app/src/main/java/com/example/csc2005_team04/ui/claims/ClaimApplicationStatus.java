package com.example.csc2005_team04.ui.claims;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc2005_team04.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class ClaimApplicationStatus extends AppCompatActivity {

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_application_status);

        db = FirebaseFirestore.getInstance();

        LinearLayout rl = (LinearLayout) findViewById(R.id.rootLayout);

        /*for( int i = 0; i < itemList.size(); i++ )
        {
            Button btn = new Button(this);
            btn.setText(itemList.get(i));
            btn.setBackgroundResource(R.color.orange);
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
            rl.addView(btn);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btn.getLayoutParams();
            params.height = 500;
            params.width = LinearLayout.LayoutParams.MATCH_PARENT;
            params.bottomMargin = 30;
            btn.setLayoutParams(params);
        }*/


    }
}