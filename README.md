# GreenCityTests
Automation tests for GreenCity project

### environment
Local
- download  ([GridEzzyRun](https://github.com/ita-social-projects/GreenCityTests/blob/fixExistingTests/src/test/resources/GridEzzyRun.rar).
- unzip it
- run downloadChrome.bat to download Chrome driver
- run downloadChrome.bat to download Firefox driver
- run hub.bat
- run node1.bat
- run node1.bat etc so many as you need
open http://localhost:4444/grid/console you should see your nodes.
if you need more than 4, just copy one of them and using note pad change parameter port for next one.
for example port -port 5555 to -port 5556

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
- 
### Build
Run `mvn test-compile` to download dependencies and build the project.


### Running UI tests
Run `mvn test -Dtestng.xml=*.xml` to execute the automation UI tests via [maven](https://maven.apache.org/).

For example

* `mvn test -Dtestng.xml=testng.xml` to running the UI tests of Eco news page


### Required to install

* Java 8
* Google [Chrome](https://www.google.com/chrome/) Browser
* Mozilla [Firefox](https://www.mozilla.org/en-US/firefox/new/) Browser
* [maven](https://maven.apache.org/)
* [Selenium](https://www.selenium.dev/) ver. 3.141.59

### Credentials for login
All user credentials storing at property file `credentials.properties`.

You need create `credentials.properties` file at `src/test/resources/` and set here your credentials for login and else methods.

For exemple:

```
defaultName = defaultNameValue
defaultEmail = defaultEmailValue
defaultPass = defaultPassValue
defaultConfirmPass = defaultConfirmPassValue

temporaryLoginName = temporaryLoginNameValue
temporaryPass = temporaryPassValue

invalidName = invalidNameValue
invalidEmail = invalidEmailValue
invalidPass = invalidPassValue
invalidConfirmPass = invalidConfirmPassValue

validUnregisterEmail = validUnregisterEmailValue
validIncorrectPassword = validIncorrectPasswordValue

googleEmail = googleEmailValue
googlePass = googlePassValue

DBProdURL = DBProdURL
DBProdUserName = DBProdUserName
DBProdUserPassword = DBProdUserPassword

emailForRegistration = emailForRegistration
passwordForRegistration = passwordForRegistration
passwordToGmailBox = passwordToGmailBox

invalidPassUppercase = invalidPassUppercase
invalidPassDigit = invalidPassDigit
invalidPassLowercase = invalidPassLowercase
invalidPassSpecChar = invalidPassSpecChar
invalidPassLength = invalidPassLength
invalidPassSpace = invalidPassSpace
```


### Where to find back/front-end part of the project

Here is the back-end part of our project: https://github.com/ita-social-projects/GreenCity

Here is the front-end part of our project: https://github.com/ita-social-projects/GreenCityClient
