package driver;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;
public class BaseTest {

    protected static AppiumDriver<MobileElement> appiumDriver;
    protected boolean localAndroid=true;
    public Logger logger = LogManager.getLogger();

    @BeforeScenario
    public void beforeScenario() throws MalformedURLException{

        System.out.println("Local Test Starting");
        if (localAndroid){
            System.out.println("Local Test Starting on Android");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.m.qr");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,"com.m.qr.home.onboarding.ui.OnBoardingActivity");
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new AndroidDriver(url,desiredCapabilities);
            appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }else {
            System.out.println("Local Test Starting on IOS");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities
                    .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            desiredCapabilities
                    .setCapability(MobileCapabilityType.UDID, "");
            desiredCapabilities
                    .setCapability(IOSMobileCapabilityType.BUNDLE_ID, "");
            desiredCapabilities
                    .setCapability(MobileCapabilityType.DEVICE_NAME, "");

            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "");

            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            appiumDriver = new IOSDriver(url, desiredCapabilities);
        }




    }

    @AfterScenario
    public void afterScenario(){
        appiumDriver.quit();
    }

}
