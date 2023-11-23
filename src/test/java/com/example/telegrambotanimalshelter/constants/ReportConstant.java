package com.example.telegrambotanimalshelter.constants;

import com.example.telegrambotanimalshelter.models.Report;

import java.time.LocalDate;

import static com.example.telegrambotanimalshelter.constants.ShelterConstant.*;

public class ReportConstant {

    public static final Report REPORT1 = new Report(LocalDate.now(), "Отчет1", SHELTER1, AdopterConstant.ADOPTER1);
    public static final Report REPORT2 = new Report(LocalDate.now(), "Отчет2", SHELTER1, AdopterConstant.ADOPTER2);
    public static final Report REPORT3 = new Report(LocalDate.now(), "Отчет3", SHELTER2, AdopterConstant.ADOPTER3);
    public static final Report REPORT4 = new Report(LocalDate.now(), "Отчет4", SHELTER2, AdopterConstant.ADOPTER4);

}
