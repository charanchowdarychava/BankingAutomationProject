package com.capstone.bank.utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.util.*;

public class ExcelUtils {
    public static List<Map<String, String>> readSheet(String filePath, String sheetName) {
        List<Map<String, String>> rows = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) return rows;
            Row header = sheet.getRow(0);
            int cols = header.getLastCellNum();
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                Map<String, String> map = new LinkedHashMap<>();
                for (int c = 0; c < cols; c++) {
                    String key = header.getCell(c).getStringCellValue();
                    Cell cell = row.getCell(c);
                    String val = cell == null ? "" : cell.toString();
                    map.put(key, val);
                }
                rows.add(map);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return rows;
    }
}
