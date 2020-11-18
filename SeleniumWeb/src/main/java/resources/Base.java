package resources;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Base {

	public static WebDriver driver;
	
	
	//Variable related to ExtentReports
	public static ExtentReports extent;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	ExtentSparkReporter sparkReporter;

	@BeforeTest
	public void generateReport() {
		sparkReporter =new ExtentSparkReporter("Reports/AutomationReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}

	@BeforeMethod
	public void methodName(Method method) {
		parentTest=extent.createTest(method.getName());
	}

	
	@BeforeClass
	public static void setup() {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.automationtestinginsider.com/2019/12/bootstrap-dropdown-example_12.html");
		
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
		extent.flush();
	}
	
	
	
	

	
	
	
	
}
