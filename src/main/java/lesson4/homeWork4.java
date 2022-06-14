package lesson4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class homeWork4 {

    public static void main(String[] args) {
        fordotone();
        fordottwo();
    }

    private static void fordotone() {
        Map<String, Integer> hm = new HashMap<>();
        String[] words = {
                "Doctor", "Apple", "Cat",
                "Dog", "Moon", "Java",
                "Dog", "Human", "Tee",
                "Java", "Dog", "Sun",
                "Women", "Cat", "Beer",
                "Moon", "Watch", "Java", "Song", "Jeans"
        };

        for (int i = 0; i < words.length; i++) {
            if (hm.containsKey(words[i]))
                hm.put(words[i], hm.get(words[i]) + 1);
            else
                hm.put(words[i], 1);
        }
        System.out.println(hm);
    }

    private static void fordottwo() {
        Directory directory = new Directory();

        directory.add("Ivanov", "89824065977");
        directory.add("Sidorov", "89572453466");
        directory.add("Karapetyan", "89855743433");
        directory.add("Ivanov", "89471121111");
        directory.add("Lenin", "8943232267");
        directory.add("Simonyan", "89843748900");
        directory.add("Sidorov", "89264389600");
        directory.add("Lapushkin", "8934332855");
        directory.add("Ivanov", "8956321122");

        System.out.println(directory.get("Ivanov"));
        System.out.println(directory.get("Sidorov"));
        System.out.println(directory.get("Karapetyan"));
        System.out.println(directory.get("Lenin"));
        System.out.println(directory.get("Simonyan"));
        System.out.println(directory.get("Lapushkin"));
    }
}
    class Directory {
        private Map<String, List<String>> directory_hm = new HashMap<>();
        private List<String> phone_number_list;

        public void add(String surname, String phone_number) {
            if (directory_hm.containsKey(surname)) {
                phone_number_list = directory_hm.get(surname);
                phone_number_list.add(phone_number);
                directory_hm.put(surname, phone_number_list);
            } else {
                phone_number_list = new ArrayList<>();
                phone_number_list.add(phone_number);
                directory_hm.put(surname, phone_number_list);
            }
        }

        public List<String> get(String surname) {
            return directory_hm.get(surname);
        }

    }
