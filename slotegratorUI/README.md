# Slotegrator UI test

Developed in Java with Selenium WebDriver, Cucumber 

### Execution
`mvn clean test` - to run from command line. Also you may run them inside the IDE.

##### src/test folder structure:

 `/src/test/java/com/slotegrator/ui/features` - feature files
 
 `/src/test/java/com/slotegrator/ui/steps` - steps implementation files
 
 `/src/test/java/com/slotegrator/ui/page_objects` - Page Object Model, with implementation functions of steps
 
 `/src/test/java/com/slotegrator/ui/RunTests.java` - test runner 
 
### Generated reports:
* `/target/cucumber/cucumber-html-reports`-  [cucumber-reporting-plugin](https://gitlab.com/monochromata-de/cucumber-reporting-plugin) report
* `/target/cucumber-html-report` - Default Cucumber report 
* `/target/screenshots` -  In case of failed tests, screenshots will be there

![](./img/report1.png))

![](./img/report2.png)

### Configuration
The execution can be configured with system properties 
```
mvn test -Dbrowser=firefox -Dheadless=true
```

Or with a property file `.properties` located in the current context directory - for maven test execution the directory is `/slotegratorUI/target/test-classes`. 
 

When property value is not provided the default value is used:
```
browser=chrome
headless=true
```

The configuration can change
* `browser` type (`chrome|firefox|edge|ie|safari|opera`) 
* `headless` mode (`true|false`)

Or create run configuration for JUnit and set the "VM options", for example 
``` -ea -Dbrowser=firefox -Dheadless=true```
 
