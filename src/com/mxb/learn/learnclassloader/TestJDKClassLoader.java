package com.mxb.learn.learnclassloader;

import sun.misc.Launcher;

import java.net.URL;

/**
 * @author miaoxuebing
 * @Description: TODO[类加载器和双亲委派机制]
 * @date 2020/7/7 9:23 上午
 */
//类主要通过类加载器来加载的，Java有如下几种类加载器：引导类加载器，扩展类加载器，应用程序类加载器，自定义加载器
//引导类加载器：负责加载支撑jvm运行的jre的lib下的核心类库，比如rt.har,charsets.jar等
//扩展类加载器：负责加载支撑jvm运行的额位于lib下的ext扩展目录的jar
//应用程序类加载器：负责加载classpath路径下的包，主要加载我们自己写的类
//自定义加载器：负责加载用户自定义路径下的类包
public class TestJDKClassLoader {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESedeKeyFactory.class.getClassLoader());
        System.out.println(TestJDKClassLoader.class.getClassLoader());
        System.out.println("--------------------");

        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassLoader.getParent();
        System.out.println("the bootstrapLoader:" + bootstrapLoader);
        System.out.println("the extClassLoader+" + extClassLoader);
        System.out.println("the appClassLoader+" + appClassLoader);
        System.out.println("--------------------");

        System.out.println("bootstrapLoader加载一下文件：");
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i]);
        }
        System.out.println("--------------------");

        System.out.println("extClassLoader下载以下文件");
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("--------------------");

        System.out.println("appClassLoader加载一下文件");
        System.out.println(System.getProperty("java.class.path"));
    }

    //综上所述：类加载器初始化过程
    //首先java.exe底层是C++语言，会创建一个引导类bootstapClassLoader.实例化一个单例的launcher实例。保证jvm虚拟机只有一个sun.misc.lanchuer实例
    //Lancher实例构造方法内部，创建了两个类加载器，分别是sum.misc.launcher.ExtClassLoader(扩展类加载器)和sun.misc.Launcher.AppClassLoader(应用类加载器)
    //jvm默认使用Launcher的getClassLoader()方法返回的AppclassLoader实例来加载我们的应用程序
}
