/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andersoncarlosfs.model.supervised.classifier.binary;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Anderson Carlos Ferreira da Silva
 */
public class Instance {

    /**
     *
     */
    private final LinkedList<Double> features;

    /**
     *
     * @param features
     */
    public Instance(Double[] features) {
        this.features = new LinkedList<>(Arrays.asList(features));
    }
    
    /**
     *
     * @param features
     */
    public Instance(LinkedList<Double> features) {
        this.features = features;
    }

    /**
     * 
     * @return the features
     */
    public LinkedList<Double> getFeatures() {
        return features;
    }

    /**
     * 
     * @return the label
     */
    public Double getLabel() {
        return features.getFirst();
    }

}
