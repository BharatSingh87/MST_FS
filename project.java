package thesis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.Vector;


public class project {
	public static double [][]mat;
	public static double [][]mat1;
	public static double [][]mat2;
	public static int numFeat;
	public static int samples;
        public static Integer[] indi;
        public static double []  arr1=new double[10000];
        
	public static double mean(int a) {
		double sum = 0;
		for(int i = 0;  i < samples; i++) {
			sum += mat1[a][i];
		}
		//if(sum == 0) System.out.println("hello");
		return sum/samples-1;
		
	}
	public static double covariance(int a, int b) {
		double ma = mean(a), mb = mean(b);
		double cov = 0;

		for (int i = 0; i < samples; i++) {
			//System.out.println(""+(mat[a][i]-ma)+ " "+mat[b][i]+" "+ -mb);
			cov += (mat1[a][i] - ma)*(mat1[b][i] - mb);
		}
		return cov/(samples-1);
	}
	
	public static double[] fisher(double[][] mata){
		double[] l = new double[numFeat];
		
		int numC = 2;
		int [] n_i = new int[2000];
		List<Integer>[] cIDX = new ArrayList[numC];
		
		// For 0-based classes
		//statistic for classes 
		for(int c = 0; c < numC; c++) {
			cIDX[c] = new ArrayList<Integer>();
			for(int j = 0; j < samples; j++) {
				
					if(mata[numFeat][j] == c) {
						cIDX[c].add(j);
					}
				
			}
			n_i[c] = cIDX[c].size();
		//	System.out.println(c + " " + n_i[c]);
		}
		 
		//calculate score for each features
		for (int i = 0; i < numFeat; i++){
			    double temp1 = 0.0;
			    double temp2 = 0.0;
			    double u_i = Utils.mean(mata[i], samples);//qwerty
			    //System.out.println("Mean"+ u_i);
			    ////////////////////////////////////////////////////////////
			    for(int c = 0; c < numC; c++){ 
			    	if(cIDX[c].size() == 0) continue; 
			    	
			    	
			    	double[] f_i = new double[cIDX[c].size()];
			    	int ii = 0;
			      	for(int j : cIDX[c]){
			    		f_i[ii++] = mata[i][j];
			    	}
			    	double u_cj = Utils.mean(f_i, ii);
			    	double var_cj = Utils.var(f_i, ii);
			    	temp1 = temp1+n_i[c]*(u_cj - u_i)*(u_cj - u_i);
			    	temp2 = temp2+ n_i[c]*var_cj;
			    	// // System.out.println(n_i[c]+" "+u_i+" "+u_cj+" "+var_cj);
			    }
			   // System.out.println(temp1+" "+temp2);///////////////////////////////////////////
			    if(temp1 == 0) {
			    	l[i] = 0.0;
			    } else {
			    	if(temp2 == 0) l[i] = 100.0;
			    	else l[i] = temp1/temp2;
			    }
			    System.out.println(" --Feature "+i+" Weight"+"      " + l[i]);
			    ///////////////////////////////////////////////////////////////////////////////
		}
		
		return l;
	}
	
