import java.util.Random;
public class Sorter {
  private static int array[];

  public static void main(String args[]) {
    if (args.length != 0) {
      getArrayFromArgs(args);
    }
    else {
      getRandArray();
    }
    printArray();
    //insertSort(array);
    //insertSortDes(array);
    //insertSortRecursive(array, array.length - 1);
    //insertSortImproved(array);
    //selectSort(array);
    //mergeSort(array, 0, array.length - 1);
    heapSort(array);
    printArray();
	}

  public static void heapSort(int array[]) {
    buildMaxHeap(array);
    printArray();
    for(int heapsize = array.length; heapsize > 1; heapsize--) {
      swap(array, 0, heapsize - 1);
      maxHeapify(0, array, heapsize - 1);
    }
  }

  public static void buildMaxHeap(int array[]) {
    for(int i = array.length/2 - 1; i >= 0; i--) {
      maxHeapify(i, array, array.length);
    }
  }

  public static void maxHeapify(int index, int array[], int heapsize) {
    int left = left(index);
    int right = right(index);
    int key = index;
    while (left < heapsize) {
      if (array[left] > array[key]) {
        key = left;
      }
      if (right < heapsize && array[right] > array[key]) {
        key = right;
      }
      if (key != index) {
        swap(array, index, key);
        index = key;
        left = left(index);
        right = right(index);
      }
      else{
        return;
      }
    }
  }

  public static int left(int index) {
    return 2 * index + 1;
  }

  public static int right(int index) {
    return 2 * index + 2;
  }

