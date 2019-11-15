import java.util.*;

public class NearestPoints{

	///////////// We define our own comparator for Priority Queue ///////////
	public class PointComparator implements Comparator<PointDistance>{
		@Override
		public int compare(PointDistance p1, PointDistance p2){
			if (p1.distance < p2.distance){
				return 1;
			}else if (p1.distance > p2.distance){
				return -1;
			}else return 0; 
		}
	}

	///////////// Our new data type that contains both points and distances ///////////
	public class PointDistance{
		public int x;
		public int y;
		public int distance; //Euclidian distance should be float or double, but why int?
		
		public PointDistance(int xcoord, int ycoord){
			this.x = xcoord;
			this.y = ycoord;
			this.distance = this.calculateDistance(xcoord, ycoord);
		}

		public int calculateDistance(int x, int y){
			return (x * x) + (y * y);  
		}
	}

	////////// The actual NearestPoints class begins here ////////
	private int[][] inputPoints;
	private int k; 
	private PriorityQueue<PointDistance> pq;
	public NearestPoints(int[][] points, int k){
		this.inputPoints = points;
		this.k = k;
		this.pq = new PriorityQueue<PointDistance>(new PointComparator());
	}

	public int[][] findSolution(){
		/// the following codes are to show the use of vector but it is unnecessary for the solution
		/// vector is dynamic, synchronized (thread-safe).
		Vector<PointDistance> v = new Vector<PointDistance>(); 
		for (int i = 0; i < this.inputPoints.length; i++){
			v.add(new PointDistance(this.inputPoints[i][0], this.inputPoints[i][1])); /// x and y coords
		}

		// we used Iterator, but what is ListIterator? what are the benefits?
		ListIterator<PointDistance> vIterator = v.listIterator();
		while (vIterator.hasNext()){
			this.pq.add(vIterator.next());
			while (this.pq.size() > this.k){
				this.pq.remove();
			}
		}
 
		// we can take items from the PriorityQueue by dequeuing OR iterating. Lets use iterate.
		int[][] results = new int[this.k][2];
		Iterator<PointDistance> pqIterator = this.pq.iterator(); //this iterator is provided by the PriorityQueue class 
		int i = 0;
		while (pqIterator.hasNext()){
			PointDistance item = pqIterator.next();
			results[i][0] = item.x;
			results[i][1] = item.y;
			i++;
		}
		return results;
		
	}

	public static void main(String args[]){
		// Example input 1, output = [[3,3], [-2,4]]
		int[][] points = { {3,3}, {5,-1}, {-2,4} };
		int k = 2;		

		// Example input 2, output = [[-2,2]]
		// int[][] points = { {1, 3}, {-2, 2} };
		// int k = 1;
		
		NearestPoints solution = new NearestPoints(points, k);
		int[][] s = solution.findSolution();
		String output = "";
		for (int i = 0; i < s.length; i++){
			output += "[" + s[i][0] + "," + s[i][1] + "]\n";
		}
		System.out.println(output);

	}


}