package se.prolore.datahelpers;

import java.io.FileInputStream;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class TestDataExcel {


    /**
     * Read Excel file row by row and return queried row.
     * File should be placed in resources folder to be found
     *
     * @param fileName String
     * @param sheetName String
     * @param rowName String
     *
     * @author mats
     */
    public String[] getExcelData(String fileName, String sheetName, String rowName) throws IOException, BiffException {

        String FilePath = TestDataExcel.class.getClassLoader().getResource(fileName).getPath();
        FileInputStream fs = new FileInputStream(FilePath);
        WorkbookSettings ws = new WorkbookSettings();
        // Choose encoding if needed (on windows use Cp1252)
        ws.setEncoding("UTF-8");
        Workbook wb = Workbook.getWorkbook(fs, ws);
        String cellContents;

        System.out.println(fileName);
        // TO get the access to the sheet
        Sheet sh = wb.getSheet(sheetName);

        // To get the number of rows present in sheet
        int totalNoOfRows = sh.getRows();

        // To get the number of columns present in sheet
        int totalNoOfCols = sh.getColumns();

        String[] arrayExcelData = new String[totalNoOfCols];

        boolean isFound = false;
        int row = 0;

        while(  !isFound && row < totalNoOfRows ) {
            cellContents = sh.getCell(0, row).getContents();

            // only loop columns if we are on the right row
            if (cellContents.equals(rowName)) {
                isFound = true;
                for (int col = 0; col < totalNoOfCols; col++) {
                    arrayExcelData[col] = sh.getCell(col, row).getContents();
                }
            }
            row++;
        }
        return arrayExcelData;
    }
}
