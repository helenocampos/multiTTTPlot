/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.heleno;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Heleno
 */
public class DataFile {
    private String path;
    private int dataPointsAmount;
    private List<Double> data;

    public DataFile(String path) {
        this.path = path;
        this.data = new LinkedList<>();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getDataPointsAmount() {
        return dataPointsAmount;
    }

    public void setDataPointsAmount(int dataPointsAmount) {
        this.dataPointsAmount = dataPointsAmount;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }
    
    public void addDataPoint(Double value){
        this.data.add(value);
    }
    
    public String getPoints(int n){
        int i= 1;
        StringBuilder builder = new StringBuilder();
        Collections.sort(data);
        for(Double value: data){
            double probability = (i-0.5)/n;
            builder.append(value);
            builder.append("\t");
            builder.append(probability);
            builder.append("\n");
            i++;
        }
        return builder.toString();
    }
}
