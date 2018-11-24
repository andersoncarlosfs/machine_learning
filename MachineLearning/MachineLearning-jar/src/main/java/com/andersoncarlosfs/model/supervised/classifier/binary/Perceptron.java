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
    private Double learning_rate;
    /**
     *
     */
    private Integer epochs;

    /**
     *
     * @param learning_rate
     * @param epochs
     */
    public Perceptron(Double learning_rate, Integer epochs) {
        this.learning_rate = learning_rate;
        this.epochs = epochs;
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
     * @return the weights
     */
    public LinkedList<Double> getWeights() {
        return weights;
    }

    /**
     * @return the learning_rate
     */
    public Double getLearning_rate() {
        return learning_rate;
    }

    /**
     * @return the epochs
     */
    public Integer getEpochs() {
        return epochs;
    }

    /**
     *
     * @param instances
     * @param weights
     * @return
     */
    public static LinkedList<Double> predict(LinkedList<Instance> instances, LinkedList<Double> weights) {
        return instances.stream().parallel().map((instance) -> {

            /**
             *
             * Activation function
             */
            return IntStream.range(1, weights.size()).parallel().mapToDouble(i -> {

                /**
                 *
                 * Element-wise multiplication
                 */
                return weights.get(i) * instance.getFeatures().get(i);

            }).sum() + weights.getFirst();

        }).map((prediction) -> {

            /**
             *
             * Prediction function
             */
            if (prediction >= 0D) {
                prediction = 1D;
            } else {
                prediction = 0D;
            }

            return prediction;

        }).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *
     * @param instances
     * @return
     */
    public LinkedList<Double> predict(LinkedList<Instance> instances) {
        return predict(instances, weights);
    }

    /**
     *
     * @param instances
     * @param learning_rate
     * @param epochs
     * @return
     */
    public static LinkedList<Double> train(LinkedList<Instance> instances, Double learning_rate, Integer epochs) {
        LinkedList<Double> weights = new LinkedList<>();

        instances.getFirst().getFeatures().forEach(i -> {
            weights.add(0D);
        });

        for (int i = 0; i < epochs; i++) {

            /**
             *
             */
            Double squared_error = 0D;

            /**
             * Stochastic gradient descent
             */
            for (Instance instance : instances) {

                final Double prediction = predict(new LinkedList<>(Arrays.asList(instance)), weights).getFirst();
               
                final Double error = instance.getLabel() - prediction;
                
                final Double update = learning_rate * error;

                squared_error += Math.pow(error, 2);

                weights.set(0, weights.getFirst() + update);
             
                IntStream.range(1, weights.size()).parallel().forEach(j -> {                    
                    weights.set(j, weights.get(j) + update * instance.getFeatures().get(j));
                });

            }

            /**
             *
             * Output the sum of the squared error
             */
            System.out.println("epoch=" + i + "\tlearning rate=" + learning_rate + "\terror=" + squared_error);

        }

        return weights;
    }

    /**
     *
     * @param instances
     * @return
     */
    public void train(LinkedList<Instance> instances) {
        weights = train(instances, learning_rate, epochs);
    }

}
