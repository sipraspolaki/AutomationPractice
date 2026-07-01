package webui.loginPage;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.Test;

import com.example.automation.utils.commonUtils.DataProviderClass;
import com.example.automation.utils.commonUtils.ExcelUtils;

public class TestExcelUtils {
	
	@Test(priority=1,dataProvider="excelDataProvider",dataProviderClass= DataProviderClass.class)
	public void testReadExcelData(String repository, Double rating){
		System.out.println("The repository is "+repository);
		System.out.println("The rating is "+rating);
	}
	
	@Test(priority=2)
	public void writeExcelData() throws IOException {
		String filePath = System.getProperty("user.dir")+ File.separator+"test-data-files"+File.separator+"TestData.xlsx";
		String sheetName = "RepositoryNames";
		Object [][] array = {{"new_Repo",4}};
		ExcelUtils.writeExcel(filePath, sheetName, array);
	}

}
