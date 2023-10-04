package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


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
//    @ResponseBody
    public String displaySearchResults(Model model, String searchType, String searchTerm) {
        String answer;
        model.addAttribute("searchType", searchType);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("columns", columnChoices);


        ArrayList<Job> jobs;

            if (searchType.equals("all") || searchTerm.isEmpty()){
                jobs = JobData.findAll();
                model.addAttribute("title", "All Jobs");
            } else {
                jobs = JobData.findByColumnAndValue(searchType, searchTerm);
                model.addAttribute("title",
                        "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
            }
            model.addAttribute("jobs", jobs);


//        if(Objects.equals(searchType, "all")) {
//            answer = "test";
//        } else {
//            answer = searchType;
//        }



//        return answer;
        return "search";
    }

}

