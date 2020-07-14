# GreenCityTests
Automation tests for GreenCity project


### Build
Run `mvn test-compile` to download dependencies and build the project.


### Running UI tests
Run `mvn test -Dtestng.xml=*.xml` to execute the automation UI tests via [maven](https://maven.apache.org/).

For example

* `mvn test -Dtestng.xml=econews.xml` to running the UI tests of Eco news page


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
