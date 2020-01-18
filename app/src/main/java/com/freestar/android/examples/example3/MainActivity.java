package com.freestar.android.examples.example3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.vdopia.ads.lw.Chocolate;
import com.vdopia.ads.lw.LVDOAdSize;

public class MainActivity extends AppCompatActivity {
    public static String API_KEY = "XqjhRR";
//    private boolean isLargeLayout;

    private FreestarChocolateBannerAdSlot slotA;
    private FreestarChocolateBannerAdSlot slotB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityJump();
            }
        });

        slotA = new FreestarChocolateBannerAdSlot(findViewById(R.id.ad_container1A), LVDOAdSize.BANNER_320_50);
        slotB = new FreestarChocolateBannerAdSlot(findViewById(R.id.ad_container1B), LVDOAdSize.MEDIUM_RECT_300_250);
//        slotB = new FreestarChocolateBannerAdSlot(findViewById(R.id.ad_container1B), LVDOAdSize.BANNER_320_50);

//        isLargeLayout = getResources().getBoolean(R.bool.large_layout);

        Chocolate.enableLogging(true);  //don't set true for production
        Chocolate.enableChocolateTestAds(false);  //don't set true for production

        slotA.init(this, API_KEY);
        slotB.init(this, API_KEY);

        slotA.loadBannerAd();
        slotB.loadBannerAd();
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
        slotA.resume();
        slotB.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        slotA.pause();
        slotB.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        slotA.destroy();
        slotB.destroy();
    }

}
