#!/bin/sh

#ls ~/master_project/workspace/prebid-mobile-android/PrebidMobile/API1.0/build/outputs/aar/API1.0-release.aar 
# /Users/bsorensen/master_project/workspace/freestar-android-sdk/freestarSDK/build/outputs/aar/freestarSDK-release.aar
mvn install:install-file -DgroupId=freestar-mobile -DartifactId=freestar-api -Dversion=1.0.0 -Dfile=/Users/bsorensen/master_project/workspace/freestar-android-sdk/freestarSDK/build/outputs/aar/freestarSDK-release.aar -Dpackaging=aar -DgeneratePom=true -DlocalRepositoryPath=.  -DcreateChecksum=true

