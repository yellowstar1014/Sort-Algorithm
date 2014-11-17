import java.util.Random;
public class QuickSorter{
  public static int array[];
  public static void main(String args[]) {
    getRandArray();
    printArray();
    quickSort(array, 0, array.length - 1);
    printArray();
  }

  public static void quickSort(int array[], int start, int end) {
    if (end <= start) {
      return;
    }
    else {
      int splitIndex = partition(array, start, end);
      quickSort(array, start, splitIndex - 1);
      quickSort(array, splitIndex + 1, end);
    }
  }

  public static int partition(int array[], int start, int end) {
    int key = array[end];
    int splitIndex = start - 1;
    int iterator = start;
    while (iterator < end) {
      if (array[iterator] < key) {
        splitIndex++;
        swap(array, splitIndex, iterator);
      }
      iterator++;
    }
    splitIndex++;
    swap(array, splitIndex, end);
    return splitIndex;
  }

  public static void swap(int array[], int index1, int index2) {
    int tmp = array[index1];
    array[index1] = array[index2];
    array[index2] = tmp;
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
