/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andersoncarlosfs.model.supervised.classifier.binary;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Anderson Carlos Ferreira da Silva
 */
public class Perceptron {

    /**
     *
     */
    private LinkedList<Double> weights;

    /**
     *
     */
    public Perceptron() {
    }

    /**
     *
     * @param weights
     */
    public Perceptron(LinkedList<Double> weights) {
        this.weights = weights;
    }

    /**
     *
     * @param weights
     */
    public Perceptron(Double[] weights) {
        this.weights = new LinkedList<>(Arrays.asList(weights));
    }

    /**
     *
     * @param instances
     * @return
     */
    public LinkedList<Double> predict(LinkedList<Instance> instances) {
        return instances.stream().parallel().map((instance) -> {

            /**
             * Activation function
             */
            return IntStream.range(1, instance.getFeatures().size()).parallel().mapToDouble(i -> {

                /**
                 * Element-wise multiplication
                 */
                return weights.get(i) * instance.getFeatures().get(i);

            }).sum() + weights.getFirst();

        }).map((prediction) -> {

            /**
             * Prediction function
             */
            if (prediction > 0) {
                prediction = 1D;
            } else {
                prediction = 0D;
            }

            return prediction;

        }).collect(Collectors.toCollection(LinkedList::new));
    }

}