	public static void run() {
		
		
		boolean [] selected = new boolean[8001];
		for(int i = 0; i <= 8000; i++) {
    		selected[i] = true;
    	}


    	BufferedReader reader;
    	try {
            String st="E://June data//NetBeansProjects//MST_FS//ovarian_61902PA.txt", st1="E://June data//NetBeansProjects//MST_FS//ovarian_61902.txt",st2="E://June data//NetBeansProjects//MST_FS//ovarian_61902Fisher.txt";
            
    		reader = new BufferedReader(new FileReader(st1));
    			String line = null;
			mat =  new double[80000][80000];
			int ii = 0, jj  =0, kk = 0;
			while( (line = reader.readLine()) != null) {
				jj = 0;
		    	String [] temp = line.split(",");
		    	kk = 0;
		    	for(String part:temp) {
		    	  		if(selected[jj] ) {
		    			mat[kk++][ii] = Double.parseDouble(part);
		    			//System.out.print(mat[kk-1][ii]+" " );
		    		}
		    		
		    		jj++;
		    	}
		    	// System.out.println("");
		    	ii++;
			}
			for(int i=0;i<numFeat;i++){
                            
                            for(int j=0;j<samples;j++){
                                
                                mat[i][j]=(mat[i][j]-Utils.mean(mat[i],samples));
                            }
                        }
			
			
			numFeat = kk-1;
		System.out.println("\n"+"value of:"+numFeat);
			samples = ii;
			System.out.println("\n"+"value of:"+samples);
			
			// // System.out.println("-=----" +numFeat);
			double [] arr = fisher(mat);
			System.out.println("qwertyuiogbzfbzf:"+arr.length);
			
			
	//		float[] array = new float[]{};
		    Map<Double, Integer> map = new TreeMap<Double, Integer>();
		    double mea = 0;
		    for (int i = 0; i < arr.length; ++i) {
		        map.put(arr[i], i);
		        mea += arr[i];
		    }
		    double y;
                   System.out.println("MEA : "+mea);
		   mea /= (arr.length); y=mea;
                   System.out.println("MEA(y) : "+y);
                  
                  
		   mea=.0326;
		//    mea=99;
		   // y=(int)(Math.sqrt(numFeat)*Math.log(numFeat));
		   
		//    mea /= arr.length;
		  //  mea=0.;
		    
		    
		   // System.out.println("dsnfsdljnfvlasncf"+arr[y]);
		 
		    
		    int toTake = 0;
		    

		    for(int i = 0; i < arr.length; i++) {
		    	if(arr[i] >= (mea)){
		    		
		    		arr1[toTake]=arr[i];toTake++;
		    		//System.out.println(i);
		    	}
		    	
		    }
		    for(int i = 0; i < toTake; i++) {
		    	//System.out.println("jitendra"+arr1[i]);
		    }
		    
		    //modify code////////////////////////////////////////////////////////////////////
                    Integer[] ind;
		      ind = new Integer[toTake];
                      indi=new Integer[toTake];
		    int pos=0;
		    for(int i = 0; i < arr.length; i++) {
		    	if(arr[i] >= (mea)){
		    		indi[pos]=i;
                                ind[pos] = i;
		    		pos++;
		    	}
		    	
		    }
		    
		    
		    ////////////////////////////////////////////////////////////////////////////////
		    
		    System.out.println("Mean -> "+ mea+ " ToTake "+toTake);
		    int temp = numFeat;
		    numFeat = toTake;
		// Collection<Integer> indices = map.values();
		    // // System.out.println(numFeat+ " size "+ indices.size());
		    
		    //Integer[] ind = indices.toArray(new Integer[toTake]);       
//		    ;
		    for(int i = 0;i < numFeat; i++) {
	//	     System.out.println("bit   "+ind[i]);  //mod
		    }
		    
		    
		    
		    
		    //////////////////////////////////////////////////////////////////////////
		    mat1 = new double[numFeat+1][samples];
		    for(int i = 0; i < toTake; i++) {
		    	
		    	for(int j =0; j < samples; j++){
		    		mat1[i][j] = mat[ind[i]][j];
		    	}
		    }
	    
		    for(int j =0; j < samples; j++){          ///fdsgfgsdughdfuhgiusdbviaufdhnviavniaunvadnvad
		    	mat1[toTake][j] = mat[temp][j];
		    }

			
		    PrintWriter writer = new PrintWriter(st2, "UTF-8");
                    
                     String str2 = new String("");
                    for (int i = 0; i < toTake; i++) {

                                str2 = str2 +i;

                                if(i != toTake) str2 = str2+",";
                            }
                            str2 = str2 +"class";
                            writer.println(str2);
                            
			for(int j = 0; j < samples; j++){
				String str = new String("");
				for (int i = 0; i <= numFeat; i++) {
					str = str +Double.toString(mat1[i][j]);
					if(i != numFeat) str = str+",";
				}
				writer.println(str);
			}
			writer.close();
		    
			
		    // // System.out.println(" --> "+numFeat);
			// // // System.out.println(""+ii);
                     
			Graph g = new Graph(numFeat);
			for (int i = 0; i < numFeat; i++) {
				for (int j = 0; j < numFeat; j++) {
					if(i == j) {
						g.mark(i, i,arr1[i]);//
					//	arr[i]=covariance(i,j);
//						System.out.println("\nCovaiance:-"+covariance(i,j));
//						System.out.print(arr[i]+ "		");
						continue;
						
					}
//					double z=0;
//					if((covariance(i,i)- covariance(j,j))<0){
//						z=-(covariance(i,i)- covariance(j,j));
//					}
					g.mark(i, j,PearsonCorrelation.fisher1(mat1, i, j));
                                        
					//if(mat[j][0]== 0) System.out.println(" -----< "+ j);PearsonCorrelation.fisher2(mat1, i, j)
					//System.out.println(""+mat[i][0]+ " "+mat[j][0]); (covariance(i,j))/(Math.sqrt(covariance(i,i))*Math.sqrt(covariance(j,j)));
                                     //   ( (covariance(i,j))/((covariance(i,i)*covariance(i,i))))
					//System.out.print((covariance(i,j))/(covariance(i,i)*covariance(i,i))+ " ");
				}				
				//System.out.println("");
			}
			//System.out.println(""+g.nodes+" ");
                   //     System.out.println("jjjjj"+covariance(0, 1));
		//	
//			Graph gh=new Graph();
//			for(int i=0;i<toTake;i++){
//				for(int j=0;j<toTake;j++){
//					System.out.print(" "+gh.val[i][j]);
//				}System.out.println("");
//			}
                       
			mst m = new mst(g);
                        
//                         System.out.println("node of graphtree");
//                             for(int i=0;i<g.nodes;i++){
//                            
//                            for(int j=0;j<g.nodes;j++){
//                                System.out.print(" - "+g.val[i][j]);
//                            }System.out.println("");
//                        }
			Graph mstTree = m.apply(false);
			SplitMst sm = new SplitMst(mstTree, g, arr1);
			Graph splitTree = sm.split();
                        
                  /////////////////////////////////////////////////////////////////      
//                        System.out.println("node of SplitedMSTtree");
//                            for(int i=0;i<g.nodes;i++){
//                            
//                            for(int j=0;j<g.nodes;j++){
//                                System.out.print(" -"+j+"- "+splitTree.val[i][j]);
//                            }System.out.println("");
//                        }
                            
                  /////////////////////////////////////////////////////////////////   
                        Vector<Integer> v = new Vector<Integer>();
                        int[] vis = new int[10000];
                        for(int i = 0; i < splitTree.nodes; i++) {
                            vis[i] = 0;
                        }
                        Queue q = new LinkedList();
                        int cnt = 0;
                        for(int j = 0; j < splitTree.nodes; j++) {
                            if(vis[j] == 1) continue;
                            
                            double mx = 0;
                            int  indd = -1;
                            q.add(j);
                            if(arr1[j] > mx) {
                                mx = arr1[j];
                                indd = j;
                            }
                            System.out.println("qwertyu:  "+indd);
                            while(q.size() > 0) {
                                int u = (int)q.peek();
                                q.remove();
                                vis[u] = 1;
                                for(int i = 0; i < splitTree.nodes; i++) {
                                    if(vis[i] == 1) continue;
                                    if(splitTree.present(u, i)){
                                        q.add(i);
                                        if(arr1[i] > mx) {
                                            mx = arr1[i];
                                            indd = i;
                                        }
                                    }
                                }

                            }
                            System.out.println("Cluster "+ cnt+ " " +mx + " index "+ind[indd]);
                            v.add(ind[indd]);
                            cnt++;
                        }
//			System.out.println("##Remaining Links##");
//			int cnt = 0;
//			///////////////////////////////////////////////////////////////////////////////////////////////////////
			mat2 = new double[v.size()+1][samples];
                        for(int i = 0; i < v.size(); i++) {
                            for(int j = 0; j < samples; j++) {
                                mat2[i][j] = mat[v.get(i)][j];
                            }
                        }
                        cnt = v.size();
////			int count=0;
////			//////////////////////////////////////////////////////////////////////////////////
//			for(int i = 0; i < splitTree.nodes; i++) {
//				boolean flag = true;
//				for(int j = 0; j < splitTree.nodes; j++) {
//					if(i == j) continue;
//					if(splitTree.present(i, j)){
//						System.out.println(ind[i]+ " hvjhh" + ind[j]);
//						flag = false;
//					}
//				}
//				if(flag) {
//					System.out.println("Feature " +ind[i]+" is Isolated" );
//					cnt++;
//					//for(int m = 0; m < toTake; m++) {////////////////////////////////////////////////////
//				  ////////////////////////////////////////////////////////////////////////////////////////////////  	
////				    	for(int h =0; h < samples+1; h++){
////				    		mat2[count][h] = mat[ind[i]][h];
////				    	}
////				    	count++;
//				   // }////////////////////////////////////////////////////////////////////////////////////////////////
//				}
//			}
			System.out.println(""+cnt);
                        
			  for(int j =0; j < samples; j++){          
			    	mat2[cnt][j] = mat[temp][j];
			    }
			 PrintWriter writer1 = new PrintWriter(st, "UTF-8");
			 String str1 = new String("");
                            for (int i = 0; i < cnt; i++) {

                                str1 = str1 +i;

                                if(i != cnt) str1 = str1+",";
                            }
                            str1 = str1 +"class";
                            writer1.println(str1);
			 
				for(int j = 0; j < samples; j++){
					String str = new String("");
					for (int i = 0; i <= cnt; i++) {
						str = str +Double.toString(mat2[i][j]);
						if(i != numFeat) str = str+",";
					}
					writer1.println(str);
				}
				writer1.close();
		} catch(Exception e) {
    		e.printStackTrace();
    	}

		
	}
	
//    for(int i = 0; i < toTake; i++) {
//    	
//    	for(int j =0; j < samples; j++){
//    		mat1[i][j] = mat[ind[i]][j];
//    	}
//    }
    public static void main(String[] args) {
    	final long startTime = System.currentTimeMillis();
            run();
            final long duration = System.currentTimeMillis() - startTime;
            System.out.print("Duration:" +duration );
    }
}
