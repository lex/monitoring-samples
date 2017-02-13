# iOS Simulator Safari sample

## Getting started

Install Appium (https://github.com/appium/appium)

```
$ npm install -g appium
```

Check the installed simulators in XCode -> Window -> Devices. Modify SafariSimulatorTest iPhone name and platform version to match.

Make sure you have run `authorize-ios` to allow Appium access to simulator:

```
$ sudo authorize_ios
Enabling DevToolsSecurity
Updating security db for developer access
Granting access to built-in simulator apps
Authorization successful
```


Start Appium

```
$ appium
```

To start test, execute:

```
$ mvn test
```
