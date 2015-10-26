package thesis;

import java.util.*;

public class mst {
	Graph g;
	int[] dis;
	public mst(Graph a) {
		g = a;
		dis = new int[g.nodes];
		for(int i = 0; i < g.nodes; i++) dis[i] = i;
	}
	public int find(int idx) {
		if(dis[idx] == idx) return idx;
		return find(dis[idx]);
	}
	public void unn(int a, int b) { 
		a = find(a);	
		b = find(b);
		if(a == b) return;
		dis[a] = b;
	}
/////
	public Graph apply(boolean print) {
		List<edge> v = g.getEdgelist();
		double cst = 0;
		Graph ng = new Graph(g.nodes);
		if(print) {
			System.out.println("\n"+"Edges of Mst");
		}
		
		for(int i = 0; i < v.size(); i++) {
			edge e = v.get(i);
			if (find(e.a) != find(e.b)) {
				cst += e.val;
				ng.mark(e.a, e.b, g.val[e.a][e.b]);/////////////////////////////
				unn(e.a, e.b);
				if(print)
					System.out.println(e.a+ " " +e.b);
			}
		}
		if(print) {
			System.out.println("Total Cost "+ cst);
		}
		return ng;
	}
}
