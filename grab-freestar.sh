#!/bin/sh

#mvn install:install-file -DgroupId=freestar-mobile -DartifactId=freestar-api -Dversion=1.0.0 -Dfile=/Users/bsorensen/master_project/workspace/freestar-android-sdk/freestarSDK/build/outputs/aar/freestarSDK-release.aar -Dpackaging=aar -DgeneratePom=true -DlocalRepositoryPath=.  -DcreateChecksum=true
mvn install:install-file -DgroupId=freestar-mobile -DartifactId=freestar-api -Dversion=1.0.0 -Dfile=/Users/bsorensen/master_project/workspace/freestar-android-sdk/freestarSDK/build/outputs/aar/freestarSDK-release.aar -Dpackaging=aar -DpomFile=freestar_pom.xml -DlocalRepositoryPath=.  -DcreateChecksum=true

