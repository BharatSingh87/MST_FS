package mst_fs;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.lazy.IBk;
import weka.classifiers.trees.J48;
import weka.core.Instances;


/**
 *
 * @author bonga
 */

public class TestFold {

    /**
     * @param args the command line arguments
     */
    public static String st = "E://June data//NetBeansProjects//himanshu//Himanshu1.arff";
    //public static String st="E://M Tech Thesis and code//Project_jitendra2014//Thesis.MST//InternetAds_full.arff";
    public static void NB()throws IOException,Exception {
        // TODO code application logic here
        
        Instances instances= new Instances(new BufferedReader(new FileReader(st)));
        instances.setClassIndex(instances.numAttributes()-1);
        int size= instances.numInstances() / 10;
        int begin = 0;
        int end = size - 1;
        double d=0;
       double Accuracy=0;
        Instances trainingInstances = new Instances(instances);
       NaiveBayes tree = new NaiveBayes();
       tree.buildClassifier(trainingInstances);
       Evaluation eval = new Evaluation(trainingInstances);
       eval.crossValidateModel(tree,trainingInstances,10,new Random(1));  
        String  st=eval.toSummaryString();
        double cnt=0;
        char [] temp=st.toCharArray() ;
        System.out.println(st.substring(58,65));
       cnt=Double.parseDouble(st.substring(58,65));
       System.out.println(" Accuracy by NaiveBayes Classifier :  "+cnt);
   }
    
    public static void C45()throws IOException,Exception {
        // TODO code application logic here
       
        Instances instances= new Instances(new BufferedReader(new FileReader(st)));
        instances.setClassIndex(instances.numAttributes()-1);
        int size= instances.numInstances() / 10;
        int begin = 0;
        int end = size - 1;
        double d=0;
        double Accuracy=0;
//        for(int i=1;i<=10;i++){
            Instances trainingInstances = new Instances(instances);
//            Instances testingInstances = new Instances(instances,begin,(end - begin));
//                    for(int j=0;j<(end-begin);j++){
//                        trainingInstances.delete(begin);
//                    }
                        J48 tree = new J48();
            tree.buildClassifier(trainingInstances);
            
            Evaluation eval = new Evaluation(trainingInstances);
            eval.crossValidateModel(tree,trainingInstances,10,new Random(1));  
            String  st=eval.toSummaryString();
            double cnt=0;
            char [] temp=st.toCharArray() ;
           // System.out.println(st.substring(58,65));
            cnt=Double.parseDouble(st.substring(58,65));
            System.out.println(" Accuracy by C4.5 Classifier :  "+cnt);
    }
    
    public static void IB1()throws IOException,Exception {
        // TODO code application logic here
               Instances instances= new Instances(new BufferedReader(new FileReader(st)));
        instances.setClassIndex(instances.numAttributes()-1);
        int size= instances.numInstances() / 10;
        int begin = 0;
        int end = size - 1;
        double d=0;
        double Accuracy=0;
       // for(int i=1;i<=10;i++){
            Instances trainingInstances = new Instances(instances);
            
            
            IBk tree = new IBk();
            tree.buildClassifier(trainingInstances);
            Evaluation eval = new Evaluation(trainingInstances);
            eval.crossValidateModel(tree,trainingInstances,10,new Random(1));  
            String  st=eval.toSummaryString();
            double cnt=0;
            char [] temp=st.toCharArray() ;
           // System.out.println(st.substring(58,65));
            cnt=Double.parseDouble(st.substring(58,65));
            System.out.println(" Accuracy by IB1 Classifier :  "+cnt);
        //System.out.println(" Accuracy by IB1 Classifier : "+Accuracy+"\n");
    }
   /*public static void SVM()throws IOException,Exception {
        // TODO code application logic here
               Instances instances= new Instances(new BufferedReader(new FileReader(st)));
        instances.setClassIndex(instances.numAttributes()-1);
        int size= instances.numInstances() / 10;
        int begin = 0;
        int end = size - 1;
        double d=0;
        double Accuracy=0;
       // for(int i=1;i<=10;i++){
            Instances trainingInstances = new Instances(instances);
            
            svm tree = new libsvm();
            tree.buildClassifier(trainingInstances);
            Evaluation eval = new Evaluation(trainingInstances);
            eval.crossValidateModel(tree,trainingInstances,10,new Random(1));  
            String  st=eval.toSummaryString();
            double cnt=0;
            char [] temp=st.toCharArray() ;
           // System.out.println(st.substring(58,65));
            cnt=Double.parseDouble(st.substring(58,65));
            System.out.println(" Accuracy by svm Classifier :  "+cnt);
        //System.out.println(" Accuracy by IB1 Classifier : "+Accuracy+"\n");
    
    */
     public static void main(String [] args) throws IOException,Exception{
        
      final long startTime = System.currentTimeMillis();  
      NB(); 
                  final long duration = System.currentTimeMillis() - startTime;
            System.out.print("\n NB Duration: " +duration );
            
       final long startTime1 = System.currentTimeMillis(); 
       C45();
       final long duration1 = System.currentTimeMillis() - startTime1;
            System.out.print("\n C45 Duration:"  +duration1 );
            
        final long startTime2 = System.currentTimeMillis(); 
            IB1();
        final long duration2 = System.currentTimeMillis() - startTime2;
            System.out.print("\n IB1 Duration:" +duration2 );
            
        //final long startTime3 = System.currentTimeMillis();
            //SVM();
       // final long duration3 = System.currentTimeMillis() - startTime3;
           // System.out.print("\n NB Duration:\n" +duration3 );
    }
}


/* 

    public static void NB()throws IOException,Exception {
        // TODO code application logic here
        
        Instances instances= new Instances(new BufferedReader(new FileReader(st)));
        instances.setClassIndex(instances.numAttributes()-1);
        int size= instances.numInstances() / 10;
        int begin = 0;
        int end = size - 1;
        double d=0;
        double Accuracy=0;
        for(int i=1;i<=10;i++){
            Instances trainingInstances = new Instances(instances);
            Instances testingInstances = new Instances(instances,begin,(end - begin));
                    for(int j=0;j<(end-begin);j++){
                        trainingInstances.delete(begin);
                    }
            
            NaiveBayes tree = new NaiveBayes();
            tree.buildClassifier(trainingInstances);
            
            Evaluation eval = new Evaluation(trainingInstances);
            eval.evaluateModel(tree, testingInstances);  
            d +=eval.pctCorrect();
            begin = end + 1;
            end += size;
            if(i == (9)){
                end=instances.numInstances();
            }
           // System.out.println("------Results-------\n"+" Accuracy by NaiveBayes Classifier : "+eval.toSummaryString(true));
        }
        Accuracy=d/10;
        
       // System.out.println("------Results-------\n"+" Accuracy by NaiveBayes Classifier : "+Accuracy);
    }

*/
