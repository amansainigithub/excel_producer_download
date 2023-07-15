package com.exe.excelmaker.excelMakerService;

import com.exe.excelmaker.entities.ExcelEntity;
import com.exe.excelmaker.excelProRepo.ExcelProRepo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelMakerService {

    @Autowired
    private ExcelProRepo excelProRepo;

    static String[] HEADERs = { "Id", "First Name", "Last Name", "Gender","Country" ,"Age" ,"RxId" ,"Serial No" };
    static String SHEET = "Tutorials";
    public ByteArrayInputStream excelMakerService()
    {
        List<ExcelEntity > excelEntityList =  this.excelProRepo.findAll();

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // SOLID_FOREGROUND pattern
            CellStyle style = workbook.createCellStyle();
            style.setFillBackgroundColor(IndexedColors.BLUE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());


            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellStyle(style);
                cell.setCellValue(HEADERs[col]);
            }

            int rowIdx = 1;
            for (ExcelEntity ex : excelEntityList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(ex.getId());
                row.createCell(1).setCellValue(ex.getFirstName());
                row.createCell(2).setCellValue(ex.getLastName());
                row.createCell(3).setCellValue(ex.getGender());
                row.createCell(4).setCellValue(ex.getCountry());
                row.createCell(5).setCellValue(ex.getAge());
                Cell cell5  = row.createCell(6);
                cell5.setCellValue(ex.getRxId());
                cell5.setCellStyle(style);
                row.createCell(7).setCellValue(ex.getSerialNo());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

}
