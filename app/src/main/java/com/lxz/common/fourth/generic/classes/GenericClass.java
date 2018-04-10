package com.lxz.common.fourth.generic.classes;

/**
 * Created by lxz on 2017/10/30 0030.
 *
 * 修饰符 classes 类名<泛型>{

   }
 *
 *注意：静态方法不可以使用类中定义的泛型
 因为类中的泛型需要在对象初始化时指定具体的类型，而静态优先于对象存在。那么类中的静态方法就需要单独进行泛型声明，
 声明泛型一定要写在static后，返回值类型之前。
 泛型类的细节：
 1、创建对象的时候要指定泛型的具体类型
 2、创建对象时可以不指定泛型的具体类型(和创建集合对象一眼)。默认是Object，例如我们使用集合存储元素的时候没有使用泛型就是那么参数的类型就是Object
 3、类上面声明的泛型只能应用于非静态成员函数，如果静态函数需要使用泛型，那么需要在函数上独立声明。
 4、如果建立对象后指定了泛型的具体类型，那么该对象操作方法时，这些方法只能操作一种数据类型。
 5、所以既可以在类上的泛型声明，也可以在同时在该类的方法中声明泛型。
 *
 */

public class GenericClass<T> {
    public T getData(T t){
        return t;
    }

    /**
     * 数组元素反转
     * @param arr
     */
    public T[] reverse(T[] arr){
        int start = 0;
        int end = arr.length - 1;
        for(int i = 0;i<arr.length;i++){
            if(start < end){
                T temp = arr[i];
                arr[i] = arr[arr.length -i-1];
                arr[arr.length -i-1] = temp;
                if(end % 2 == 0){//偶数个
                    if(i == end/2)break;
                }else{//奇数个
                    if(i == (end-1)/2)break;
                }
            }
        }
        return arr;
    }

}
