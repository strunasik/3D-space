import java.awt.Color;

public class Centroid {
	double x, y, z;
	Color color;
	
	Centroid(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
		color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
	}
	
	static void create(int n, Centroid arr[]){
		int x, y, z;
		
		for(int i = 0; i < n; i++){
			x = (int) (Math.random()*100);
			y = (int) (Math.random()*100);
			z = (int) (Math.random()*100);
			arr[i] = new Centroid(x, y, z);
		}
	}

}
