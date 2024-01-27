package com.example.medinsighthub.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/model")
public class modelController {

    @Autowired
    private   modelService MService;



    @PostMapping(value="/predict", consumes = "multipart/form-data")
    public String predictCase(@RequestPart("image") MultipartFile image){

        return "result:"+MService.cacerPrediction(image).getClass_name()+"Confidence: "+MService.cacerPrediction(image).getConfidence_score();



    }

}
