package com.chejet.cloud.excel.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import com.chejet.cloud.excel.vo.Excel;
import com.chejet.cloud.excel.vo.ExcelSheet;

public class ExcelUtils<T> {
	/**
	 * 生成样式
	 * 
	 * @param workbook
	 * @return
	 */
	public static Map<String, CellStyle> createStyles(Workbook workbook) {
		Map<String, CellStyle> styles = new HashMap();
		CellStyle style = workbook.createCellStyle();
		style.setAlignment((short) 2);
		style.setVerticalAlignment((short) 1);
		Font titleFont = workbook.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 16);
		titleFont.setBoldweight((short) 700);
		style.setFont(titleFont);
		styles.put("title", style);
		style = workbook.createCellStyle();
		style.setVerticalAlignment((short) 1);
		style.setBorderRight((short) 1);
		style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderLeft((short) 1);
		style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderTop((short) 1);
		style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setBorderBottom((short) 1);
		style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
		Font dataFont = workbook.createFont();
		dataFont.setFontName("Arial");
		dataFont.setFontHeightInPoints((short) 10);
		style.setFont(dataFont);
		styles.put("data", style);
		style = workbook.createCellStyle();
		style.cloneStyleFrom((CellStyle) styles.get("data"));
		style.setAlignment((short) 1);
		styles.put("data1", style);
		style = workbook.createCellStyle();
		style.cloneStyleFrom((CellStyle) styles.get("data"));
		style.setAlignment((short) 2);
		styles.put("data2", style);
		style = workbook.createCellStyle();
		style.cloneStyleFrom((CellStyle) styles.get("data"));
		style.setAlignment((short) 3);
		styles.put("data3", style);
		style = workbook.createCellStyle();
		style.cloneStyleFrom((CellStyle) styles.get("data"));
		style.setAlignment((short) 2);
		style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
		style.setFillPattern((short) 1);
		Font headerFont = workbook.createFont();
		headerFont.setFontName("Arial");
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setBoldweight((short) 700);
		headerFont.setColor(IndexedColors.WHITE.getIndex());
		style.setFont(headerFont);
		styles.put("header", style);
		return styles;
	}
	
	/**
	 * 导出excel表格
	 * 
	 * @param title excel表格头
	 * @param fileName excel表格名称
	 * @param t 导出的bean名称
	 * @param data 导出的bean列表数据
	 * @param response http响应实例
	 * @return boolean类型
	 * @throws Exception
	 */
	public static <T> boolean exportExcel(String title, String fileName, Class t, List<T> data, HttpServletResponse response) throws Exception{
		Excel excel = new Excel();
		ExcelSheet sheet = excel.createSheet();
		Map<String, CellStyle> styles = createStyles(excel.getWorkbook()); 
		sheet.title(title).cellStyle(styles.get("title"));    
		sheet.header(t).setData(data);
		return excel.saveExcel(fileName, response);
	}
}
