/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andersoncarlosfs.model.supervised.classifier.binary;

import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AndersonCarlos
 */
public class PerceptronTest {

    public PerceptronTest() {
    }

    /**
     * Test of predict method, of class Perceptron.
     */
    @org.junit.Test
    public void testPredict() {

        Double[][] dataset = {
            {0D, 2.7810836D, 2.550537003D},
            {0D, 1.465489372D, 2.362125076D},
            {0D, 3.396561688D, 4.400293529D},
            {0D, 1.38807019D, 1.850220317D},
            {0D, 3.06407232D, 3.005305973D},
            {1D, 7.627531214D, 2.759262235D},
            {1D,5.332441248D, 2.088626775D},
            {1D,6.922596716D, 1.77106367D},
            {1D,8.675418651D, -0.242068655D},
            {1D,7.673756466D, 3.508563011D}
        };

        Double[] weights = {-0.1D, 0.20653640140000007D, -0.23418117710000003D};

        Perceptron perceptron = new Perceptron(weights);
        
        LinkedList<Instance> instances = new LinkedList<>();
        
        for (Double[] data : dataset) { 
            instances.add(new Instance(data));
        }
        
        LinkedList<Double> predict = perceptron.predict(instances);
        
        for (int i = 0; i < predict.size(); i++) {
            System.out.println("Expected=" + instances.get(i).getLabel() + ", Predicted=" + predict.get(i));
        }
        
    }
    
}
