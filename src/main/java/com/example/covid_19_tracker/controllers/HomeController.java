package com.example.covid_19_tracker.controllers;

import com.example.covid_19_tracker.models.LocationStats;
import com.example.covid_19_tracker.services.Covid19Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    Covid19Service covid19Service;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = covid19Service.getAllStats();
        long totalCases = allStats.stream().mapToLong(stat -> stat.getLatestTotalCases()).sum();
        long totalNewCases = allStats.stream().mapToLong(stat -> stat.getDiffFromPreviousDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
