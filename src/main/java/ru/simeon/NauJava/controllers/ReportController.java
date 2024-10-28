package ru.simeon.NauJava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.simeon.NauJava.models.Report;
import ru.simeon.NauJava.models.ReportData;
import ru.simeon.NauJava.services.ReportService;

import java.util.List;

@Controller
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/new-report")
    public String createReport(Model model) {
        Long reportId = reportService.createReport();
        model.addAttribute("reportId", reportId);
        return "redirect:/api/reports";
    }

    @GetMapping("/{reportId}")
    public String getReportContent(@PathVariable("reportId") Long reportId, Model model) {
        Report report = reportService.getReportById(reportId);
        model.addAttribute("report", report);
        return "report";
    }

    @GetMapping
    public String getAllReports(Model model) {
        List<Report> reports = reportService.getAllReports();
        model.addAttribute("reports", reports);
        return "reports";
    }
}