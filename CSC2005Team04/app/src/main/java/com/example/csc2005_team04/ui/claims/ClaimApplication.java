package com.example.csc2005_team04.ui.claims;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.csc2005_team04.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ClaimApplication extends AppCompatActivity {

    FirebaseFirestore db;
    String PathHolder;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    List<String> itemlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_claim_application);

        db = FirebaseFirestore.getInstance();
        StorageReference storageRef = storage.getReference();

        TextView upload_txt = (TextView) findViewById(R.id.text_uploaded);
        TextView item0 = (TextView) findViewById(R.id.itemview0);
        Button button_submit = (Button) findViewById(R.id.button5);
        Button button_item = (Button) findViewById(R.id.button8);
        EditText edit_item = (EditText) findViewById(R.id.editTextItem);
        EditText edit_desc = (EditText) findViewById(R.id.editTextDesc);
        EditText edit_amt = (EditText) findViewById(R.id.editTextNumber);
        ImageButton button_attach = (ImageButton) findViewById(R.id.imageButton3);

        button_attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(intent, 7);
            }
        });

        button_item.setOnClickListener(new View.OnClickListener() {
            int i = 0;

            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                String item = edit_item.getText().toString();
                String description = edit_desc.getText().toString();
                String amt = edit_amt.getText().toString();

                try {
                    if (item.matches("")) {
                        Toast.makeText(getApplicationContext(),"Item name cannot be blank.",Toast.LENGTH_SHORT).show();
                    }else if (description.matches("")){
                        Toast.makeText(getApplicationContext(),"Description cannot be blank.",Toast.LENGTH_SHORT).show();
                    }else if (amt.matches("")){
                        Toast.makeText(getApplicationContext(),"Claim Amount cannot be blank.",Toast.LENGTH_SHORT).show();
                    }else {

                        int resID = getResources().getIdentifier("itemview" + i, "id", getPackageName());
                        TextView txtView = (TextView) findViewById(resID);
                        txtView.setText(item + ": " + description + " $" + amt);
                        txtView.setBackgroundColor(Color.parseColor("#AAAAAA"));
                        edit_item.getText().clear();
                        edit_desc.getText().clear();
                        edit_amt.getText().clear();
                        i++;
                    }
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Maximum of 10 items has been reached.",Toast.LENGTH_SHORT).show();}
            }
        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item0.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Cannot submit with no items.", Toast.LENGTH_SHORT).show();
                }else if (upload_txt.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Please upload attachment.", Toast.LENGTH_SHORT).show();
                }else{
                    String generatedStr = generateCode();

                    for (int i = 0; i <9; i++){
                        int resID = getResources().getIdentifier("itemview" + i, "id", getPackageName());
                        TextView itemi = (TextView) findViewById(resID);
                        String itemstr = itemi.getText().toString();
                        if (!itemstr.equals("")){
                            itemlist.add(itemstr); }
                    }

                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();

                    Map<String, Object> claims = new HashMap<>();
                    claims.put("Claim Application Code", generatedStr);
                    claims.put("No. of Items",itemlist.size());
                    claims.put("Item Details", itemlist);
                    claims.put("Receipt", PathHolder);
                    claims.put("Application Date", dtf.format(now));
                    //claims.put("Employee ID", )

                    db.collection("claims").add(claims).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            //Toast.makeText(ClaimApplication.this, "Successful push to DB", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ClaimApplication.this, "Failed push to DB", Toast.LENGTH_LONG).show();
                        }
                    });

                    openClaimApplicationDone();
                }
            }
        });
    }

    protected String generateCode() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

        public void openClaimApplicationDone(){
            Intent intent = new Intent(this, ClaimApplicationDone.class);
            startActivity(intent);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            // TODO Auto-generated method stub

            super.onActivityResult(requestCode, resultCode, data);
            TextView upload_txt = (TextView) findViewById(R.id.text_uploaded);

            switch (requestCode) {

                case 7:

                    if (resultCode == RESULT_OK) {

                        PathHolder = data.getData().getPath();

                        Toast.makeText(ClaimApplication.this, PathHolder, Toast.LENGTH_LONG).show();
                        upload_txt.setText("Attachment Uploaded Successfully.");
                        upload_txt.setBackgroundColor(Color.parseColor("#ACF49C"));
                    }
                    break;
            }
        }


    }
