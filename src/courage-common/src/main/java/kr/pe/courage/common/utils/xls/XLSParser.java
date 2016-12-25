/**========================================================
 *@FileName       : XLSParser.java
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

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

@Deprecated
public class XLSParser {
    
    private boolean isParsed;
    private int numberOfColumns;
    private int numberOfRows;
    
    private String XLSFilePath;
    
    /* Excel WorkBook Instance */
    private Sheet xlsWorkSheet;
    
    /**
     * <P>Microsoft Excel (.xls) 파일을 읽어들이기 위한 기본 생성자.</P>
     */
    public XLSParser() {
        this.xlsWorkSheet = null;
        this.isParsed = false;
        this.numberOfRows = 0;
        this.numberOfColumns = 0;
        this.XLSFilePath = null;
    }
    
    /**
     * <P>기정의된 XLS File Path로 parser를 구성한다.
     * @param xlsFilePath XLS File Path
     */
    public XLSParser(String xlsFilePath) {
        this();
        if( xlsFilePath != null /*&& xlsFilePath.indexOf(".xls") != -1*/ ) {
            this.XLSFilePath = xlsFilePath;
        } else {
            System.out.println("XLSParser.XLSParser() :: XLS 파일의 경로가 null이거나 잘못된 경로입니다.");
        }
    }
    
    /**
     * <P> Parser를 초기화시키고 HSSF 관련 변수들을 메모리 해제시킨다.</P>
     */
    public void clear() {
        System.out.println("XLSParser.clear() :: Cleaning member variables...START");
        this.xlsWorkSheet = null;
        this.isParsed = false;
        this.numberOfRows = 0;
        this.numberOfColumns = 0;
        this.XLSFilePath = null;
        System.out.println("XLSParser.clear() :: Cleaning member variables...DONE");        
        System.out.println("XLSParser.clear() :: Call Garbage Collector_START");
        System.gc();
        System.out.println("XLSParser.clear() :: Call Garbage Collector_DONE");
    }
    
    /**
     * <P>Parsing된 xls document의 현재 Sheet의 원하는 Row의 원하는 column위치의 Cell 을 가져온다.</P>
     * 
     * @param rowIndex Row Index of XLS Sheet
     * @param columnIndex Column Index of XLS Sheet
     * @return Parsing된 xls document의 현재 Sheet의 원하는 Row의 원하는 column위치의 Cell instance
     */
    public Cell getCell(int rowIndex, short columnIndex) {
        if( this.isParsed ) {
            Cell [] row = this.getRow(rowIndex);
            if( row != null ) {
                if( columnIndex < row.length ) {
                    return row[columnIndex];
                } else {
                    System.out.println("XLSParser.getCell() :: 존재하지 않는 column index입니다. --> "+columnIndex);
                    return null;
                }
            } else {
                System.out.println("XLSParser.getCell() :: Row를 가져오지 못했습니다.");
                return null;
            }
        } else {
            System.out.println("XLSParser.getCell() :: Parser가 초기화되지 않았습니다.");
            return null;
        }
    }
    
    /**
     * <P>Parsing된 xls document의 현재 Sheet의 원하는 Row의 원하는 column위치의 Cell 을 String type으로 가져온다.</P>
     * 
     * @param rowIndex Row Index of XLS Sheet
     * @param columnIndex Column Index of XLS Sheet
     * @return Parsing된 xls document의 현재 Sheet의 원하는 Row의 원하는 column위치의 Cell Value
     */
    public String getCellValue(int rowIndex, short columnIndex) {
        Cell cell = this.getCell(rowIndex, columnIndex);
        if( cell != null ) {
            return cell.getContents();
        } else {
            return "";
        }
    }
    
    /**
     * <P>Parsing된 XLS문서의 현재 Sheet의 총 Column수를 가져온다.</P>
     * 
     * @return Parsing된 XLS문서의 현재 Sheet의 총 Column수.<br>
     * 만약 parser가 초기화되지 않았다면 (Parsing된 XLS 문서가 없을 경우) <code>-1</code>이 반환된다.
     */
    public int getNumberOfColumns() {
        if( this.isParsed ) {
            return this.numberOfColumns;
        } else {
            System.out.println("XLSParser.getNumberOfCellsPerSheet() :: Parser가 초기화되지 않았습니다.");
            return -1;
        }
    }
    
    /**
     * <P>Parsing된 XLS 문서의 현재 Sheet의 총 row 개수를 가져온다.</P>
     * 
     * @return Parsing된 XLS문서의 현재 Sheet의 총 row수<br>
     * 만약 parser가 초기화되지 않았다면 (Parsing된 XLS 문서가 없을 경우) <code>-1</code>이 반환된다.
     */
    public int getNumOfRows() {
        if( this.isParsed ) {
            return this.numberOfRows;
        } else {
            System.out.println("XLSParser.getNumOfRowsPerSheet() :: Parser가 초기화되지 않았습니다.");
            return -1;
        }
    }
    
    /**
     * <P>Parsing된 XLS Document의 현재 Sheet의 원하는 위치의 Row를 Cell Array 형태로 가져온다.</P>
     * <P>
     * 존재하지 않는 sheet나 row의 index를 파라미터로 넘겼을 경우에는 <code>null</code>이 반환된다.
     * </P>
     * 
     * @param rowIndex Row Index of XLS Sheet
     * @return Parsing된 XLS Document의 현재 Sheet의 원하는 위치의 Row
     */
    public Cell [] getRow(int rowIndex) {
        if( rowIndex < 0 ) {
            System.out.println("XLSParser.getRow() :: 올바르지 않은 row index입니다. --> "+rowIndex);
            return null;
        }
        if( this.isParsed ) {
            if( rowIndex < this.numberOfRows ) {
                return this.xlsWorkSheet.getRow(rowIndex);
            } else {
                System.out.println("XLSParser.getRow() :: Sheet에 존재하지 않는 Row입니다.");
                return null;
            }
        } else {
            System.out.println("XLSParser.getRow() :: Parser가 초기화되지 않았습니다.");
            return null;
        }
    }
    
    /**
     * <P>Parsing된 XLS Document의 현재 Sheet의 원하는 위치의 Column를 Cell Array 형태로 가져온다.</P>
     * <P>
     * 존재하지 않는 sheet나 col의 index를 파라미터로 넘겼을 경우에는 <code>null</code>이 반환된다.
     * </P>
     * 
     * @param colIndex Col Index of XLS Sheet
     * @return Parsing된 XLS Document의 현재 Sheet의 원하는 위치의 Col
     */
    public Cell [] getCol(int colIndex) {
        if( colIndex < 0 ) {
            System.out.println("XLSParser.getRow() :: 올바르지 않은 col index입니다. --> "+colIndex);
            return null;
        }
        if( this.isParsed ) {
            if( colIndex < this.numberOfColumns ) {
                return this.xlsWorkSheet.getColumn(colIndex);
            } else {
                System.out.println("XLSParser.getRow() :: Sheet에 존재하지 않는 Column입니다.");
                return null;
            }
        } else {
            System.out.println("XLSParser.getRow() :: Parser가 초기화되지 않았습니다.");
            return null;
        }
    }
    
    /**
     * <P>Parsing된 XLS Document의 현재 Sheet의 원하는 위치의 row를 String Array 형태로 가져온다.</P>
     * <P>Cell의 String 변환값은 각 Cell Object의 getContents() method를 호출하여 가져오게 되며, getRow(int) method의 각 cell에 
     * getContents() method를 적용한 것과 동일하다.</P> 
     * 
     * @param rowIndex Row index of XLS Sheet
     * @return Parsing된 XLS Document의 현재 Sheet의 원하는 위치의 row
     */
    public String [] getRowValue(int rowIndex) {
        Cell [] row = this.getRow(rowIndex);
        String [] rowValue = new String[row.length];
        for(int i = 0; i < row.length; i++) {
            if( row[i] != null ) {
                rowValue[i] = row[i].getContents();
            } else {
                rowValue[i] = new String("");
            }
        }        
        return rowValue;
    }
    
    /**
     * <P>Parsing된 XLS Document의 현재 Sheet를 가져온다.</P>
     * @return Parsing된 XLS Document의 현재 Sheet
     */
    public Sheet getSheet() {
        if( this.isParsed ) {
            return this.xlsWorkSheet;
        } else {
            System.out.println("XLSParser.getSheet() :: Parser가 초기화되지 않았습니다.");
            return null;
        }
    }
    
    /**
     * <P>Parsing된 XLS 문서의 해당 Sheet의 Sheet Name을 가져온다.</P>
     * 
     * @return Parsing된 XLS 문서의 해당 Sheet의 Sheet Name.<br>
     * 만약 parser가 초기화되지 않았다면 (Parsing된 XLS 문서가 없을 경우) <code>null</code>이 반환된다.
     */
    public String getSheetName() {
        if( this.isParsed ) {
            return this.xlsWorkSheet.getName();
        } else {
            System.out.println("XLSParser.getSheetName() :: Parser가 초기화되지 않았습니다.");
            return null;
        }
    }
    
    /**
     * <P>Parsing된 XLS문서의 물리적 경로를 가져온다.</P>
     * 
     * @return Parsing된 XLS문서의 물리적 경로<br>
     * 만약 Setting된 경로가 없거나 parser가 초기화되지 않았다면 <code>null</code>이 반환된다.
     */
    public String getXLSFilePath() {
        return this.XLSFilePath;
    }
    
    /**
     * <P>Parsing된 문서가 존재하는지 여부를 가져온다.</P>
     * 
     * @return XLS 문서 Parsing 여부
     */
    public boolean isParsed() {
        return this.isParsed;
    }
    
    /**
     * <P>XLS 문서를 Parsing한다. 결과적으로 parseXLS()과 동일하다.</P>
     * <P>
     * <strong>주의사항</strong><br>
     * 만약 이미 Parsing된 문서가 있다면 이 메소드는 해당 문서를 새 문서로 대체시키므로 주의해서 사용해야 한다.
     * </P>
     * 
     * @param fromFile Parsing할 XLS 문서
     * @param sheetIndex Parsing할 sheet number
     * @return Parsing 성공 여부
     * @see #parseXLS(int)
     */
    public boolean parseXLS(File fromFile, int sheetIndex) {
        boolean result = false;
        
        if( fromFile != null && fromFile.isFile() ) {
            this.XLSFilePath = fromFile.getPath();
            return this.parseXLS(sheetIndex);
        } else {
            System.out.println("XLSParser.parseXLS(String) :: XLS file is null or not a file");
            return false;
        }
    }
    
    /**
     * <P>설정된 경로에 있는 XLS 문서를 Parsing한다.</P>
     * 
     * @param sheetIndex Parsing할 sheet Number 
     * @return Parsing 성공 여부
     */
    public boolean parseXLS(int sheetIndex) {
        if( this.XLSFilePath == null /*|| this.XLSFilePath.indexOf(".xls") == -1*/ ) {
            System.out.println("XLSParser.parseXLS() :: XLS 파일 경로가 지정되지 않았거나 .xls 타입이 아닙니다.");
            isParsed = false;
        } else {
	        try {
	            System.out.println("XLSParser.parseXLS() :: ━━━━━━━━━━━━━━━ XLS 파일 Parsing 준비 ━━━━━━━━━━━━━━━");
		        Workbook book = Workbook.getWorkbook(new File(this.XLSFilePath));
		        System.out.println("XLSParser.parseXLS() :: xls 파일 Loading Done. Parsing 시작");
		        
	            if( book != null ) {
	                int numberOfSheets = book.getNumberOfSheets();
	                System.out.println("XLSParser.parseXLS() :: XLS문서의 총 Sheet 수 = "+numberOfSheets);
	                if( sheetIndex < numberOfSheets ) {
	                	System.out.println("workSheet loding..");
	                    this.xlsWorkSheet = book.getSheet(sheetIndex);
	                    System.out.println("workSheet loding success");
	                    this.numberOfRows = this.xlsWorkSheet.getRows();
	                    this.numberOfColumns = this.xlsWorkSheet.getColumns();
	                    System.out.println("XLSParser.parseXLS() :: Parsing된 XSL Sheet Row/Column --> "+this.numberOfRows+"/"+this.numberOfColumns);
	                    this.isParsed = true;
	                    
	                    System.out.println("XLSParser.parseXLS() :: ━━━━━━━━━━━━━━━ XLS 파일 Parsing Done ━━━━━━━━━━━━━━━");
	                } else {
	                    System.out.println("XLSParser.parseXLS() :: 전달된 Workbook에 원하는 Sheetindex에 해당하는 sheet가 없습니다.-->"+sheetIndex);
	                    this.xlsWorkSheet = null;
	                    this.isParsed = false;
	                }
	            } else {
	                System.out.println("XLSParser.parseXLS() :: 전달된 Workbook이 null입니다.");
	                this.xlsWorkSheet = null;
	                this.isParsed = false;
	            }
	        } catch (IOException ex) {
	            System.out.println("XLSParser.parseXLS() :: XLS File Loading중 오류 발생");
	            System.out.println("XLSParser.parseXLS() :: "+ex.toString());
	            return false;
	        } catch (BiffException ex ) {
	            System.out.println("XLSParser.parseXLS() :: XLS File Loading중 오류 발생");
	            System.out.println("XLSParser.parseXLS() :: "+ex.toString());
	            return false;
	        }
        }
        
        System.gc();
        
        return this.isParsed;
    }
    
    /**
     * <P>물리적 경로로 전달된 XLS 문서를 파싱한다. 결과적으로 parseXLS()과 동일하다.</P>
     * <P>
     * <strong>주의사항</strong><br>
     * 만약 이미 Parsing된 문서가 있다면 이 메소드는 해당 문서를 새 문서로 대체시키므로 주의해서 사용해야 한다.
     * </P>
     *  
     * @param path XLS 문서 경로 (XLS 문서명 포함)
     * @param sheetIndex Parsing할 Sheet Number
     * @return Parsing 성공 여부
     * @see #parseXLS(int)
     */
    public boolean parseXLS(String path, int sheetIndex) {
        boolean result = false;
        
        System.out.println("XLSParser.parseXLS(String) :: Receive Parsing Request. ");
        if( path != null /*&& path.indexOf(".xls") != -1*/ ) {
            this.XLSFilePath = path;
            return this.parseXLS(sheetIndex);
        } else {
            System.out.println("XLSParser.parseXLS(String) :: XLS filepath is null or not valid format --> "+path);
            return false;
        }
    }
    
    /**
     * <P>Parsing된 XLS Document의 가져올 Column수의 최대값을 설정한다.</P>
     * 
     * @param columnSize Parsing된 XLS Document의 가져올 Column수의 최대값
     */
    public void setMaxColumnSize(int columnSize) {
        if( this.isParsed ) {
            if( columnSize >= 0 ) {
                this.numberOfColumns = columnSize;
            } else {
                System.out.println("XLSParser.setMaxColumnSize() :: 유효하지 않은 Column Size입니다.");
            }
        } else {
            System.out.println("XLSParser.setMaxColumnSize() :: Parsing된 문서에 한해서 column수를 지정할 수 있습니다.");
        }
    }
    
    /**
     * <P>Parsing된 XLS Document의 가져올 Row수의 최대값을 설정한다.</P>
     * 
     * @param rowNum Parsing된 XLS Document의 가져올 Row수의 최대값
     */
    public void setMaxRowSize(int rowNum) {
        if( this.isParsed ) {
            if( rowNum >= 0 ) {
                this.numberOfRows = rowNum;
            } else {
                System.out.println("XLSParser.setMaxRowSize() :: 유효하지 않은 Row Size입니다.");
            }
        } else {
            System.out.println("XLSParser.setMaxRowSize() :: Parsing된 문서에 한해서 row수를 지정할 수 있습니다.");
        }
    }
    
   /**
     * <P>Parsing할 XLS 문서의 물리적 경로를 설정한다.</P>
     * 
     * @param path 설정할 XLS 문서의 물리적 경로
     */
    public void setXLSFilePath(String path) {
        if( path != null /*&& path.indexOf(".xls") != -1*/ ) {
            this.XLSFilePath = path;
        } else {
            System.out.println("XLSParser.setXLSFilePath() :: XLS 파일의 경로가 null이거나 잘못된 경로입니다.");
        }
    }
}
