import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

public class NetworkCapture {

	String fileName = "./lib//datalicious.json";
	
	
	public BrowserMobProxy proxy;
	
	
	public void setUp(WebDriver driver,String url) throws IOException {
		
	    proxy = new BrowserMobProxyServer();
	    proxy.start(0);

	    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
		
		driver = new FirefoxDriver(capabilities);
		
	    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

	    proxy.newHar("www.datalicious.com");

	    driver.get(url);
	    driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

		Har har = proxy.getHar();
		File harFile = new File(fileName);
		try {
			har.writeTo(harFile);
		} catch (IOException ex) {
			 System.out.println (ex.toString());
		     System.out.println("Could not find file " + fileName);
		}
		if (driver != null) {
			proxy.stop();
			driver.quit();
		}
		ReadingFile.writingToCsv(fileName);
		
		
	}
	
	
}
	
	

