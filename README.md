# GreenCityTests
Automation tests for GreenCity project

### environment
com.softserve.edu.greencity.ui.tests.runner.GreenCityTestRunner.setUpBeforeClass
ChromeOptions options = new ChromeOptions();
* options.addArguments("--disable-gpu");
* options.addArguments("--disable-popup-blocking");
* options.addArguments("--allow-failed-policy-fetch-for-test");
* options.addArguments("--disable-browser-side-navigation");
* options.addArguments("--incognito");
* options.addArguments("--disable-notifications");
* options.addArguments("--window-size=1920,1080", "--no-sandbox", "'--disable-dev-shm-usage");
            
com.softserve.edu.greencity.ui.tests.runner.DriverSetup.optionsArguments
DesiredCapabilities capabilities = DesiredCapabilities.chrome();
* capabilities.setBrowserName("chrome");
* capabilities.setVersion("84.0");
* capabilities.setCapability("enableVNC", true);
* capabilities.setCapability("enableVideo", false);
* capabilities.setCapability(ChromeOptions.CAPABILITY, options);

Local:
* GridHub.startLocally(4444);
* RegisterChrome.startNode(5551);
* RegisterChrome.startNode(5552);
* RegisterChrome.startNode(5553);
* RegisterChrome.startNode(5554);
* driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),options);

Remote:
* driver = new RemoteWebDriver(
                    URI.create("http://35.198.124.146:4444/wd/hub").toURL(),
                    capabilities);
                    
Jenkins primitive usage:
 Manage Jenkins -> plugin manager -> available -> download Selenium plugin
 - Download the [latest stable Jenkins WAR file](http://mirrors.jenkins.io/war-stable/latest/jenkins.war) to an appropriate directory on your machine.
 - run java -jar jenkins.war go to http://localhost:8080 and follow advised by Jenkins steps
 - [Create job](https://dl.dropboxusercontent.com/s/y06thpciqfxtvk0/shot_200821_200455.png)
 - [Add description](https://dl.dropboxusercontent.com/s/8xbw5l23e7gpsz6/shot_200821_200719.png)
 - [Setup environment](https://dl.dropboxusercontent.com/s/ft619bnskcm7zqa/shot_200821_200850.png)
 - [Build run](https://dl.dropboxusercontent.com/s/wzbffbvb47837ga/shot_200821_200951.png)
 - [Save](https://dl.dropboxusercontent.com/s/dc3v0gpvkm84yc3/shot_200821_201047.png)
 - Now you should see your [job](http://localhost:8080/)
 - [Run](https://dl.dropboxusercontent.com/s/32byliz8hoytbkb/shot_200821_201323.png)
 
### Build
* First run, mvn install -Dmaven.test.skip=true
* Run `mvn test-compile` to download dependencies and build the project.

### Running UI tests
Run `mvn test -Dtestng.xml=*.xml` to execute the automation UI tests via [maven](https://maven.apache.org/).

* Run localy: set in google sheet remote false
* Run remotely: set in google sheet remote true
* selenid UI http://35.198.124.146:8080/#/
* selenid connect http://35.198.124.146:4444

For example
* `mvn test -Dtestng.xml=testng.xml` to running the UI tests of Eco news page


### Required to install

* Java 8
* Google [Chrome](https://www.google.com/chrome/) Browser
* [maven](https://maven.apache.org/)

### Credentials 
All user credentials storing at google sheets.
You should :
* Create  google sheet, add Share with people and groups.
* Allow API https://developers.google.com/sheets/api/quickstart/java
* Add key to com.softserve.edu.greencity.ui.tools.api.google.sheets.GoogleSheet
* SPREADSHEET_ID = THIS_IS_KEY_THAT_YOU_NEED
* docs.google.com/spreadsheets/d/ THIS_IS_KEY_THAT_YOU_NEED  /edit...

  
set variables, for example:

```
Name of value	value
invalidPassDigit	12345678-
defaultName	Pavel
emailForRegistration	greencitypavel@gmail.com
defaultPass	1234qwerTY-
temporaryPass	Temp#001
invalidName	21CharString21CharSt
invalidPassLowercase	qwertyasdfg-
nameForRegistration	greencitypavel
invalidPass	as2f
invalidPassSpecChar	bRDYBhAs3 z48Y5H-
invalidPassSpace	                                         
invalidPassLength	aA-
comfTemporaryPass	1234qwerTY-
googleEmail	greencitypavel@gmail.com
googlePass	1234qwerTY-
invalidPassUppercase	QWERTYASDFG-
validIncorrectPassword	As3z48Y5H-bRDYBh
temporaryLoginName	xdknxusqvjeovowpfk@awdrt.com
defaultEmail	greencitypavel@gmail.com
invalidEmail	\ asd 
passwordForRegistration	1234qwerTY-
validUnregisterEmail	greencitypavel@gmail.com
remote	TRUE
```


### Where to find back/front-end part of the project

Here is the back-end part of our project: https://github.com/ita-social-projects/GreenCity

Here is the front-end part of our project: https://github.com/ita-social-projects/GreenCityClient
