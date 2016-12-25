
package kr.pe.courage.common.utils.hwp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Formatter;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * <pre>
 * kr.pe.courage.cmm.hwp
 * HwpDocHandler.java
 * </pre>
 * 
 * @Author : jong-jin, park
 * @Date : 2013. 2. 14.
 * @Version : 1.0
 */
public class HwpDocHandler {

	final public static String HWP_FILE_HEADER = "FileHeader";
	final public static String HWP_DOC_INFO = "DocInfo";
	final public static String HWP_BODY_TEXT = "BodyText";
	final public static String HWP_SUMMARY = "\005HwpSummaryInfomation";
	final public static String HWP_BIN_DATA = "BinData";
	final public static String HWP_PRV_TEXT = "PrvText";
	final public static String HWP_PRV_IMAGE = "PrvImage";

	public static final String IDENTITY_HWP = "HWP Document File";
	private static final int HWP5TAG_BEGIN = 0x10;
	private static final int HWP5TAG_DOCUMENT_PROPERTIES = HWP5TAG_BEGIN; // 문서속성
	private static final int HWP5TAG_ID_MAPPINGS = HWP5TAG_BEGIN + 1; // 아이디매핑헤더
	private static final int HWP5TAG_BIN_DATA = HWP5TAG_BEGIN + 2; // BinData
	private static final int HWP5TAG_FACE_NAME = HWP5TAG_BEGIN + 3; // Typeface
																	// Name
	private static final int HWP5TAG_BORDER_FILL = HWP5TAG_BEGIN + 4; // 테두리/배경
	private static final int HWP5TAG_CHAR_SHAPE = HWP5TAG_BEGIN + 5; // 글자 모양
	private static final int HWP5TAG_TAB_DEF = HWP5TAG_BEGIN + 6; // 탭 정의
	private static final int HWP5TAG_NUMBERING = HWP5TAG_BEGIN + 7; // 번호 정의
	private static final int HWP5TAG_BULLET = HWP5TAG_BEGIN + 8; // 불릿 정의
	private static final int HWP5TAG_PARA_SHAPE = HWP5TAG_BEGIN + 9; // 문단 모양
	private static final int HWP5TAG_STYLE = HWP5TAG_BEGIN + 10; // 스타일
	private static final int HWP5TAG_DOC_DATA = HWP5TAG_BEGIN + 11; // 문서의 임의의 데이터
	private static final int HWP5TAG_DISTRIBUTE_DOC_DATA = HWP5TAG_BEGIN + 12; // 배포용 문서 데이터
	private static final int HWP5TAG_COMPATIBLE_DOCUMENT = HWP5TAG_BEGIN + 14; // 호환 문서
	private static final int HWP5TAG_LAYOUT_COMPATIBILITY = HWP5TAG_BEGIN + 15; // 레이아웃 호환성
	private static final int HWP5TAG_FORBIDDEN_CHAR = HWP5TAG_BEGIN + 78; // 금칙처리 문자

	private static final int HWP5TAG_PARA_HEADER = HWP5TAG_BEGIN + 50;
	private static final int HWP5TAG_PARA_TEXT = HWP5TAG_BEGIN + 51;
	private static final int HWP5TAG_PARA_CHAR_SHAPE = HWP5TAG_BEGIN + 52;
	private static final int HWP5TAG_PARA_LINE_SEG = HWP5TAG_BEGIN + 53;
	private static final int HWP5TAG_PARA_RANGE_TAG = HWP5TAG_BEGIN + 54;
	private static final int HWP5TAG_CTRL_HEADER = HWP5TAG_BEGIN + 55;
	private static final int HWP5TAG_LIST_HEADER = HWP5TAG_BEGIN + 56;
	private static final int HWP5TAG_PAGE_DEF = HWP5TAG_BEGIN + 57;
	private static final int HWP5TAG_FOOTNOTE_SHAPE = HWP5TAG_BEGIN + 58;
	private static final int HWP5TAG_PAGE_BORDER_FILL = HWP5TAG_BEGIN + 59;
	private static final int HWP5TAG_SHAPE_COMPONENT = HWP5TAG_BEGIN + 60;
	private static final int HWP5TAG_TABLE = HWP5TAG_BEGIN + 61;
	private static final int HWP5TAG_SHAPE_COMPONENT_LINE = HWP5TAG_BEGIN + 62;
	private static final int HWP5TAG_SHAPE_COMPONENT_RECTANGLE = HWP5TAG_BEGIN + 63;
	private static final int HWP5TAG_SHAPE_COMPONENT_ELLIPSE = HWP5TAG_BEGIN + 64;
	private static final int HWP5TAG_SHAPE_COMPONENT_ARC = HWP5TAG_BEGIN + 65;
	private static final int HWP5TAG_SHAPE_COMPONENT_POLYGON = HWP5TAG_BEGIN + 66;
	private static final int HWP5TAG_SHAPE_COMPONENT_CURVE = HWP5TAG_BEGIN + 67;
	private static final int HWP5TAG_SHAPE_COMPONENT_OLE = HWP5TAG_BEGIN + 68;
	private static final int HWP5TAG_SHAPE_COMPONENT_PICTURE = HWP5TAG_BEGIN + 69;
	private static final int HWP5TAG_SHAPE_COMPONENT_CONTAINER = HWP5TAG_BEGIN + 70;
	private static final int HWP5TAG_CTRL_DATA = HWP5TAG_BEGIN + 71;
	private static final int HWP5TAG_EQEDIT = HWP5TAG_BEGIN + 72;
	private static final int HWP5TAG_SHAPE_COMPONENT_TEXTART = HWP5TAG_BEGIN + 74;
	private static final int HWP5TAG_FORM_OBJECT = HWP5TAG_BEGIN + 75;
	private static final int HWP5TAG_MEMO_SHAPE = HWP5TAG_BEGIN + 76;
	private static final int HWP5TAG_MEMO_LIST = HWP5TAG_BEGIN + 77;
	private static final int HWP5TAG_CHART_DATA = HWP5TAG_BEGIN + 79;
	private static final int HWP5TAG_SHAPE_COMPONENT_UNKNOWN = HWP5TAG_BEGIN + 99;

