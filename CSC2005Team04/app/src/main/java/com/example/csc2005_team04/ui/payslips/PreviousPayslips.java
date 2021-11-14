package com.example.csc2005_team04.ui.payslips;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc2005_team04.R;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class PreviousPayslips extends AppCompatActivity implements DownloadFile.Listener, AdapterView.OnItemSelectedListener{

    private RemotePDFViewPager remotePDFViewPager;

    private PDFPagerAdapter pdfPagerAdapter;

    private String url_may,url_jun,url_jul,url_aug,url_sep;
    private ProgressBar progressBar;

    private LinearLayout pdfLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_payslips);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        pdfLayout = findViewById(R.id.pdf_layout);

        url_may = "https://firebasestorage.googleapis.com/v0/b/csc2005-team04.appspot.com/o/payslip_may.pdf?alt=media&token=bfbd07b3-c636-4dbc-9a85-f51d6cbb2d23";
        url_jun = "https://firebasestorage.googleapis.com/v0/b/csc2005-team04.appspot.com/o/payslip_jun.pdf?alt=media&token=9092087f-a102-4fa0-b930-aea8c82f461b";
        url_jul = "https://firebasestorage.googleapis.com/v0/b/csc2005-team04.appspot.com/o/payslip_jul.pdf?alt=media&token=6d8015c8-b7de-4569-b5e3-c59f95e14051";
        url_aug = "https://firebasestorage.googleapis.com/v0/b/csc2005-team04.appspot.com/o/payslip_aug.pdf?alt=media&token=79cd4ff2-32ab-467f-82a5-5ce062acf731";
        url_sep = "https://firebasestorage.googleapis.com/v0/b/csc2005-team04.appspot.com/o/payslip_sep.pdf?alt=media&token=ae7a2ded-4d6b-4f98-ae00-8836d9192a81";

        Spinner dropdown =findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        Button button_download = (Button) findViewById(R.id.btn_download);
        button_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Download Successful.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                pdfLayout.removeAllViews();
                remotePDFViewPager = new RemotePDFViewPager(this, url_may, this);
                Toast.makeText(getApplicationContext(), "May", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                pdfLayout.removeAllViews();
                remotePDFViewPager = new RemotePDFViewPager(this, url_jun, this);
                Toast.makeText(getApplicationContext(), "June", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                pdfLayout.removeAllViews();
                remotePDFViewPager = new RemotePDFViewPager(this, url_jul, this);
                Toast.makeText(getApplicationContext(), "July", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                pdfLayout.removeAllViews();
                remotePDFViewPager = new RemotePDFViewPager(this, url_aug, this);
                Toast.makeText(getApplicationContext(), "August", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                pdfLayout.removeAllViews();
                remotePDFViewPager = new RemotePDFViewPager(this, url_sep, this);
                Toast.makeText(getApplicationContext(), "September", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parentView) {
        // your code here
    }


    @Override
    public void onSuccess(String url, String destinationPath) {
        pdfPagerAdapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(pdfPagerAdapter);
        updateLayout();
        progressBar.setVisibility(View.GONE);
    }

    private void updateLayout() {

        pdfLayout.addView(remotePDFViewPager,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (pdfPagerAdapter != null) {
            pdfPagerAdapter.close();
        }
    }
}