import java.util.Random;
import java.util.HashMap;
public class MaximumSubarray {
  public static int array[];
  public final static int LEFT = 0;
  public final static int RIGHT = 1;
  public final static int SUM = 2;

  public static void main(String args[]) {
    getArrayFromArgs(args);
    printArray();
    int[] result = findMaxSubarray(array, 0, array.length - 1);
    printArray(result);
    getArrayFromArgs(args);
    result = findMaxSubarray2(array);
    printArray(result);
  }

  public static int[] findMaxSubarray(int array[], int left, int right) {
    if(left == right) {
      int result[] = new int[3];
      result[LEFT] = left;
      result[RIGHT] = left;
      result[SUM] = array[left];
      return result;
    }
    else {
      int mid = (left + right) / 2;
      int[] lresult = findMaxSubarray(array, left, mid);
      int[] rresult = findMaxSubarray(array, mid + 1, right);
      int[] cresult = findMaxCrossArray(array, left, mid, right);
      if (lresult[SUM] > rresult[SUM] && lresult[SUM] > cresult[SUM]) {
        return lresult;
      }
      else if (rresult[SUM] > lresult[SUM] && rresult[SUM] > cresult[SUM]) {
        return rresult;
      }
      else {
        return cresult;
      }
    }
  }

  public static int[] findMaxCrossArray(int array[], int left, int mid, int right) {
    int lmaxValue = array[mid];
    int lmaxIndex = mid;
    int value = array[mid];
    for (int i = mid - 1; i >= left; i--) {
      value += array[i];
      if (value > lmaxValue) {
        lmaxValue = value;
        lmaxIndex = i;
      }
    }

    int rmaxValue = array[mid + 1];
    int rmaxIndex = mid + 1;
    value = array[mid + 1];
    for (int i = mid + 2; i <= right; i++) {
      value += array[i];
      if (value > rmaxValue) {
        rmaxValue = value;
        rmaxIndex = i;
      }
    }

    int result[] = new int[3];
    if (lmaxValue > 0 && rmaxValue > 0) {
      result[LEFT] = lmaxIndex;
      result[RIGHT] = rmaxIndex;
      result[SUM] = lmaxValue + rmaxValue;
    }
    else if (lmaxValue > rmaxValue) {
      result[LEFT] = lmaxIndex;
      result[RIGHT] = mid;
      result[SUM] = lmaxValue;
    }
    else {
      result[LEFT] = mid + 1;
      result[RIGHT] = rmaxIndex;
      result[SUM] = rmaxValue;
    }
    return result;
  }

  public static int[] findMaxSubarray2(int arrary[]) {
    int a[][] = new int[array.length][3];
    a[0][SUM] = array[0];
    a[0][LEFT] = 0;
    a[0][RIGHT] = 0;
    for (int j = 1; j < array.length; j++) {
      if (a[j - 1][SUM] > 0) {
        a[j][SUM] = a[j - 1][SUM] + array[j];
        a[j][LEFT] = a[j - 1][LEFT];
        a[j][RIGHT] = j;
      }
      else {
        a[j][SUM] = array[j];
        a[j][LEFT] = j;
        a[j][RIGHT] = j;
      }
    }

    int[] max = new int[3];
    max[SUM]= array[0];
    max[LEFT] = 0;
    max[RIGHT] = 0;
    for (int i = 1; i < array.length; i++) {
      if (max[SUM] < a[i][SUM]) {
        max[SUM] = a[i][SUM];
        max[LEFT] = a[i][LEFT];
        max[RIGHT] = a[i][RIGHT];
      }
    }

    return max;
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

  public static void printArray(int[] array, int left, int right) {
    for(int i = left;i <= right; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }

  public static void getRandArray() {
    array = new int[10];
    Random random = new Random();
    for (int i = 0; i < array.length; i++) {
      array[i] = random.nextInt(10) + 1;
    }
  }
}
