package com.bawp.babyneeds;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class Diat extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diat);
        pdfView= findViewById(R.id.mypdfdt);
        pdfView.fromAsset("FoodstoAvoid.pdf").load();
    }
}
