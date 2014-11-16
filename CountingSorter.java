import java.util.Random;
public class CountingSorter {
  private static int array[];
  public static void main(String args[]) {
    getRandArray();
    printArray();
    countingSort(array, 1, 10);
    printArray();
  }

  public static void countingSort(int array[], int min, int max) {
    int barray[] = new int[array.length];
    int carray[] = new int[max - min + 1];
    for (int i = 0; i < carray.length; i++) {
      carray[i] = 0;
    }
    for (int i = 0; i < array.length; i++) {
      carray[array[i] - min] ++;
    }
    for (int i = 1; i < carray.length; i++) {
      carray[i] += carray[i - 1];
    }
    for (int i = array.length - 1; i >= 0 ; i--) {
      barray[carray[array[i] - min] - 1] = array[i];
      carray[array[i] - min]--;
    }
    for (int i = 0; i < array.length; i++) {
      array[i] = barray[i];
    }
  }

  public static void countingSort(int array[], int k) {
    int barray[] = new int[array.length];
    int carray[] = new int[k+1];
    for (int i = 0; i < carray.length; i++) {
      carray[i] = 0;
    }
    for (int i = 0; i < array.length; i++) {
      carray[array[i] - min] ++;
    }
    for (int i = 1; i < carray.length; i++) {
      carray[i] += carray[i - 1];
    }
    for (int i = array.length - 1; i >= 0 ; i--) {
      barray[carray[array[i] - min] - 1] = array[i];
      carray[array[i] - min]--;
    }
    for (int i = 0; i < array.length; i++) {
      array[i] = barray[i];
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
