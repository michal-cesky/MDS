import cz.vutbr.bmds.cv01.MyClass;

public class Main {
    public static void main(String[] args) {
        MyClass prvni = new MyClass();
        MyClass druha = new MyClass();
        MyClass treti = null;

        MapClass map = new MapClass();

        try {
            treti = new MyClass(1, 2, 3, 4, 5, 6);

            prvni.addInteger(20);
            prvni.addInteger(30);
            prvni.addInteger(40);

            druha.addInteger(30);
            druha.addInteger(1);

        } catch (IllegalAccessException e) {
            System.out.println("Chyba . " + e.getMessage());
        }

        System.out.println("Pocet vytvorenych trid: " + MyClass.getCount());

        System.out.println("Existuje ctirka ve trojce " + treti.intigerexist(4));

        MyClass united = MyClass.createUnited(prvni, druha);
        united.print();

        System.out.println(united);


    }
}
