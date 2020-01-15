package com.bawp.babyneeds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class Benefit extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benefit);

        pdfView =findViewById(R.id.mypdfbne);
        pdfView.fromAsset("BenefitsOfBreastfeeding.pdf").load();
    }
}
