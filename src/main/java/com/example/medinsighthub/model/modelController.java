package com.example.medinsighthub.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/model")
public class modelController {

    @Autowired
    private   modelService MService;



    @PostMapping(value="/predict")
    public String predictCase(@RequestPart("immg")  MultipartFile immg){

      return  "result:"+MService.cacerPrediction(immg).getClass_name()+"Confidence: "+MService.cacerPrediction(immg).getConfidence_score();



    }

}