  public static int parent(int index) {
    return (index - 1) / 2;
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
	public static void insertSort(int[] array) {
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

  public static void insertSortImproved(int[] array) {
    for (int i = 1; i < array.length; i++) {
      int key = array[i];
      int endIndex = i - 1;
      int insertIndex = binarySearchVariant(key, array, endIndex);
      for(int j = endIndex; j >= insertIndex; j--) {
        array[j+1] = array[j];
      }
      array[insertIndex] = key;
    }
  }
  /*
  loop invariant
  1. array[0, endIndex) is sorted
  */
  public static void insertSortRecursive(int[] array, int endIndex) {
    if(endIndex != 0) {
      insertSortRecursive(array, endIndex - 1);
      int j = endIndex - 1;
      int key = array[endIndex];
      while (j >= 0 && array[j] > key) {
        array[j + 1] = array[j];
        j--;
      }
      array[j + 1] = key;
    }
  }

  //insertion sort by des order
  public static void insertSortDes(int[] array) {
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
  // linear search a unsorted array
  public static int findMin(int[]array, int startIndex) {
    int minIndex = startIndex;
    for (int nextToCheck = startIndex + 1; nextToCheck < array.length; nextToCheck++) {
      if(array[minIndex] > array[nextToCheck]) {
        minIndex = nextToCheck;
      }
    }
    return minIndex;
  }

  // swap two elements in array
  public static void swap(int[] array, int index1, int index2) {
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
  public static void selectSort(int[] array) {
    for (int numbersorted = 0; numbersorted < array.length - 1; numbersorted++) {
      swap(array, findMin(array, numbersorted), numbersorted);
    }
  }
  /*
    loop invariant:
    arraySorted[0,startIndex) < key
    arraySorted(endIndex,(arraySorted.length - 1)] > key
  */
  public static int binarySearch(int key, int[] arraySorted) {
    int startIndex = 0;
    int endIndex = arraySorted.length - 1;
    while (startIndex <= endIndex) {
      int splitIndex = (startIndex + endIndex) / 2;
      if(key > arraySorted[splitIndex]) {
        startIndex = splitIndex + 1;
      }
      else if(key < arraySorted[splitIndex]) {
        endIndex = splitIndex - 1;
      }
      else {
        return splitIndex;
      }
    }
    return -1;
  }

  public static int binarySearchRecursive(int key, int[] arraySorted, int startIndex, int endIndex) {
    if(startIndex <= endIndex) {
      int splitIndex = (startIndex + endIndex) / 2;
      if (key == arraySorted[splitIndex]) {
        return splitIndex;
      }
      else if (key > arraySorted[splitIndex]) {
        return binarySearchRecursive(key, arraySorted, splitIndex + 1, endIndex);
      }
      else {
        return binarySearchRecursive(key, arraySorted, startIndex, splitIndex - 1);
      }
    }
    else {
      return -1;
    }
  }

  /*
  return the Index x where array[x] == key or array[x-1] is the
  first element who is smaller than key from back to front.
  put it simpe, return the index x where key should be inserted in.
  */

  public static int binarySearchVariantRecur(int key, int[] arraySorted, int left, int right, int lastMid) {
    if (left > right) {
      if(key > arraySorted[lastMid]) {
        return lastMid + 1;
      }
      else
      {
        return lastMid;
      }
    }
    else{
      int mid = (left + right) / 2;
      if (key == arraySorted[mid]) {
        return mid;
      }
      else if (key > arraySorted[mid]) {
        return binarySearchVariantRecur(key, arraySorted, mid + 1, right, mid);
      }
      else {
        return binarySearchVariantRecur(key, arraySorted, left, mid - 1, mid);
      }
    }
  }

  /*
  return the Index x where array[x] == key or array[x-1] is the
  first element who is smaller than key from back to front.
  put it simpe, return the index x where key should be inserted in.
  */

  public static int binarySearchVariant(int key, int[] arraySorted, int endIndex) {
    int left = 0;
    int right = endIndex;
    int mid = (right + left) / 2;
    while(left <= right) {
      mid = (right + left) / 2;
      if (key == arraySorted[mid]) {
        break;
      }
      else if (key > arraySorted[mid]) {
        left = mid + 1;
      }
      else {
        right = mid - 1;
      }
    }
    if (key <= arraySorted[mid]) {
      return mid;
    }
    else {
      return mid + 1;
    }
  }

  public static void mergeSort(int[] array, int startIndex, int endIndex) {
    if (startIndex < endIndex) {
      int splitIndex = (startIndex + endIndex) / 2;
      mergeSort(array, startIndex, splitIndex);
      mergeSort(array, splitIndex + 1, endIndex);
      merge2(array, startIndex, splitIndex, endIndex);
    }
    else if (startIndex == endIndex){
      return;
    }
  }

  /*
  loop invariant
  1. array[startIndex, startIndex + numberMerged) are the smallest numberMerged
    values order by asce in array[startIndex, endIndex];
  2. L_nextChecked is the smallest in the rest values of L array
  3. R_nextChecked is the smallest in the rest values of R array
  */
  // merge with etra setinel element
  public static void merge(int[] array, int startIndex, int splitIndex, int endIndex) {

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
  }

  // merge without etra setinel element
  public static void merge2(int[] array, int startIndex, int splitIndex, int endIndex) {
    int L_array[] = new int[splitIndex - startIndex + 1];
    int R_array[] = new int[endIndex - splitIndex];

    for (int i = 0; i < splitIndex - startIndex + 1; i++) {
      L_array[i] = array[startIndex + i];
    }
    for (int i = 0; i < endIndex - splitIndex; i++) {
      R_array[i] = array[splitIndex + 1 + i];
    }

    int L_Index = 0;
    int R_Index = 0;
    int A_Index = startIndex;

    while (A_Index <= endIndex) {
      if (L_Index > splitIndex - startIndex) {
        array[A_Index] = R_array[R_Index];
        R_Index++;
        A_Index++;
      }
      else if(R_Index > endIndex - splitIndex - 1) {
        array[A_Index] = L_array[L_Index];
        L_Index++;
        A_Index++;
      }
      else if (L_array[L_Index] > R_array[R_Index]) {
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
  }

  public static void getRandArray() {
    array = new int[10];
    Random random = new Random();
    for (int i = 0; i < array.length; i++) {
      array[i] = random.nextInt(10) + 1;
    }
  }

  public static void getArrayFromArgs(String args[]) {
    array = new int[args.length];
    for(int i  =0; i < args.length; i++) {
      int temp = Integer.parseInt(args[i]);
      array[i] = temp;
    }
  }

  public static void printArray() {
    for(int i = 0;i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }

  public static void printArray(int[] array) {
    for(int i = 0;i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }
}
