package driverFcatory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
	
	String inputpath ="/Users/sencytharammel/Automation_Selenium/Hybrid_Framework/TestInput/HybridTest.xlsx";
	String outputpath="/Users/sencytharammel/Automation_Selenium/Hybrid_Framework/TestOutput/HybridTestResult.xlsx";
    String TCSheet ="Mastertestcase";
    String TSSheet ="TestSteps";
@Test
public void startTest() throws Throwable
{
	boolean res= false;
	String tcres="";
	ExcelFileUtil xl = new ExcelFileUtil(inputpath) ;
	// count no of rows in TCSheey and TSSheet
	int TCcount =xl.rowCount(TCSheet);
	int TScount =xl.rowCount(TSSheet);
	Reporter.log(TCcount+"--------"+TScount);
	for(int i=1;i<=TCcount;i++)
	{
		// read the execution status cell Nor Y
		String executionStatus=xl.readData(TCSheet, i, 2);
		if(executionStatus.equalsIgnoreCase("Y"))
		{
			//read tcid from cell
			String tcId = xl.readData(TCSheet, i, 0);
			//iterate i testdata for the same  tcid  ie steps of each tc
			for(int j =1;j<=TScount;j++)
			{
				// read tsid from cell
				String tsId = xl.readData(TSSheet, j, 0);
				// check both id is same to iteration
				if(tcId.equalsIgnoreCase(tsId))
				{
				 // read the keyword 
					String keyword = xl.readData(TSSheet, j, 4);
					if(keyword.equalsIgnoreCase("AdminLogin")) {
						String para1 = xl.readData(TSSheet, j, 5);
						String para2 = xl.readData(TSSheet, j, 6);
						res = FunctionLibrary.verifyLogin(para1, para2);
					}else if(keyword.equalsIgnoreCase("NewBranch")){
						String para1 = xl.readData(TSSheet, j, 5);
						String para2 = xl.readData(TSSheet, j, 6);
						String para3 = xl.readData(TSSheet, j, 7);
						String para4 = xl.readData(TSSheet, j, 8);
						String para5 = xl.readData(TSSheet, j, 9);
						String para6 = xl.readData(TSSheet, j, 10);
						String para7 = xl.readData(TSSheet, j, 11);
						String para8 = xl.readData(TSSheet, j, 12);
						String para9 = xl.readData(TSSheet, j, 13);
						FunctionLibrary.clickBranches();
						res = FunctionLibrary.createBranch(para1, para2, para3, para4, para5, para6, para7, para8, para9) ;
						
					}else if(keyword.equalsIgnoreCase("BranhUpdate"))
					{
						String para1 = xl.readData(TSSheet, j, 5);
						String para2 = xl.readData(TSSheet, j, 6);
						String para3 = xl.readData(TSSheet, j, 10);
						FunctionLibrary.clickBranches();
						res = FunctionLibrary.verifyBranchUpdate(para1, para2,para3);
					}else if (keyword.equalsIgnoreCase("Adminlogout"))
					{
						res= FunctionLibrary.verifyLogout();
					}
					String tsres="";
					if(res) {
						tsres="Pass";
						xl.writeData(TSSheet, j, 3, tsres, outputpath);
					}else
					{
						tsres="Fail";
						xl.writeData(TSSheet, j, 3, tsres, outputpath);
					}
					tcres=tsres;
					
				}	
	
			}
			// write the status of tsres in tcres mastersheet
			
			xl.writeData(TCSheet, i, 3, tcres, outputpath);
		}else
		{
			// write as blocked which tc is flag N (status coloum)
			xl.writeData(TCSheet, i, 3, "Blocked", outputpath);
		}
	}
	
}
}
