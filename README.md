![Freestar](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/freestar.jpg)
# Freestar Mobile Android SDK Integration Guide
# API - _freestar-android-sdk_ ***View Injector***

### What's New
We are pleased to announce the release of our SDK! Banner ad formats are currently supported, with more coming.  Be sure to check-in frequently for the latest releases and announcements.

###### Change History
|  Version  |     Release Date     |                Description                |
| --------- | -------------------- | ----------------------------------------- |
| __1.0.0__ | _October 14th, 2019_ |  • Initial release.                       |

###### GMA SDK Compatibility Matrix

| FSAdSDK Version | GMS play-services-ads Version | Repository |
| --------------- | ----------------------------- | ---------- |
| _____1.0.0_____ | ___________18.2.0____________ |  jcenter() |

---
#### Minimum Requirements
minSDKVersion 16
targetSDKVersion 28
com.android.tools.build:gradle 3.5.1

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

OPTION A
```
adPlacement1=freestar_androidapp_320x50_ATF
adPlacement2=freestar_androidapp_300x250_InContent
#ads_layout.articleDetailAutoRefresh=38
```
OPTION B (preferred)
```
```

`3. ` Edit your activity class, in the onCreate() method.


Create your adSlot objects.  They should be static within the activity to ensure consistent ad activity as activities are recreated, etc.  Choose the approprate slot type.  And choose your custom tags as needed.

OPTION A
```
    private static final String AD_PLACEMENT1 = "adPlacement1";
    private static final String AD_PLACEMENT2 = "adPlacement2";
    private static final String DFP_PLACEMENT1 = "dfpPlacement1";
    private static final String DFP_PLACEMENT2 = "dfpPlacement2";
    private static final String DFP_SIZE1 = "dfpSize1";
    private static final String DFP_SIZE2 = "dfpSize2";
    private static final FreestarAdSlot adSlot = new FreestarAdSlot.Builder()
```

then

```
         ViewGroup adView = findViewById(R.id.ads_layout);
         List<CustomTargetingEntry> customTargets = new CustomTargetingEntry.ListBuilder()
                 .addCustomTargeting("custom1", "value2")
                 .addCustomTargeting("custom2", "value1")
                 .build();
         FreestarViewInjector injector = FreestarAdModel.getInstance(this).lookupViewInjector(R.layout.activity_main);
         String placementName1 = FreestarAdModel.getInstance(this).getProperty(AD_PLACEMENT1);
         #String placementName2 = FreestarAdModel.getInstance(this).getProperty(AD_PLACEMENT2);
         #String dfpName1 = FreestarAdModel.getInstance(this).getProperty(DFP_PLACEMENT1);
         #String dfpName2 = FreestarAdModel.getInstance(this).getProperty(DFP_PLACEMENT2);
         injector.injectBannerAd(adView, "ads_layout", placementName1, customTargets);
         #injector.injectBannerAd(adView, "ads_layout", placementName2, customTargets);
         #String dfpSize = FreestarAdModel.getInstance(this).getProperty(DFP_SIZE1);
         #injector.injectBannerAdByDfp(adView, "ads_layout", dfpName1, dfpSize, customTargets);
         #String dfpSize = FreestarAdModel.getInstance(this).getProperty(DFP_SIZE2);
         #injector.injectBannerAdByDfp(adView, "ads_layout", dfpName2, dfpSize, customTargets);

```
OPTION B (preferred)
```
    private static final FreestarAdSlot adSlot = new FreestarAdSlot.Builder()
            .setPlacementId("freestar_androidapp_320x50_ATF")
            .addCustomTarget("pos", "top")
            .addCustomTarget("s1", "home")
            .addCustomTarget("pid", "home")
            .build();


    private static final DfpAdSlot adSlot = new DfpAdSlot.Builder()
            .setDfpPlacementId("/15184186/freestar_androidapp_320x50_ATF")
            .setAutoRefreshSeconds(30)
            .addSize(320, 50)
            .addCustomTarget("pos", "top")
            .addCustomTarget("s1", "home")
            .addCustomTarget("pid", "home")
            .build();
```

then

```
            ViewGroup adView = findViewById(R.id.ads_layout);
            FreestarViewInjector injector = FreestarAdModel.getInstance(this).lookupViewInjector(adSlot);
            injector.injectBannerAd(adView, adSlot);

```

`4. ` Add the following methods to your activity class. 

OPTION A
```
    @Override
    protected void onResume() {
        super.onResume();
        FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(R.layout.activity_main).resumeBannerAds();
    }

    @Override
    protected void onPause() {
        FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(R.layout.activity_main).pauseBannerAds();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(R.layout.activity_main).destroyBannerAds();
        FreestarAdModel.releaseInstance(this);
        super.onDestroy();
    }

```
OPTION B (preferred)
```
    @Override
    protected void onResume() {
        super.onResume();
        FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(adSlot).resumeBannerAds();
    }

    @Override
    protected void onPause() {
        FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(adSlot).pauseBannerAds();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        FreestarAdModel.getInstance(this).lookupRecyclerViewInjector(adSlot).destroyBannerAds();
        FreestarAdModel.releaseInstance(this);
        super.onDestroy();
    }

```

## By Example

The **master** branch contains the basic reference application.  The **freestar-api-install** branch has the initialization steps completed, and may act as a reference application for further examples.

[**Reference Application**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/master)

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

Example of using the <FreestarBannerAd> tag within your _activity_ layout _xml_ files.

[_**Example video**_***Comming Soon***]()

