import java.io.*;
import java.util.*;

public class SortingTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try
		{
			boolean isRandom = false;	// 입력받은 배열이 난수인가 아닌가?
			int[] value;	// 입력 받을 숫자들의 배열
			String nums = br.readLine();	// 첫 줄을 입력 받음
			if (nums.charAt(0) == 'r')
			{
				// 난수일 경우
				isRandom = true;	// 난수임을 표시

				String[] nums_arg = nums.split(" ");

				int numsize = Integer.parseInt(nums_arg[1]);	// 총 갯수
				int rminimum = Integer.parseInt(nums_arg[2]);	// 최소값
				int rmaximum = Integer.parseInt(nums_arg[3]);	// 최대값

				Random rand = new Random();	// 난수 인스턴스를 생성한다.

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 각각의 배열에 난수를 생성하여 대입
					value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
			}
			else
			{
				// 난수가 아닐 경우
				int numsize = Integer.parseInt(nums);

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 한줄씩 입력받아 배열원소로 대입
					value[i] = Integer.parseInt(br.readLine());
			}

			// 숫자 입력을 다 받았으므로 정렬 방법을 받아 그에 맞는 정렬을 수행한다.
			while (true)
			{
				int[] newvalue = (int[])value.clone();	// 원래 값의 보호를 위해 복사본을 생성한다.

				String command = br.readLine();

				long t = System.currentTimeMillis();
				switch (command.charAt(0))
				{
					case 'B':	// Bubble Sort
						newvalue = DoBubbleSort(newvalue);
						break;
					case 'I':	// Insertion Sort
						newvalue = DoInsertionSort(newvalue);
						break;
					case 'H':	// Heap Sort
						newvalue = DoHeapSort(newvalue);
						break;
					case 'M':	// Merge Sort
						newvalue = DoMergeSort(newvalue);
						break;
					case 'Q':	// Quick Sort
						newvalue = DoQuickSort(newvalue);
						break;
					case 'R':	// Radix Sort
						newvalue = DoRadixSort(newvalue);
						break;
					case 'X':
						return;	// 프로그램을 종료한다.
					default:
						throw new IOException("잘못된 정렬 방법을 입력했습니다.");
				}
				if (isRandom)
				{
					// 난수일 경우 수행시간을 출력한다.
					System.out.println((System.currentTimeMillis() - t) + " ms");
				}
				else
				{
					// 난수가 아닐 경우 정렬된 결과값을 출력한다.
					for (int i = 0; i < newvalue.length; i++)
					{
						System.out.println(newvalue[i]);
					}
				}

			}
		}
		catch (IOException e)
		{
			System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoBubbleSort(int[] value)
	{
		
		int temp = 0;
		for(int i = 0 ; i<value.length-1 ; ++i)
		{
			boolean changed = false;
			for(int j = 1 ; j<value.length-i ; ++j)
			{
				if(value[j-1]>value[j]){
				 	temp = value[j-1];
				 	value[j-1] = value[j];
				 	value[j] = temp;
				 	changed = true;
				 
				}
			}
			if(!changed)
				break;
		}

		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoInsertionSort(int[] value)
	{
		
		int temp = 0;
		for(int i = 1 ; i < value.length; ++i)
		{
			temp = value[i];
			int j = i-1;
			for( ; (j > -1) && (value[j]>temp) ; --j){
					value[j+1]=value[j];
			}
			value[j+1] = temp;
		}

		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoHeapSort(int[] value)
	{
		for(int i = value.length/2 ; i >= 0 ; i--){
			poculateDown(value, i, value.length-1);
		}
		
		int temp;
		for(int last = value.length-1 ; last>=0 ; last--){
			temp = value[0];
			value[0] = value[last];
			value[last] = temp;
			poculateDown(value, 0, last-1);
		}
		
		return (value);
	}
	
	private static void poculateDown(int[] value, int root, int lastIndex){
		int leftChild = 2 * root + 1;
		int rightChild = 2 * root + 2;
		
		if(2*root+1>lastIndex ||(value[root]>=value[leftChild]&&value[root]>=value[(rightChild>lastIndex)?lastIndex:rightChild]))
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

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoMergeSort(int[] value)
	{
		DoRecursiveMSort(value, 0, value.length-1);

		return (value);
	}
	private static void DoRecursiveMSort(int []value, int first, int last){
		if(first>=last)
			return;
		int lastOfFirst = (last+first)/2;
		DoRecursiveMSort(value, first, lastOfFirst);
		DoRecursiveMSort(value, lastOfFirst+1, last);
		merge(value, first, lastOfFirst, last);
	
	
	}
	private static void merge(int[] value, int first, int lastOfFirst, int last){
		int size = last-first+1;
		int [] result = new int [size];
		int leftCur = first;
		int rightCur = lastOfFirst+1;
		for(int i = 0 ; (i < size); i++){
			if(value[leftCur]<=value[rightCur]){
				result[i]=value[leftCur++];
				if(leftCur==lastOfFirst+1){
					i++;
					while(rightCur<=last){
						result[i++]=value[rightCur++];
					}//end while
					break;
				}//end if
			}// end if
			else{
				result[i]=value[rightCur++];
				if(rightCur==last+1){
					i++;
					while(leftCur<=lastOfFirst){
						result[i++]=value[leftCur++];
					}//end while
					break;
				}//end if
			}//end if
		}//end for
		
		for(int i = 0 ; i < size ; i++){
			value[first+i]=result[i];
		}
		
	}//end merge

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoQuickSort(int[] value)
	{
		DoRecursiveQSort(value, 0, value.length-1);
		return (value);
	}
	private static void DoRecursiveQSort(int[] value, int first, int last){
		if(first>=last)
			return;
		else{
		
			int pivot = value[first];
			int lessCursor = first;
			int temp;
			int i = first+1;
			for(i = first+1 ; i<last+1; i++ ){
				if(value[i]<pivot){
					temp=value[++lessCursor];
					value[lessCursor]=value[i];
					value[i] = temp;
				}
			}
			temp = value[lessCursor];
			value[lessCursor]=value[first];
			value[first]=temp;
			
			DoRecursiveQSort(value, first, lessCursor-1);
			DoRecursiveQSort(value, lessCursor+1, last);
		
		
		}
	
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoRadixSort(int[] value)
	{
		ArrayList<ArrayDeque<Integer>> qArray = new ArrayList<ArrayDeque<Integer>>();
		for(int i = 0 ; i < 19 ; i++)
			qArray.add(new ArrayDeque<Integer>());
		
		boolean loopFin = true;
		int divider = 10;
		while(loopFin){
			loopFin = false;
			for(int i = 0 ; i< value.length; i++){
				loopFin = (loopFin==true || value[i]/divider!=0) ? true : false;
				qArray.get((value[i]%divider) / (divider/10)+9).add(value[i]);
			}
			int j = 0;
			for(int i = 0 ; i <19 ; i++){
				while(!qArray.get(i).isEmpty()){
					value[j] = qArray.get(i).remove();
					j++;
					
				}
			}
			divider*=10;
		
		}
			
		
		
		return (value);
	}
}

