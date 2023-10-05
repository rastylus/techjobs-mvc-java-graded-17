package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;
import static org.launchcode.techjobsmvc.models.JobData.findByColumnAndValue;
import static org.launchcode.techjobsmvc.models.JobData.getFieldValue;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping("results")
    public String displaySearchResults(Model model, @RequestParam String searchType,
                                       @RequestParam String searchTerm) {
        String answer;
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("columns", columnChoices);


        ArrayList<Job> jobs;

            if (searchType.equals("all") || searchTerm.isEmpty()){
                jobs = JobData.findByValue(searchTerm);
                model.addAttribute("title", "All Jobs" + ": " + searchTerm);
                model.addAttribute("searchTerm", searchTerm);

            } else {
                jobs = JobData.findByColumnAndValue(searchType, searchTerm);
                model.addAttribute("title",
                        "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
            }

            model.addAttribute("jobs", jobs);
        return "search";
    }

}

