# iOS Java Appium Sample


## App

This directory contains a modified version of the iOS Wikipedia app in a zip file.  The application has been
compiled and signed with debug options, and any certificate checks have been turned off.


## Test Package

In addition an Appium test script is required. To create the test package, just run

    cd java && zip -r9 ./wikipedia-ios-test.zip .

Then upload the resulting `wikipedia-ios-test.zip` as an Appium test, and the `wikipedia-ios.zip` as the application when creating new monitoring check.


