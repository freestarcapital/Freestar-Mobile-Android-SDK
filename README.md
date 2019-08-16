![Freestar](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/freestar.jpg)
# Freestar Mobile Android SDK Integration Guide
# API - _freestar-android-sdk_ installation

### What's New
We are pleased to announce the release of our SDK! Banner ad formats are currently supported, with more coming.  Be sure to check-in frequently for the latest releases and announcements.

###### Change History
| Version | Release Date | Description |
| ---- | ------- | ----------- |
| __1.1.0__ | _August 16th, 2019_ |  • Updated to androidx. |
| __1.0.0__ | _June 21st, 2019_ |  • Initial release. |

###### Major API Changes
| Latest |
| ---- |
| [ __1.1.0__ ] <br>• Updated the api to latest mopub api and androidx support libraries.<br>|

| Previous |
| ---- |
| [ __1.0.0__ ]<br>• Test application support release. |

###### GMA SDK Compatibility Matrix

| FSAdSDK Version | GMA SDK Version | Prebid SDK Version<br>(Freestar) | Podfile |
| ---- | ----- | ----- | ------------ |
| ~> 1.1.0 | 18.1.1 | FS-1.2.0 | com.google.android.gms:play-services-ads, : jcenter() |
| <= 1.0.0 [EOL]| 17.1.3 | FS-1.0.6 | com.freestar.org.prebid:API1.0 : jcenter() |

---
#### Minimum Requirements
minSDKVersion 16
targetSDKVersion 28
com.android.tools.build:gradle 3.4.2

## Getting Started
---

Here are the basic steps required to use the SDK with your project.

`1. ` Setup **Freestar** specific properties control file

`  a)` Create _assets_ directory in your _src/main_ directory

`  b)` In the _assets_ directory, create a _freestar_ads.properties_ file

`  c)` Add preliminary entries:

```
SHARE_GEO_LOCATION=true
PREBID_FSDATA=https://a.pub.network/app/io.freestar.mobile.Freestar-News/fsdata.json
PREBID_HOST=https://dev-prebid.pub.network/openrtb2/auction`
```

`2. ` Add access permissions to your _AndroidManifest.xml_ in the **manifest** tag block

```
<uses-permission android:name="android.permission.INTERNET"/>`
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>`
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>`
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />`
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />`
```

`3. ` Add add manager meta-data to your _AndroidManifest.xml_ in the **application** tag block

```
<meta-data
  android:name="com.google.android.gms.ads.AD_MANAGER_APP"
  android:value="true"/>
  ```

`4. ` Add dependency to your _build.gradle_ (Project) in the **buildscript.dependencies** block

```
maven {
  url  "https://dl.bintray.com/freestarmobile/com.freestar.org.prebid"
}
```

`5. ` Add dependency to your _build.gradle_ (Module) in the **dependencies** block

```
api 'com.freestar.org.prebid:freestarSDK:1.1.0'
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

[_**Example video**_](https://youtu.be/-k5FHg4YXy4)

![**Basic Reference Application #1**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/app-FSA-1-1.png)
