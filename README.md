# GreenCityTests
Automation tests for GreenCity project


### Build
Run `mvn test-compile` to download dependencies and build the project.


### Running tests
Run `mvn clean test -Dsurefire.suiteXmlFiles=*.xml` to execute the automation tests via [maven](https://maven.apache.org/).

For example

* `mvn clean test -Dsurefire.suiteXmlFiles=UI_Tests.xml` to running the UI tests of Eco news page


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

temporaryEmail = temporaryEmailValue
temporaryPass = temporaryPassValue

validUnregisterEmail = validUnregisterEmailValue
validUnregisterPassword = validUnregisterPasswordValue

invalidName = invalidNameValue
invalidEmail = invalidEmailValue
invalidPass = invalidPassValue

gMailAddressSignIn = gMailAddressSignInValue
gMailPassSignIn = gMailPassSignInValue

gMailAddressSignUP = gMailAddressSignUPValue

Password below is not used in the tests themselves, but might be needed to manage the account,
used in Gmail API helper
gMailPassSignUP = gMailPassSignUPValue

DBProdURL = DBProdURLValue
DBProdUserName = DBProdUserNameValue
DBProdUserPassword = DBProdUserPasswordValue

invalidPassUppercase = invalidPassUppercaseValue
invalidPassDigit = invalidPassDigitValue
invalidPassLowercase = invalidPassLowercaseValue
invalidPassSpecChar = invalidPassSpecCharValue
invalidPassLength = invalidPassLengthValue
invalidPassSpace = invalidPassSpaceValue

passwordForRegistration = passwordForRegistrationValue
```


### Where to find back/front-end part of the project

Here is the back-end part of our project: https://github.com/ita-social-projects/GreenCity

Here is the front-end part of our project: https://github.com/ita-social-projects/GreenCityClient
