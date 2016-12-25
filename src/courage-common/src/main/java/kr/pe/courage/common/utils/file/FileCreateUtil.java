/**
 * 클래스설명 : 파일생성 Class
 * @version : 2010. 12. 13.
 * @author : ChangHo Seok
 * @분류 : 
 * rdms_renewal / kr.pe.courage.common.file;
 */

package kr.pe.courage.common.utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

public class FileCreateUtil
{
	private BufferedWriter m_bWriter = null;
	
	// Create BufferedReader : System encode
	public FileCreateUtil(String strFilename) throws IOException
	{
		this.m_bWriter = new BufferedWriter( new FileWriter(strFilename) );
	}
	
	// Create BufferedReader : user encode
	public FileCreateUtil(String strFilename, String strCharset) throws IOException, FileNotFoundException, UnsupportedEncodingException
	{
		this.m_bWriter = new BufferedWriter(
							new OutputStreamWriter(
								new FileOutputStream( new File(strFilename) ),
								strCharset
							)
						);
		writeBOM(strCharset);
	}
	
	// write BOM
	private void writeBOM(String strCharset) throws IOException
	{
		if( strCharset.compareTo("UTF-8"   ) == 0 ||
			strCharset.compareTo("UTF-16LE") == 0 ||
			strCharset.compareTo("UTF-16BE") == 0 )
		{
			// write BOM (EF BB BF : UTF-16BE -> "65279")
			this.m_bWriter.write(65279);
		}
	}

	// write file
	public void writeLine(String strLine) throws IOException
	{
		this.m_bWriter.write(strLine);
		this.m_bWriter.newLine();
	}
	
	// write file
	public void write(String str) throws IOException
	{
		String strLine = "";
		BufferedReader reader = new BufferedReader(new StringReader(str));
	
		while ((strLine = reader.readLine()) != null) {
			this.m_bWriter.write(strLine);
			this.m_bWriter.newLine();
		}
		reader.close();
	}
	
	// close file
	public void close() throws IOException
	{
		if( this.m_bWriter != null )
		{
			this.m_bWriter.close();
			this.m_bWriter = null;
		}
	}
}
