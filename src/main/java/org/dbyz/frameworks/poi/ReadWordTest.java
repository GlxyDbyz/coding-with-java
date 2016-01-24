package org.dbyz.frameworks.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;

/**
 * 读取word文档简单例子
 *
 * @ClassName: ReadWord
 * @author: 作者 E-mail <a href="mailto:glxydbyz@gmail.com">Dbyz</a>
 * @version: V1.0
 */
public class ReadWordTest {
	private static String DOC_PATH = "E://github//Features//src//main//java//org//dbyz//frameworks//poi//test.doc";
	private static String DOCX_PATH = "E://github//Features//src//main//java//org//dbyz//frameworks//poi//test.docx";

	@SuppressWarnings("deprecation")
	@Test
	public void readDoc1() throws IOException {
		// doc 通用对象
		WordExtractor doc = new WordExtractor(new FileInputStream(DOC_PATH));
		System.out.println("**************************");
		// 获取全部文本数据
		System.out.println(doc.getText()); // 可见文本
		System.out.println(doc.getTextFromPieces()); // 不可见文本一起获取
		System.out.println("**************************");

		// 页眉页脚
		System.out.println(doc.getHeaderText());
		System.out.println(doc.getFooterText());
		System.out.println("**************************");

		// 遍历获取段落
		for (String paragraph : doc.getParagraphText()) {
			System.out.println(paragraph);
		}
		System.out.println("**************************");

		doc.close();
	}

	@Test
	public void readDoc2() throws IOException {
		// doc 对象
		HWPFDocument doc = new HWPFDocument(new FileInputStream(DOC_PATH));
		// 获取文本
		System.out.println(doc.getText());
		System.out.println(doc.getDocumentText());

		System.out.println("******************************");
		// 遍历段落
		for (int i = 0; i < doc.getRange().numParagraphs(); i++) {
			System.out.println(i + " " + doc.getRange().getParagraph(i).text());
		}

	}

	@Test
	public void readDocx1() throws IOException {
		XWPFDocument document = new XWPFDocument(new FileInputStream(DOCX_PATH));
		// docx 通用对象
		XWPFWordExtractor docx = new XWPFWordExtractor(document);
		// 获取全部文本数据
		System.out.println(docx.getText()); // 可见文本
		docx.close();
	}

	@Test
	public void readDocx2() throws IOException {
		// doc 对象
		XWPFDocument docx = new XWPFDocument(new FileInputStream(DOCX_PATH));
		// 遍历段落
		for (XWPFParagraph paragraph : docx.getParagraphs()) {
			System.out.println(paragraph.getText());
		}

		// 遍历表格
		for (XWPFTable table : docx.getTables()) {
			// 获取表格对应的行
			List<XWPFTableRow> rows = table.getRows();
			for (XWPFTableRow row : rows) {
				// 获取行对应的单元格
				List<XWPFTableCell> cells = row.getTableCells();
				for (XWPFTableCell cell : cells) {
					System.out.println(cell.getText());
				}
			}
		}
		docx.close();
	}

}
