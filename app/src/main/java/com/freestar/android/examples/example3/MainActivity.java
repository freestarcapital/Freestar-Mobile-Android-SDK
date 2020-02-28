package com.freestar.android.examples.example3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.confiant.sdk.ConfiantKit;
import com.confiant.sdk.SettingsBuilder;
import com.confiant.sdk.Status;
import com.confiant.sdk.StatusCallback;
import com.freestar.android.sdk.adslot.AdSlotManager;
import com.freestar.android.sdk.adslot.DfpAdSlot;
import com.freestar.android.sdk.domain.DemandFetcher;
import com.freestar.android.sdk.model.FreestarAdModel;
import com.freestar.android.sdk.model.FreestarInteraction;
import com.freestar.android.sdk.model.FreestarViewInjector;
import com.freestar.android.sdk.model.InjectorProperties;
import com.freestar.android.sdk.widget.holder.FreestarAdListener;
import com.freestar.common.LogUtil;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity implements OnInitializationCompleteListener {
    private static final boolean interactionSuccess = new FreestarInteraction.Builder()
            .addDisplayOption("<html><body><h2 style='color:red;font-size:40px'>Your patronage helps us better support you</h2></body></html>")
            .addDisplayOption("<html><body><h2 style='color:red;font-size:40px'>Please support our sponsors</h2></body></html>")
            .addDisplayOption("<html><body><h2 style='color:red;font-size:40px'>Ads support this site</h2></body></html>")
            .addDisplayOption("<html><body><h2 style='color:green;font-size:40px'>Ads powered by Freestar</h2></body></html>")
            .addClickSupportOption("\"<html><body><h1 style='color:orange;font-size:40px'>Thank-you for your support</h1></body></html>\"")
            .load();

    private static DfpAdSlot adSlotA = new DfpAdSlot.Builder()
            .setDfpPlacementId("/15184186/freestar_androidapp_320x50_ATF")
            .addSize(320, 50)
            .addCustomTarget("custom1", "value2")
            .addCustomTarget("custom2", "value1")
            .setAutoRefreshSeconds(30)
            .build();

    private static DfpAdSlot adSlotB = new DfpAdSlot.Builder()
            .setDfpPlacementId("/15184186/freestar_androidapp_300x250_InContent")
            .addSize(300, 250)
            .addCustomTarget("custom1", "value2")
            .addCustomTarget("custom2", "value1")
            .setAutoRefreshSeconds(30)
            .build();

    private View textViewAd1A;
    private View textViewAd1B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FreestarAdModel.getInstance(this).setOnInitilizationCompeteListener(this);
        setContentView(R.layout.activity_main);

        textViewAd1A = findViewById(R.id.text_view_ad_1a);
        textViewAd1B = findViewById(R.id.text_view_ad_1b);
        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityJump();
            }
        });

        ViewGroup adViewA = findViewById(R.id.ad_container1A);
//        adViewA.setBackgroundColor(Color.parseColor("#00ff00"));
        FreestarViewInjector injectorA = FreestarAdModel.getInstance(this).lookupViewInjector(adSlotA);
        injectorA.injectBannerAd(
                adViewA,
                new InjectorProperties.Builder().build(),
                adSlotA);
        ViewGroup adViewB = findViewById(R.id.ad_container1B);
//        adViewB.setBackgroundColor(Color.parseColor("#00ff00"));
        FreestarViewInjector injectorB = FreestarAdModel.getInstance(this).lookupViewInjector(adSlotB);
        injectorB.injectBannerAd(
                adViewB,
                new InjectorProperties.Builder().build(),
                adSlotB);

//        adSlotA.setLocation(new Location("bks"));
//        adSlotB.setLocation(new Location("julee"));
    }

    private void activityJump() {
        Intent intent = new Intent(this, SecondaryActivity.class);
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
        ViewGroup adViewA = findViewById(R.id.ad_container1A);
        adViewA.removeAllViews();
        ViewGroup adViewB = findViewById(R.id.ad_container1B);
        adViewB.removeAllViews();
        FreestarAdModel.getInstance(this).lookupViewInjector(adSlotA).destroyAd();
        FreestarAdModel.getInstance(this).lookupViewInjector(adSlotB).destroyAd();
        FreestarAdModel.releaseInstance(this);
        super.onDestroy();
    }

    @Override
    public void onInitializationComplete(InitializationStatus initializationStatus) {
        final @NonNull SettingsBuilder settingsBuilder = new SettingsBuilder()
                .withPropertyID("i0pU4gfzb-opTaw3FzyGGbraZ-g")
                .withAlwaysBlocking(true);
        ConfiantKit.instance.initialize(settingsBuilder, new StatusCallback(){
            @Override public void callback(Status status) {
                LogUtil.i("Freestar", "Confiant initialize status: " + status.getDescription());
// TODO: check errors
            }
        });
    }
}
