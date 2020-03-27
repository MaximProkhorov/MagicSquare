
import java.util.Arrays;
import magicsquare.MagicSquare;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author PM051
 */
public class MagicSquareTest {

    public MagicSquareTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void sizeTwoShouldThrowException() {
        MagicSquare square = new MagicSquare(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalSizeShouldThrowException() {
        MagicSquare square = new MagicSquare(-2);
    }

    @Test
    public void sumsShouldBeMagicConstForOdd() {
        MagicSquare square = new MagicSquare(9);
        square.build();
        int[] array = new int[9];
        int[] arrayForDiagonals = new int[2];
        Arrays.fill(array, square.getMagicConst());
        Arrays.fill(arrayForDiagonals, square.getMagicConst());
        Assert.assertArrayEquals(array, square.summaStrok());
        Assert.assertArrayEquals(array, square.summaColumns());
        Assert.assertArrayEquals(arrayForDiagonals, square.summaDiagonalei());
    }

    @Test
    public void sumsShouldBeMagicConstForEven() {
        MagicSquare square = new MagicSquare(14);
        square.build();
        int[] array = new int[14];
        int[] arrayForDiagonals = new int[2];
        Arrays.fill(array, square.getMagicConst());
        Arrays.fill(arrayForDiagonals, square.getMagicConst());
        Assert.assertArrayEquals(array, square.summaStrok());
        Assert.assertArrayEquals(array, square.summaColumns());
        Assert.assertArrayEquals(arrayForDiagonals, square.summaDiagonalei());
    }

    @Test
    public void sumsShouldBeMagicConstForEvenFour() {
        MagicSquare square = new MagicSquare(16);
        square.build();
        int[] array = new int[16];
        int[] arrayForDiagonals = new int[2];
        Arrays.fill(array, square.getMagicConst());
        Arrays.fill(arrayForDiagonals, square.getMagicConst());
        Assert.assertArrayEquals(array, square.summaStrok());
        Assert.assertArrayEquals(array, square.summaColumns());
        Assert.assertArrayEquals(arrayForDiagonals, square.summaDiagonalei());
    }
}
