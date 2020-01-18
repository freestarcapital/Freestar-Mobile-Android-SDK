package com.freestar.android.examples.example3;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.vdopia.ads.lw.Chocolate;
import com.vdopia.ads.lw.InitCallback;
import com.vdopia.ads.lw.LVDOAdRequest;
import com.vdopia.ads.lw.LVDOAdSize;
import com.vdopia.ads.lw.LVDOBannerAd;
import com.vdopia.ads.lw.LVDOBannerAdListener;
import com.vdopia.ads.lw.LVDOConstants;

public class FreestarChocolateBannerAdSlot implements LVDOBannerAdListener {

    private final ViewGroup masterView;
    private final LVDOAdSize bannerSize;
    private Activity activity;
    private LVDOAdRequest adRequest;
    private LVDOBannerAd bannerAd;

    public FreestarChocolateBannerAdSlot(ViewGroup masterView, LVDOAdSize bannerSize) {
        this.masterView = masterView;
        this.bannerSize = bannerSize;
    }

    public void init(Activity activity, String apiKey) {
        this.activity = activity;
        adRequest = new LVDOAdRequest(activity);

        Chocolate.init(activity, apiKey, adRequest, new InitCallback() {
            @Override
            public void onSuccess() {
                System.out.println("BKS WIN A");
                //Do not pre-fetch ads here.  Chocolate does it automatically, internally.
                //This callback simply lets you know it was fine.
            }

            @Override
            public void onError(String initError) {
                System.out.println("BKS WIN-NOPE A");

            }
        });


    }

    public void loadBannerAd() {
        if (bannerAd != null)
            bannerAd.destroyView();

        bannerAd = new LVDOBannerAd(activity, bannerSize, this);
        bannerAd.loadAd(adRequest);

        /*ChocolatePartners.choosePartners(ChocolatePartners.ADTYPE_INVIEW, this, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ChocolatePartners.setInviewPartners(adRequest);
                bannerAd.loadAd(adRequest);
            }
        });*/

    }

    @Override
    public void onBannerAdLoaded(View view) {
        masterView.addView(view);
        System.out.println("**** onBannerAdLoaded");

    }

    @Override
    public void onBannerAdFailed(View view, LVDOConstants.LVDOErrorCode lvdoErrorCode) {
        System.out.println("**** onBannerAdFailed");
    }

    @Override
    public void onBannerAdClicked(View view) {
        System.out.println("**** onBannerAdClicked");
    }

    @Override
    public void onBannerAdClosed(View view) {
        System.out.println("**** onBannerAdClosed");
    }

    public void resume() {
        if (bannerAd != null) {
            bannerAd.onResume();
        }
    }

    public void pause() {
        if (bannerAd != null) {
            bannerAd.onPause();
        }
    }

    public void destroy() {
        masterView.removeAllViews();
        if (bannerAd != null) {
            bannerAd.destroyView();
        }
    }
}
