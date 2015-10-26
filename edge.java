package thesis;

public class edge implements Comparable<edge>{
	public int a;
	public int b;
	public double val;
	
	public edge(int a, int b, double val) {
		this.a = a;
		this.b = b;
		this.val= val;
	}
	public edge() {

	}
	public int compareTo(edge o) {
	    double myVal = val;
	    double oVal = o.val;
	    if(myVal < oVal) return -1;
	    if(myVal > oVal) return 1;
	    return 0;
	}
}
