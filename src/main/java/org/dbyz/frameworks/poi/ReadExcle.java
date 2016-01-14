package org.dbyz.frameworks.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ReadExcle {
	/**
	 * 读取 excel 2003 文件
	 * 
	 * @Title: readXls
	 * @param
	 * @return: void
	 * @throws IOException
	 * @since V1.0
	 */
	@Test
	public void readXls() throws IOException {
		FileInputStream input = new FileInputStream(
				"E://github//Features//src//main//java//org//dbyz//frameworks//poi//test.xls");
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(input);

		// 1.读取excel含有多少个 sheet表
		int sheetNum = hssfWorkbook.getNumberOfSheets();

		// 2.遍历文件的每个sheet表
		sheetLoop: for (int sheetIndex = 0; sheetIndex < sheetNum; sheetIndex++) {
			HSSFSheet sheet = hssfWorkbook.getSheetAt(sheetIndex); // 获取一个sheet表
			if (sheet == null) {
				continue sheetLoop;
			}

			// 3.遍历单个sheet表的行
			int rowNum = sheet.getLastRowNum(); // 最大行号
			rowLoop: for (int rowIndex = 0; rowIndex < rowNum; rowIndex++) {
				HSSFRow row = sheet.getRow(rowIndex); // 获取一行
				if (row == null) {
					continue rowLoop;
				}

				// 4.遍历每行
				short cellNum = row.getLastCellNum(); // 最大单元格号
				cellLoop: for (int cellIndex = 0; cellIndex < cellNum; cellIndex++) {
					HSSFCell cell = row.getCell(cellIndex);
					if (cell == null) {
						continue cellLoop;
					}
					System.out.print(getCellValue(cell) + " ");
				}
				System.out.println();
			}
		}
		hssfWorkbook.close();
	}

	@Test
	public void readXlsx() throws IOException {
		FileInputStream input = new FileInputStream(
				"E://github//Features//src//main//java//org//dbyz//frameworks//poi//test.xlsx");

		XSSFWorkbook excel = new XSSFWorkbook(input);
		// 1.读取excel含有多少个 sheet表
		int sheetNum = excel.getNumberOfSheets();

		// 2.遍历文件的每个sheet表
		sheetLoop: for (int sheetIndex = 0; sheetIndex < sheetNum; sheetIndex++) {
			XSSFSheet sheet = excel.getSheetAt(sheetIndex); // 获取一个sheet表
			if (sheet == null) {
				continue sheetLoop;
			}

			// 3.遍历单个sheet表的行
			int rowNum = sheet.getLastRowNum(); // 最大行号
			rowLoop: for (int rowIndex = 0; rowIndex < rowNum; rowIndex++) {
				XSSFRow row = sheet.getRow(rowIndex); // 获取一行
				if (row == null) {
					continue rowLoop;
				}

				// 4.遍历每行
				short cellNum = row.getLastCellNum(); // 最大单元格号
				cellLoop: for (int cellIndex = 0; cellIndex < cellNum; cellIndex++) {
					XSSFCell cell = row.getCell(cellIndex);
					if (cell == null) {
						continue cellLoop;
					}
					System.out.print(getCellValue(cell) + " ");
				}
				System.out.println();
			}
		}
		excel.close();
	}

	@Test
	public void readXlsAndXlsx() throws IOException {
		String pathname = "E://github//Features//src//main//java//org//dbyz//frameworks//poi//test.xls";
		// String pathname = "E://github//Features//src//main//java//org//dbyz//frameworks//poi//test.xlsx";

		String suffix = pathname.substring(pathname.lastIndexOf("."),
				pathname.length());

		Workbook excel = null;

		if (".xls".equalsIgnoreCase(suffix)) {
			excel = new HSSFWorkbook(new FileInputStream(new File(pathname)));
		} else if (".xlsx".equalsIgnoreCase(suffix)) {
			excel = new XSSFWorkbook(new FileInputStream(new File(pathname)));
		} else {
			throw new RuntimeException("文件错误!");
		}

		// 1.读取excel含有多少个 sheet表
		int sheetNum = excel.getNumberOfSheets();

		// 2.遍历文件的每个sheet表
		sheetLoop: for (int sheetIndex = 0; sheetIndex < sheetNum; sheetIndex++) {
			Sheet sheet = excel.getSheetAt(sheetIndex); // 获取一个sheet表
			if (sheet == null) {
				continue sheetLoop;
			}

			// 3.遍历单个sheet表的行
			int rowNum = sheet.getLastRowNum(); // 最大行号
			rowLoop: for (int rowIndex = 0; rowIndex < rowNum; rowIndex++) {
				Row row = sheet.getRow(rowIndex); // 获取一行
				if (row == null) {
					continue rowLoop;
				}

				// 4.遍历每行
				short cellNum = row.getLastCellNum(); // 最大单元格号
				cellLoop: for (int cellIndex = 0; cellIndex < cellNum; cellIndex++) {
					Cell cell = row.getCell(cellIndex);
					if (cell == null) {
						continue cellLoop;
					}
					System.out.print(getCellValue(cell) + " ");
				}
				System.out.println();
			}
		}
		excel.close();
	}

	/**
	 * 获取cell里面的值
	 * 
	 * @Title: getCellValue
	 * @param @param cell
	 * @param @return
	 * @return: Object
	 * @since V1.0
	 */
	public Object getCellValue(Cell cell) {
		int type = cell.getCellType();
		Object result = null;
		switch (type) {
		case HSSFCell.CELL_TYPE_BOOLEAN:
			result = cell.getBooleanCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			result = cell.getNumericCellValue();
			break;
		default:
			result = cell.getStringCellValue();
			break;
		}
		return result;
	}
}
