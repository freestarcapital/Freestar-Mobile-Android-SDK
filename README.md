![Freestar](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/freestar.jpg)
# Freestar Mobile Android SDK Integration Guide
# API - _freestar-android-sdk_ ***FrestarBannerAd*** tag

### What's New
We are pleased to announce the release of our SDK! Banner ad formats are currently supported, with more coming.  Be sure to check-in frequently for the latest releases and announcements.

###### Change History
| Version | Release Date | Description |
| ---- | ------- | ----------- |
| __1.0.2__ | _August 28th, 2019_ |  • use FreestarBannerAd name for stand alone - injectable moved to SimpleBannerAd . |
| __1.0.1__ | _August 28th, 2019_ |  • freestar API to 1.2.3. |
| __1.0.0__ | _August 16th, 2019_ |  • Initial release. |

###### GMA SDK Compatibility Matrix

| FSAdSDK Version | GMA SDK Version | Prebid SDK Version<br>(Freestar) | Podfile |
| ---- | ----- | ----- | ------------ |
| ~> 1.2.5 | 18.1.1 | FS-1.2.4 | com.google.android.gms:play-services-ads, : jcenter() |

---
#### Minimum Requirements
minSDKVersion 16
targetSDKVersion 28
com.android.tools.build:gradle 3.4.2

## Getting Started
---

Here are the basic steps required to use the **<FreestarBannerAd>** your project.

`0. ` Configure using the "basic reference application" instructions


`1. ` Add the *<FreestarBannerAd>* tag to your layout activity _xml_file.  

```
    <com.freestar.android.sdk.view.FreestarBannerAd
        xmlns:custom="http://schemas.android.com/apk/res-auto"
        android:id="@+id/ad_placement1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
```

`2. ` Configure the custom entries _**adSize**_ and _**adUnitId**_ and optional _**customTargets**_ attributes

```
    <com.freestar.android.sdk.view.FreestarBannerAd
        xmlns:custom="http://schemas.android.com/apk/res-auto"
        android:id="@+id/ad_placement1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        custom:adSize="320x50"
        custom:adUnitId="/15184186/freestar_androidapp_320x50_ATF"
        />
```

`3. ` In your **activity** class in the **onCreate()** method, initialize the ad model prior to the content view initialization.

before
```
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
```
after
```
        super.onCreate(savedInstanceState);
        FreestarAdModel.getInstance(this);
        setContentView(R.layout.activity_main);
```

`4. ` In your **activity** class in the **onCreate()** method, create an optional AdListener if you need one.

```
        adPlacement1 = findViewById(R.id.ad_placement1);
        adPlacement1.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int var1) {
                super.onAdFailedToLoad(var1);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            /**
             * Ad displayed on page
             */
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }
        });

```

`5. ` In your **activity** class in the **onCreate()** method, create a PublisherAdRequest and you can then load an ad.

```
        PublisherAdRequest.Builder builder = new PublisherAdRequest.Builder();
        builder = builder.addCustomTargeting("myTarget3", "myValue3");
        PublisherAdRequest request = builder.build();
        adPlacement1.loadAd(request);
    
```

`6. ` In your **activity** class create appropriate methods so the ad view can behave appropriately with application lifecycle state changes.

```
    @Override
    protected void onResume() {
        super.onResume();
        adPlacement1.resume();
    }

    @Override
    protected void onPause() {
        adPlacement1.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        adPlacement1.destroy();
        super.onDestroy();
    }
    
```

`7. ` In **freestar_ads.properties** you **may** add properties to set the auto refresh rates for your FreestarBannerAds (in seconds) to override the backend supplied value.  Notice the root of the tag matches the entries in your layout xml file.

```
ad_placement1.autoRefreshSeconds=38
ad_placement2.autoRefreshSeconds=39
```


## By Example

The **master** branch contains the basic reference application.  The **freestar-banner-ad** branch has the initialization steps completed, and may act as a reference application for further examples.

[**Reference Application**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/master)

![**Basic Reference Application #1**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/app-FSA-1-0.png)

The basic reference application (#1) can be found here.  A very simple, single activity game app.  It was adapted from the example presented by https://www.youtube.com/channel/UC_Fh8kvtkVPkeihBs42jGcA .

[**Install Freestar API Branch**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-api-install)

Example of reference application (#1), with the **Freestar** ads api installed and ready for specific advertising options to be implemented.  All advertising examples will be derived by the activities defined by this option (#1-I).

[**Install2 Freestar API Branch**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-api-install2)

Example of reference application (RecyclerView) (#2), with the **Freestar** ads api installed and ready for specific advertising options to be implemented with .  All advertising examples will be derived by the activities defined by this option (#2-I).

[**Using Freestar Create Banner View**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/create-banner)

Example of reference application (#1-B), using the create banner factory.  This facility returns a PublisherAdView.

[**Using Freestar View Injector**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-view-injector)

Example of reference application (#1-I), with the usage of the **Freestar** view injector.  This facility injects an ad into an existing LayoutView created and named for that purpose.

[**Using FreestarRecycler View Injector**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-recycler-view-injector)

Example of reference application (#2-I), with the usage of the **Freestar** recycler view injector.  This facility injects ads into an existing list, destined for a RecyclerView.

[**Using FreestarBannerAd View**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-banner-ad)

Example of reference application (#1-I), with the usage of the **Freestar** banner ad view.

![**Basic Reference Application #1**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/app-FSA-1-0.png)

[_**Example video**_](https://drive.google.com/open?id=1qFlshPINDZNs5RPi1OWu1M7-66PM1_7x)
