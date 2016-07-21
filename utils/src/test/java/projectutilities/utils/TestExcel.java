package projectutilities.utils;

import org.testng.annotations.Test;

public class TestExcel {
	@Test
	public void testExcel(){
		ExcelPOI.setExcelConnection("data.xlsx", "test data");
		int noc =ExcelPOI.getColumns();
		System.out.println("no of rows are: "+ExcelPOI.getRows());
		System.out.println("no of columns are: "+noc);
		for(int i=1; i<ExcelPOI.getRows(); i++){
			for(int j=0; j<ExcelPOI.getColumns();j++){
				System.out.print(ExcelPOI.readData(i, j)+"\t");
			}
			System.out.print("\n");
			ExcelPOI.writeData(i, noc, "successfully read");
		}
		ExcelPOI.saveWorkBook();
	}

}
