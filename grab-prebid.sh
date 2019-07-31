#!/bin/sh

#ls ~/master_project/workspace/prebid-mobile-android/PrebidMobile/API1.0/build/outputs/aar/API1.0-release.aar 

mvn install:install-file -DgroupId=freestar-mobile -DartifactId=prebid-api-1.0 -Dversion=FS-1.0.0 -Dfile=/Users/bsorensen/master_project/workspace/prebid-mobile-android/PrebidMobile/API1.0/build/outputs/aar/API1.0-release.aar -Dpackaging=aar -DgeneratePom=true -DlocalRepositoryPath=.  -DcreateChecksum=true

