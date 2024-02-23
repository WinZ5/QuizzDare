package util;

import java.util.ArrayList;
import java.util.Random;

public class Util {
    public static void clear() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

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
