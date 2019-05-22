package com.test.iview.dayin.view.word;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFPicture;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.util.Xml;

public class FR {
    private String nameStr;
    public Range range = null;
    public HWPFDocument hwpf = null;
    public String htmlPath;
    public String picturePath;
    public List pictures;
    public TableIterator tableIterator;
    public int presentPicture = 0;
    public int screenWidth;
    public FileOutputStream output;
    public File myFile;
    StringBuffer lsb = new StringBuffer();
    String returnPath = "";
    static final int BUFFER = 2048;

    public FR(String namepath) {
        // this.screenWidth =
        // this.getWindowManager().getDefaultDisplay().getWidth() -
        // 10;//?????????????-10
        this.nameStr = namepath;
        this.htmlPath = namepath;
        read();
    }

    public void read() {

        if (this.nameStr.endsWith(".doc")) {
            this.getRange();
            this.makeFile();
            this.readDOC();
            returnPath = "file:///" + this.htmlPath;
            // this.view.loadUrl("file:///" + this.htmlPath);
            System.out.println("htmlPath" + this.htmlPath);
        }
        if (this.nameStr.endsWith(".docx")) {
            this.makeFile();
            this.readDOCX();
            returnPath = "file:///" + this.htmlPath;
            // this.view.loadUrl("file:///" + this.htmlPath);
            System.out.println("htmlPath" + this.htmlPath);
        }
        if (this.nameStr.endsWith(".xls")) {

            try {
                this.makeFile();
                this.readXLS();
                returnPath = "file:///" + this.htmlPath;
                // this.view.loadUrl("file:///" + this.htmlPath);
                System.out.println("htmlPath" + this.htmlPath);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        if (this.nameStr.endsWith(".xlsx")) {
            this.makeFile();
            try {
                this.readXLSX();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            returnPath = "file:///" + this.htmlPath;
            // this.view.loadUrl("file:///" + this.htmlPath);
            System.out.println("htmlPath" + this.htmlPath);
        }

        if (this.nameStr.endsWith(".pptx")) {
            this.makeFile();
            this.readPPTX();
            returnPath = "file:///" + this.htmlPath;
            System.out.println("pptxhtmlPath=====" + this.htmlPath);
        }

    }

    /* ???word?е?????д??sdcard???.html????? */
    public void readDOC() {

        try {
            myFile = new File(htmlPath);
            output = new FileOutputStream(myFile);
            String head = "<html><meta charset=\"utf-8\"><body>";
            String tagBegin = "<p>";
            String tagEnd = "</p>";
            output.write(head.getBytes());
            int numParagraphs = range.numParagraphs();// ?????????е??????
            for (int i = 0; i < numParagraphs; i++) { // ??????????
                Paragraph p = range.getParagraph(i); // ???????е?????????
                if (p.isInTable()) {
                    int temp = i;
                    if (tableIterator.hasNext()) {
                        String tableBegin = "<table style=\"border-collapse:collapse\" border=1 bordercolor=\"black\">";
                        String tableEnd = "</table>";
                        String rowBegin = "<tr>";
                        String rowEnd = "</tr>";
                        String colBegin = "<td>";
                        String colEnd = "</td>";
                        Table table = tableIterator.next();
                        output.write(tableBegin.getBytes());
                        int rows = table.numRows();
                        for (int r = 0; r < rows; r++) {
                            output.write(rowBegin.getBytes());
                            TableRow row = table.getRow(r);
                            int cols = row.numCells();
                            int rowNumParagraphs = row.numParagraphs();
                            int colsNumParagraphs = 0;
                            for (int c = 0; c < cols; c++) {
                                output.write(colBegin.getBytes());
                                TableCell cell = row.getCell(c);
                                int max = temp + cell.numParagraphs();
                                colsNumParagraphs = colsNumParagraphs
                                        + cell.numParagraphs();
                                for (int cp = temp; cp < max; cp++) {
                                    Paragraph p1 = range.getParagraph(cp);
                                    output.write(tagBegin.getBytes());
                                    writeParagraphContent(p1);
                                    output.write(tagEnd.getBytes());
                                    temp++;
                                }
                                output.write(colEnd.getBytes());
                            }
                            int max1 = temp + rowNumParagraphs;
                            for (int m = temp + colsNumParagraphs; m < max1; m++) {
                                temp++;
                            }
                            output.write(rowEnd.getBytes());
                        }
                        output.write(tableEnd.getBytes());
                    }
                    i = temp;
                } else {
                    output.write(tagBegin.getBytes());
                    writeParagraphContent(p);
                    output.write(tagEnd.getBytes());
                }
            }
            String end = "</body></html>";
            output.write(end.getBytes());
            output.close();
        } catch (Exception e) {

            System.out.println("readAndWrite Exception:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void readDOCX() {
        String river = "";
        try {
            this.myFile = new File(this.htmlPath);// new???File,·???html???
            this.output = new FileOutputStream(this.myFile);// new?????,????html???
            String head = "<!DOCTYPE><html><meta charset=\"utf-8\"><body>";// ????????,???????????utf-8,????????????
            String end = "</body></html>";
            String tagBegin = "<p>";// ???俪?,??????
            String tagEnd = "</p>";// ???????
            String tableBegin = "<table style=\"border-collapse:collapse\" border=1 bordercolor=\"black\">";
            String tableEnd = "</table>";
            String rowBegin = "<tr>";
            String rowEnd = "</tr>";
            String colBegin = "<td>";
            String colEnd = "</td>";
            String style = "style=\"";
            this.output.write(head.getBytes());// д?????
            ZipFile xlsxFile = new ZipFile(new File(this.nameStr));
            ZipEntry sharedStringXML = xlsxFile.getEntry("word/document.xml");
            InputStream inputStream = xlsxFile.getInputStream(sharedStringXML);
            XmlPullParser xmlParser = Xml.newPullParser();
            xmlParser.setInput(inputStream, "utf-8");
            int evtType = xmlParser.getEventType();
            boolean isTable = false; // ???? ??????? ?? ?? ??
            boolean isSize = false; // ??С??
            boolean isColor = false; // ?????
            boolean isCenter = false; // ??????
            boolean isRight = false; // ??????
            boolean isItalic = false; // ??б??
            boolean isUnderline = false; // ???????
            boolean isBold = false; // ???
            boolean isR = false; // ?????r??
            boolean isStyle = false;
            int pictureIndex = 1; // docx ??????е????? iamge1 ??? ??????????1???
            while (evtType != XmlPullParser.END_DOCUMENT) {
                switch (evtType) {

                // ??????
                case XmlPullParser.START_TAG:
                    String tag = xmlParser.getName();

                    if (tag.equalsIgnoreCase("r")) {
                        isR = true;
                    }
                    if (tag.equalsIgnoreCase("u")) { // ?ж??????
                        isUnderline = true;
                    }
                    if (tag.equalsIgnoreCase("jc")) { // ?ж?????
                        String align = xmlParser.getAttributeValue(0);
                        if (align.equals("center")) {
                            this.output.write("<center>".getBytes());
                            isCenter = true;
                        }
                        if (align.equals("right")) {
                            this.output.write("<div align=\"right\">"
                                    .getBytes());
                            isRight = true;
                        }
                    }

                    if (tag.equalsIgnoreCase("color")) { // ?ж????

                        String color = xmlParser.getAttributeValue(0);

                        this.output
                                .write(("<span style=\"color:" + color + ";\">")
                                        .getBytes());
                        isColor = true;
                    }
                    if (tag.equalsIgnoreCase("sz")) { // ?ж??С
                        if (isR == true) {
                            int size = decideSize(Integer.valueOf(xmlParser
                                    .getAttributeValue(0)));
                            this.output.write(("<font size=" + size + ">")
                                    .getBytes());
                            isSize = true;
                        }
                    }
                    // ??????????
                    if (tag.equalsIgnoreCase("tbl")) { // ???tbl ????
                        this.output.write(tableBegin.getBytes());
                        isTable = true;
                    }
                    if (tag.equalsIgnoreCase("tr")) { // ??
                        this.output.write(rowBegin.getBytes());
                    }
                    if (tag.equalsIgnoreCase("tc")) { // ??
                        this.output.write(colBegin.getBytes());
                    }

                    if (tag.equalsIgnoreCase("pic")) { // ?????? pic ??
                        String entryName_jpeg = "word/media/image"
                                + pictureIndex + ".jpeg";
                        String entryName_png = "word/media/image"
                                + pictureIndex + ".png";
                        String entryName_gif = "word/media/image"
                                + pictureIndex + ".gif";
                        String entryName_wmf = "word/media/image"
                                + pictureIndex + ".wmf";
                        ZipEntry sharePicture = null;
                        InputStream pictIS = null;
                        sharePicture = xlsxFile.getEntry(entryName_jpeg);
                        // ???????docx???? ??????????
                        if (sharePicture == null) {
                            sharePicture = xlsxFile.getEntry(entryName_png);
                        }
                        if (sharePicture == null) {
                            sharePicture = xlsxFile.getEntry(entryName_gif);
                        }
                        if (sharePicture == null) {
                            sharePicture = xlsxFile.getEntry(entryName_wmf);
                        }

                        if (sharePicture != null) {
                            pictIS = xlsxFile.getInputStream(sharePicture);
                            ByteArrayOutputStream pOut = new ByteArrayOutputStream();
                            byte[] bt = null;
                            byte[] b = new byte[1000];
                            int len = 0;
                            while ((len = pictIS.read(b)) != -1) {
                                pOut.write(b, 0, len);
                            }
                            pictIS.close();
                            pOut.close();
                            bt = pOut.toByteArray();
                            Log.i("byteArray", "" + bt);
                            if (pictIS != null)
                                pictIS.close();
                            if (pOut != null)
                                pOut.close();
                            writeDOCXPicture(bt);
                        }

                        pictureIndex++; // ??????? ????+1
                    }

                    if (tag.equalsIgnoreCase("b")) { // ????????
                        isBold = true;
                    }
                    if (tag.equalsIgnoreCase("p")) {// ??? p ???
                        if (isTable == false) { // ????????? ??????
                            this.output.write(tagBegin.getBytes());
                        }
                    }
                    if (tag.equalsIgnoreCase("i")) { // б??
                        isItalic = true;
                    }
                    // ???? ???
                    if (tag.equalsIgnoreCase("t")) {
                        if (isBold == true) { // ???
                            this.output.write("<b>".getBytes());
                        }
                        if (isUnderline == true) { // ??????????,????<u>
                            this.output.write("<u>".getBytes());
                        }
                        if (isItalic == true) { // ???б????,????<i>
                            output.write("<i>".getBytes());
                        }
                        river = xmlParser.nextText();
                        this.output.write(river.getBytes()); // д?????
                        if (isItalic == true) { // ???б????,??????????,????</i>,????б????=false
                            this.output.write("</i>".getBytes());
                            isItalic = false;
                        }
                        if (isUnderline == true) {// ??????????,??????????,????</u>,???????????=false
                            this.output.write("</u>".getBytes());
                            isUnderline = false;
                        }
                        if (isBold == true) { // ???
                            this.output.write("</b>".getBytes());
                            isBold = false;
                        }
                        if (isSize == true) { // ?????С????,??????????
                            this.output.write("</font>".getBytes());
                            isSize = false;
                        }
                        if (isColor == true) { // ?????????????,??????????
                            this.output.write("</span>".getBytes());
                            isColor = false;
                        }
                        if (isCenter == true) { // ???????,??????????
                            this.output.write("</center>".getBytes());
                            isCenter = false;
                        }
                        if (isRight == true) { // ??????????<right></right>,???div??????????,??????
                            this.output.write("</div>".getBytes());
                            isRight = false;
                        }
                    }
                    break;
                // ???????
                case XmlPullParser.END_TAG:
                    String tag2 = xmlParser.getName();
                    if (tag2.equalsIgnoreCase("tbl")) { // ?????????,????????
                        this.output.write(tableEnd.getBytes());
                        isTable = false;
                    }
                    if (tag2.equalsIgnoreCase("tr")) { // ?н???
                        this.output.write(rowEnd.getBytes());
                    }
                    if (tag2.equalsIgnoreCase("tc")) { // ?н???
                        this.output.write(colEnd.getBytes());
                    }
                    if (tag2.equalsIgnoreCase("p")) { // p????,????????о?????
                        if (isTable == false) {
                            this.output.write(tagEnd.getBytes());
                        }
                    }
                    if (tag2.equalsIgnoreCase("r")) {
                        isR = false;
                    }
                    break;
                default:
                    break;
                }
                evtType = xmlParser.next();
            }
            this.output.write(end.getBytes());
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        if (river == null) {
            river = "???????????????";
        }
    }

    public StringBuffer readXLS() throws Exception {

        myFile = new File(htmlPath);
        output = new FileOutputStream(myFile);
        lsb.append("<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>");
        lsb.append("<head><meta http-equiv=Content-Type content='text/html; charset=utf-8'><meta name=ProgId content=Excel.Sheet>");
        HSSFSheet sheet = null;

        String excelFileName = nameStr;
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
                    excelFileName)); // ??????Excel

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                sheet = workbook.getSheetAt(sheetIndex);// ?????е?sheet
                String sheetName = workbook.getSheetName(sheetIndex); // sheetName
                if (workbook.getSheetAt(sheetIndex) != null) {
                    sheet = workbook.getSheetAt(sheetIndex);// ??ò????????sheet
                    if (sheet != null) {
                        int firstRowNum = sheet.getFirstRowNum(); // ?????
                        int lastRowNum = sheet.getLastRowNum(); // ??????
                        // ????Table
                        lsb.append("<table width=\"100%\" style=\"border:1px solid #000;border-width:1px 0 0 1px;margin:2px 0 2px 0;border-collapse:collapse;\">");
                        for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                            if (sheet.getRow(rowNum) != null) {// ????в?????
                                HSSFRow row = sheet.getRow(rowNum);
                                short firstCellNum = row.getFirstCellNum(); // ???е??????????
                                short lastCellNum = row.getLastCellNum(); // ???е????????????
                                int height = (int) (row.getHeight() / 15.625); // ?е???
                                lsb.append("<tr height=\""
                                        + height
                                        + "\" style=\"border:1px solid #000;border-width:0 1px 1px 0;margin:2px 0 2px 0;\">");
                                for (short cellNum = firstCellNum; cellNum <= lastCellNum; cellNum++) { // ??????е??????????
                                    HSSFCell cell = row.getCell(cellNum);
                                    if (cell != null) {
                                        if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                            continue;
                                        } else {
                                            StringBuffer tdStyle = new StringBuffer(
                                                    "<td style=\"border:1px solid #000; border-width:0 1px 1px 0;margin:2px 0 2px 0; ");
                                            HSSFCellStyle cellStyle = cell
                                                    .getCellStyle();
                                            HSSFPalette palette = workbook
                                                    .getCustomPalette(); // ??HSSFPalette???????????????????
                                            HSSFColor hColor = palette
                                                    .getColor(cellStyle
                                                            .getFillForegroundColor());
                                            HSSFColor hColor2 = palette
                                                    .getColor(cellStyle
                                                            .getFont(workbook)
                                                            .getColor());

                                            String bgColor = convertToStardColor(hColor);// ???????
                                            short boldWeight = cellStyle
                                                    .getFont(workbook)
                                                    .getBoldweight(); // ??????
                                            short fontHeight = (short) (cellStyle
                                                    .getFont(workbook)
                                                    .getFontHeight() / 2); // ?????С
                                            String fontColor = convertToStardColor(hColor2); // ???????
                                            if (bgColor != null
                                                    && !"".equals(bgColor
                                                            .trim())) {
                                                tdStyle.append(" background-color:"
                                                        + bgColor + "; ");
                                            }
                                            if (fontColor != null
                                                    && !"".equals(fontColor
                                                            .trim())) {
                                                tdStyle.append(" color:"
                                                        + fontColor + "; ");
                                            }
                                            tdStyle.append(" font-weight:"
                                                    + boldWeight + "; ");
                                            tdStyle.append(" font-size: "
                                                    + fontHeight + "%;");
                                            lsb.append(tdStyle + "\"");

                                            int width = (int) (sheet
                                                    .getColumnWidth(cellNum) / 35.7); //
                                            int cellReginCol = getMergerCellRegionCol(
                                                    sheet, rowNum, cellNum); // ??????У?solspan??
                                            int cellReginRow = getMergerCellRegionRow(
                                                    sheet, rowNum, cellNum);// ??????У?rowspan??
                                            String align = convertAlignToHtml(cellStyle
                                                    .getAlignment()); //
                                            String vAlign = convertVerticalAlignToHtml(cellStyle
                                                    .getVerticalAlignment());

                                            lsb.append(" align=\"" + align
                                                    + "\" valign=\"" + vAlign
                                                    + "\" width=\"" + width
                                                    + "\" ");

                                            lsb.append(" colspan=\""
                                                    + cellReginCol
                                                    + "\" rowspan=\""
                                                    + cellReginRow + "\"");
                                            lsb.append(">" + getCellValue(cell)
                                                    + "</td>");
                                        }
                                    }
                                }
                                lsb.append("</tr>");
                            }
                        }
                    }

                }

            }
            output.write(lsb.toString().getBytes());
        } catch (FileNotFoundException e) {
            throw new Exception("??? " + excelFileName + " ??????!");
        } catch (IOException e) {
            throw new Exception("??? " + excelFileName + " ???????("
                    + e.getMessage() + ")!");
        }
        return lsb;
    }

