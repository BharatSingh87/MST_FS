package thesis;

public class Utils {
	public static double mean(double[] arr,int len) {
		double m = 0;
		for(int i = 0; i < len; i++) {
			m+= arr[i];
		}
		return m/len;
	}
	public static double var(double[] arr, int len) {
		double v = 0;
		double u = mean(arr,len);
		for(int i = 0; i < len; i++) {
			v+= (arr[i]-u)*(arr[i]-u);
		}
		return v/len;
	}
}
