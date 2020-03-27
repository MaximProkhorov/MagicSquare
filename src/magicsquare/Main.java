package magicsquare;

import java.util.Scanner;

/**
 *
 * @author PM051
 */
public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Введите размер квадрата: ");
        int n = Integer.parseInt(reader.nextLine());
        MagicSquare square = new MagicSquare(n);
        square.build();
        square.printMatrix();
    }
}
