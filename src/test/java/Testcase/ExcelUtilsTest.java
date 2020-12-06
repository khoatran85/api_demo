package Testcase;

import utils.ExcelUtils;

public class ExcelUtilsTest {
    public static void main(String[] args) {
        String excelPath = "./data/TestData.xlsx";
        String sheetName = "Sheet1";
        ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

        excel.getRowCount();
        excel.getCellData(2, 0);
        excel.getCellData(2, 1);
        excel.getCellData(2, 2);
    }
}
