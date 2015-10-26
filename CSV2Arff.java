package thesis;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

/**
 *
 * @author g2
 */
public class CSV2Arff {
    
    public static void main(String[] args) throws Exception {

    String st="E:\\June data\\NetBeansProjects\\himanshu\\himanshu2PA.csv",st1="E:\\June data\\NetBeansProjects\\himanshu\\Himanshu2PA.arff";
    CSVLoader loader = new CSVLoader();
    loader.setSource(new File(st));
    System.out.println("IONO"+loader.getDataSet());
    Instances data;
        data = loader.getDataSet();
 
    // save ARFF
    ArffSaver saver = new ArffSaver();
    saver.setInstances(data);
    
    saver.setFile(new File(st1));    
// saver.setDestination(new File(st1));
    saver.writeBatch();
  }
    
}
