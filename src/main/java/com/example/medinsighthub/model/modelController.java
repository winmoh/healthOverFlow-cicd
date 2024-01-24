package com.example.medinsighthub.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/model")
public class modelController {

    @Autowired
    private   modelService MService;



    @PostMapping("/predict")
    public modelResponse predictCase(@RequestBody MultipartFile image){

        modelResponse response=MService.cacerPrediction(image);
        return response;


    }

}
