
public class heapSort {
	
	
	public static void main(String arg[]){
		
		int[] a = {1, 0,3,2,39,14 ,-10, 5, -4};
		
		DoHeapSort(a);
		for(int i = 0 ; i < a.length; i++){
			System.out.println(a[i]);
		}
		
		
	}
	
	
	private static int[] DoHeapSort(int[] value){
		for(int i = value.length/2 ; i >= 0 ; i--){
			poculateDown(value, i, value.length-1);
		}
		
		RecursiveHSort(value, value.length-1);
		
		return value;
	}
	
	private static void RecursiveHSort(int[] maxHeap, int lastIndex){
		if(lastIndex==0)
			return;

		int temp = maxHeap[0];
		maxHeap[0] = maxHeap[lastIndex];
		maxHeap[lastIndex] = temp;
		
		poculateDown(maxHeap, 0, lastIndex-1);
		
		RecursiveHSort(maxHeap, lastIndex-1);
		
		
	}
	
	private static void poculateDown(int[] value, int root, int lastIndex){
		int leftChild = 2 * root + 1;
		int rightChild = 2 * root + 2;
		
		if(2*root+1>lastIndex ||(value[root]>=value[leftChild]&&value[root]>=value[rightChild]))
			return;
		else{
			int temp = value[root];
			int next = leftChild;
			if(rightChild>lastIndex){}	
			else if(value[root]<value[rightChild]&&value[root]>=value[leftChild])
				next = rightChild;
			else if(value[rightChild]>=value[leftChild]&&value[root]<value[leftChild])
				next = rightChild;
			
			value[root] = value[next];
			value[next] = temp;
			poculateDown(value, next, lastIndex);
				
			
		}
	}

}
