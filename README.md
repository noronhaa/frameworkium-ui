# frameworkium-ui

### Frameworkium 3

This is one of the Frameworkium 3 libraries. Frameworkium 3 is a new release of [frameworkium-core](https://github.com/Frameworkium/frameworkium-core) aka Frameworkium 2.
 In Frameworkium 3 we have split the project up into their own logical modules that can be used independently of the other modules. 
 
 Original Framewokium 2 docs [here]()
 
 Frameworkium 3 libraries:
 1. [frameworkium-ui](https://github.com/Frameworkium/frameworkium-ui)
 2. [frameworkium-api](https://github.com/Frameworkium/frameworkium-api)
 3. [frameworkium-reporting](https://github.com/Frameworkium/frameworkium-reporting)
 4. [frameworkium-jira](https://github.com/Frameworkium/frameworkium-jira)  
 
 Example Projects implementing Frameworkium 3:
 - [frameworkium-examples](https://github.com/Frameworkium/frameworkium-examples/tree/frameworkium3)
 - [frameworkium-bdd](https://github.com/Frameworkium/frameworkium-bdd/tree/frameworkium3)

***

##Contents
[contributionstest1](#Contributions)
[contributionstest1](###Contributions)

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

## Selenium Grid
Providing you have already set up selenium grid you can run your tests on the grid with frameworkium-ui by specifying the gridURL parameter at runtime:

```bash
mvn clean verify -DgridURL=http://someurl:4444/wd/hub
```

Setting up a grid using the selenium standalone jar is easy enough, but managing larger grids,
and updating the dependencies along the way (e.g. DriverServer, jars, paths etc.) 
on each of your nodes can be a bit of a pain.

The [Selenium Grid Extras](https://github.com/groupon/Selenium-Grid-Extras) project aims to take the pain out of this.
You just run (or have a batch script set up to automatically run).

```bash
java -jar SeleniumGridExtras-1.7.1-SNAPSHOT-jar-with-dependencies.jar
```

and it handles the rest.
It can set up nodes, hubs, or combined hubs and nodes; records videos of the executions,
automatically installs and configures all the drivers you'll need (and auto-update them to the latest versions),
kill stale sessions and broken browsers, and even periodically reboot machines to keep your grid up with minimal maintenance.

Download the latest version from <https://github.com/groupon/Selenium-Grid-Extras/releases>.

## frameworkium-ui Structure
#### How does frameworkium-ui work?
frameworkium functionality comes from extending `BasePage` and `BaseTest`. 

##### BaseTest
This means when your test class is executed it will also run code from BaseTest. 
Code here is hooked onto TestNG for example we will have code run at different points of the 
TestNG test lifecycle which allows us to setup a driver `@BeforeMethod` and close the driver
`@AfterMethod` (a test is mapped as a method with TestNG)

##### BasePage
We have functionality here that can run each time a new page is loaded (ie you load a new Page Object class) which 
will can then wait for the visible elements on that page to be loaded and visible

##### Listeners
We also make use of 'listeners' which hook onto TestNG tests and can react accordingly, for example taking a screenshot 
if a test has failed.

##### Parameterization
Functionality where necessary has been parametrized to give the end user flexibility to choose how to use features.
To communicate back options, system properties are used which are injected at runtime. 

For example if you want to 
use chrome browser instead of firefox just parse `mvn clean test -Dbrowser=chrome` on the commandline when running tests. 

Want to run parallel execution
with 2 threads? just parse `mvn clean test -Dthreads=2` and frameworkium takes care of all the logic.

please refer to '[Command Line Options](https://frameworkium.github.io/#_pages/Command-Line-Options.md)' for a full list of options

## Command Line Options
Tests can be executed by running `mvn clean verify`.
This can be followed by any properties, in the form `-DpropertyName=value`,
you wish to specify.

**See also** the "Using Config yaml files..." entry for using config files
to provide a subset of these params instead

### General

Property | Description | Values | Default
-------- | ----------- | ------ | -------
`test`| The test class, or comma separated list of test classes, to run. Can include wildcards. | e.g. `MyTest*` | All tests
`threads`| The number of threads to use. | e.g. `3` | 1
`reuseBrowser` | Will re-use existing browsers rather than starting a new instance for each test. | `true` or `false` |  `false`
`groups`| The TestNG test groups which you wish to run. | e.g. `checkintest` | All groups
`build`| The build version or app version to log to Sauce Labs, BrowserStack, or Capture. | e.g. `build-1234` | none
`proxy` | Proxy server to be used from Selenium and REST API requests. | `system`, `autodetect`, `direct` or `http://{hostname}:{port}`, e.g. `http://10.3.2.22:80` | none
`maxRetryCount` | Additional attempts to retry a failed test. | e.g. `3` | 1

### Browser Properties/Capabilities

Property | Description | Values | Default
-------- | ----------- | ------ | -------
`browser`|  The browser on which you wish to run the tests. |`firefox`, `chrome`, `safari`, `ie`, `opera`, `phantomjs`,`legacyfirefox`,`electron`,`custom` | `firefox`
`maximise`| Maximise browser on opening (if possible). | `true` or `false` | `false`
`resolution`| Set browser dimensions to specific setting (if possible). | e.g. `1024x543` | none
`firefoxProfile`| Legacy. See `customBrowserImpl` below for preferred method. Which will also be changing soon, once upgrade to Selenium to 3.7.1 is complete. | e.g. `path/to/CustomFF.profile` | none
`chromeUserDataDir`| Legacy. See `customBrowserImpl` below for preferred method. | e.g. `path/to/chrome_user_data_dir` | none
`customBrowserImpl`| Used alongside the `-Dbrowser=custom` param. Allows users to specify classname of their own browser implementation, for example for specifying a custom set of `Capabilities`. | e.g. `ChromeIncognitoBrowserImpl` | none
`headless`| Allows users to run Chrome or Firefox in a headless environment | `true` or `false` | `false`

### Remote Grids. Devices and Platforms

No defaults.

Property | Description | Values
-------- | ----------- | ------
`gridURL`| The URL of your Selenium Grid hub. | e.g.`http://localhost:4444/wd/hub` - NB `/wd/hub` is required!
`browserStack`| Must be set to `true` if you wish to run on BrowserStack. | `true` or `false`
`sauce`| Must be set to true if you wish to run on Sauce Labs. | `true` or `false`
`browserVersion`| The browser version on which you wish to run the tests. Only used when running remotely e.g. on Selenium Grid, Sauce Labs or BrowserStack. | e.g. `8.0`
`platform`| The platform on which you wish to run the tests. Only used when running remotely. To be specified instead of 'os' when running with BrowserStack. | e.g. `windows`, `ios`, `android`, `OSX`
`platformVersion`| The platform version on which you wish to run the tests. Only used when running remotely. To be specified instead of 'os_version' when running with BrowserStack. | e.g. `5.0`
`device`| The device on which you wish to run remote tests. If not using SauceLabs or BrowserStack, can be specified with the `-Dbrowser=chrome` parameter to instigate a Chrome browser emulator of the specified device. |`iPhone`, `iPad`, `iPhone Retina 4-inch`, `Galaxy S4`, etc.
`applicationName`| Specify applicationName parameter for a grid run.| e.g. `windows7_32bits_firefox`
`videoCaptureUrl`| Enable video capture using Grid plugins. Usage of video capture is generic as possible. All grid plugins, such as Selenium Grid Extras, capture videos by the WebDriver session ID.| e.g. `http://localhost:3000/download_video/%s.mp4`
`appPath`| The path to the apk file to be used in Sauce Labs. It is also used as the `app` desired capability of the supported [WinAppDriver](https://github.com/Microsoft/WinAppDriver). | e.g. `/home/dev/android/build/newapp_1.2.5.apk` for SauceLabs or `Microsoft.WindowsAlarms_8wekyb3d8bbwe!App`, or `C:\Windows\System32\notepad.exe` for the WinAppDriver.

#### Remote Supported Devices/Platforms

- [BrowserStack](https://www.browserstack.com/list-of-browsers-and-platforms)
- [SauceLabs](https://saucelabs.com/platforms)

### Examples

Running tests using Firefox:

```bash
mvn clean verify
```

Running web tests using Chrome:

```bash
mvn clean verify -Dbrowser=chrome
```

Running web tests on Firefox 58.0.1 with Selenium Grid:

```bash
mvn clean verify -Dbrowser=firefox -DbrowserVersion=58.0.1 -DgridURL=http://localhost:4444/wd/hub
```

Running test methods which match the pattern`testM*`on Firefox with Selenium Grid and Capture:

```bash
mvn clean verify -Dtest=TestClass#testM* -Dbrowser=firefox -DgridURL=http://grid:4444/wd/hub -DsutName="My Project" -DsutVersion="0.0.1" -DcaptureURL=http://capture:5000
```

Running mobile web tests on Chrome, using their device emulation:

```bash
mvn clean verify -Dbrowser=chrome -Ddevice="Apple iPad 3 / 4"
```

Running mobile web tests on BrowserStack Win XP Firefox 33:

```bash
export BROWSER_STACK_USERNAME=<username>
export BROWSER_STACK_ACCESS_KEY=<access_key>
mvn clean verify -DbrowserStack=true -Dbrowser=firefox -DbrowserVersion=57.0 -Dplatform=Windows -DplatformVersion=XP
```

Running mobile web tests on Sauce Labs iOS 8.0 iPad Simulator:

```bash
export SAUCE_USERNAME=<username>
export SAUCE_ACCESS_KEY=<access_key>
mvn clean verify -Dsauce=true -Dplatform=ios -Dbrowser=safari -DplatformVersion=8.0 -Ddevice="iPad Simulator"
```

Running mobile web tests on BrowserStack iOS iPad Air (real device):

```bash
export BROWSER_STACK_USERNAME=<username>
export BROWSER_STACK_ACCESS_KEY=<access_key>
mvn clean verify -DbrowserStack=true -Dbrowser=ios -Ddevice="iPad Air"
```

NB - platform and platformVersion (os & os_version in BrowserStack config) are 
not required or supported when running on mobile devices

Running mobile app tests on Sauce Labs Android Emulator:

```bash
export SAUCE_USERNAME=<username>
export SAUCE_ACCESS_KEY=<access_key>
mvn clean verify -Dsauce=true -Dplatform=android -DappPath=<path_to_.apk>
```

Run regression tests (as marked in JIRA with the label REGRESSION) and log test 
results against the v1.1.2 version in JIRA:

```bash
mvn clean verify -Dbrowser=firefox -DjiraURL=http://jira:8080 -DjiraResultVersion=v1.1.2 -DjqlQuery="labels=REGRESSION"
```

For full lists of platforms/browsers supported see:
[BrowserStack](https://www.browserstack.com/list-of-browsers-and-platforms?product=automate) 
and [SauceLabs](https://saucelabs.com/platforms/) platform lists.

## Features
#### Page waits and @visible annotations
**Only use waits in your Page Object - _never_ in your tests**

Everytime you load a new page, frameworkium-ui will automatically wait for that page to load, we can make more reliable 
by using the `@visible` annotation above various page objects, when the new page load frameworkium-ui will wait
for the visibility of all elements that have been tagged `@visible` to be visible before continuing the test. This means
you should not need any explicit waits

##### Related annotations
- `@Visible` - covered above - make sure element is visible when page loads
- `@Invisible` - make sure element is **not** visible when page loads
- `@ForceVisible` - attempt to force visibility of a hidden element when a page loads so we can interact with it

##### Visibility of a list<Webelement> or a table?
We may want to check for visibility of a table/list, however, we don't want to spend time checking each element in the table is 
visible (could take a long time for large lists). For this we can parse a parameter to the annotation: 
`@Visible(checkAtMost=1)` which will check 1 element (or any amount chosen) in the list for visibility. Parameter works for all visibility annotations


##### Explicit waits
However - there are times when you'll need to wait explicitly. For example:

 - we want to click a button
 - the button is initially hidden; but made visible by linking the 'more' arrow

So we want to click the 'more' arrow, then wait for the button to be visible before clicking on it.
We use `wait.until` and `ExpectedConditions`, as in the following example:

```java
@Visible
@Name("More Arrow")
@FindBy(css = "div#more-arrow")
private WebElement moreArrow;

@Name("Initially hidden button")
@FindBy(css = "div#button")
private WebElement initiallyHiddenButton;

public Page clickInitiallyHiddenButton() {
  moreArrow.click();
  wait.until(ExpectedConditions.visibilityOf(initiallyHiddenButton));
  initiallyHiddenButton.click();
  return this;
}
```

`ExpectedConditions` has lots of methods to help your waits - e.g.
`elementToBeSelected()`, `titleContains()`, `textToBePresentInElement()`, `textToBePresentInElementValue()`, etc. - see [here](https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html) for the full list.

We have introduced our own extension to `ExpectedConditions` called `ExtraExpectedConditions`.

NB - frameworkium-ui has implicit waits due to our use of HtmlElements.
#### Logging
Each test run generates a log as defined in the log4j.xml

By default, all contents will push to `frameworkium.log`, in the `/logs/` folder.

If you want to write to this log (useful for debugging),
you should instantiate a new `Logger` in the class from which you're logging. E.g.:

```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SomeClass extends BasePageOrBaseTestMaybe {

    private Logger logger = LogManager.getLogger(SomeClass.class);
    
    public void someMethod() {
        logger.info("I'm logging an info message");
        logger.warn("I'm logging a warn message");
        logger.debug("I'm logging a debug message");
        logger.error("I'm logging an error message");
    }
}
```

#### Custom Browser Implementations

Sometimes, one needs to open browsers and devices with certain capabilities and flags set.
This is achieved in the WebDriver protocol by `DesiredCapabilties`, 
and frameworkium provides a simple mechanism to set these, 
whilst continuing to handle the eventual browser instantiation behind the scenes.

The solution is to create a new Java class extending `AbstractDriver`
somewhere in your test code, which sets up the DesiredCapabilities and WebDriver as you require them;
then reference this class (by name) using the `-DcustomBrowserImpl` parameter at runtime.

##### Running chrome in incognito mode example:

Example would be to create the class `ChromeIncognitoImpl.java` somewhere in your test code:

```java
public class ChromeIncognitoImpl extends AbstractDriver {

    @Override
    public DesiredCapabilities getDesiredCapabilities() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-incognito");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return capabilities;
    }

    @Override
    public WebDriver getWebDriver(DesiredCapabilities capabilities) {
        return new ChromeDriver(capabilities);
    }
}
```

Then run your tests with:

`mvn clean verify -DcustomBrowserImpl=ChromeIncognitoImpl`

Whenever `-DcustomBrowserImpl` is provided, the browser parameter defaults to `custom`.

See [the default driver implementations][driver-impl] used in frameworkium-ui.
If you create a better default implementation (or just something cool) please submit a pull request.

[driver-impl]: https://github.com/Frameworkium/frameworkium-ui/tree/master/src/main/java/com/frameworkium/ui/driver/drivers
####1 Using Config file instead of CLI parameters
Rather than providing all of the details in the command line,
you can instead create config files (in your `resources` folder) to store common configurations.
An example would be:

`/resources/config/FirefoxGrid.yaml`:

```yaml
browser: Firefox
firefoxProfile:
browserVersion:
platform:
platformVersion:
device:
captureURL:
gridURL: http://localhost:4444/wd/hub
build:
appPath:
sauce:
browserStack:
jiraURL:
spiraURL:
resultVersion:
zapiCycleRegEx:
jqlQuery:
sutName:
sutVersion:
jiraResultFieldName:
jiraResultTransition:
jiraUsername:
jiraPassword:
maximise:
resolution:
proxy:
maxRetryCount: 2
```

and you'd then use this config file by running:

```bash
mvn clean verify -Dconfig=FirefoxGrid.yaml
```

#### Automatically Downloading WebDrivers
Since: 2.4.4

frameworkium-ui supports the driver-binary-downloader Maven plugin, which allows the automatic download of webdriver binaries from the Internet i.e.:

mvn driver-binary-downloader:selenium
The default parameters store the latest 64-bit drivers for the current OS under the drivers/bin/ directory. The configuration options can be parameterised to select specific versions, different operating systems etc. If all requested drivers are already present under the designated directory, then no further actions take place at the back of this goal.

For further information, visit the plugin's GitHub page.

### Contributions
To Contribute:
- Implement the feature on a branch
- When all tests are passing, submit a pull request
- Coding standards, based on the [Google Style Guide](https://github.com/google/styleguide) are enforced using Checkstyle.
 Formatters for Eclipse and IntelliJ are provided in the doc/style folder. Please refer to guides on how to import these in [Eclipse](https://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Freference%2Fpreferences%2Fjava%2Fcodestyle%2Fref-preferences-formatter.htm) and [Intellij](https://blog.jetbrains.com/idea/2014/01/intellij-idea-13-importing-code-formatter-settings-from-eclipse/). CheckStyle plugins also exist for both IDEs.


## todo
- supported browsers / browserstack / appium?
