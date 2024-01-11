package com.example.MedInsightHub.model;
import org.deeplearning4j.util.ModelSerializer;
import org.springframework.web.multipart.*;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class modelService {
    public ComputationGraph loadModel(String modelPath) throws IOException {
        File modelFile = new File(modelPath);
        return ModelSerializer.restoreComputationGraph(modelFile);
    }

    public String makePrediction(MultipartFile file) throws IOException {
        byte[] imageData=file.getBytes();
        ComputationGraph model =this.loadModel("keras_model.h5");


    }

}
