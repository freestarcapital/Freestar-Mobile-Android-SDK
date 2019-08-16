![Freestar](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/freestar.jpg)
# Freestar Mobile Android SDK Integration Guide

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

## Information on Getting Started
---

To see instructions for different install tips and options, please switch branches:

## By Example

[**Reference Application**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/master)

The basic reference application (#1) can be found here.  A very simple, single activity game app.  It was adapted from the example presented by https://www.youtube.com/channel/UC_Fh8kvtkVPkeihBs42jGcA .

[**Install Freestar API Branch**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-api-install)

Example of reference application (#1), with the **Freestar** ads api installed and ready for specific advertising options to be implemented.  All advertising examples will be derived by the activities defined by this option (#1-I).

[**Install2 Freestar API Branch**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-api-install2)

Example of reference application (RecyclerView) (#2), with the **Freestar** ads api installed and ready for specific advertising options to be implemented with .  All advertising examples will be derived by the activities defined by this option (#2-I).

[**Using Freestar View Injector**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-view-injector)

Example of reference application (#1-I), with the usage of the **Freestar** view injector.  This facility injects an ad into an existing LayoutView created and named for that purpose.

[**Using FreestarRecycler View Injector**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-recycler-view-injector)

Example of reference application (#2-I), with the usage of the **Freestar** recycler view injector.  This facility injects ads into an existing list, destined for a RecyclerView.

[**Using FreestarBannerAd View**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-banner-ad)

Example of reference application (#1-I), with the usage of the **Freestar** banner ad view.

![**Basic Reference Application #1**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/app-FSA-1-0.png)

