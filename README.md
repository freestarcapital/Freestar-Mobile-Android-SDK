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
