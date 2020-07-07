package com.mxb.learn.learnclassloader;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * @author miaoxuebing
 * @Description: TODO[手动写一个自定义类加载器]
 * @date 2020/7/7 10:59 上午
 */
public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] data = new byte[0];
            try {
                data = loadByte(name);
                //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数据
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
            return defineClass(name, data, 0, data.length);
        }
    }

    public static void main(String[] args) throws Exception {
        //初始化类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载器设置为应用程序类加载器AppClassLoader
        MyClassLoader classLoader = new MyClassLoader("/Users/msonbang/Documents/MsonbangLearnCodes");
        //在这个路径中丢一个class文件，如果需要打破双气委派模式，就要重写loadClass方法
        Class clazz = classLoader.loadClass("com1.mxb1.learn1.User");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("sout", null);
        method.invoke(obj, null);
        System.out.println(clazz.getClassLoader().getClass().getName());
    }
}
