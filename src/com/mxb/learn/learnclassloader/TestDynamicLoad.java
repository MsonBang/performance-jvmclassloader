package com.mxb.learn.learnclassloader;

/**
 * @author miaoxuebing
 * @Description: TODO[测试类加载的时候，使用了其他类，那么也需要加载其他类]
 * @date 2020/7/6 10:47 下午
 */
//1 注意：主类加载的时候，如果使用到其他类，会逐步加载这些类。比如下面我们加载Math.class,但是用到了User，还需要加载User.class.
//1.1 但是如果是我们引入的jar或者war包。不回去一次性全加载，都是使用到才加载。
//1.2 如果是使用的其他对象为Null，那么是不用加载的
public class TestDynamicLoad {

    static {
        System.out.println("**********load testDynamicLoad**********");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("**********load test**********");
        B b = null;
    }
}

class A{
    static {
        System.out.println("**********load A**********");
    }

    public A(){
        System.out.println("**********initial A**********");
    }
}

class B{
    static {
        System.out.println("**********load B**********");
    }

    public B(){
        System.out.println("**********initial B**********");
    }
}
