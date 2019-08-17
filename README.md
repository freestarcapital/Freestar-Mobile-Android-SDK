![Freestar](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/freestar.jpg)
# Freestar Mobile Android SDK Integration Guide
# API - _freestar-android-sdk_ ***FrestarBannerAd*** tag

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

Here are the basic steps required to use the **<FreestarBannerAd>** your project.

`0. ` Configure using the "basic reference application" instructions


`1. ` Add the *<FreestarBannerAd>* tag to your layout activity _xml_file

```
    <com.freestar.android.sdk.view.FreestarBannerAd
       xmlns:custom="http://schemas.android.com/apk/res-auto"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
    />
```

`2. ` Configure the _**type**_ and _**placement**_ attributes

```
    <com.freestar.android.sdk.view.FreestarBannerAd
       xmlns:custom="http://schemas.android.com/apk/res-auto"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       custom:type="prebid"
       custom:placement="Freestar_Test_320x50"
    />
```

## By Example

The **master** branch contains the basic reference application.  The **freestar-banner-ad** branch has the initialization steps completed, and may act as a reference application for further examples.

[**Reference Application**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/master)

![**Basic Reference Application #1**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/app-FSA-1-0.png)

The basic reference application (#1) can be found here.  A very simple, single activity game app.  It was adapted from the example presented by https://www.youtube.com/channel/UC_Fh8kvtkVPkeihBs42jGcA .

[**Install Freestar API Branch**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-api-install)

Example of reference application #1, with the **Freestar** ads api installed and ready for specific advertising options to be implemented.  All advertising examaples will be derived by the activities defined by this option.

[**Using FreestarBannerAd View**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-banner-ad)

Example of using the <FreestarBannerAd> tag within your _activity_ layout _xml_ files.

_**Using the Freestar FreestarBannerAd tag**_

0) Configure using the "basic reference application" instructions

1) Add the *<FreestarBannerAd>* tag to your layout activity _xml_file
```
    <com.freestar.android.sdk.view.FreestarBannerAd
       xmlns:custom="http://schemas.android.com/apk/res-auto"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
    />
```

2) Configure the _**type**_ and _**placement**_ attributes
```
    <com.freestar.android.sdk.view.FreestarBannerAd
       xmlns:custom="http://schemas.android.com/apk/res-auto"
       android:layout_width="wrap_content"
       android:layout_height="wrap_content"
       custom:type="prebid"
       custom:placement="Freestar_Test_320x50"
    />
```

[_**Example video**_](https://drive.google.com/open?id=1qFlshPINDZNs5RPi1OWu1M7-66PM1_7x)
