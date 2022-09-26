package cz.vutbr.bmds.cv01;

import java.util.ArrayList;

public class MyClass implements ISum {

    private static int count = 0;
    private ArrayList <Integer> List = new ArrayList<Integer>();

    // konstruktor
    public MyClass(){
        count++;
    }

    public MyClass(int...numbers) throws IllegalAccessException{
        this();

        for (int i: numbers) {
            addInteger(i);
        }
    }

    //staticke metody

    public static int getCount() {
        return count;
    }

    public static MyClass createUnited(MyClass prvni, MyClass druhy){
        MyClass newObject = new MyClass();

        newObject.List.addAll(prvni.List);
        newObject.List.addAll(druhy.List);

        return newObject;
    }

    //tridni metody

    public void addInteger(int i) throws IllegalAccessException {
        if (i > 0) {
            List.add(i);
        }
        else {
            throw new IllegalAccessException("Nelze vkladat zaporna cisla");
        }
    }

    public boolean intigerexist(int i){
        return List.contains(i);
    }

    @Override
    public int sum(){
        int sum = 0;

        for (int i: List) {
            sum += i;
        }
        return sum;
    }

    @Override
    public String toString (){
        return "Seznam o velikosti " + List.size() + "se souctem " + sum();
    }

    public void print(){
        System.out.print("Seznam (" + List.size() + ") ");

        for (int i: List){
            System.out.println(i + " ");
        }
    }
}
