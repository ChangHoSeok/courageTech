/**========================================================
 *@FileName       : WriteExcelHandler.java
 *Open Issues     : N/A
 *
 *@LastModifyDate : 2009. 04. 14.
 *@LastModifier   : 석창호 
 *@LastVersion    : 1.0
 *Change History
 *    2009. 04. 14.    석창호    1.0    최초생성 
 =========================================================*/
package kr.pe.courage.common.utils.xls;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;

@Deprecated
public class WriteExcelHandler {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WriteExcelHandler.class);
	private static final int MAX_ROW_COUNT = 50000;
	
	public WriteExcelHandler() {
        super();
    }
    
    public static boolean defaultWriteExcel(WriteExcelParameter wep) throws Exception {
        boolean bMakeExcel = true;
        try {
            wep.setTitleFormat(getDefaultTitleFormat());
            wep.setHeaderFormat(getDefaultHeaderFormat());
            wep.setRowFormat(getDefaultRowFormat());
            writeExcel(wep);
        } catch(IOException io) {
        	logger.error(io);
    	} catch(Exception e) {
            logger.error(e);
        }
        
        return bMakeExcel;
    }
    
    public static void writeExcel(WriteExcelParameter wep) throws WriteException, IOException {
    	File excelFile = new File(wep.getFilePath());
    	
    	if (!excelFile.isDirectory()) {
    		excelFile.mkdirs();
    	}
    	excelFile = new File(wep.getFilePath() + wep.getFileNm());
    	logger.info("## createFileName = " + excelFile.getName());
        WritableWorkbook workbook = Workbook.createWorkbook(excelFile);
        logger.info("## workBook Created");
        ArrayList alRowData = wep.getRowData();
        
        try {			
        	if (alRowData.size() > MAX_ROW_COUNT) {
        		int nSelCount = Math.abs(alRowData.size()/MAX_ROW_COUNT)+1;
        		int nStartPoint = 0;
        		int nEndPoint = 0;
        		for(int i=0; i<nSelCount; i++) {
        			WritableSheet sheet = workbook.createSheet(wep.getSheetNm()+"_"+i, i);
        			if(i==0) {
        				writeTitle(sheet, wep.getHeader(), wep.getTitle(), wep.getTitleFormat());                        
        			}
        			writeHeader(sheet, wep.getHeader(), wep.getHeaderFormat());
        			nStartPoint = i*MAX_ROW_COUNT;
        			nEndPoint = ((i+1)*MAX_ROW_COUNT);
        			if(i != (nSelCount-1)) {
        				selWriteExcel(alRowData, sheet, nStartPoint, nEndPoint,wep.getRowFormat());
        			} else {
        				selWriteExcel(alRowData, sheet, nStartPoint, alRowData.size(),wep.getRowFormat());
        			}
        		}
        	} else {
        		WritableSheet sheet = workbook.createSheet(wep.getSheetNm(), 0);
        		writeTitle(sheet, wep.getHeader(), wep.getTitle(), wep.getTitleFormat());
        		writeHeader(sheet, wep.getHeader(), wep.getHeaderFormat());
        		selWriteExcel(alRowData, sheet, 0, alRowData.size(), wep.getRowFormat());                    
        	}
		} catch (Exception e) {
			logger.error(e);
		} finally {			
			workbook.write();
			workbook.close();
		}
    }
    
    private static void writeTitle(WritableSheet sheet, String[] arrHeader,
    		String szTitle, WritableCellFormat titleFormat) throws WriteException {
        int nCellCount = arrHeader.length;
        // 셀 Merge하는 경우.(타이틀 쓰기)
        sheet.mergeCells(0,0,nCellCount,0);
        Label titlelabel = new Label(0,0, szTitle, titleFormat);
        sheet.addCell(titlelabel);
    }
    
    private static void writeHeader(WritableSheet sheet, String[] arrHeader,
    		WritableCellFormat headerFormat) throws WriteException {
    	int nCellCount = arrHeader.length;
        // 해더 쓰기
        Label headlabelCount = new Label(0,1,"순번",headerFormat);
        sheet.addCell(headlabelCount);
        Label headlabel = null;
        
        for ( int nCellNum = 0; nCellNum < nCellCount; nCellNum++ ) {
            headlabel = new Label(nCellNum+1,1, arrHeader[nCellNum], headerFormat);
            sheet.addCell(headlabel);
        }
    }
    
    private static void selWriteExcel(ArrayList alRowData, WritableSheet sheet,
    		int nStart, int nEnd, WritableCellFormat cellFormat) throws WriteException {
    	Hashtable htCellData = null;
        String szCellData = "";

        jxl.write.Number num = null;
        Label label2 = null;
        int k = 0;
        for ( int rownum = nStart; rownum < nEnd ; rownum++) {
        	htCellData = (Hashtable)alRowData.get(rownum);            
            for ( int nCellnum = 0; nCellnum <= htCellData.size(); nCellnum++) {
            	if(nCellnum == 0) {
                  // Count 부분
            		num = new jxl.write.Number(nCellnum,k+2, rownum+1, cellFormat);
            		sheet.addCell(num);                
            	} else {
            		szCellData = (String)htCellData.get(Integer.toString(nCellnum));
            		//실제 데이타
            		label2 = new Label(nCellnum, k+2, szCellData, cellFormat);
            		
	                sheet.setColumnView(nCellnum , 20);
	                sheet.addCell(label2);
            	}
            }
            k++;
        }
    }
    
    public static WritableCellFormat getDefaultTitleFormat() throws WriteException {
		WritableFont wf_title = new WritableFont(WritableFont.createFont("굴림"),14, WritableFont.BOLD,  false,  UnderlineStyle.SINGLE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT);
		WritableCellFormat wcf_title_format = new WritableCellFormat(wf_title);
		wcf_title_format.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf_title_format.setAlignment(Alignment.CENTRE);
		      
		return wcf_title_format;
    }
    
    public static WritableCellFormat getDefaultHeaderFormat() throws WriteException {
		WritableFont wf_header = new WritableFont(WritableFont.createFont("굴림"),10, WritableFont.BOLD,  false,  UnderlineStyle.NO_UNDERLINE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT);
		WritableCellFormat wcf_header_format = new WritableCellFormat(wf_header);
		wcf_header_format.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf_header_format.setAlignment(Alignment.CENTRE);
		wcf_header_format.setBorder(Border.ALL, BorderLineStyle.THIN);
		      
		wcf_header_format.setWrap(true);
		
		return wcf_header_format;
    }

	public static WritableCellFormat getDefaultRowFormat() throws WriteException {
		WritableFont wf_content = new WritableFont(WritableFont.createFont("굴림"),10, WritableFont.NO_BOLD,  false,  UnderlineStyle.NO_UNDERLINE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT);
		WritableCellFormat wcf_content_format = new WritableCellFormat(wf_content);
		wcf_content_format.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf_content_format.setAlignment(Alignment.JUSTIFY);
		wcf_content_format.setBorder(Border.ALL, BorderLineStyle.THIN);
				  
		wcf_content_format.setWrap(true);      
		
		return wcf_content_format;
	}
}
