import java.util.ArrayList;
import java.util.Random;
public class PriorityQueue {
  private ArrayList<QueueElement> queue;
  public PriorityQueue() {
    queue = new ArrayList<QueueElement>();
  }

  private class QueueElement {
    public int key;
    public QueueElement(int key) {
      this.key = key;
    }
  }

  public static void main(String args[]) {
    PriorityQueue pq = new PriorityQueue();
    pq.getRandElements();
    System.out.println("Get some elements with random keys");
    pq.printKeys();
    pq.buildMaxHeap();
    System.out.println("Build a max heap:");
    pq.printKeys();
    pq.heapIncreaseKey(8,7);
    System.out.println("Increase key of No.9 element to 7:");
    pq.printKeys();
    pq.maxHeapInsert(pq.new QueueElement(9));
    System.out.println("Insert a element of key 9:");
    pq.printKeys();
    System.out.println("Extract elements by desc order of keys:");
    pq.printKeysByOrder();
  }

  public void maxHeapify(int index) {
    int heapsize = queue.size();
    int left = left(index);
    int right = right(index);
    int lKey = queue.get(index).key;
    int lIndex = index;
    if (left < heapsize && queue.get(left).key > lKey) {
      lKey = queue.get(left).key;
      lIndex = left;
    }
    if (right < heapsize && queue.get(right).key > lKey) {
      lKey = queue.get(right).key;
      lIndex = right;
    }
    if (lIndex != index) {
      swap(index, lIndex);
      maxHeapify(lIndex);
    }
  }

  public void swap(int index1, int index2) {
    QueueElement temp = queue.get(index1);
    queue.set(index1,queue.get(index2));
    queue.set(index2,temp);
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

  public void buildMaxHeap() {
    int heapsize = queue.size();
    int index = heapsize / 2 - 1;
    for (int i = index; i >= 0; i--) {
      maxHeapify(i);
    }
  }

  public QueueElement heapMaxExtract() {
    int heapsize = queue.size();
    if (heapsize != 0) {
      QueueElement max = queue.get(0);
      queue.set(0, queue.get(heapsize - 1));
      queue.remove(heapsize - 1);
      if (queue.size() > 0) {
        maxHeapify(0);
      }
      return max;
    }
    else {
      return null;
    }
  }

  public void heapIncreaseKey(int index, int key) {
    if (key < queue.get(index).key) {
      queue.get(index).key = key;
      maxHeapify(index);
    }
    else {
      QueueElement inElement = queue.get(index);
      inElement.key = key;
      while (parent(index) >= 0 && key > queue.get(parent(index)).key) {
        queue.set(index, queue.get(parent(index)));
        index = parent(index);
      }
      queue.set(index, inElement);
    }
  }

  public void maxHeapInsert(QueueElement insert) {
    queue.add(insert);
    heapIncreaseKey(queue.size() - 1, insert.key);
  }

  public void printKeys() {
    for(int i = 0; i < queue.size(); i++) {
      System.out.print(queue.get(i).key + " ");
    }
    System.out.println();
  }

  public void printKeysByOrder() {
    int size = queue.size();
    for(int i = 0; i < size; i++) {
      System.out.print(heapMaxExtract().key + " ");
    }
    System.out.println();
  }

  public void getRandElements() {
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      QueueElement qe = new QueueElement(random.nextInt(10) + 1);
      queue.add(qe);
    }
  }
}