	private File hwpFile = null;
	private POIFSFileSystem pfs = null;
	private DirectoryEntry root = null;

	public HwpDocHandler(File f) throws Exception {
		this.hwpFile = f;
		pfs = new POIFSFileSystem(new FileInputStream(hwpFile));
		root = pfs.getRoot();
	}

	/**
	 * <pre>
	 * 한글문서의 미리보기 thumbnail을 byte형태로 리턴한다.
	 * </pre>
	 * 
	 * @Date : 2013. 2. 14.
	 * @Method Name : getPreviewImageToByte
	 * @return
	 * @throws Exception
	 */
	public byte[] getPreviewImageToByte() throws Exception {
		/*
		 * 테스트 결과 hwp의 thumbnail image size가
		 * 이보다 크진 않을 듯 하다..
		 */
		
		final int thumbnailBuffSize = 4096;
		byte[] buf = null;
		InputStream is = null;
		try {
			DocumentEntry prvImage = (DocumentEntry) root.getEntry(HWP_PRV_IMAGE);
			is = new DocumentInputStream(prvImage);
			buf = new byte[thumbnailBuffSize];
			is.read(buf, 0, thumbnailBuffSize);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				is.close();
			} catch (Exception ee) {
			}
		}
		return buf;
	}

	/**
	 * <pre>
	 * 한글문서의 version정보를 리턴한다.
	 * 파일 버전. 0xMMnnPPrr의 형태(예 5.0.3.0)
	 *  ■ MM: 문서 형식의 구조가 완전히 바뀌는 것을 나타냄. 숫자가 다르면 구 버전과 호환 불가능.	
	 *  ■ nn: 큰 구조는 동일하나, 큰 변화가 있는 것을 나타냄. 숫자가 다르면 구 버전과 호환 불가능.
	 *  ■ PP: 구조는 동일, Record가 추가되었거나, 하위 버전에서 호환되지 않는 정보가 추가된 것을 나타냄. 숫자가 달라도 구 버전과 호환 가능.
	 *  ■ rr: Record에 정보들이 추가된 것을 나타냄. 숫자가 달라도 구 버전과 호환 가능.
	 * </pre>
	 * 
	 * @Date : 2013. 2. 14.
	 * @Method Name : getHwpVersion
	 * @return
	 * @throws Exception
	 */
	public String getHwpVersion() throws Exception {
		byte[] buf = new byte[1024];
		InputStream is = null;
		int ver;
		try {
			DocumentEntry fileHeader = (DocumentEntry) root.getEntry(HWP_FILE_HEADER);
			is = new DocumentInputStream(fileHeader);
			is.read(buf, 0, 1024);
			is.close();
			ver = (int) readLittleEndian(buf, 32, 4);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				is.close();
			} catch (Exception ee) {
			}
		}
		return new Formatter().format("[%08X]", ver).toString();
	}

	/**
	 * (hwp 데이터 레코드의 header recode를 배열로 리턴한다. 아래 참조) convert c-little endian
	 * structure to java-big endian int array <br/>
	 * header structure is DWORD [10bit][10bit][12bit] <br/>
	 * so it splits [8bit + next lower 2bit] [high 6bit + next lower 4bit] [high
	 * 4bit + next 8bit]<br/>
	 * 
	 * @return element 0 : current pos, element 1 : tag, element 2 : level,
	 *         element 3 : size
	 */
	public static int[] readRecordHeader(byte[] buf, int pos) {
		int[] ret = new int[4];
		ret[1] = (buf[pos] & 0xff) | ((buf[pos += 1] & 0x03) << 8);
		ret[2] = ((buf[pos] & 0xff) >> 2) | ((buf[pos += 1] & 0x0f) << 6);
		ret[3] = ((buf[pos] & 0xff) >> 4) | ((buf[pos += 1] & 0xff) << 4);
		ret[0] = pos += 1;
		return ret;
	}

	/**
	 * <pre>
	 * hwp 자료형에서 한 바이트는 8비트로 표현되며, 
	 * 두 바이트 이상의 길이를 가지는 자료형은 최하위 바이트가 가장 먼저 저장되고, 
	 * 최상위 바이트가 가장 나중에 저장되는 리틀 인디언(little indian) 형태이다
	 * </pre>
	 * 
	 * @Date : 2013. 2. 14.
	 * @Method Name : readLittleEndian
	 * @param buf
	 * @param pos
	 * @param bytes
	 * @return
	 */
	public static long readLittleEndian(byte[] buf, int pos, int bytes) {
		long ret = 0;
		for (int inx = 0; inx < bytes; inx++) {
			ret = ret | ((buf[pos + inx] & 0xff) << (inx * 8));
		}
		return ret;
	}

}
