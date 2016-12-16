# Android Java Appium Sample

This directory contains an Appium Java example that tests the [Wikipedia Android app](https://github.com/wikimedia/apps-android-wikipedia).

## App 

This directory contains the app used for monitoring, [Wikipedia app](wikipedia.apk).

## Test Package

To create the test package, run the following command.

    $ cd java && zip -r9 ./wikipedia-android-test.zip .

Then upload the resulting `wikipedia-android-test.zip` and the Wikipedia app as the application when creating new monitoring check.
