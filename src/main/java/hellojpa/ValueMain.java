package hellojpa;

public class ValueMain {

    public static void main(String[] args) {

//        int a = 10;
//        int b = 10;

//        b = 20; // b의 값을 변경해도 a의 값은 변경되지 않는다.

        Integer a = new Integer(10);
        Integer b = a;

        b.setValue(20); // b의 값을 변경하면 a의 값도 변경된다.

        System.out.println("a = " + a);
        System.out.println("b = " + b);
    }
}
