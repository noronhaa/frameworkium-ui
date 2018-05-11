# frameworkium-ui

## Summary

frameworkium-ui is a library that provides your front end testing framework coding guideline by using the 
PageFactory pattern which is an extension to the Page Object design pattern. It also contains libraries that 
that helps take care of common functionality that needs to be implemented in every new framework. By implementing 
these features in frameworkium it means you don't have to in your framework allowing you to get an automation project 
up and running faster, more reliably and being able to start writing tests instantly. Some features are:
- Setting up and instantiating drivers for any browser 
- Automatically waiting for pages to load in tests
- Automatically taking screenshots when a test fails
- Parallel Execution of tests (with or without selenium grid)

### Technologies used / supported
frameworkium-ui is build with and uses the following technologies
- Java
- TestNG
- Maven

frameworkium-ui can also be used with **Cucumber-JVM**, for more details head to the [frameworkium-bdd](https://github.com/Frameworkium/frameworkium-bdd/tree/frameworkium3)
project

Note: **JUnit** support coming soon

## Getting Started

**Note:** we have setup a full example project of how to set up a project using frameworkium libraries including a
fully implemented test suite. visit [frameworkium-examples](https://github.com/Frameworkium/frameworkium-examples/tree/frameworkium3). 
you can even clone this project and add packages for your own project to this to get started or follow the steps below

 1. Start a new Java Maven project (this can be done thought an IDE such as [IntelliJ](https://www.jetbrains.com/idea/))
 2. Add frameworkium-ui dependency to your pom
```xml 
 <dependencies>
     <dependency>
       <groupId>com.github.frameworkium</groupId>
       <artifactId>frameworkium-ui</artifactId>
       <version>3.0-BETA1</version>
     </dependency>
 </dependencies>
 ```
 
 3. Start laying out your project in accordance to the Page Object model (See [Layers](https://frameworkium.github.io/#_pages/Layers.md) section for more info) 
 4. Have each **Page Object Class** extend frameworkium-ui's `com.frameworkium.ui.pages.BasePage<T>`
```java
import com.frameworkium.ui.pages.BasePage;
 
public class HomePage extends BasePage<HomePage> {
 
 }
```
5. Have each **Test Class**  extend extend frameworkium-ui's `com.frameworkium.ui.tests.BaseTest`

 ```java
import com.frameworkium.ui.tests.BaseTest;

public class HomePageTests extends BaseTest{
    
}
```
6. Create a Profile in your `pom.xml` to run your tests containing a compiler and surefire plugin like so

```xml
<profiles>
    <profile>
      <id>tests</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <build>
        <plugins>

          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.6.0</version>
            <configuration>
              <source>1.8</source>
              <target>1.8</target>
            </configuration>
          </plugin>

          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.21.0</version>
            <configuration>
              <parallel>methods</parallel>
              <threadCount>${threads}</threadCount>
              <includes>
                <include>**/*Test.java</include>
                <include>**/*Tests.java</include>
              </includes>
              <groups>${groups}</groups>
              <testFailureIgnore>false</testFailureIgnore>
            </configuration>
          </plugin>

        </plugins>
      </build>
    </profile>
  </profiles>
```

7. You are now set up to run tests, once you have have written your first test run `mvn clean test` from the command line



## frameworkium-ui Structure
####How does frameworkium-ui work?
frameworkium functionality comes from extending `BasePage` and `BaseTest`. 

#####BaseTest
This means when your test class is executed it will also run code from BaseTest. 
Code here is hooked onto TestNG for example we will have code run at different points of the 
TestNG test lifecycle which allows us to setup a driver `@BeforeMethod` and close the driver
`@BAfterMethod` (a test is mapped as a method with TestNG)

#####BasePage
We have functionality here that can run each time a new page is loaded (ie you load a new Page Object class) which 
will can then wait for the visible elements on that page to be loaded and visible

##### Listeners
We also make use of 'listeners' which hook onto TestNG tests and can react achordingly, for example taking a screenshot 
if a test has failed.

##### Parameterization
Functionality where necessary has been parametrized to give the end user flexibility to choose how to use features.
To communicate back options, system properties are used which are injected at runtime. 

For example if you want to 
use chrome browser instead of firefox just parse `mvn clean test -Dbrowser=chrome` on the commandline when running tests. 

Want to run parallel execution
with 2 threads? just parse `mvn clean test -Dthreads=2` and frameworkium takes care of all the logic.

please refer to '[Command Line Options](https://frameworkium.github.io/#_pages/Command-Line-Options.md)' for a full list of options







## todo
- visible
- supported browsers / browserstack / appium?
-logging
- selenium grid
-custom browser impl
- Automatically Downloading WebDrivers
- contributions

## 
