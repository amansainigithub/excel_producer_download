package com.exe.excelmaker.controller;


import com.exe.excelmaker.excelMakerService.ExcelMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exe")
public class ExcelMakerController {

    @Autowired
    private ExcelMakerService excelMakerService;

    @GetMapping("/excelProducer")
    public ResponseEntity<Resource> excelProducre()
    {
        System.out.println("Running....");
        String filename = "tutorials.xlsx";
        InputStreamResource file = new InputStreamResource(excelMakerService.excelMakerService());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

}