package beans;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;

import java.util.zip.*;

public class CreateXLSXFromScratch 
{
	//some static parts of the XLSX file:

	 static String content_types_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Types xmlns=\"http://schemas.openxmlformats.org/package/2006/content-types\"><Default ContentType=\"application/vnd.openxmlformats-package.relationships+xml\" Extension=\"rels\"/><Default ContentType=\"application/xml\" Extension=\"xml\"/><Override ContentType=\"application/vnd.openxmlformats-officedocument.extended-properties+xml\" PartName=\"/docProps/app.xml\"/><Override ContentType=\"application/vnd.openxmlformats-package.core-properties+xml\" PartName=\"/docProps/core.xml\"/><Override ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.sharedStrings+xml\" PartName=\"/xl/sharedStrings.xml\"/><Override ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.styles+xml\" PartName=\"/xl/styles.xml\"/><Override ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml\" PartName=\"/xl/workbook.xml\"/><Override ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml\" PartName=\"/xl/worksheets/sheet1.xml\"/></Types>";

	 static String docProps_app_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Properties xmlns=\"http://schemas.openxmlformats.org/officeDocument/2006/extended-properties\"><Application>" + "Created Low level From Scratch" + "</Application></Properties>";

	 static String docProps_core_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><cp:coreProperties xmlns:cp=\"http://schemas.openxmlformats.org/package/2006/metadata/core-properties\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:dcterms=\"http://purl.org/dc/terms/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><dcterms:created xsi:type=\"dcterms:W3CDTF\">" + java.time.Instant.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS).toString() + "</dcterms:created><dc:creator>" + "Axel Richter from scratch" + "</dc:creator></cp:coreProperties>";

	 static String _rels_rels_xml  = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\"><Relationship Id=\"rId1\" Target=\"xl/workbook.xml\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument\"/><Relationship Id=\"rId2\" Target=\"docProps/app.xml\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/extended-properties\"/><Relationship Id=\"rId3\" Target=\"docProps/core.xml\" Type=\"http://schemas.openxmlformats.org/package/2006/relationships/metadata/core-properties\"/></Relationships>";

	 static String xl_rels_workbook_xml_rels_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\"><Relationship Id=\"rId1\" Target=\"sharedStrings.xml\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/sharedStrings\"/><Relationship Id=\"rId2\" Target=\"styles.xml\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles\"/><Relationship Id=\"rId3\" Target=\"worksheets/sheet1.xml\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet\"/></Relationships>";

	 static String xl_sharedstrings_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><sst count=\"0\" uniqueCount=\"0\" xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"/>"; 

	 static String xl_styles_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><styleSheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"><numFmts count=\"0\"/><fonts count=\"1\"><font><sz val=\"11.0\"/><color indexed=\"8\"/><name val=\"Calibri\"/><family val=\"2\"/><scheme val=\"minor\"/></font></fonts><fills count=\"2\"><fill><patternFill patternType=\"none\"/></fill><fill><patternFill patternType=\"darkGray\"/></fill></fills><borders count=\"1\"><border><left/><right/><top/><bottom/><diagonal/></border></borders><cellStyleXfs count=\"1\"><xf numFmtId=\"0\" fontId=\"0\" fillId=\"0\" borderId=\"0\"/></cellStyleXfs><cellXfs count=\"1\"><xf numFmtId=\"0\" fontId=\"0\" fillId=\"0\" borderId=\"0\" xfId=\"0\"/></cellXfs></styleSheet>";

	 static String xl_workbook_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><workbook xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"><workbookPr date1904=\"false\"/><bookViews><workbookView activeTab=\"0\"/></bookViews><sheets><sheet name=\"" + "Sheet1" + "\" r:id=\"rId3\" sheetId=\"1\"/></sheets></workbook>";

	 static String xl_worksheets_sheet1_xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\"><dimension ref=\"A1\"/><sheetViews><sheetView workbookViewId=\"0\" tabSelected=\"true\"/></sheetViews><sheetFormatPr defaultRowHeight=\"15.0\"/><sheetData/><pageMargins bottom=\"0.75\" footer=\"0.3\" header=\"0.3\" left=\"0.7\" right=\"0.7\" top=\"0.75\"/></worksheet>";

	 public static void main(String[] args) throws Exception {

	  // result goes into a ByteArrayOutputStream
	  ByteArrayOutputStream resultout = new ByteArrayOutputStream();

	  // needed objects
	  ZipEntry zipentry = null;
	  byte[] data = null;

	  // create ZipOutputStream
	  ZipOutputStream zipout = new ZipOutputStream(resultout);

	  // create the static parts of the XLSX ZIP file:

	  zipentry = new ZipEntry("[Content_Types].xml");
	  zipout.putNextEntry(zipentry);
	  data = content_types_xml.getBytes();
	  zipout.write(data, 0, data.length);
	  zipout.closeEntry();

	  zipentry = new ZipEntry("docProps/app.xml");
	  zipout.putNextEntry(zipentry);
	  data = docProps_app_xml.getBytes();
	  zipout.write(data, 0, data.length);
	  zipout.closeEntry();

	  zipentry = new ZipEntry("docProps/core.xml");
	  zipout.putNextEntry(zipentry);
	  data = docProps_core_xml.getBytes();
	  zipout.write(data, 0, data.length);
	  zipout.closeEntry();

	  zipentry = new ZipEntry("_rels/.rels");
	  zipout.putNextEntry(zipentry);
	  data = _rels_rels_xml.getBytes();
	  zipout.write(data, 0, data.length);
	  zipout.closeEntry();

	  zipentry = new ZipEntry("xl/_rels/workbook.xml.rels");
	  zipout.putNextEntry(zipentry);
	  data = xl_rels_workbook_xml_rels_xml.getBytes();
	  zipout.write(data, 0, data.length);
	  zipout.closeEntry();

	  zipentry = new ZipEntry("xl/sharedStrings.xml");
	  zipout.putNextEntry(zipentry);
	  data = xl_sharedstrings_xml.getBytes();
	  zipout.write(data, 0, data.length);
	  zipout.closeEntry();

	  zipentry = new ZipEntry("xl/styles.xml");
	  zipout.putNextEntry(zipentry);
	  data = xl_styles_xml.getBytes();
	  zipout.write(data, 0, data.length);
	  zipout.closeEntry();

	  zipentry = new ZipEntry("xl/workbook.xml");
	  zipout.putNextEntry(zipentry);
	  data = xl_workbook_xml.getBytes();
	  zipout.write(data, 0, data.length);
	  zipout.closeEntry();

	  // preparing the sheet data:

	  Object[][] sheetData = new Object[][] {
	   {"Text", "Value", "Formula"},
	   {"Text1", 1.23456, "=SIN(B2)"},
	   {"Text2", 2.34567, "=SQRT(B3)"},
	   {"Text3", 123.456, "=B4/10"}
	  };
	  String sheetdata = "<sheetData>";
	  int r = 0;
	  char c = 'A'; --c;
	  for (Object[] rowData : sheetData) {
	   sheetdata += "<row r=\"" + ++r + "\">";
	   c = 'A'; --c;
	   for (Object cellData : rowData) {
	    sheetdata += "<c r=\"" + Character.toString(++c) + r + "\"";
	    if (cellData instanceof String && ((String)cellData).startsWith("=")) {
	     sheetdata += "><f>" + ((String)cellData).replace("=", "") + "</f></c>";
	    } else if (cellData instanceof String) {
	     sheetdata += " t=\"inlineStr\"><is><t>" + ((String)cellData) + "</t></is></c>";
	    } else if (cellData instanceof Double) {
	     sheetdata += "><v>" + ((Double)cellData) + "</v></c>";
	    }
	   }
	   sheetdata += "</row>";
	  }
	  sheetdata += "</sheetData>";

	  // get the static sheet xml into a buffer for further processing
	  StringBuffer xl_worksheets_sheet1_xml_buffer = new StringBuffer(xl_worksheets_sheet1_xml);

	  // get position of the <dimension ref=\"A1\"/> in the static xl_worksheets_sheet1_xml
	  int dimensionstart = xl_worksheets_sheet1_xml_buffer.indexOf("<dimension ref=\"A1\"/>");
	  // replace the <dimension ref=\"A1\"/> with the new dimension
	  xl_worksheets_sheet1_xml_buffer = xl_worksheets_sheet1_xml_buffer.replace(
	   dimensionstart, 
	   dimensionstart + "<dimension ref=\"A1\"/>".length(), 
	   "<dimension ref=\"A1:" + Character.toString(c) + r + "\"/>");

	  // get position of the <sheetData/> in the static xl_worksheets_sheet1_xml
	  int sheetdatastart = xl_worksheets_sheet1_xml_buffer.indexOf("<sheetData/>");
	  // replace the <sheetData/> with the prepared sheet date string
	  xl_worksheets_sheet1_xml_buffer = xl_worksheets_sheet1_xml_buffer.replace(
	   sheetdatastart, 
	   sheetdatastart + "<sheetData/>".length(), 
	   sheetdata);

	  // create the xl/worksheets/sheet1.xml
	  zipentry = new ZipEntry("xl/worksheets/sheet1.xml");
	  zipout.putNextEntry(zipentry);
	  data = xl_worksheets_sheet1_xml_buffer.toString().getBytes();
	  zipout.write(data, 0, data.length);
	  zipout.closeEntry();

	  zipout.finish();

	  // now ByteArrayOutputStream resultout contains the XLSX file data

	  // writing this data into a file
	  try (java.io.FileOutputStream fileout = new java.io.FileOutputStream("c:\\Entorno de Desarrollo\\Out\\test.xlsx")) {
	   resultout.writeTo(fileout);
	   resultout.close();
	  }

	 }

}
