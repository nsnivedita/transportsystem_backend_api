package com.transportsystem.backend.api.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.transportsystem.backend.api.dao.entity.Planet;
import com.transportsystem.backend.api.dao.entity.Route;
import com.transportsystem.backend.api.dao.entity.Traffic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

@Tag(name = "Excel Read", description = "Logic to read Data from Excel")
public class ExcelRead {

    @Parameter(description = "Location of file")
    public String filename = "src/main/resources/dataset.xlt";
    public FileInputStream fis = null;
    public List<Planet> planetList = null;

     public ExcelRead() {
        try {

            fis = new FileInputStream(filename);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            planetList = readPlanetData(workbook.getSheetAt(0));
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Operation(description = "Read Data From Planet Table")
    public List<Planet> readPlanetData(HSSFSheet sheet) {
        ArrayList<Planet> planets = new ArrayList<>();
        Iterator<Row> row = sheet.rowIterator();
        while (row.hasNext()) {
            Row r = row.next();
            if (r.getRowNum() == 0)
                continue;
            Planet planet = new Planet();
            Iterator<Cell> cell = r.cellIterator();
            while (cell.hasNext()) {
                Cell c = cell.next();
                if (0 == c.getColumnIndex())
                    planet.setPlanet_node(c.getStringCellValue());
                else if (1 == c.getColumnIndex())
                    planet.setPlanet_name(c.getStringCellValue());
            }
            planets.add(planet);
        }
        return planets;
    }

    @Operation(description = "Read Data From Route Table")
    public List<Route> readRoutesData(HSSFSheet sheet) {
        ArrayList<Route> routes = new ArrayList<>();
        Iterator<Row> row = sheet.rowIterator();
        while (row.hasNext()) {
            Row r = row.next();
            if (r.getRowNum() == 0)
                continue;
            Route route = new Route();
            Iterator<Cell> cell = r.cellIterator();
            while (cell.hasNext()) {
                Cell c = cell.next();
                if (0 == c.getColumnIndex())
                    route.setRoute_id((int)c.getNumericCellValue ());
                else if (1 == c.getColumnIndex())
                    route.setPlanet_origin(c.getStringCellValue());
                else if (2 == c.getColumnIndex())
                    route.setPlanet_destination(c.getStringCellValue());
                else if (3 == c.getColumnIndex())
                    route.setDistance(c.getNumericCellValue());
            }
            routes.add(route);
        }
        return routes;
    }

    @Operation(description = "Read Data From Traffic Table")
    public List<Traffic> readTrafficData(HSSFSheet sheet) {
        ArrayList<Traffic> traffic = new ArrayList<>();
        Iterator<Row> row = sheet.rowIterator();
        while (row.hasNext()) {
            Row r = row.next();
            if (r.getRowNum() == 0)
                continue;
            Traffic t = new Traffic();
            Iterator<Cell> cell = r.cellIterator();
            while (cell.hasNext()) {
                Cell c = cell.next();
                if (0 == c.getColumnIndex())
                    t.setRouteid((int)c.getNumericCellValue());
                else if (1 == c.getColumnIndex())
                    t.setPlanet_origin(c.getStringCellValue());
                else if (2 == c.getColumnIndex())
                    t.setPlanet_destination(c.getStringCellValue());
                else if (3 == c.getColumnIndex())
                    t.setTraffic_delay(c.getNumericCellValue());
            }
            traffic.add(t);
        }
        return traffic;
    }

}
