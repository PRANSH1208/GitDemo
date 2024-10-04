package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReportsNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	
	ExtentReports extent =	ExtentReportsNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread Safe
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // assign unique thread id
	}
	
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	} 
	
	
	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL, "Test FAILED");
		extentTest.get().fail(result.getThrowable());
		
		
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// take a screenshot and attach it to the report
		
	String filePath = null;
	try {
		filePath = getScreenshot(result.getMethod().getMethodName(), driver);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName() );
		
	}
	
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}


}
