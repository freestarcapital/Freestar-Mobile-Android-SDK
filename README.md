# Android demo app - _freestar-android-sdk_ integration
The _**freestar-android-sdk**_ allows you to add [**Freestar**](https://www.freestar.com) driven ads to your application.

To see instructions for different install tips and options, please switch branches:

[**Reference Application**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/master)

![**Basic Reference Application #1**](https://github.com/freestarcapital/Freestar-Mobile-Android-SDK/raw/master/images/app-FSA-1-0.png)

The basic reference application (#1) can be found here.  A very simple, single activity game app.  It was adapted from the example presented by https://www.youtube.com/channel/UC_Fh8kvtkVPkeihBs42jGcA .

[**Install Freestar API Branch**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-api-install)

Example of reference application #1, with the **Freestar** ads api installed and ready for specific advertising options to be implemented.  All advertising examaples will be derived by the activities defined by this option.

[**Using FreestarBannerAd View**](https://freestarcapital/Freestar-Mobile-Android-SDK/new/freestar-banner-ad)

Example of using the <FreestarBannerAd> tag within your _activity_ layout _xml_ files.

_**Installing the Freestar API Branch**_

1) Setup **Freestar** specific properties control file
  a) Create _assets_ directory in your _src/main_ directory
  b) In the _assets_ directory, create a _freestar_ads.properties_ file
  c) Add entries:

> `##############################
 SHARE_GEO_LOCATION=true
 PREBID_FSDATA=https://a.pub.network/app/io.freestar.mobile.Freestar-News/fsdata.json
 PREBID_HOST=https://dev-prebid.pub.network/openrtb2/auction`

2) Add access permissions to your _AndroidManifest.xml_ in the **manifest** tag block

> `<uses-permission android:name="android.permission.INTERNET"/>`
> `<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>`
> `<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>`
> `<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />`
> `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />`

3) Add add manager meta-data to your _AndroidManifest.xml_ in the **application** tag block

> `<meta-data
  android:name="com.google.android.gms.ads.AD_MANAGER_APP"
  android:value="true"/>`

4) Add dependency to your _build.gradle_ (Project) in the **buildscript.dependencies** block

> `maven {
  url  "https://dl.bintray.com/freestarmobile/com.freestar.org.prebid"
}`

4) Add dependency to your _build.gradle_ (Module) in the **dependencies** block

> api 'com.freestar.org.prebid:freestarSDK:1.1.0'

[_**Example video**_](https://drive.google.com/open?id=1vPBw0RJq3inqrUUEfznA4UvHx_P-ilKC)
