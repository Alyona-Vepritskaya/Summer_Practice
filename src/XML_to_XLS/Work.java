package XML_to_XLS;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Work {

    private static String[] columns = {"№", "Groups", "Subject", "Tutor(s)", "SubjectType", "Day", "З/Ч", "Pair"};

    public static void main(String[] args) {
        try {
            WoW_Handler handler = new WoW_Handler();
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File("2.xml"), handler);
            handler.getLessons().sort(Work::compare);
            ArrayList<Lesson> lessons = new ArrayList<>(handler.getLessons());
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("Lessons");
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setItalic(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.BLACK.getIndex());
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            // Create a Row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
                cell.setCellStyle(headerCellStyle);
            }
            // Create Other rows and cells with contacts data
            int rowNum = 1;
            for (Lesson l : lessons) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rowNum - 1);
                row.createCell(3).setCellValue(l.getTutors().toString().replace(']', ' ').replace('[', ' '));
                row.createCell(1).setCellValue(l.getGroups().toString().replace(']', ' ').replace('[', ' '));
                row.createCell(2).setCellValue(l.getSubject());
                row.createCell(4).setCellValue(l.getSubjectType());
                row.createCell(5).setCellValue(l.getDay().getText());
                row.createCell(6).setCellValue(l.getCycle().getText());
                row.createCell(7).setCellValue(l.getPair().getText());
            }
            // Resize all columns to fit the content size
            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
                sheet.autoSizeColumn(i, true);
            }
            // Write the output to a file
            FileOutputStream fileOut = new FileOutputStream("Lessons.xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

    private static int compare(Lesson o1, Lesson o2) {
        int j = o1.getGroups().toString()
                .compareTo(o2.getGroups().toString());
        if (j == 0) {
            j = o1.getSubject().compareTo(o2.getSubject());
        }
        return j;
    }
}
