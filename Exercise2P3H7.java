/*
Describe a nlgn-time algorithm that, given a set S of n integers and another
integer x, determines whether or not there exist two elements in S whose sum is
exactly x.
*/
import java.util.Random;

public class Exercise2P3H7 {
  public static int S[] = {9,1,4,3,6,7,1,9,6,5};
  public static int x = 3;

  public static void main(String args[]) {
    test(true);
    test(false);
  }

  public static void test(boolean flag) {
    int S[] = new int[10];
    Random rand = new Random();
    for (int i = 0; i < S.length; i++) {
      S[i] = rand.nextInt(10) + 1;
    }

    if(flag) {
      int index1 = 0;
      int index2 = 0;
      while(index1 == index2) {
        index1 = rand.nextInt(S.length);
        index2 = rand.nextInt(S.length);
      }
      x = S[index1] + S[index2];
    }
    else {
      x = rand.nextInt(10) + 21;
    }
    System.out.println(solution(S, x));
  }

  public static boolean solution(int array[], int x) {
    mergeSort(array, 0, array.length - 1);
    int index = 0;
    while (index < array.length) {
      int key = x - array[index];
      if (key < array[index]) {
        return false;
      }
      if(binarySearch(key, array) != -1) {
        return true;
      }
      index++;
    }
    return false;
  }

  public static void mergeSort(int array[], int left, int right) {
    if (right - left <= 0) {
      return;
    }
    else {
      int mid = (left + right) / 2;
      mergeSort(array, left, mid);
      mergeSort(array, mid + 1, right);
      merge(array, left, mid, right);
    }
  }

  public static void merge(int array[], int left, int mid, int right) {
    int l_array[] = new int[mid - left + 1];
    int r_array[] = new int[right - mid];
    for(int i = 0; i < mid - left + 1; i++) {
      l_array[i] = array[left + i];
    }
    for(int i = 0; i < right - mid; i++) {
      r_array[i] = array[mid + 1 + i];
    }
    int l_index = 0;
    int r_index = 0;
    int a_index = left;
    while(l_index <= mid-left && r_index <= right - mid - 1) {
      if(l_array[l_index] > r_array[r_index]) {
        array[a_index] = r_array[r_index];
        r_index++;
      }
      else {
        array[a_index] = l_array[l_index];
        l_index++;
      }
      a_index++;
    }

    while(l_index <= mid - left) {
      array[a_index] = l_array[l_index];
      a_index++;
      l_index++;
    }

    while(r_index <= right - mid -1) {
      array[a_index] = r_array[r_index];
      a_index++;
      r_index++;
    }
  }

  public static int binarySearch(int key, int array[]) {
    int left = 0;
    int right = array.length - 1;
    while(left <= right) {
      int mid = (left + right) / 2;
      if (key == array[mid]) {
        return mid;
      }
      else if (key > array[mid]) {
        left = mid + 1;
      }
      else {
        right = mid - 1;
      }
    }
    return -1;
  }
}
