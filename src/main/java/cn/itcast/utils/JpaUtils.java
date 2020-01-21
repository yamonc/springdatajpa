package cn.itcast.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/21
 * 解决实体管理器工厂的浪费资源和耗时问题
 *  通过静态代码块的形式，当程序第一次访问此工具类的时候，创建一个公共的实体管理器工厂对象
 *  第一次访问getEntityManger方法，通过静态代码块创建一个factory对象，再调用方法创建一个EntityManager对象
 *  第二次访问getEntityManger方法，直接通过创建好的factory对象，创建EntityManager对象
 */
public class JpaUtils {
    private static EntityManagerFactory factory;
    static{
        //根据配置文件，创建entityManagerFactory
        factory = Persistence.createEntityManagerFactory("myJpa");
    }
    /**
     * 获取entityManager对象
     */
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
