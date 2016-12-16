This directory contains a modified version of the iOS Wikipedia app in a zip file.  The application has been
compiled and signed with debug options, and any certificate checks have been turned off.

In addition, there is an Appium test script.

To pack it all up, just run

    cd java && zip -r9 ~/wikipedia-ios-test.zip .

Then upload the resulting ~/wikipedia-ios-test.zip as an Appium test, and the wikipedia-ios.zip as the application.

