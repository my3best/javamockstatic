package mockstatic;

public class MockStaticDemo {
    public static void main(String[] args) {
        MockStaticDemo demo = new MockStaticDemo();
        String catString = demo.nonStaticMethod();
        System.out.println(catString);
    }
    public String nonStaticMethod() {
        return StaticClass.staticMethod("mock", "static");
    }
}

class StaticClass {
    public static String staticMethod(String str1, String str2) {
        return str1+" "+str2;
    }
}
