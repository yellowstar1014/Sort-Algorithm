import java.util.Random;
public class Problem2H4DInversion {
  public static int array[];

  public static void main(String args[]) {
    getArrayFromArgs(args);
    printArray();
    System.out.println(inversionCount(array, 0, array.length - 1));
    printArray();
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

  public static void getRandArray() {
    array = new int[10];
    Random random = new Random();
    for (int i = 0; i < array.length; i++) {
      array[i] = random.nextInt(10) + 1;
    }
  }

  public static int inversionCount(int array[], int left, int right) {
    if(left >= right) {
      return 0;
    }
    else {
      int count = 0;
      int mid = (right + left) / 2;
      count += inversionCount(array, left, mid);
      count += inversionCount(array, mid + 1, right);
      count += mergeCount(array, left, mid, right);
      return count;
    }
  }

  public static int mergeCount(int array[], int left, int mid, int right) {
    int l_array[] = new int[mid - left + 1];
    int r_array[] = new int[right - mid];
    for (int i = 0; i < l_array.length; i++) {
      l_array[i] = array[left + i];
    }
    for (int i = 0; i < r_array.length; i++) {
      r_array[i] = array[mid + 1 + i];
    }
    int l_index = 0;
    int r_index = 0;
    int a_index = left;
    int iCount = 0;
    while(a_index <= right) {
      if(l_index > l_array.length - 1) {
        array[a_index] = r_array[r_index];
        r_index++;
        a_index++;
      }
      else if(r_index > r_array.length - 1) {
        array[a_index] = l_array[l_index];
        l_index++;
        a_index++;
      }
      else if(l_array[l_index] < r_array[r_index]) {
        array[a_index] = l_array[l_index];
        l_index++;
        a_index++;
      }
      else {
        array[a_index] = r_array[r_index];
        r_index++;
        a_index++;
        iCount += (mid - left + 1) - l_index;
      }
    }
    System.out.println(iCount);
    return iCount;
  }
}
