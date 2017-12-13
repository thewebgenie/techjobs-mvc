package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        String checkedCol = "all";
        model.addAttribute("checkedCol", checkedCol);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value = "results")
//     method = RequestMethod.GET
    public String results(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList jobs;
        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
        }
        else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }
        String numOfResults = jobs.size() + " Result(s)";
        String checkedCol = searchType;
        model.addAttribute("numOfResults", numOfResults);
        model.addAttribute("checkedCol", checkedCol);
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }
}
