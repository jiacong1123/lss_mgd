package com.lss.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIExcelUtil {

	/**
	 * Excel 2003
	 */
	private final static String XLS = "xls";

	/**
	 * Excel 2007
	 */
	private final static String XLSX = "xlsx";

	private static final DateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 导出excel
	 * 
	 * @param dataList
	 * @param columnNames
	 * @param columnMethods
	 * @param model
	 * @return
	 * @throws Exception
	 * 
	 */
	public static HSSFWorkbook exportExcel(List<?> dataList,
			String[] columnNames, String[] columnMethods, Object model)
			throws Exception {

		HSSFWorkbook workbook = null;

		try {
			// 创建工作簿实例
			workbook = new HSSFWorkbook();

			// 创建工作表实例
			HSSFSheet sheet = workbook.createSheet("数据明细");

			// 设置列宽
			POIExcelUtil.setSheetColumnWidth(sheet, columnNames.length);
			// 表头标题样式
			HSSFCellStyle headerStyle = POIExcelUtil
					.createHeaderStyle(workbook);

			// 获取样式
			HSSFCellStyle rowStyle = POIExcelUtil.createTitleStyle(workbook);
			rowStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			rowStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			rowStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			rowStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			rowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			rowStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.BLACK.index); // BLACK颜色
			font.setFontHeight((short) 200);
			rowStyle.setFont(font);

			// 获取样式
			HSSFCellStyle styleT = POIExcelUtil.createTitleStyle(workbook);

			if (dataList != null && dataList.size() > 0) {
				HSSFRow row = sheet.createRow((short) 0);// 建立新行
				for (int i = 0; i < columnNames.length; i++) {
					POIExcelUtil.createCell(row, i, headerStyle,
							HSSFCell.CELL_TYPE_STRING, columnNames[i]);
				}
				row.setHeightInPoints(40);
				setExportExcel(dataList, sheet, columnMethods, rowStyle, model,
						workbook, font);
			} else {
				POIExcelUtil.createCell(sheet.createRow(0), 0, styleT,
						HSSFCell.CELL_TYPE_STRING, "未查找到记录");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return workbook;
	}

	/**
	 * 
	 * @description
	 * @fileName:POIExcelUtil.java
	 * @createTime:2015年7月6日
	 * @param dataList
	 * @param sheet
	 * @param columnMethods
	 * @param rowStyle
	 * @param model
	 * @param workbook
	 * @param font
	 * @throws Exception
	 * @version:1.0.0
	 */
	public static void setExportExcel(List<?> dataList, HSSFSheet sheet,
			String[] columnMethods, HSSFCellStyle rowStyle, Object model,
			HSSFWorkbook workbook, HSSFFont font) throws Exception {

		// 给excel填充数据
		Map<String, Object> rowData = new HashMap<String, Object>();

		for (int i = 0; i < dataList.size(); i++) {
			if (model != null) {
				model = dataList.get(i);
			} else {
				rowData = (Map<String, Object>) dataList.get(i);
			}

			HSSFRow row1 = sheet.createRow(sheet.getLastRowNum() + 1);// 建立新行
			row1.setHeightInPoints(20);
			for (int j = 0; j < columnMethods.length; j++) {
				Object objColor = null;
				HSSFCellStyle style = rowStyle;
				if (model != null) {
					try {
						objColor = model.getClass().getMethod("getColor")
								.invoke(model);
					} catch (Exception e) {

					}
					if (null != objColor) {
						HSSFFont tmpFont = workbook.createFont();
						font.setFontHeight((short) 200);
						if ((Short) objColor == HSSFColor.RED.index) {
							tmpFont.setColor(HSSFColor.RED.index); // RED颜色
						} else if ((Short) objColor == HSSFColor.BLUE.index) {
							tmpFont.setColor(HSSFColor.BLUE.index); // BLUE颜色
						} else if ((Short) objColor == HSSFColor.BLACK.index) {
							tmpFont.setColor(HSSFColor.BLACK.index); // BLACK颜色
						} else if ((Short) objColor == HSSFColor.YELLOW.index) {
							tmpFont.setColor(HSSFColor.YELLOW.index); // YELLOW颜色
						}
						style.setFont(font);
					}
				}

				if (StringUtils.isBlank(columnMethods[j])) {
					POIExcelUtil.createCell(row1, j, style,
							HSSFCell.CELL_TYPE_STRING, "");
					continue;
				}
				String columnMethod = model != null ? columnMethods[j]
						.replaceFirst(columnMethods[j].substring(0, 1),
								columnMethods[j].substring(0, 1).toUpperCase())
						: columnMethods[j];
				Object obj = null;
				// System.out.println("columnMethod="+columnMethod);
				try {
					if (model != null) {
						obj = model.getClass().getMethod("get" + columnMethod)
								.invoke(model);
					} else {
						obj = rowData.get(columnMethod);
					}
					// System.out.println("columnValue="+obj.toString());
				} catch (Exception e) {
					POIExcelUtil.createCell(row1, j, style,
							HSSFCell.CELL_TYPE_STRING, "");
					continue;
				}

				// 如果是时间类型，转化格式
				if (obj instanceof Date) {
					// obj = DateUtil.formatDataToDatetime((Date)obj);
				}// 如果是金额格式
				if (obj instanceof BigDecimal) {
					// 当导出字段名是 定期项目 日收益率(%) 时保留3位小数
					if (columnMethods[j].equals("profitRate")) {
						// obj =
						// com.hx.qb.common.util.StringUtils.moneyToKeepDecimals(obj,
						// 3);
					} else {
						// obj =
						// com.hx.qb.common.util.StringUtils.moneyToKeepDecimals(obj,
						// 2);
					}
				}

				boolean trus = false;
				if (null != obj) {
					try {
						Integer.parseInt(obj + "");
						trus = true;
					} catch (Exception e) {
					}
				}
				if (trus) {
					POIExcelUtil.createCell(row1, j, style,
							HSSFCell.CELL_TYPE_NUMERIC, obj == null ? "" : obj);
				} else {
					POIExcelUtil.createCell(row1, j, style,
							HSSFCell.CELL_TYPE_STRING, obj == null ? "" : obj);
				}
			}
		}
		// System.out.println("rowEnd!");

	}

	/**
	 * 
	 * @description 更新excel，用于分多次写入同一个excel
	 * @fileName:POIExcelUtil.java
	 * @createTime:2015年7月6日
	 * @param workbook
	 * @param dataList
	 * @param columnMethods
	 * @param model
	 * @throws Exception
	 * @version:1.0.0
	 */
	public static void updateExportExcel(HSSFWorkbook workbook,
			List<?> dataList, String[] columnMethods, Object model)
			throws Exception {
		// 获取样式
		HSSFCellStyle rowStyle = POIExcelUtil.createTitleStyle(workbook);
		rowStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		rowStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		rowStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		rowStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		rowStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		rowStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);

		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index); // BLACK颜色
		font.setFontHeight((short) 200);
		rowStyle.setFont(font);

		setExportExcel(dataList, workbook.getSheetAt(0), columnMethods,
				rowStyle, model, workbook, workbook.getFontAt((short) 0));
	}

	// 设置excel的单元格样式
	public static HSSFCellStyle createTitleStyle(HSSFWorkbook wb) {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontHeight((short) 200);
		boldFont.setFontHeightInPoints((short) 12);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(boldFont);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		return style;
	}

	// 设置excel的表头标题样式
	public static HSSFCellStyle createHeaderStyle(HSSFWorkbook wb) {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontHeight((short) 200);
		boldFont.setColor(HSSFColor.WHITE.index); // 颜色
		boldFont.setFontHeightInPoints((short) 12); // 字体
		// boldFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); // 字体增粗
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(boldFont);
		style.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);// 设置背景色
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		return style;
	}

	// 设置列宽
	public static void setSheetColumnWidth(HSSFSheet sheet, int size) {
		for (int i = 0; i < size; i++) {
			sheet.setColumnWidth(i, 5000);
		}
	}

	/**
	 * 生成工作薄模板
	 * 
	 * @param workbook
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> toExeclTemple(HSSFWorkbook workbook,
			String fileName) throws Exception {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		workbook.write(output);
		fileName = fileName + df.format(new Date()) + ".xls";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", new String(fileName.getBytes("GB2312"), "ISO8859-1"));
		map.put("value", (new ByteArrayInputStream(output.toByteArray())));
		output.flush();
		output.close();
		return map;
	}

	// 创建Excel单元格
	public static void createCell(HSSFRow row, int column, HSSFCellStyle style,
			int cellType, Object value) {

		HSSFCell cell = row.createCell(column);
		if (style != null) {
			cell.setCellStyle(style);
		}

		switch (cellType) {
		case HSSFCell.CELL_TYPE_BLANK: {
		}

			break;

		case HSSFCell.CELL_TYPE_STRING: {
			cell.setCellValue(value.toString());
		}

			break;
		case HSSFCell.CELL_TYPE_NUMERIC: {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(Double.parseDouble(value.toString()));
		}
			break;
		default:
			break;
		}
	}

	/**
	 * excel文件导入
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 */
	public static List<List<String>> readExcel(InputStream is,
			String extensionName, int sheetNum) throws IOException {
		Workbook workbook = null;
		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		List<List<String>> list = new ArrayList<List<String>>();

		Sheet sheet = workbook.getSheetAt(sheetNum);
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			if (sheet == null) {
				continue;
			}

			CellReference cellReference = new CellReference("A4");
			boolean flag = false;

			for (int i = cellReference.getRow(); i <= sheet.getLastRowNum();) {
				Row r = sheet.getRow(i);
				if (r == null) {
					// 如果是空行（即没有任何数据、格式），直接把它以下的数据往上移动
					sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
					continue;
				}
				flag = false;
				for (Cell c : r) {
					if (c.getCellType() != Cell.CELL_TYPE_BLANK) {
						flag = true;
						break;
					}
				}
				if (flag) {
					i++;
					continue;
				} else {// 如果是空白行（即可能没有数据，但是有一定格式）
					if (i == sheet.getLastRowNum())// 如果到了最后一行，直接将那一行remove掉
					{
						sheet.removeRow(r);
					} else// 如果还没到最后一行，则数据往上移一行
					{
						sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
					}
				}
			}
		}

		// 循环行Row
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				continue;
			}
			// 循环列
			List<String> str = new ArrayList<String>();
			boolean fvg = false;
			for (short colIx = 0; colIx <= row.getLastCellNum(); colIx++) {
				String cellValue = "";
				Cell cell = row.getCell(new Integer(colIx));
				if (cell != null) {
					// 自定义时间类型
					if (colIx != 6) {
						cell.setCellType(Cell.CELL_TYPE_STRING);
					}
					cellValue = POIExcelUtil.getValue(cell);
				}
				if (!ObjectUtil.isEmpty(cellValue)) {
					fvg = true;
				}
				str.add(cellValue);
			}

			if (str != null && str.size() > 0 && fvg) {
				list.add(str);
			}
		}
		return list;
	}

	/**
	 * 得到Excel表中的值
	 * 
	 * @param hssfCell
	 *            Excel中的每一个格子
	 * @return Excel中每一个格子中的值
	 */
	@SuppressWarnings("static-access")
	public static String getValue(Cell cell) {
		if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			short format = cell.getCellStyle().getDataFormat();
            System.out.println("format:"+format+";;;;;value:"+cell.getNumericCellValue());
            SimpleDateFormat sdf = null;
            if (format == 14 || format == 31 || format == 57 || format == 58  
                    || (176<=format && format<=178) || (182<=format && format<=196) 
                    || (210<=format && format<=213) || (208==format ) ) { // 日期
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            } else if (format == 20 || format == 32 || format==183 || (200<=format && format<=209) ) { // 时间
                sdf = new SimpleDateFormat("HH:mm");
            } else { // 不是日期格式
                return String.valueOf(cell.getNumericCellValue());
            }
            double value = cell.getNumericCellValue();
            Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
            if(date==null || "".equals(date)){
                return "";
            }
            String result="";
            try {
                result = sdf.format(date);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
            return result;
		} else {
			// 返回字符串类型的值
			return String.valueOf(cell.getStringCellValue());
		}
	}

	/**
	 * Read the Excel 2010
	 * 
	 * @param path
	 *            the path of the excel file
	 * @return
	 * @throws IOException
	 */
	public static List<String> readXlsx(String path) throws IOException {

		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		List<String> list = new ArrayList<String>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					XSSFCell no = xssfRow.getCell(0);
					/*
					 * XSSFCell name = xssfRow.getCell(1); XSSFCell age =
					 * xssfRow.getCell(2); XSSFCell score = xssfRow.getCell(3);
					 */

					list.add(getValue(no));
				}
			}
		}
		return list;
	}

	/**
	 * Read the Excel 2003-2007
	 * 
	 * @param path
	 *            the path of the Excel
	 * @return
	 * @throws IOException
	 */
	public List<String> readXls(String path) throws IOException {

		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		List<String> list = new ArrayList<String>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {

					HSSFCell no = hssfRow.getCell(0);
					/*
					 * HSSFCell name = hssfRow.getCell(1); HSSFCell age =
					 * hssfRow.getCell(2); HSSFCell score = hssfRow.getCell(3);
					 */

					list.add(getValue(no));
				}
			}
		}
		return list;
	}

	@SuppressWarnings("static-access")
	private static String getValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	public static List<Test> res(String path, Test test) throws IOException {

		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		List<Test> list = new ArrayList<Test>();
		// Read the Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// Read the Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					try {
						Test t = new Test();
						HSSFCell no = hssfRow.getCell(0);
						HSSFCell name = hssfRow.getCell(1);
						HSSFCell age = hssfRow.getCell(2);
						// HSSFCell score = hssfRow.getCell(3);

						t.setS1(no.toString());
						t.setS2(name.toString());
						t.setS3(age.toString());

						list.add(t);
					} catch (Exception e) {
						continue;
					}

				}
			}
		}
		return list;
	}
}