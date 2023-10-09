package org.launchcode.techjobsmvc.controllers;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.AbstractMap;
import java.util.HashMap;

public class TechJobsController {
    
    static HashMap<String, String> actionChoices = new HashMap<>();

    @ModelAttribute("actions")
    static HashMap<String, String> getActionChoices() {
        return actionChoices;
    }
    
}
