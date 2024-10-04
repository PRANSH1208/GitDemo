package rahulshettyacademy.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	
	
	public static ExtentReports getReportObject()
	{
		
		// 2classes - ExtentReports, ExtentSparkReporter
		String path = System.getProperty("user.dir")+"//reports//index.html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			reporter.config().setReportName("Web Automation results");
			reporter.config().setDocumentTitle("Test Results");
			
			
			ExtentReports extent = new ExtentReports();
			extent.attachReporter(reporter);
			extent.setSystemInfo("Tester", "Anshika Singh");
			return extent;
		
		
	}

}
