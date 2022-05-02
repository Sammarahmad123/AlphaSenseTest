# AlphaSense Test

Appium Java Tests for Google App - Android 

## Dependencies

Following are the requirements to run the tests.

```bash
Java 
Appium
Maven 
Allure
```

## How to run

In resourses/config.properties file update the following as per the device preferences.
```
plateformVersion
deviceName
```
Run the below maven command to run the build process.

```
mvn clean test
``` 

After test completion, run the below command to launch the allure test report
```
allure serve
``` 

After the test execution, screenshot are visible in the report for only failed cases.

