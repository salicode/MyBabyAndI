package com.bawp.babyneeds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class BreastProduction extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breast_production);
        setContentView(R.layout.activity_diat);
        pdfView= findViewById(R.id.yourpdfdt);
        pdfView.fromAsset("FoodstoAvoid.pdf").load();
    }
}
