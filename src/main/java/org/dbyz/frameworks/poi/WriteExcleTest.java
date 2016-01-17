package org.dbyz.frameworks.poi;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * 写出excel文件简单例子
 *
 * @ClassName: WriteExcleTest
 * @author: 作者 E-mail <a href="mailto:845927437@qq.com">Dbyz</a>
 * @version: V1.0
 */
public class WriteExcleTest {

	/**
	 * 写出excel 2003文件
	 * 
	 * @Title: writeXls
	 * @param @throws IOException
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void writeXls() throws IOException {
		// 1.创建excel文件实例
		HSSFWorkbook excel = new HSSFWorkbook();
		// 2.创建sheet表实例
		HSSFSheet testSheet = excel.createSheet("testSheet");
		testSheet.setColumnWidth(0, 20000); // 设置第一列列宽
		// 3.创建一行
		HSSFRow row = testSheet.createRow(0);
		// 4.设置单元格格式
		HSSFCellStyle style = excel.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		style.setWrapText(true); // 设置自动换行
		// 5.设置字体
		HSSFFont font = excel.createFont();
		font.setFontName("楷体");// 字体
		font.setColor(HSSFFont.COLOR_RED); // 颜色
		font.setFontHeightInPoints((short) 25); // 字体大小
		style.setFont(font);
		// 4.创建单元格并赋值
		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("Hello,excel(你好,EXCEL)");
		// 5.创建文件
		FileOutputStream fout = new FileOutputStream("D:/test.xls");
		excel.write(fout);
		fout.close();
		excel.close();
	}

	/**
	 * 写出excel 2007+
	 * 
	 * @Title: writeXlsx
	 * @param @throws IOException
	 * @return: void
	 * @since V1.0
	 */
	@Test
	public void writeXlsx() throws IOException {
		// 1.创建excel文件实例
		XSSFWorkbook excel = new XSSFWorkbook();
		// 2.创建sheet表实例
		XSSFSheet testSheet = excel.createSheet("testSheet");
		testSheet.setColumnWidth(0, 20000); // 设置第一列列宽
		// 3.创建一行
		XSSFRow row = testSheet.createRow(0);
		// 4.设置单元格格式
		XSSFCellStyle style = excel.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		style.setWrapText(true); // 设置自动换行
		// 5.设置字体
		XSSFFont font = excel.createFont();
		font.setFontName("楷体");// 字体
		font.setColor(HSSFFont.COLOR_RED); // 颜色
		font.setFontHeightInPoints((short) 25); // 字体大小
		style.setFont(font);
		// 4.创建单元格并赋值
		XSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("Hello,excel(你好,EXCEL)");
		// 5.创建文件
		FileOutputStream fout = new FileOutputStream("D:/test.xlsx");
		excel.write(fout);
		fout.close();
		excel.close();
	}
}
