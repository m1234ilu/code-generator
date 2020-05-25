package cn.generate.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zycstart
 * @create 2020-05-10 14:38
 */
public class BeanUtils {
    //一：map2javabean 把map对象转换为javabean
    public static <T> T map2bean(Map<String, Object> map, Class<T> beantype) throws Exception {
        T object = beantype.newInstance();//创建对象
        //获取类的属性描述器
        BeanInfo beaninfo = Introspector.getBeanInfo(beantype, Object.class);
        //获取类的属性集
        PropertyDescriptor[] pro = beaninfo.getPropertyDescriptors();
        for (PropertyDescriptor property : pro) {
            //获取属性的名字
            String name = property.getName();
            Object value = map.get(name);//得到属性name在map中对应的value。
            Method set = property.getWriteMethod();//得到属性的set方法
            //接下来将map的value转换为属性的value
            set.invoke(object, value);//执行set方法
        }
        return object;
    }

    //二：将javabean转换为map
    public static Map<String, Object> javabean2map(Object bean) throws Exception {//传入一个javabean对象
        Map<String, Object> map = new HashMap<>();
        //获取类的属性描述器
        BeanInfo beaninfo = Introspector.getBeanInfo(bean.getClass(), Object.class);
        //获取类的属性集
        PropertyDescriptor[] pro = beaninfo.getPropertyDescriptors();
        for (PropertyDescriptor property : pro) {
            //得到属性的name
            String key = property.getName();
            Method get = property.getReadMethod();
            //执行get方法得到属性的值
            Object value = get.invoke(bean);
            map.put(key, value);
        }
        return map;
    }
}
