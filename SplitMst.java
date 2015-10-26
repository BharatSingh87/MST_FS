package thesis;


public class SplitMst {
	Graph g;
	Graph vg;
	double[] feat;
	public SplitMst(Graph s, Graph b, double[] l) {
		g = s;
                vg = b;
		feat = l;
	}
	public boolean isSplit(double fa, double fb, double val) {
		if(val<0){
			val=-(val);
		}
		if( val<fa && val<fb) {
			System.out.println("Viola!");
			return true;
		}
		return false;
	}
	public Graph split() {
            int k=0;
//		 System.out.println("node of MSTtree");
//             for(int i=0;i<g.nodes;i++){
//                            
//                            for(int j=0;j<g.nodes;j++){
//                                System.out.print(" -"+j+"- "+g.val[i][j]);
//                            }System.out.println("");
//                        }
		for(int i = 0; i < g.nodes; i++) {
                   for(int j = i+1; j < g.nodes; j++) {
				if(g.present(i, j)) {
					System.out.println(feat[i]+" "+ feat[j]+" check "+vg.value(i, j));
					if(isSplit(feat[i], feat[j], vg.value(i, j))) {
					System.out.println(feat[i]+" "+ feat[j]+" "+vg.value(i, j)+" "+i+ " " + j);
						g.demark(i, j);k++;
					}//i++;
				}
			}
		}
                System.out.println("Voila Number:  "+k);
		return g;
	}
}
