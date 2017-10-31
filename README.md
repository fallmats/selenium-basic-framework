# selenium-basic-framework

# Getting started
**Common prereqs: JDK8u101+, Maven 3.0.5+**

# Run tests with maven
Run All tests on local machine
```
mvn test

mvn -Dlocal_run=true test
```

# Adding a test suite (module)

Create a new java class in package **.tests** the class should extend BaseTest.

Example for a new test suite
```java
package tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import static org.junit.Assert.*;

public class NewSuiteTest extends BaseTest {

    @Rule
    public TestRule watcher = new TestWatcher() {
        protected void starting(Description description) { System.out.println(description.getMethodName()); }

        protected void failed (Throwable e, Description description)  {
            e.printStackTrace();
            try {
                screenShot.takeScreenShot(description.getMethodName());
            } catch (Exception exception) { exception.printStackTrace(); }
            driver.quit();
        }

        protected void succeeded(Description description) { driver.quit(); }

    };
    ...
    @Test
    public void newTest() {
    ...
```

# Create a new test Module 

Create a new java class in relevant package:
  - **.modules** 
  
```java
package .modules.newPackage;
//imports
public class NewModule extends BasePage {
   ...
   //Locators used in the module
   ...
   public NewModule(WebDriver d) { super(d); }
   ...
```

Now add the NewModule to BaseTest in package **tests**
```java
class BaseTest {

    // MODULES

    protected Login login;
    protected NewModule newModule;
    ...
    @Before
    public void startup() throws  Exception{
        ...
        // Module setup
        newModule =  new NewModule(driver);

```

Using the new module in a test
```java
public class NewTestSuite extends BaseTest {
...
    @Test
    public void NewTest() throws Exception {
        ...
        newModule.doSomeFunction();
        assertTrue("Field is not visible", newModule.isFieldVisible());
        ...
    }
```
