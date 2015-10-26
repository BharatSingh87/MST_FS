/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package thesis;

import java.util.ArrayList;
import java.util.List;
import static thesis.project.numFeat;
import static thesis.project.samples;

/**
 *
 * @author g2
 */
public class PearsonCorrelation {
    
        public static double fisher2(double[][] mata,int m,int n ){
		double l=0 ;
		
		int numC = 2;
		int [] n_i = new int[2000];
                int [] n_i1 = new int[2000];
                int [] ni = new int [4000];
		List<Integer>[] cIDX = new ArrayList[numC];
                List<Integer>[] cIDX1 = new ArrayList[numC];
		
		// For 0-based classes
		//statistic for classes 
		for(int c = 0; c < numC; c++) {
			cIDX[c] = new ArrayList<Integer>();
                        cIDX1[c] = new ArrayList<Integer>();
			for(int j = 0; j < samples; j++) {
				
					if(mata[numFeat][j] == c) {
						cIDX[c].add(j);
                                                cIDX1[c].add(j);
					}
				
			}
			n_i[c] = cIDX[c].size();
                        n_i1[c] = cIDX1[c].size();
//                        ni[c] = n_i[c]+n_i1[c];                        
		//	System.out.println(c + " " + n_i[c]);
		}
                
		 
		//calculate score for corelation features
		for (int i = 0; i < 1; i++){
			    double temp1 = 0.0;
			    double temp2 = 0.0;
			    
                            double u_i = Utils.mean(mata[m], samples);//qwerty
                            double u_i1 = Utils.mean(mata[m], samples);
//                
                           
                            
			   
			    ////////////////////////////////////////////////////////////
			    for(int c = 0; c < numC; c++){ 
			    	if(cIDX[c].size() == 0) continue;
                                if(cIDX1[c].size() == 0) continue;
                                
			    	
			    	
			    	double[] f_i = new double[cIDX[c].size()];
                                double[] f_i1 = new double[cIDX1[c].size()];
			    	int ii = 0;
                                int jj = 0;
			      	for(int j : cIDX[c]){
			    		f_i[ii++] = mata[m][j];
                                        
			    	}
                                for(int u : cIDX1[c]){
                                    f_i1[jj++] = mata[n][u];
                                }
//                               
			    	double u_cj = Utils.mean(f_i , (f_i.length-1));
			    	double var_cj = Utils.var(f_i , (f_i.length-1));
                                double u_cj1 = Utils.mean(f_i1 , (f_i1.length-1));
			    	double var_cj1 = Utils.var(f_i1 , (f_i1.length-1));
                                
			    	temp1 = temp1+ ni[c]*(u_cj - u_i)*(u_cj1 - u_i1);
			    	temp2 = temp2+ ni[c]*(var_cj)*(var_cj1);
			    	// // System.out.println(n_i[c]+" "+u_i+" "+u_cj+" "+var_cj);
			    }
		//	 System.out.println(temp1+" "+temp2);///////////////////////////////////////////
			    if(temp1 == 0) {
			    	l = 0.0;
			    } else {
			    	if(temp2 == 0) l = 100.0;
			    	else l = temp1/temp2;
			    }
		//	  System.out.println(" --Feature "+i+" Weight"+"      " + l);
			    ///////////////////////////////////////////////////////////////////////////////
		}
//                double l1=project.arr1[m]+project.arr1[n];
//                l=l*2;
//                l=l/l1;
		return l;
                
		
	}
}
