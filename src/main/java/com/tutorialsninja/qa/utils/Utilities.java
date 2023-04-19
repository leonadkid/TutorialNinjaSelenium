package com.tutorialsninja.qa.utils;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

public class Utilities
{
    public static final int WAIT_TIME = 10;
    public static final int PAGE_WAIT_TIME = 5;

    public static String generateEmailTimeStamp()
    {
        String time = java.time.LocalTime.now().toString().replace(".", "_").replace(":", "_");
        return "leonadkid" + time + "@gmail.com";
    }

    public static Object[][] getTestDataFromExcel(String sheetName) throws InvalidFormatException, IOException
    {
        File fileExcel = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/testdata/TutorialNinjaTestData.xlsx");
        FileInputStream fisExcel = new FileInputStream(fileExcel);
        XSSFWorkbook workbook = new XSSFWorkbook(fisExcel);
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int lastRow = sheet.getLastRowNum();
        int lastCol = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[lastRow][lastCol];

        for (int i = 0; i < lastRow; i++) {
            XSSFRow row = sheet.getRow(i + 1);
            for (int j = 0; j < lastCol; j++) {
                XSSFCell cell = row.getCell(j);
                CellType cellType = cell.getCellType();

                switch (cellType) {
                    case STRING:
                        data[i][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i][j] = Integer.toString((int) cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        data[i][j] = cell.getBooleanCellValue();
                }
            }
        }
        return data;
    }

    public static String captureScreenshot(WebDriver driver, String testname)
    {
        File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

//        String destinationsPath = System.getProperty("user.dir") + "/screenshots/" + testname + ".png";
        String destinationsPath = System.getProperty("user.dir") + "/screenshots/" + testname + ".png";
        try {
            FileHandler.copy(srcScreenshot, new File(destinationsPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/screenshots/" + testname + ".png";
    }

    public static String captureScreenshot2(WebDriver driver, String testname)
    {
        String result = null;
        File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try (InputStream is = new FileInputStream(srcScreenshot)) {
            byte[] bytes = IOUtils.toByteArray(is);
            result = Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
        }

        return "data:image/png;base64," + result;
    }
}