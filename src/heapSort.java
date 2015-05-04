
public class heapSort {
	
	
private static int[] DoHeapSort(int[] value){
		
		return value;
	}
	
	
	private static void poculateDown(int[] value, int root){
		int leftChild = 2 * root + 1;
		int rightChild = 2 * root + 2;
		
		if(2*root+2>=value.length ||(value[root]>=value[leftChild]&&value[root]>=value[rightChild]))
			return;
		else{
			int temp = value[root];
			int next = leftChild;
			if(value[root]<value[rightChild]&&value[root]>=value[leftChild])
				next = rightChild;
			else if(value[rightChild]>=value[leftChild]&&value[root]<value[leftChild])
				next = rightChild;
			
			value[root] = value[next];
			value[next] = temp;
			poculateDown(value, next);
				
			
		}
	}

}
