# Android demo app - examples for freestar-android-sdk integration
The freestar-android-sdk allows you to add [**Freestar**] (https://www.freestar.com) ads to your application.

To see instructions for different install options, please switch branches:

[**Reference Application**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/master)

[**Install Freestar API Branch**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-api-install)

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

