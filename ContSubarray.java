import java.util.*; 

public class ContSubarray{
	private int[] input;
	private int leftIndex;
	private int rightIndex;
	private ArrayDeque<Integer> stack;

	public ContSubarray(int[] array){
		this.input = array; 
		this.leftIndex = array.length; //assign the highest value 
		this.rightIndex = 0; //assign the lowest value
		this.stack = new ArrayDeque<Integer>();
	}

	public int findSolution(){
		int result;
		//Check from front to end for the left most index of unsorted subarray
		for (int i = 0; i < this.input.length; i++){
			while (!stack.isEmpty() && this.input[stack.peekLast()] > this.input[i]){ //input[i] is supposed to be bigger in a sorted array
				this.leftIndex = Math.min(this.leftIndex, stack.removeLast());
			}
			stack.addLast(i); // we add the index. NOT the value
		}
		
		stack.clear();

		//Check from the end to front for the right most index of unsorted subarray
		for (int i = this.input.length-1; i >= 0; i--){
			while (!stack.isEmpty() && this.input[stack.peekLast()] < this.input[i]){ //input[i] is supposed to be bigger in a sorted array
				this.rightIndex = Math.max(this.rightIndex, stack.removeLast());
			}
			stack.addLast(i); 
		}

		result = this.rightIndex - this.leftIndex;
		return result > 0 ? result + 1 : 0;
	}

public static void main(String args[]){
	int[] array = {2, 6, 4, 8, 10, 9, 15};
	ContSubarray solution = new ContSubarray(array);
	System.out.println(solution.findSolution());
}

}