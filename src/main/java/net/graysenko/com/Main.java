package net.graysenko.com;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

class Calculate {

    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface calc {
        int first() default 4;
        int second() default 12;
    }
}

public class Main {

    @Calculate.calc(first = 10, second = 21)
    public static void main(String[] args) {
        Calculate.calc annotation = null;
        try {
            Method mainMethod = Main.class.getMethod("main", String[].class);
            annotation = mainMethod.getAnnotation(Calculate.calc.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (annotation != null) {
            int first = annotation.first();
            int second = annotation.second();
            int sum = first + second;
            System.out.println("Sum: " + sum);
        }
    }
}
