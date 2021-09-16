package com.example.projetMedical.controller.website;


import com.example.projetMedical.model.services.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patients")
public class PatientControllerWeb {

    private final PatientService patientService;


    public PatientControllerWeb(PatientService patientService){
        this.patientService = patientService;

    }

    @RequestMapping("/list")
    public String patientList(Model model) {
        model.addAttribute("patient_list", patientService.getAllPatients());
                return "patients/list";
    }






}
