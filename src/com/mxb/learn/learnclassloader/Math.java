package com.mxb.learn.learnclassloader;

/**
 * @author miaoxuebing
 * @Description: TODO[Math类，主要后续用于编译]
 * @date 2020/7/6 10:19 下午
 */

//1.windows系统下，加载使用java.exe文件，底层是c++实现的，创建了一个引导类加载器
//2.然后c++会调用java代码创建jvm启动器，实力sun.misc.Launcher，并且由引导类加载器创建其他类加载器
//3.获取到math自己的类加载器classLoader，也就是AppClassLoader，classLoader.loadClass(",,/../,,")获取加载的类
//4.开始执行main方法

//1.loadclass的类加载过程有一下几步：加载>>验证>>准备>>解析>>初始化
//1.1 加载是硬盘上查找class文件，通过IO读取
//1.2 验证是校验字节码文件的正确性，如果私自修改之后编译是报错额
//1.3 准备，给类的静态变量分配内存，赋予默认值
//1.4 解析，将符号引用转换为直接引用。这个符号值得是什么呢？比如下面的initData就是符号1>>#1 compute()方法就是符号2>>#2.就是将这些符号转为引用并指向对应的应用

//2.类被加载到方法去后主要包含：运行时常量池、类型信息、字段信息、方法信息、类加载器应用、对用class实例的引用等信息
//2.1 类加载器的引用：这个类到类加载器的引用，这时候还不是我们开发中用的那个引用，切记
//2.2 对应class实例的引用：作为开发人员访问方法去中类定义的入口和切入点，也就是我们放到堆中的

public class Math {
    public static final int initData = 666;
    public static User user = new User();

    public int compute(){
        int a = 1;
        int b = 2;
        int c = (a+b)*10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        math.compute();
    }
}

