package com.example.medinsighthub.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.net.http.HttpResponse;

@FeignClient(name="modelClient" ,url="http://127.0.0.1:5000")
public interface modelService {

    @PostMapping("/model/predict")
    modelResponse cacerPrediction(@RequestBody MultipartFile image);


}
