package com.example.telegrambotanimalshelter.controllers;

import com.example.telegrambotanimalshelter.model_services.AdopterService;
import com.example.telegrambotanimalshelter.model_services.ReportService;
import com.example.telegrambotanimalshelter.models.Adopter;
import com.example.telegrambotanimalshelter.models.Report;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("report")
public class ReportController {
    Logger logger = LoggerFactory.getLogger(ReportController.class);
    private final AdopterService adopterService;
    private final ReportService reportService;

    public ReportController(AdopterService adopterService, ReportService reportService) {
        this.adopterService = adopterService;
        this.reportService = reportService;
    }


    @Operation(summary = "Поиск отчетов за сегодняшний день",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Поиск отчетов за сегодняшний день",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Report.class)
                                    )
                            }
                    )
            }, tags = "Отчет"
    )
    @GetMapping(value = "/todayReports")
    public List<Report> findTodaysReportsByShelterId(@RequestParam(name = "ID приюта") Long shelterId){
        logger.info("Was invoked method for output of Report");

        List<Adopter> adopters = adopterService.findAdoptersOfShelterAnimals(shelterId);
        LocalDate currentDate = LocalDate.now();
        List<Report> reports = new ArrayList<>();
        Report report = null;
        for (Adopter adopter : adopters) {

            report = reportService.findByAdopterIdAndCreationDate(adopter.getId(), currentDate);
            reports.add(report);


        }


        return reports;
    }
    @Operation(summary = "Получение фото по ID отчета",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Получение фото по ID отчета",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                            schema = @Schema(implementation = Report.class)
                                    )
                            }
                    )
            }, tags = "Отчет"
    )
    @GetMapping(value = "/getPhoto")
    public void getPhoto(@RequestParam(name = "ID отчета") Long reportId
            , HttpServletResponse response) throws IOException {
        Report report = reportService.findById(reportId);
        Path path = Path.of(report.getPhotoPath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            is.transferTo(os);
        }
    }





}

