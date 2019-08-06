# Android demo app - examples for freestar-android-sdk integration
The freestar-android-sdk allows you to add [**Freestar**] (https://www.freestar.com) ads to your application.

To see instructions for different install options, please switch branches:

[**Reference Application**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/master)
![alt text](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/app-FSA-1-0.png)
Adapted from the example presented by https://www.youtube.com/channel/UC_Fh8kvtkVPkeihBs42jGcA .

[**Install Freestar API Branch**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-api-install)
![alt text](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/app-FSA-1-1.png)

[**Using FreestarBannerAd View**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-banner-ad)


Installing the Freestar API Branch

1) add access permissions to your AndroidManifest.xml
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
2) add add manager meta-data to your AndroidManifest.xml application
        <meta-data
            android:name="com.google.android.gms.ads.AD_MANAGER_APP"
            android:value="true"/>
3) add dependencie(s) to your build.gradle (Project)/(Module)
        maven {
            url  "https://dl.bintray.com/freestarmobile/com.freestar.org.prebid"
        }
    api 'com.google.android.gms:play-services-ads:18.1.1'
    api 'com.freestar.org.prebid:API1.0:FS-1.0.3'
    api 'com.freestar.org.prebid:freestarSDK:1.0.4'
    implementation 'com.google.code.gson:gson:2.8.5'
    implementation 'com.google.android.gms:play-services-auth:16.0.1'
4) add ad tag to appropriate layout files
    <com.freestar.android.sdk.view.FreestarBannerAd
        xmlns:custom="http://schemas.android.com/apk/res-auto"
        android:id="@+id/ads_layout"
        android:layout_width="320dp"
        android:layout_height="100dp"
        android:gravity="bottom"
        android:orientation="vertical"
        custom:type="prebid"
        custom:placement="Freestar_Test_320x50"
        />
5) setup properties control file
  a) create assets directory in your src/main directory
  b) in assets directory, create freestar_ads.properties file
  c) Add entries:
  ##############################
  SHARE_GEO_LOCATION=true

  PREBID_FSDATA=https://a.pub.network/app/io.freestar.mobile.Freestar-News/fsdata.json
  #PREBID_HOST=https://prebid.pub.network/openrtb2/auction
  PREBID_HOST=https://dev-prebid.pub.network/openrtb2/auction
  #PREBID_HOST=http://Admins-MBP.pub.network:8080/openrtb2/auction
  #PREBID_HOST=http://192.168.1.131:8080/openrtb2/auction

  listInjectType=prebid
  listInjectPlacement=Freestar_Test_320x50

  item_list.listInjectOffsetCount=3
  item_list.listInjectAutoRefresh=120000
  item_list.listInjectOffsetIncludeLeading=true
  item_list.listInjectOffsetIncludeTrailing=true

  articleDetailType=prebid
  articleDetailPlacement=Freestar_Test_320x100
  ads_layout.articleDetailAutoRefresh=120000



        FreestarAdModel.getInstance(this);

        ViewGroup adView = findViewById(R.id.ads_layout);
        FreestarViewInjector injector = FreestarAdModel.getInstance(this).lookupViewInjector(R.layout.activity_main);
        AdEngineType articleDetailType = AdEngineType.valueOf(TargetingModel.getInstance().getProperty("articleDetailType", AdEngineType.prebid.toString()));
        String adKey = TargetingModel.getInstance().getProperty("articleDetailPlacement");
        injector.injectBannerAd(articleDetailType, adView, "ads_layout", adKey);


    @Override
    protected void onResume() {
        super.onResume();
        AdEngineType articleDetailType = AdEngineType.valueOf(TargetingModel.getInstance().getProperty("articleDetailType", AdEngineType.prebid.toString()));
        FreestarAdModel.getInstance(this).lookupViewInjector(R.layout.activity_main).resumeAd(articleDetailType);
    }

    @Override
    protected void onPause() {
        AdEngineType articleDetailType = AdEngineType.valueOf(TargetingModel.getInstance().getProperty("articleDetailType", AdEngineType.prebid.toString()));
        FreestarAdModel.getInstance(this).lookupViewInjector(R.layout.activity_main).pauseAd(articleDetailType);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        AdEngineType articleDetailType = AdEngineType.valueOf(TargetingModel.getInstance().getProperty("articleDetailType", AdEngineType.prebid.toString()));
        FreestarAdModel.getInstance(this).lookupViewInjector(R.layout.activity_main).destroyAd(articleDetailType);
        super.onDestroy();
    }


    <!--
    <LinearLayout
        android:id="@+id/ads_layout"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        />
    <com.freestar.android.sdk.view.FreestarBannerAd
        xmlns:custom="http://schemas.android.com/apk/res-auto"
        android:id="@+id/ads_layout"
        android:layout_width="320dp"
        android:layout_height="100dp"
        android:gravity="bottom"
        android:orientation="vertical"
        custom:type="prebid"
        custom:placement="Freestar_Test_320x50"
        />
        -->
    <com.freestar.android.sdk.view.FreestarBannerAd
        xmlns:custom="http://schemas.android.com/apk/res-auto"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        custom:type="prebid"
        custom:placement="Freestar_Test_320x50"
        />

