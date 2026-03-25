package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> A = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();
        int[] nums = new int[]{4, 5, 6};
        for (int i : nums) {
            A.addLast(i);
            B.addLast(i);
        }

        for (int j = 0; j < 3; j++) {
            A.removeLast();
            B.removeLast();
            assertEquals(A.size(), B.size());
            if (A.size() > 0) {
                for (int k = 0; k < A.size(); k++){
                    assertEquals(A.get(k), B.get(k));
                }
            }
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> A = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                A.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size_A = A.size();
                int size_B = B.size();
                assertEquals(size_A, size_B);
            } else if (operationNumber == 2) {
                // getLast
                if (A.size() > 0 && B.size() > 0) {
                    int value_A = A.getLast();
                    int value_B = B.getLast();
                    assertEquals(value_A, value_B);
                }
            } else if (operationNumber == 3) {
                if (A.size() > 0) {
                    int temp_A = A.removeLast();
                    int temp_B = B.removeLast();
                    assertEquals(temp_A, temp_B);
                }
            }
        }
    }
}
