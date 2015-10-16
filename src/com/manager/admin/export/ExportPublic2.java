package com.manager.admin.export;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.manager.admin.service.AdminLogService;
import com.manager.admin.serviceimpl.AdminLogServiceImpl;


public class ExportPublic2 {
	
	private AdminLogService adminLogService=new AdminLogServiceImpl();

	// 设置cell编码解决中文高位字节截断
	private static short XLS_ENCODING = HSSFWorkbook.ENCODING_UTF_16;

	// 定制日期格式
	private static String DATE_FORMAT = "m/d/yy";// "m/d/yy h:mm"

	// 定制浮点格式
	private static String NUMBER_FORMAT = " #,##0.00 ";

	private String xlsFileName;

	private HSSFWorkbook workbook = new HSSFWorkbook();

	private HSSFRow row;
	
	private HSSFRow row2;

	private HSSFCellStyle style1;// 设置单元格数据格式

	private HSSFCellStyle style2;// 标题样式

	private HSSFCellStyle style3;// 内容样式
	
	public ExportPublic2(String xlsFileName){
		this.xlsFileName = xlsFileName;

		// 设置单元格数据格式
		HSSFDataFormat df = workbook.createDataFormat();
		this.style1 = workbook.createCellStyle();
		this.style1.setDataFormat(df.getFormat("0.00%"));

		//
		HSSFFont f1 = workbook.createFont();
		f1.setFontHeightInPoints((short) 10);// 字号
		f1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		this.style2 = workbook.createCellStyle();
		this.style2.setFont(f1);
		this.style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中
		this.style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中

		HSSFFont f2 = workbook.createFont();
		f2.setFontName("楷体GB2312");
		this.style3 = workbook.createCellStyle();
		this.style3.setFont(f2);
		this.style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		this.style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	}
	
	
	public int exportXLS(List resultlist, Object[] titleobj,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int state = 0;
		try {
			HSSFSheet sheet = workbook.createSheet("sheet0");

			// 创建HSSFCell对象 格子单元
			HSSFCell cell;

			// //-----------------创建标题开始-------------
			row = sheet.createRow((short) (0));
			for (int h = 0; h < titleobj.length; h++) {
				cell = row.createCell((short) h);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(titleobj[h] + "");
				cell.setCellStyle(style2);
			}
			// //----------标题结束-----------------------
			
			int i = 0;
			int j = 0;
			int num=resultlist.size();
			if(num>5000)num=5000;
			for (; j < num; j++) {
				i = 0;
				row = sheet.createRow((short) j + 2);
				Object[] o=(Object[]) resultlist.get(j);
				
				for( int y = 0; y < o.length; y++){
					cell = row.createCell((short) (i++));
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(style3);
					if(o[y]!=null){
						cell.setCellValue(o[y].toString());
					}else{
						cell.setCellValue(" ");
					}
				}
				
			}
			// 输出Excel----------------------------------------------------------------

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ xlsFileName + ".xls");
			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			state = 1;
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
//			OperatorService operServer = new OperatorServiceImpl();
//			operServer.Write_Operator_log("导出相关","导出"+xlsFileName+".xls", state, request);
			this.adminLogService.addAdminLog("导出相关", "导出"+xlsFileName+".xls", 1, request);
		}
	}
	
	
	public int exportXLS2(List resultlist, Object[] titleobj,List resultlist2,Object[] titleobj2,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int state = 1;
		try {
			HSSFSheet sheet = workbook.createSheet("sheet0");

			// 创建HSSFCell对象 格子单元
			HSSFCell cell;

			// //-----------------创建标题开始-------------
			row = sheet.createRow((short) (0));
			for (int h = 0; h < titleobj.length; h++) {
				cell = row.createCell((short) h);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(titleobj[h] + "");
				cell.setCellStyle(style2);
			}
			// //----------标题结束-----------------------
			
			int i = 0;
			int j = 0;
			int num=resultlist.size();
			if(num>5000)num=5000;
			for (; j < num; j++) {
				i = 0;
				row = sheet.createRow((short) j + 2);
				Object[] o=(Object[]) resultlist.get(j);
				
				for( int y = 0; y < o.length; y++){
					cell = row.createCell((short) (i++));
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(style3);
					if(o[y]!=null){
						cell.setCellValue(o[y].toString());
					}else{
						cell.setCellValue(" ");
					}
				}
				
			}
			
			// 创建HSSFCell对象 格子单元
			HSSFCell cell2;
			j=j+2;
			// //-----------------创建标题开始-------------
			row2 = sheet.createRow((short) (0));
			for (int h = 0; h < titleobj2.length; h++) {
				row2 = sheet.createRow((short) j + 2);
				cell2 = row2.createCell((short) h);
				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell2.setCellValue(titleobj2[h] + "");
				cell2.setCellStyle(style2);
			}
			// //----------标题结束-----------------------
			
			int i2 = 0;
			int j2 = 0;
			for (; j2 < resultlist2.size(); j2++) {
				j++;
				i2 = 0;
				row2 = sheet.createRow((short) j + 2);
				
				Object[] o=(Object[]) resultlist.get(j2);
				
				for( int y = 0; y < o.length; y++){
					cell2 = row.createCell((short) (i++));
					cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell2.setCellStyle(style3);
					if(o[y]!=null){
						cell2.setCellValue(o[y].toString());
					}else{
						cell2.setCellValue(" ");
					}
				}
				
			}
			
			// 输出Excel----------------------------------------------------------------

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ xlsFileName + ".xls");
			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			
			return 1;
		} catch (Exception e) {
			state = 0;
			e.printStackTrace();
			throw e;
		}finally{
//			OperatorService operServer = new OperatorServiceImpl();
//			operServer.Write_Operator_log("导出相关","导出"+xlsFileName+".xls", state, request);
			this.adminLogService.addAdminLog("导出相关", "导出"+xlsFileName+".xls", 1, request);
		}
				
		
	}
	

	public int exportXLS3(List resultlist, Object[] titleobj,Object[] result2,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int state = 1;
		try {
			HSSFSheet sheet = workbook.createSheet("sheet0");

			// 创建HSSFCell对象 格子单元
			HSSFCell cell;

			// //-----------------创建标题开始-------------
			row = sheet.createRow((short) (0));
			for (int h = 0; h < titleobj.length; h++) {
				cell = row.createCell((short) h);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(titleobj[h] + "");
				cell.setCellStyle(style2);
			}
			// //----------标题结束-----------------------
			
			int i = 0;
			int j = 0;
			int num=resultlist.size();
			if(num>5000)num=5000;
			for (; j < num; j++) {
				i = 0;
				row = sheet.createRow((short) j + 2);
				Object[] o=(Object[]) resultlist.get(j);
				
				for( int y = 0; y < o.length; y++){
					cell = row.createCell((short) (i++));
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					cell.setCellStyle(style3);
					if(o[y]!=null){
						cell.setCellValue(o[y].toString());
					}else{
						cell.setCellValue(" ");
					}
				}
				
			}
			
			// 创建HSSFCell对象 格子单元
			HSSFCell cell2;
			j=j+1;
			// //-----------------生成第二个结果集-------------
			row2 = sheet.createRow((short) (0));
			for (int h = 0; h < result2.length; h++) {
				row2 = sheet.createRow((short) j + 2);
				cell2 = row2.createCell((short) h);
				cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell2.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell2.setCellValue(result2[h] + "");
				cell2.setCellStyle(style2);
			}
			
			
			// 输出Excel----------------------------------------------------------------

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename="
					+ xlsFileName + ".xls");
			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			
			return 1;
		} catch (Exception e) {
			state = 0;
			e.printStackTrace();
			throw e;
		}finally{
//			OperatorService operServer = new OperatorServiceImpl();
//			operServer.Write_Operator_log("导出相关","导出"+xlsFileName+".xls", state, request);
			this.adminLogService.addAdminLog("导出相关", "导出"+xlsFileName+".xls", 1, request);
		}
				
		
	}
}
