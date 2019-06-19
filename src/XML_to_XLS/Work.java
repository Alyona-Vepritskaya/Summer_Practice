package XML_to_XLS;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Work {

    private static String[] columns = {"№", "Groups", "Tutor(s)\n", "Subject", "SubjectType", "Day", "З/Ч"};

    public static void main(String[] args) throws Exception {


        WoW_Handler handler = new WoW_Handler();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(new File("2.xml"), handler);
        handler.getLessons().sort(Work::compare);
        ArrayList<Lesson> lessons = new ArrayList<>(handler.getLessons());
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Lessons");
        System.out.println("End...");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setItalic(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.GREEN.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        // Create a Row
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        System.out.println("End...");
        // Create Other rows and cells with contacts data
        int rowNum = 1;
        for (Lesson l : lessons) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rowNum - 1);
            row.createCell(2).setCellValue(l.getTutors().toString().replace(']', ' ').replace('[', ' '));
            row.createCell(1).setCellValue(l.getGroups().toString().replace(']', ' ').replace('[', ' '));
            row.createCell(3).setCellValue(l.getSubject());
            row.createCell(4).setCellValue(l.getSubjectType());
            row.createCell(5).setCellValue(l.getDay().getText());
            row.createCell(6).setCellValue(l.getCycle().getText());
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
        System.out.println("End...");
    }

    private static int getIndex(Lesson lesson) {
        int ind = 0;
        char[] characters = lesson.getGroups().toString().toCharArray();
        for (int i = 0; i < characters.length; i++) {
            if (Character.isUpperCase(characters[i])) {
                ind = i;
                break;
            }
        }
        return ind;
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
