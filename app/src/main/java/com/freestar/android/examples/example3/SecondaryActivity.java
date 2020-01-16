package com.freestar.android.examples.example3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.freestar.android.sdk.adslot.DfpAdSlot;
import com.freestar.android.sdk.model.FreestarAdModel;
import com.freestar.android.sdk.model.FreestarViewInjector;
import com.freestar.android.sdk.model.InjectorProperties;

public class SecondaryActivity extends AppCompatActivity {
    private static DfpAdSlot adSlotA = new DfpAdSlot.Builder()
            .setDfpPlacementId("/15184186/freestar_androidapp_300x250_InContent")
            .addSize(300, 250)
            .addCustomTarget("custom1", "value2")
            .addCustomTarget("custom2", "value1")
            .setAutoRefreshSeconds(30)
            .build();

    private static DfpAdSlot adSlotB = new DfpAdSlot.Builder()
            .setDfpPlacementId("/15184186/freestar_androidapp_320x50_ATF")
            .addSize(320, 50)
            .addCustomTarget("custom1", "value2")
            .addCustomTarget("custom2", "value1")
            .setAutoRefreshSeconds(30)
            .build();

    private View textViewAd2A;
    private View textViewAd2B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FreestarAdModel.getInstance(this);
        setContentView(R.layout.activity_secondary);

        textViewAd2A = findViewById(R.id.text_view_ad_2a);
        textViewAd2B = findViewById(R.id.text_view_ad_2b);
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityJump();
            }
        });

        ViewGroup adViewA = findViewById(R.id.ad_container2A);
        //adViewA.setBackgroundColor(Color.parseColor("#00aa00"));
        FreestarViewInjector injectorA = FreestarAdModel.getInstance(this).lookupViewInjector(adSlotA);
        injectorA.injectBannerAd(
                adViewA,
                new InjectorProperties.Builder().build(),
                adSlotA);
        ViewGroup adViewB = findViewById(R.id.ad_container2B);
        //adViewB.setBackgroundColor(Color.parseColor("#00aa00"));
        FreestarViewInjector injectorB = FreestarAdModel.getInstance(this).lookupViewInjector(adSlotB);
        injectorB.injectBannerAd(
                adViewB,
                new InjectorProperties.Builder().build(),
                adSlotB);
    }

    private void activityJump() {
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        FreestarAdModel.getInstance(this).lookupViewInjector(adSlotA).resumeAd();
        FreestarAdModel.getInstance(this).lookupViewInjector(adSlotB).resumeAd();
    }

    @Override
    protected void onPause() {
        FreestarAdModel.getInstance(this).lookupViewInjector(adSlotA).pauseAd();
        FreestarAdModel.getInstance(this).lookupViewInjector(adSlotB).pauseAd();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        FreestarAdModel.getInstance(this).lookupViewInjector(adSlotA).destroyAd();
        FreestarAdModel.getInstance(this).lookupViewInjector(adSlotB).destroyAd();
        super.onDestroy();
    }
}
