public class Sorter {
  private static int array[] = {7,5,8,1,2,4,1,4};
  public static void main(String args[]) {
      if (args.length != 0) {
        array = new int[args.length];
        for(int i  =0; i < args.length; i++) {
          int temp = Integer.parseInt(args[i]);
          array[i] = temp;
        }
      }
      printArray();
      Sorter sorter = new Sorter();
      //sorter.insertSort(array);
      //printArray();
      //sorter.insertSortDes(array);
      //sorter.selectSort(array);
      sorter.mergeSort(array, 0, array.length - 1);
      printArray();
	}

  public static void printArray() {
    System.out.println();
    for(int i = 0;i <array.length;i++) {
      System.out.print(array[i] + " ");
    }
  }

  public static void printArray(int[] array) {
    System.out.println();
    for(int i = 0;i <array.length;i++) {
      System.out.print(array[i] + " ");
    }
  }

  /* loop invariant
    in terms of numbersorted
    1. a[0,numbersorted) are sorted
    2. the rest are in location a[numbersorted,n)

    set numbersorted = 1; loop invariant is true at start
    and is true before every loop and after every loop
    and is true at the terminate when numbersorted = n;
    every loop numbersorted + 1
  */
	public void insertSort(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int key = array[i];
      int j = i - 1;
      while(j >= 0 && array[j] > key) {
        array[j+1] = array[j];
        j--;
      }
      array[j+1] = key;
    }
  }

  public void insertSortDes(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int key = array[i];
      int j = i - 1;
      while(j >= 0 && array[j] < key) {
        array[j+1] = array[j];
        j--;
      }
      array[j+1] = key;
    }
  }

  /*
  loop invariant
  1. array[minIndex] < every member in array[startIndex, nextToCheck)
  2. for all k in [stardIndex, nextToCheck), array[k] >= array[minIndex]

  set the Loop Invariant true at start by setting:
  nextToCheck = startIndex + 1 ;
  minIndex = startIndex ;
  loop invariant is true before and after every loop
  is true at end when nextToCheck = n;
  */
  public int findMin(int[]array, int startIndex) {
    int minIndex = startIndex;
    for (int nextToCheck = startIndex + 1; nextToCheck < array.length; nextToCheck++) {
      if(array[minIndex] > array[nextToCheck]) {
        minIndex = nextToCheck;
      }
    }
    return minIndex;
  }

  public void swap(int[] array, int index1, int index2) {
    int tmp = array[index1];
    array[index1] = array[index2];
    array[index2] = tmp;
  }

  /*
  loop invariant
  in terms of numbersorted
  1. array[0, numbersorted) have the smallest numbersorted values in asce order
  2. the rest are in location array[numbersorted, n)

  set numbersorted = 0, loop invariant is true at start, no value sorted.
  and is true before and after every loop
  and is true at end when numbersorted = n - 1;
  every loop numbersorted + 1

  */
  public void selectSort(int[] array) {
    for (int numbersorted = 0; numbersorted < array.length - 1; numbersorted++) {
      swap(array, findMin(array, numbersorted), numbersorted);
    }
  }

  public void mergeSort(int[] array, int startIndex, int endIndex) {
    if (startIndex < endIndex) {
      int splitIndex = (startIndex + endIndex) / 2;
      mergeSort(array, startIndex, splitIndex);
      mergeSort(array, splitIndex + 1, endIndex);
      merge(array, startIndex, splitIndex, endIndex);
    }
    else if (startIndex == endIndex){
      return;
    }
  }

  public void merge2(int[] array, int startIndex, int splitIndex, int endIndex) {

  }
  /*
  loop invariant
  1. array[startIndex, startIndex + numberMerged) are the smallest numberMerged
    values order by asce in array[startIndex, endIndex];
  2. L nextChecked is the smallest in the rest values of L array
  3. R nextChecked is the smallest in the rest values of R array
  */
  public void merge(int[] array, int startIndex, int splitIndex, int endIndex) {

    int L_array[] = new int[splitIndex - startIndex + 2];
    int R_array[] = new int[endIndex - splitIndex + 1];
    final int MAX_NUM = 99999;
    L_array[splitIndex - startIndex + 1] = MAX_NUM;
    R_array[endIndex - splitIndex] = MAX_NUM;


    for (int i = 0; i < splitIndex - startIndex + 1; i++) {
      L_array[i] = array[startIndex + i];
    }

    for (int i = 0; i < endIndex - splitIndex; i++) {
      R_array[i] = array[splitIndex + 1 + i];
    }

    //printArray(L_array);
    //printArray(R_array);
    //printArray(array);

    int L_Index = 0;
    int R_Index = 0;
    int A_Index = startIndex;

    while (L_array[L_Index] != MAX_NUM && R_array[R_Index] != MAX_NUM) {
      if (L_array[L_Index] > R_array[R_Index]) {
        array[A_Index] = R_array[R_Index];
        R_Index++;
        A_Index++;
      }
      else {
        array[A_Index] = L_array[L_Index];
        L_Index++;
        A_Index++;
      }
    }

    if (R_array[R_Index] != MAX_NUM) {
      while (A_Index <= endIndex) {
        array[A_Index] = R_array[R_Index];
        A_Index++;
        R_Index++;
      }
    }

    if (L_array[L_Index] != MAX_NUM) {
      while (A_Index <= endIndex) {
        array[A_Index] = L_array[L_Index];
        A_Index++;
        L_Index++;
      }
    }

    //printArray(L_array);
    //printArray(R_array);
    //printArray(array);
  }
}
