package com.transportsystem.backend.api.component;

import com.transportsystem.backend.api.service.TransportService;
import com.transportsystem.backend.api.utility.ExcelRead;
import io.swagger.v3.oas.annotations.Parameter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class AppComponent {

    @Parameter(description = "Location of dataset ")
    private String filename = "src/main/resources/dataset.xlt";
    private FileInputStream fis = null;

    @Autowired
    private TransportService transportService;

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ExcelRead excelRead = new ExcelRead();
        try {
            fis = new FileInputStream(filename);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            transportService.savePlanetData(excelRead.readPlanetData(workbook.getSheetAt(0)));
            transportService.saveRoutsData(excelRead.readRoutesData(workbook.getSheetAt(1)));
            transportService.saveTrafficData(excelRead.readTrafficData(workbook.getSheetAt(2)));
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

