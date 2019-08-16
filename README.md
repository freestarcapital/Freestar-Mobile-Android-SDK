![Freestar](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/freestar.jpg)
# Freestar Mobile Android SDK Integration Guide
# API - _freestar-android-sdk_ ***View Injector***

### What's New
We are pleased to announce the release of our SDK! Banner ad formats are currently supported, with more coming.  Be sure to check-in frequently for the latest releases and announcements.

###### Change History
| Version | Release Date | Description |
| ---- | ------- | ----------- |
| __1.2.0__ | _August 16th, 2019_ |  • Updated to androidx. |
| __1.1.0__ | _June 21st, 2019_ |  • Support FreestarNews app. |
| __1.0.0__ | _June 21st, 2019_ |  • Initial release. |

###### Major API Changes
| Latest |
| ---- |
| [ __1.2.0__ ] <br>• Updated the api to latest mopub api and androidx support libraries.<br>|

| Previous |
| ---- |
| [ __1.0.0__ ]<br>• Test application support release. |

###### GMA SDK Compatibility Matrix

| FSAdSDK Version | GMA SDK Version | Prebid SDK Version<br>(Freestar) | Podfile |
| ---- | ----- | ----- | ------------ |
| ~> 1.2.0 | 18.1.1 | FS-1.2.0 | com.google.android.gms:play-services-ads, : jcenter() |
| = 1.1.0 [EOL]| 17.1.3 | FS-1.1.0 | com.google.android.gms:play-services-ads, : mavenLocal() |
| <= 1.0.0 [EOL]| 17.1.3 | FS-1.0.6 | com.freestar.org.prebid:API1.0 : jcenter() |

---
#### Minimum Requirements
minSDKVersion 16
targetSDKVersion 28
com.android.tools.build:gradle 3.4.2

## Getting Started
---

Here are the basic steps required to use the injector your project.

`1. ` Create a _LinearLayout_ tag in your activity layout _.xml_ file.  In our example we are going to give it the id of **ads_layout**.

```
    <LinearLayout
        android:id="@+id/ads_layout"
        android:layout_width="320dp"
        android:layout_height="100dp"
        android:gravity="bottom"
        android:orientation="vertical"
        />
```

`2. ` Edit your _assets/freestar_ads.properties_ file and add in your ad specific information (for example it should look something like this)

```
articleDetailType=prebid
articleDetailPlacement=Freestar_Test_320x100
ads_layout.articleDetailAutoRefresh=120000
```

`3. ` Edit your activity class, in the onCreate() method.

```
        AdEngineType articleDetailType = AdEngineType.valueOf(FreestarAdModel.getInstance(this).getProperty("articleDetailType", AdEngineType.gam.toString()));
        String adKey = FreestarAdModel.getInstance(this).getProperty("articleDetailPlacement");
        if (adKey != null) {
            playAds = true;
        } else {
            System.err.println("missing placement for article detail, check configuration properties");
            playAds = false;
        }

        if (playAds) {
            ViewGroup adView = findViewById(R.id.ads_layout);
            FreestarViewInjector injector = FreestarAdModel.getInstance(this).lookupViewInjector(R.layout.activity_main);
            injector.injectBannerAd(articleDetailType, adView, "ads_layout", adKey);
        }

```

`4. ` Add the following methods to your activity class. 

```
    @Override
    protected void onResume() {
        super.onResume();
        if (playAds) {
            FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(R.layout.activity_main).resumeBannerAds();
        }
    }

    @Override
    protected void onPause() {
        if (playAds) {
            FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(R.layout.activity_main).pauseBannerAds();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (playAds) {
            FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(R.layout.activity_main).destroyBannerAds();
            FreestarAdModel.releaseInstance(this);
        }
        super.onDestroy();
    }

```

## By Example

The **master** branch contains the basic reference application.  The **freestar-api-install** branch has the initialization steps completed, and may act as a reference application for further examples.

[**Reference Application**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/master)

![**Basic Reference Application #1**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/app-FSA-1-0.png)

The basic reference application (#1) can be found here.  A very simple, single activity game app.  It was adapted from the example presented by https://www.youtube.com/channel/UC_Fh8kvtkVPkeihBs42jGcA .

[**Install Freestar API Branch**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-api-install)

Example of reference application #1, with the **Freestar** ads api installed and ready for specific advertising options to be implemented.  All advertising examaples will be derived by the activities defined by this option.

[**Using FreestarBannerAd View**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-banner-ad)

Example of using the <FreestarBannerAd> tag within your _activity_ layout _xml_ files.

[_**Example video**_***Comming Soon***]()

![**Basic Reference Application #1**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/app-FSA-1-1.png)
