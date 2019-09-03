![Freestar](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/freestar.jpg)
# Freestar Mobile Android SDK Integration Guide
# API - _freestar-android-sdk_ main2 installation for RecyclerView

### What's New
We are pleased to announce the release of our SDK! Banner ad formats are currently supported, with more coming.  Be sure to check-in frequently for the latest releases and announcements.

###### Change History
| Version | Release Date | Description |
| ---- | ------- | ----------- |
| __1.0.1__ | _September 3rd, 2019_ |  • freestar API to 1.2.5. |
| __1.0.0__ | _August 28th, 2019_ |  • Initial release. |

###### GMA SDK Compatibility Matrix

| FSAdSDK Version | GMA SDK Version | Prebid SDK Version<br>(Freestar) | Podfile |
| ---- | ----- | ----- | ------------ |
| ~> 1.2.5 | 18.1.1 | FS-1.2.5 | com.google.android.gms:play-services-ads, : jcenter() |
| ~> 1.2.2 | 18.1.1 | FS-1.2.3 | com.google.android.gms:play-services-ads, : jcenter() |

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
PREBID_FSDATA_ID=com.freestar.android.examples
#USE_PREBID_DEV_HOST=true
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

`4. ` Add dependency to your _build.gradle_ (Project) in the **allprojects.repositories** block

```
maven {
  url  "https://dl.bintray.com/freestarmobile/com.freestar.org.prebid"
}
```

`5. ` Add dependency to your _build.gradle_ (Module) in the **dependencies** block

```
api 'com.freestar.org.prebid:freestarSDK:1.2.5'
```

## By Example

The **master** branch contains the basic reference RecyclerView application.  The **freestar-api-install** branch has the initialization steps completed, and may act as a reference application for further examples.

[**Reference Application**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/master2)

The basic reference application (#2) can be found here.  A very simple, RecyclerView app.

[**Install2 Freestar API Branch**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-api-install2)

Example of reference application (RecyclerView) (#2), with the **Freestar** ads api installed and ready for specific advertising options to be implemented with .  All advertising examples will be derived by the activities defined by this option (#2-I).

[**Using FreestarRecycler View Injector**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-recycler-view-injector)

Example of reference application (#2-I), with the usage of the **Freestar** recycler view injector.  This facility injects ads into an existing list, destined for a RecyclerView.
