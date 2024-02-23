package util;

import java.util.ArrayList;
import java.util.Random;

public class Util {
    // Method to claer terminal.
    public static void clear() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    // Method to shuffle the given list.
    public static <E> void shuffle(ArrayList<E> list) {
        ArrayList<E> list2 = new ArrayList<E>();
        Random rand = new Random();

        for (int i = 0; i < list.size(); i++) {
            list2.add(list.get(i));
        }

        for (int i = 0; i < list.size(); i++) {
            int j = rand.nextInt(list2.size());

            list.set(i, list2.get(j));
            list2.remove(j);
        }
    }
}
