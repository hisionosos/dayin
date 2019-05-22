package com.test.iview.dayin.view.word;

import java.io.File;

import org.apache.poi.ddf.EscherClientAnchorRecord;
import org.apache.poi.hssf.usermodel.HSSFPictureData;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class PictureData {
    private final HSSFWorkbook workbook;
    private final HSSFSheet sheet;
    private final HSSFPictureData pictureData;
    private final EscherClientAnchorRecord clientAnchor;
    int ColNum;
    int RowNum;
    String SheetName;
    String picPath;
    
    public String getSheetName() {
        return SheetName;
    }
    
    public String  getPicPath(){
        File sdFile = android.os.Environment
                .getExternalStorageDirectory();// ��ȡ��չ�豸���ļ�Ŀ¼
        String path = sdFile.getAbsolutePath() + File.separator
                + "gdemm" + File.separator + "aaa";
        return path;
        
    }


    public void setSheetName(String sheetName) {
        SheetName = sheetName;
    }


    public int getRowNum() {
        return RowNum;
    }


    public void setRowNum(int rowNum) {
        RowNum = rowNum;
    }


    public int getColNum() {
        return ColNum;
    }


    public void setColNum(int colNum) {
        ColNum = colNum;
    }


    public PictureData(HSSFWorkbook workbook, HSSFSheet sheet, HSSFPictureData pictureData, EscherClientAnchorRecord clientAnchor) {
        this.workbook = workbook;
        this.sheet = sheet;
        this.pictureData = pictureData;
        this.clientAnchor = clientAnchor;
    }
    

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }
    
    public HSSFSheet getSheet() {
        return sheet;
    }
    
    public EscherClientAnchorRecord getClientAnchor() {
        return clientAnchor;
    }
    
    public HSSFPictureData getPictureData() {
        return pictureData;
    }

    public byte[] getData() {
        return pictureData.getData();
    }

    public String suggestFileExtension() {
        return pictureData.suggestFileExtension();
    }
    
    /**
     * �Ʋ�ͼƬ���������ǵĵ�Ԫ�����ֵ��һ��׼ȷ����ͨ����Ч
     * 
     * @return the row0
     */
    public short getRow0() {
        int row1 = getRow1();
        int row2 = getRow2();
        if (row1 == row2) {
            return (short) row1;
        }
        
        int heights[] = new int[row2-row1+1];
        for (int i = 0; i < heights.length; i++) {
            heights[i] = getRowHeight(row1 + i);
        }
        
        // HSSFClientAnchor �� dx ֻ���� 0-1023 ֮��,dy ֻ���� 0-255 ֮��
        // ��ʾ���λ�õı��ʣ����Ǿ���ֵ
        int dy1 = getDy1() * heights[0] / 255;
        int dy2 = getDy2() * heights[heights.length-1] / 255;
        return (short) (getCenter(heights, dy1, dy2) + row1);
    }
    
    
    private short getRowHeight(int rowIndex) {
        HSSFRow row = sheet.getRow(rowIndex);
        short h = row == null? sheet.getDefaultRowHeight() : row.getHeight();
        return h;
    }
    
    /**
     * �Ʋ�ͼƬ���������ǵĵ�Ԫ�����ֵ��һ��׼ȷ����ͨ����Ч
     * 
     * @return the col0
     */
    public short getCol0() {
        short col1 = getCol1();
        short col2 = getCol2();
        
        if (col1 == col2) {
            return col1;
        }
        
        int widths[] = new int[col2-col1+1];
        for (int i = 0; i < widths.length; i++) {
            widths[i] = sheet.getColumnWidth(col1 + i);
        }
        
        // HSSFClientAnchor �� dx ֻ���� 0-1023 ֮��,dy ֻ���� 0-255 ֮��
        // ��ʾ���λ�õı��ʣ����Ǿ���ֵ
        int dx1 = getDx1() * widths[0] / 1023;
        int dx2 = getDx2() * widths[widths.length-1] / 1023;

        return (short) (getCenter(widths, dx1, dx2) + col1);
    }
    
    /**
     * �������߶εĳ��ȣ��Լ������������ε�ƫ�������յ�������յ�ε�ƫ������
     * �����ĵ����ڵ��߶�
     * 
     * @param a the a ���߶εĳ���
     * @param d1 the d1 ������������
     * @param d2 the d2 �յ�������յ�ε�ƫ����
     * 
     * @return the center
     */
    protected static int getCenter(int[] a, int d1, int d2) {
        // �߶γ���
        int width = a[0] - d1 + d2;
        for (int i = 1; i < a.length-1; i++) {
            width += a[i];
        }

        // ���ĵ�λ��
        int c = width / 2 + d1;
        int x = a[0];
        int cno = 0;
        
        while (c > x) {
            x += a[cno];
            cno++;
        }
        
        return cno;
    }

    /**
     * ���Ͻ�������
     * 
     * @return the col1
     */
    public short getCol1() {
        return clientAnchor.getCol1();
    }

    /**
     * ���½����ڵ���
     * 
     * @return the col2
     */
    public short getCol2() {
        return clientAnchor.getCol2();
    }

    /**
     * ���Ͻǵ����ƫ����
     * 
     * @return the dx1
     */
    public short getDx1() {
        return clientAnchor.getDx1();
    }

    /**
     * ���½ǵ����ƫ����
     * 
     * @return the dx2
     */
    public short getDx2() {
        return clientAnchor.getDx2();
    }

    /**
     * ���Ͻǵ����ƫ����
     * 
     * @return the dy1
     */
    public short getDy1() {
        return clientAnchor.getDy1();
    }

    /**
     * ���½ǵ����ƫ����
     * 
     * @return the dy2
     */
    public short getDy2() {
        return clientAnchor.getDy2();
    }

    /**
     * ���Ͻ����ڵ���
     * 
     * @return the row1
     */
    public short getRow1() {
        return clientAnchor.getRow1();
    }

    /**
     * ���½����ڵ���
     * 
     * @return the row2
     */
    public short getRow2() {
        return clientAnchor.getRow2();
    }
    
}
