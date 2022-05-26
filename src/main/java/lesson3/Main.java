package lesson3;

import com.sun.org.apache.xpath.internal.operations.Or;

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());
        System.out.println(appleBox.getWeight());

        appleBox.pour(appleBox);
    }

    static void changeObj(Object[] array, int indexOne, int indexTwo) {
        Object val = array[indexOne];
        array[indexOne] = array[indexTwo];
        array[indexTwo] = val;
    } // смена элементов местами
}

