package util;

import java.util.ArrayList;

import quiz.User;

public class Util {
    public static void clear() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static void scoreSorter(ArrayList<User> list) {
        boolean swap = false;

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = 0; j < list.size()  - 1 - i; j++) {
                if (list.get(j).getScore() > list.get(j + 1).getScore()) {
                    User temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swap = true;
                }
            }

            if (!swap) {
                break;
            }
        }
    }
}
