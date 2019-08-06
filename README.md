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

1) setup properties control file
  a) create assets directory in your src/main directory
  b) in assets directory, create freestar_ads.properties file
  c) Add entries:
  ##############################
  SHARE_GEO_LOCATION=true

  PREBID_FSDATA=https://a.pub.network/app/io.freestar.mobile.Freestar-News/fsdata.json
  PREBID_HOST=https://dev-prebid.pub.network/openrtb2/auction

2) add access permissions to your AndroidManifest.xml
    <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
3) add add manager meta-data to your AndroidManifest.xml application
        <meta-data
            android:name="com.google.android.gms.ads.AD_MANAGER_APP"
            android:value="true"/>
4) add dependencie(s) to your build.gradle (Project)/(Module)
        maven {
            url  "https://dl.bintray.com/freestarmobile/com.freestar.org.prebid"
        }
    api 'com.freestar.org.prebid:freestarSDK:1.1.0'
