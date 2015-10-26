package thesis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Graph {
	boolean [][] adj;
	double [][] val;
	int nodes;
	public Graph(){
	
	}
	public Graph(int n) {
		nodes = n;
		adj = new boolean[1000][1000];
		val = new double[1000][1000];
		for(int i = 0; i < 1000; i++) {
			for(int j = 0;j < 1000; j++) {
				adj[i][j] = false;;
				val[i][j] = 0;
			}
		}
	}
	public void mark(int a, int b) {
		adj[a][b] = true;
		adj[b][a] = true;

	}
	public void mark(int a, int b, double va) {
		
			adj[a][b] = true;
			val[a][b] = va;
			adj[b][a] = true;
			val[b][a] = va;
		
	}
	public boolean present(int a, int b) {
		return adj[a][b];
	}
	public double value(int a, int b) {
		if(adj[a][b])
			return val[a][b];
		else
			return 0;
	}
	public void demark(int a, int b) {
		
			adj[a][b] = false;
			
			adj[b][a] = false;
			
		
	}
	public List<edge> getEdgelist() {
		List<edge> l = new ArrayList<edge>();
		for(int i = 0; i < nodes; i++) {
			for(int j = i+1; j < nodes; j++) {
				if(adj[i][j]){
					edge e = new edge(i, j, val[i][j]);
					l.add( e);
				}
			}
		}
   Collections.sort(l);
//Collections.reverse(l);
		return l;
	}
}
