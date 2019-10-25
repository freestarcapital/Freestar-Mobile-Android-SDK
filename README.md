![Freestar](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/freestar.jpg)
# Freestar Mobile Android SDK Integration Guide
# API - _freestar-android-sdk_ ***Create Banner View***

### What's New
We are pleased to announce the release of our SDK! Banner ad formats are currently supported, with more coming.  Be sure to check-in frequently for the latest releases and announcements.

###### Change History
|  Version  |     Release Date     |                Description                |
| --------- | -------------------- | ----------------------------------------- |
| __1.0.0__ | _October 14th, 2019_ |  • Initial release.                       |
| __1.0.1__ | _October 25th, 2019_ |  • move to api v1.0.1.                    |

###### GMA SDK Compatibility Matrix

| FSAdSDK Version | GMS play-services-ads Version | Repository |
| --------------- | ----------------------------- | ---------- |
| _____1.0.1_____ | ___________18.2.0____________ |  jcenter() |

---
#### Minimum Requirements
minSDKVersion 16
targetSDKVersion 28
com.android.tools.build:gradle 3.5.1

## Getting Started
---

Here are the basic steps required to use the create banner.

`1. ` Add an id to your ad's parent tag in your activity layout _.xml_ file.  In our example we are going to give it the id of **ad_container**.

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:id="@+id/ad_container"
    tools:context=".MainActivity">
```

`2. ` Edit your _assets/freestar_ads.properties_ file and add in your ad specific information (for example it should look something like this)

```
None required at this time.
```

`3. ` Edit your activity class, in the onCreate() method.

Create your adSlot objects.  They should be static within the activity to ensure consistent ad activity as activities are recreated, etc.  Choose the approprate slot type.  And choose your custom tags as needed.

Option A
```
    private static final String AD_PLACEMENT1 = "adPlacement1";
```

then

```
        String adKey = FreestarAdModel.getInstance(this).getProperty(AD_PLACEMENT1);
        ViewGroup adContainer = findViewById(R.id.ad_container);
        adView = FreestarAdModel.getInstance(this).createBanner(adKey);
        adContainer.addView(adView);

        final PublisherAdRequest.Builder builder = new PublisherAdRequest.Builder();
        PublisherAdRequest adRequest = builder
        // .addTestDevice("98FA47D6A44364C8F59E90AD4E59A932")
          .build();
          
        adView.loadAd(adRequest);
```
Option B (preferred)
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
            ViewGroup adContainer = findViewById(R.id.ad_container);
            adView = FreestarAdModel.getInstance(this).createBanner(adContainer, adSlot);

            final PublisherAdRequest.Builder builder = new PublisherAdRequest.Builder();
            PublisherAdRequest adRequest = builder
//                .addTestDevice("98FA47D6A44364C8F59E90AD4E59A932")
                    .build();
            adView.loadAd(adRequest);
```

`4. ` Add the following methods to your activity class. 

```
    @Override
    protected void onResume() {
        super.onResume();
        adView.resume();
    }

    @Override
    protected void onPause() {
        adView.pause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        adView.destroy();
        super.onDestroy();
    }


```

## By Example

The **master** branch contains the basic reference application.  The **freestar-api-install** branch has the initialization steps completed, and may act as a reference application for further examples.

[**Reference Application #1**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/master)

![**Basic Reference Application #1**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/app-FSA-1-0.png)

The basic reference application (#1) can be found here.  A very simple, single activity game app.  It was adapted from the example presented by https://www.youtube.com/channel/UC_Fh8kvtkVPkeihBs42jGcA .

[**Install Freestar API Branch**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-api-install)

Example of reference application #1, with the **Freestar** ads api installed and ready for specific advertising options to be implemented.  All advertising examaples will be derived by the activities defined by this option.

[**Using FreestarBannerAd View**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-banner-ad)

Example of using the <FreestarBannerAd> tag within your _activity_ layout _xml_ files.

[_**Example video**_***Comming Soon***]()

![**Basic Reference Application #1**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/app-FSA-1-1.png)
