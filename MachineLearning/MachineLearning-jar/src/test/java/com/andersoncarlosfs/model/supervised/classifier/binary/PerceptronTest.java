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

    private Double[][] dataset = {
        {0D, 2.781083600D, 2.5505370030D},
        {0D, 1.465489372D, 2.3621250760D},
        {0D, 3.396561688D, 4.4002935290D},
        {0D, 1.388070190D, 1.8502203170D},
        {0D, 3.064072320D, 3.0053059730D},
        {1D, 7.627531214D, 2.7592622350D},
        {1D, 5.332441248D, 2.0886267750D},
        {1D, 6.922596716D, 1.7710636700D},
        {1D, 8.675418651D, -0.242068655D},
        {1D, 7.673756466D, 3.5085630110D}
    };

    public PerceptronTest() {
    }

    /**
     *
     * Test of predict method, of class Perceptron.
     */
    @org.junit.Test
    public void testPredict() {

        Double[] weights = {-0.1D, 0.20653640140000007D, -0.23418117710000003D};

        Perceptron perceptron = new Perceptron(weights);

        LinkedList<Instance> instances = new LinkedList<>();

        for (Double[] data : dataset) {
            instances.add(new Instance(data));
        }

        LinkedList<Double> predict = perceptron.predict(instances);

        for (int i = 0; i < predict.size(); i++) {
            System.out.println("expected=" + instances.get(i).getLabel() + "\tpredicted=" + predict.get(i));
        }

    }

    /**
     * Test of train method, of class Perceptron.
     */
    @org.junit.Test
    public void testTrain() {

        Perceptron perceptron = new Perceptron(0.1, 5);

        LinkedList<Instance> instances = new LinkedList<>();

        for (Double[] data : dataset) {
            instances.add(new Instance(data));
        }

        perceptron.train(instances);
        
        LinkedList<Double> weights = perceptron.getWeights();
        
        System.out.println(weights);

    }

}