    public void readXLSX() throws FileNotFoundException{
        myFile = new File(htmlPath);
        output = new FileOutputStream(myFile);
        lsb.append("<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>");
        lsb.append("<head><meta http-equiv=Content-Type content='text/html; charset=utf-8'><meta name=ProgId content=Excel.Sheet>");
        XSSFSheet sheet = null;

        String excelFileName = nameStr;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(
                    excelFileName)); // ??????Excel

            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                sheet = workbook.getSheetAt(sheetIndex);// ?????е?sheet
                Log.e("=================", sheet.getSheetName());
            }
        }catch(Exception e){
            e.printStackTrace();
            
        }
    }
    
//    public void readXLSX() {
//        try {
//            this.myFile = new File(this.htmlPath);// new???File,·???html???
//            this.output = new FileOutputStream(this.myFile);// new?????,????html???
//            String head = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\"http://www.w3.org/TR/html4/loose.dtd\"><html><meta charset=\"utf-8\"><head></head><body>";// ????????,???????????utf-8,????????????
//            String tableBegin = "<table style=\"border-collapse:collapse\" border=1 bordercolor=\"black\">";
//            String tableEnd = "</table>";
//            String rowBegin = "<tr>";
//            String rowEnd = "</tr>";
//            String colBegin = "<td>";
//            String colEnd = "</td>";
//            String end = "</body></html>";
//            this.output.write(head.getBytes());
//            this.output.write(tableBegin.getBytes());
//            String str = "";
//            String v = null;
//            boolean flat = false;
//            List<String> ls = new ArrayList<String>();
//            try {
//                ZipFile xlsxFile = new ZipFile(new File(this.nameStr));// ???
//                ZipEntry sharedStringXML = xlsxFile
//                        .getEntry("xl/sharedStrings.xml");// ?????????
//                InputStream inputStream = xlsxFile
//                        .getInputStream(sharedStringXML);// ?????? ????????????????
//                XmlPullParser xmlParser = Xml.newPullParser();// new ??????
//                xmlParser.setInput(inputStream, "utf-8");// ?????????????
//                int evtType = xmlParser.getEventType();// ??????????????????
//                while (evtType != XmlPullParser.END_DOCUMENT) {// ????????? ???????
//                    switch (evtType) {
//                    case XmlPullParser.START_TAG: // ??????
//                        String tag = xmlParser.getName();
//                        if (tag.equalsIgnoreCase("t")) {
//                            ls.add(xmlParser.nextText());
//                        }
//                        break;
//                    case XmlPullParser.END_TAG: // ???????
//                        break;
//                    default:
//                        break;
//                    }
//                    evtType = xmlParser.next();
//                }
//                ZipEntry sheetXML = xlsxFile
//                        .getEntry("xl/worksheets/sheet1.xml");
//                InputStream inputStreamsheet = xlsxFile
//                        .getInputStream(sheetXML);
//                XmlPullParser xmlParsersheet = Xml.newPullParser();
//                xmlParsersheet.setInput(inputStreamsheet, "utf-8");
//                int evtTypesheet = xmlParsersheet.getEventType();
//                this.output.write(rowBegin.getBytes());
//                int i = -1;
//                while (evtTypesheet != XmlPullParser.END_DOCUMENT) {
//                    switch (evtTypesheet) {
//                    case XmlPullParser.START_TAG: // ??????
//                        String tag = xmlParsersheet.getName();
//                        if (tag.equalsIgnoreCase("row")) {
//                        } else {
//                            if (tag.equalsIgnoreCase("c")) {
//                                String t = xmlParsersheet.getAttributeValue(
//                                        null, "t");
//                                if (t != null) {
//                                    flat = true;
//                                    System.out.println(flat + "??");
//                                } else {// ???????? ??????n??,????????? ????<td></td> ??????
//                                    this.output.write(colBegin.getBytes());
//                                    this.output.write(colEnd.getBytes());
//                                    System.out.println(flat + "???");
//                                    flat = false;
//                                }
//                            } else {
//                                if (tag.equalsIgnoreCase("v")) {
//                                    v = xmlParsersheet.nextText();
//                                    this.output.write(colBegin.getBytes());
//                                    if (v != null) {
//                                        if (flat) {
//                                            str = ls.get(Integer.parseInt(v));
//                                        } else {
//                                            str = v;
//                                        }
//                                        this.output.write(str.getBytes());
//                                        this.output.write(colEnd.getBytes());
//                                    }
//                                }
//                            }
//                        }
//                        break;
//                    case XmlPullParser.END_TAG:
//                        if (xmlParsersheet.getName().equalsIgnoreCase("row")
//                                && v != null) {
//                            if (i == 1) {
//                                this.output.write(rowEnd.getBytes());
//                                this.output.write(rowBegin.getBytes());
//                                i = 1;
//                            } else {
//                                this.output.write(rowBegin.getBytes());
//                            }
//                        }
//                        break;
//                    }
//                    evtTypesheet = xmlParsersheet.next();
//                }
//                System.out.println(str);
//            } catch (ZipException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (XmlPullParserException e) {
//                e.printStackTrace();
//            }
//            if (str == null) {
//                str = "???????????????";
//            }
//            this.output.write(rowEnd.getBytes());
//            this.output.write(tableEnd.getBytes());
//            this.output.write(end.getBytes());
//        } catch (Exception e) {
//            System.out.println("readAndWrite Exception");
//        }
//    }

    public void readPPTX() {
        List<String> ls = new ArrayList<String>();
        String river = "";
        ZipFile xlsxFile = null;
        try {
            xlsxFile = new ZipFile(new File(this.nameStr));// pptx??????zip??????
        } catch (ZipException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            ZipEntry sharedStringXML = xlsxFile.getEntry("[Content_Types].xml");// ?????????????????
            InputStream inputStream = xlsxFile.getInputStream(sharedStringXML);// ??????????
            XmlPullParser xmlParser = Xml.newPullParser();// ?????pull
            xmlParser.setInput(inputStream, "utf-8");// ???????pull??
            int evtType = xmlParser.getEventType();// ?????????????
            while (evtType != XmlPullParser.END_DOCUMENT) {// ????????
                switch (evtType) {
                case XmlPullParser.START_TAG: // ?ж?????????
                    String tag = xmlParser.getName();// ??????
                    if (tag.equalsIgnoreCase("Override")) {
                        String s = xmlParser
                                .getAttributeValue(null, "PartName");
                        if (s.lastIndexOf("/ppt/slides/slide") == 0) {
                            ls.add(s);
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:// ??????????
                    break;
                default:
                    break;
                }
                evtType = xmlParser.next();// ???????????
            }
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < (ls.size() + 1); i++) {// ??????6?????
            river += "??" + i + "?????????????????????????????????" + "\n";
            try {
                ZipEntry sharedStringXML = xlsxFile.getEntry("ppt/slides/slide"
                        + i + ".xml");// ?????????????????
                InputStream inputStream = xlsxFile
                        .getInputStream(sharedStringXML);// ??????????
                XmlPullParser xmlParser = Xml.newPullParser();// ?????pull
                xmlParser.setInput(inputStream, "utf-8");// ???????pull??
                int evtType = xmlParser.getEventType();// ?????????????
                while (evtType != XmlPullParser.END_DOCUMENT) {// ????????
                    switch (evtType) {
                    case XmlPullParser.START_TAG: // ?ж?????????
                        String tag = xmlParser.getName();// ??????
                        if (tag.equalsIgnoreCase("t")) {
                            river += xmlParser.nextText() + "\n";
                        }
                        break;
                    case XmlPullParser.END_TAG:// ??????????
                        break;
                    default:
                        break;
                    }
                    evtType = xmlParser.next();// ???????????
                }
            } catch (ZipException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
        }
        if (river == null) {
            river = "???????????????";
        }
    }

    /**
     * ?????????
     * 
     * @param cell
     * @return
     * @throws IOException
     */
    private static Object getCellValue(HSSFCell cell) throws IOException {
        Object value = "";
        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            value = cell.getRichStringCellValue().toString();
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                Date date = (Date) cell.getDateCellValue();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                value = sdf.format(date);
            } else {
                double value_temp = (double) cell.getNumericCellValue();
                BigDecimal bd = new BigDecimal(value_temp);
                BigDecimal bd1 = bd.setScale(3, bd.ROUND_HALF_UP);
                value = bd1.doubleValue();

                DecimalFormat format = new DecimalFormat("#0.###");
                value = format.format(cell.getNumericCellValue());

            }
        }
        if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
            value = "";
        }
        return value;
    }

    /**
     * ?ж?????????????????Χ????????????????????????
     * 
     * @param sheet
     *            ??????
     * @param cellRow
     *            ???ж????????к?
     * @param cellCol
     *            ???ж????????к?
     * @return
     * @throws IOException
     */
    private static int getMergerCellRegionCol(HSSFSheet sheet, int cellRow,
            int cellCol) throws IOException {
        int retVal = 0;
        int sheetMergerCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            CellRangeAddress cra = (CellRangeAddress) sheet.getMergedRegion(i);
            int firstRow = cra.getFirstRow(); // ????????CELL?????
            int firstCol = cra.getFirstColumn(); // ????????CELL?????
            int lastRow = cra.getLastRow(); // ????????CELL??????
            int lastCol = cra.getLastColumn(); // ????????CELL??????
            if (cellRow >= firstRow && cellRow <= lastRow) { // ?ж??????????????????????
                if (cellCol >= firstCol && cellCol <= lastCol) {
                    retVal = lastCol - firstCol + 1; // ????????????
                    break;
                }
            }
        }
        return retVal;
    }

    /**
     * ?ж??????????????????????????????????????
     * 
     * @param sheet
     *            ??
     * @param cellRow
     *            ???ж????????к?
     * @param cellCol
     *            ???ж????????к?
     * @return
     * @throws IOException
     */
    private static int getMergerCellRegionRow(HSSFSheet sheet, int cellRow,
            int cellCol) throws IOException {
        int retVal = 0;
        int sheetMergerCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            CellRangeAddress cra = (CellRangeAddress) sheet.getMergedRegion(i);
            int firstRow = cra.getFirstRow(); // ????????CELL?????
            int firstCol = cra.getFirstColumn(); // ????????CELL?????
            int lastRow = cra.getLastRow(); // ????????CELL??????
            int lastCol = cra.getLastColumn(); // ????????CELL??????
            if (cellRow >= firstRow && cellRow <= lastRow) { // ?ж??????????????????????
                if (cellCol >= firstCol && cellCol <= lastCol) {
                    retVal = lastRow - firstRow + 1; // ????????????
                    break;
                }
            }
        }
        return 0;
    }

    /**
     * ??????????
     * 
     * @param hc
     * @return
     */
    private String convertToStardColor(HSSFColor hc) {
        StringBuffer sb = new StringBuffer("");
        if (hc != null) {
            int a = HSSFColor.AUTOMATIC.index;
            int b = hc.getIndex();
            if (a == b) {
                return null;
            }
            sb.append("#");
            for (int i = 0; i < hc.getTriplet().length; i++) {
                String str;
                String str_tmp = Integer.toHexString(hc.getTriplet()[i]);
                if (str_tmp != null && str_tmp.length() < 2) {
                    str = "0" + str_tmp;
                } else {
                    str = str_tmp;
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    /**
     * ?????С?????
     * 
     * @param alignment
     * @return
     */
    private String convertAlignToHtml(short alignment) {
        String align = "left";
        switch (alignment) {
        case HSSFCellStyle.ALIGN_LEFT:
            align = "left";
            break;
        case HSSFCellStyle.ALIGN_CENTER:
            align = "center";
            break;
        case HSSFCellStyle.ALIGN_RIGHT:
            align = "right";
            break;
        default:
            break;
        }
        return align;
    }

    /**
     * ??????????
     * 
     * @param verticalAlignment
     * @return
     */
    private String convertVerticalAlignToHtml(short verticalAlignment) {
        String valign = "middle";
        switch (verticalAlignment) {
        case HSSFCellStyle.VERTICAL_BOTTOM:
            valign = "bottom";
            break;
        case HSSFCellStyle.VERTICAL_CENTER:
            valign = "center";
            break;
        case HSSFCellStyle.VERTICAL_TOP:
            valign = "top";
            break;
        default:
            break;
        }
        return valign;
    }

    public void makeFile() {
        String sdStateString = android.os.Environment.getExternalStorageState();// ??????洢??
        if (sdStateString.equals(android.os.Environment.MEDIA_MOUNTED)) {// ???sd??????,????,??尲???
            try {
                File sdFile = android.os.Environment
                        .getExternalStorageDirectory();// ???????豸???????
                String path = sdFile.getAbsolutePath() + File.separator
                        + "gdemm" + File.separator + "aaa";// ???sd??(????豸)?????·??+"/"+xiao
                File dirFile = new File(path);// ???xiao????е??
                if (!dirFile.exists()) {// ?????????
                    dirFile.mkdir();// ??????
                }
                File myFile = new File(path + File.separator + "file.html");// ???my.html????
                if (!myFile.exists()) {// ?????????
                    myFile.createNewFile();// ???????
                }
                this.htmlPath = myFile.getAbsolutePath();// ????·??
            } catch (Exception e) {
            }
        }
    }

    /* ??????sdcard??????? */
    public void makePictureFile() {
        String sdString = android.os.Environment.getExternalStorageState();// ??????洢??
        if (sdString.equals(android.os.Environment.MEDIA_MOUNTED)) {// ???sd??????,????
            try {
                File picFile = android.os.Environment
                        .getExternalStorageDirectory();// ???sd????
                String picPath = picFile.getAbsolutePath() + File.separator
                        + "gdemm" + File.separator + "aaa";// ??????,?????н???
                File picDirFile = new File(picPath);
                if (!picDirFile.exists()) {
                    picDirFile.mkdir();
                }
                File pictureFile = new File(picPath + File.separator
                        + presentPicture + ".jpg");// ????jpg???,??????html???
                if (!pictureFile.exists()) {
                    pictureFile.createNewFile();
                }
                this.picturePath = pictureFile.getAbsolutePath();// ???jpg???????·??
            } catch (Exception e) {
                System.out.println("PictureFile Catch Exception");
            }
        }
    }

    public void writePicture() {
        Picture picture = (Picture) pictures.get(presentPicture);

        byte[] pictureBytes = picture.getContent();

        Bitmap bitmap = BitmapFactory.decodeByteArray(pictureBytes, 0,
                pictureBytes.length);

        makePictureFile();
        presentPicture++;

        File myPicture = new File(picturePath);

        try {

            FileOutputStream outputPicture = new FileOutputStream(myPicture);

            outputPicture.write(pictureBytes);

            outputPicture.close();
        } catch (Exception e) {
            System.out.println("outputPicture Exception");
        }

        String imageString = "<img src=\"" + picturePath + "\"";
        imageString = imageString + ">";

        try {
            output.write(imageString.getBytes());
        } catch (Exception e) {
            System.out.println("output Exception");
        }
    }

    public int decideSize(int size) {

        if (size >= 1 && size <= 8) {
            return 1;
        }
        if (size >= 9 && size <= 11) {
            return 2;
        }
        if (size >= 12 && size <= 14) {
            return 3;
        }
        if (size >= 15 && size <= 19) {
            return 4;
        }
        if (size >= 20 && size <= 29) {
            return 5;
        }
        if (size >= 30 && size <= 39) {
            return 6;
        }
        if (size >= 40) {
            return 7;
        }
        return 3;
    }

    private String decideColor(int a) {
        int color = a;
        switch (color) {
        case 1:
            return "#000000";
        case 2:
            return "#0000FF";
        case 3:
        case 4:
            return "#00FF00";
        case 5:
        case 6:
            return "#FF0000";
        case 7:
            return "#FFFF00";
        case 8:
            return "#FFFFFF";
        case 9:
            return "#CCCCCC";
        case 10:
        case 11:
            return "#00FF00";
        case 12:
            return "#080808";
        case 13:
        case 14:
            return "#FFFF00";
        case 15:
            return "#CCCCCC";
        case 16:
            return "#080808";
        default:
            return "#000000";
        }
    }

    private void getRange() {
        FileInputStream in = null;
        POIFSFileSystem pfs = null;

        try {
            in = new FileInputStream(nameStr);
            pfs = new POIFSFileSystem(in);
            hwpf = new HWPFDocument(pfs);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        range = hwpf.getRange();

        pictures = hwpf.getPicturesTable().getAllPictures();

        tableIterator = new TableIterator(range);

    }

    public void writeDOCXPicture(byte[] pictureBytes) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(pictureBytes, 0,
                pictureBytes.length);
        makePictureFile();
        this.presentPicture++;
        File myPicture = new File(this.picturePath);
        try {
            FileOutputStream outputPicture = new FileOutputStream(myPicture);
            outputPicture.write(pictureBytes);
            outputPicture.close();
        } catch (Exception e) {
            System.out.println("outputPicture Exception");
        }
        String imageString = "<img src=\"" + this.picturePath + "\"";

        imageString = imageString + ">";
        try {
            this.output.write(imageString.getBytes());
        } catch (Exception e) {
            System.out.println("output Exception");
        }
    }

    public void writeParagraphContent(Paragraph paragraph) {
        Paragraph p = paragraph;
        int pnumCharacterRuns = p.numCharacterRuns();

        for (int j = 0; j < pnumCharacterRuns; j++) {

            CharacterRun run = p.getCharacterRun(j);

            if (run.getPicOffset() == 0 || run.getPicOffset() >= 1000) {
                if (presentPicture < pictures.size()) {
                    writePicture();
                }
            } else {
                try {
                    String text = run.text();
                    if (text.length() >= 2 && pnumCharacterRuns < 2) {
                        output.write(text.getBytes());
                    } else {
                        int size = run.getFontSize();
                        int color = run.getColor();
                        String fontSizeBegin = "<font size=\""
                                + decideSize(size) + "\">";
                        String fontColorBegin = "<font color=\""
                                + decideColor(color) + "\">";
                        String fontEnd = "</font>";
                        String boldBegin = "<b>";
                        String boldEnd = "</b>";
                        String islaBegin = "<i>";
                        String islaEnd = "</i>";

                        output.write(fontSizeBegin.getBytes());
                        output.write(fontColorBegin.getBytes());

                        if (run.isBold()) {
                            output.write(boldBegin.getBytes());
                        }
                        if (run.isItalic()) {
                            output.write(islaBegin.getBytes());
                        }

                        output.write(text.getBytes());

                        if (run.isBold()) {
                            output.write(boldEnd.getBytes());
                        }
                        if (run.isItalic()) {
                            output.write(islaEnd.getBytes());
                        }
                        output.write(fontEnd.getBytes());
                        output.write(fontEnd.getBytes());
                    }
                } catch (Exception e) {
                    System.out.println("Write File Exception");
                }
            }
        }
    }

    private void readXLSaa() throws Exception {
        List<PictureData> picInfos = new ArrayList<PictureData>();
        myFile = new File(htmlPath);
        output = new FileOutputStream(myFile);
        lsb.append("<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>");
        lsb.append("<head><meta http-equiv=Content-Type content='text/html; charset=utf-8'><meta name=ProgId content=Excel.Sheet>");
        HSSFSheet sheet = null;
        String excelFileName = nameStr;
        try {
            FileInputStream fis = new FileInputStream(excelFileName);
            HSSFWorkbook workbook = (HSSFWorkbook) WorkbookFactory.create(fis); // ??????Excel
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                if (workbook.getSheetAt(sheetIndex) != null) {
                    sheet = workbook.getSheetAt(sheetIndex);// ??ò????????sheet
                    if (picInfos.size() > 0 && picInfos != null) {
                        picInfos.clear();
                    }
                    if (sheet.getDrawingPatriarch() != null) {
                        List<HSSFShape> shapes = sheet.getDrawingPatriarch()
                                .getChildren();
                        for (HSSFShape shape : shapes) {
                            HSSFClientAnchor anchor = (HSSFClientAnchor) shape
                                    .getAnchor();
                            if (shape instanceof HSSFPicture) {
                                HSSFPicture pic = (HSSFPicture) shape;
                                pic.getPictureData();
                                PictureData info = new PictureData(workbook, sheet, null, null);

                                int row = anchor.getRow1();
                                int col = anchor.getCol1();
                                info.setColNum(col);
                                info.setRowNum(row);
                                info.setSheetName(sheet.getSheetName());
                                HSSFPictureData picData = pic.getPictureData();
                                try {
                                    info = savePic(info, sheet.getSheetName(),
                                            picData);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                picInfos.add(info);
                            }
                        }

                    }
                    int firstRowNum = sheet.getFirstRowNum(); // ?????
                    int lastRowNum = sheet.getLastRowNum(); // ??????
                    // ????Table
                    lsb.append("<table width=\"100%\" style=\"border:1px solid #000;border-width:1px 0 0 1px;margin:2px 0 2px 0;border-collapse:collapse;\">");
                    for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                        if (sheet.getRow(rowNum) != null) {// ????в?????
                            HSSFRow row = sheet.getRow(rowNum);
                            short firstCellNum = row.getFirstCellNum(); // ???е??????????
                            short lastCellNum = row.getLastCellNum(); // ???е????????????
                            int height = (int) (row.getHeight() / 15.625); // ?е???
                            lsb.append("<tr height=\""
                                    + height
                                    + "\" style=\"border:1px solid #000;border-width:0 1px 1px 0;margin:2px 0 2px 0;\">");
                            for (short cellNum = firstCellNum; cellNum <= lastCellNum; cellNum++) { // ??????е??????????
                                HSSFCell cell = row.getCell(cellNum);
                                StringBuffer tdStyle = new StringBuffer(
                                        "<td style=\"border:1px solid #000; border-width:0 1px 1px 0;margin:2px 0 2px 0; ");
                                if (null != cell) {
                                    HSSFCellStyle cellStyle = cell
                                            .getCellStyle();

                                    short boldWeight = cellStyle.getFont(
                                            workbook).getBoldweight(); // ??????
                                    short fontHeight = (short) (cellStyle
                                            .getFont(workbook).getFontHeight() / 2); // ?????С
                                    tdStyle.append(" font-weight:" + boldWeight
                                            + "; ");
                                    tdStyle.append(" font-size: " + fontHeight
                                            + "%;");
                                    lsb.append(tdStyle + "\"");

                                }
                                boolean flag = false;
                                if (picInfos.size() > 0 && picInfos != null) {
                                    for (PictureData picInfo : picInfos) {
                                        // ??????????????
                                        if (picInfo.getSheetName().equals(
                                                sheet.getSheetName())
                                                && picInfo.getRowNum() == rowNum
                                                && picInfo.getColNum() == cellNum) {
                                            flag = true;
                                            String imagePath = "<img src=\""
                                                    + picInfo.getPicPath()
                                                    + "\"" + "/>";
                                            lsb.append("<td>");
                                            lsb.append(">" + imagePath
                                                    + "</td>");
                                        }
                                    }
                                }
                                if (null != cell && !flag) {
                                    lsb.append(">" + getCellValue(cell)
                                            + "</td>");
                                }
                            }
                            lsb.append("</tr>");
                        }
                    }

                }
            }
            output.write(lsb.toString().getBytes());
            fis.close();
        } catch (FileNotFoundException e) {
            throw new Exception("??? " + excelFileName + " ??????!");
        } catch (IOException e) {
            throw new Exception("??? " + excelFileName + " ???????("
                    + e.getMessage() + ")!");
        }

    }

    private PictureData savePic(PictureData info, String sheetName,
            HSSFPictureData picData) {
        
        return null;
    }

}
