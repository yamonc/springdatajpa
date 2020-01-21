package cn.itcast;

import cn.itcast.domain.Customer;
import cn.itcast.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/18
 */
public class test {
    /*
    * 测试jpa的保存
    *   案例 保存一个客户到数据库
    * jpa操作步骤：
    *   1. 加载配置文件创建工厂（实体管理工厂）对象
    *   2. 通过实体管理类获取实体管理器
    *   3. 获取事务对象，开启事务
    *   4. 完成增删改查操作
    *   5. 提交事务（回滚事务）
    *   6. 释放资源
    * */
    @Test
    public void testSave(){
    /*    //1. 加载配置文件创建工厂（实体管理工厂）对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2. 通过实体管理类获取实体管理器
        EntityManager em = factory.createEntityManager();*/
        EntityManager em= JpaUtils.getEntityManager();
        // 3 . 获取事务对象，开启事务
        EntityTransaction tx = em.getTransaction();
        //开启事务
        tx.begin();
        //4。完成增删改查操作
        Customer customer=new Customer();
        customer.setCustName("chenyameng");
        customer.setCustIndustry("IT");
        //保存
        em.persist(customer);
        // 5. 提交事务（回滚事务）
        tx.commit();
        // 6. 释放资源
        em.close();
        //factory.close();

    }

    /**
     * 根据id查询用户
     */
    @Test
    public void testFind(){
        //1, 通过工具类获取entityManger
        EntityManager em = JpaUtils.getEntityManager();
        //2，开启事务
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        //3， 增删改查
        /**
         * find：根据id查询数据
         *      class：字节码，查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         *
         *      使用find方法查询
         *          1. 查询的对象就是当前客户对象本身
         *          2. 调用find方法的时候，就会发送sql请求
         */
        Customer customer = em.find(Customer.class, 1l);
        System.out.println(customer.toString());
        //4，提交事务，
        tx.commit();
        // 释放资源
        em.close();
    }

    /**
     * 根据id查询用户
     */
    @Test
    public void testReference(){
        //1, 通过工具类获取entityManger
        EntityManager em = JpaUtils.getEntityManager();
        //2，开启事务
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        //3， 增删改查
        /**
         * getReference：根据id查询数据
         *      class：字节码，查询数据的结果需要包装的实体类类型的字节码
         *      id：查询的主键的取值
         *
         *      特点：
         *          获取的对象是动态代理的对象
         *          调用reference方法不会立即发送sql语句查询数据库
         *              * 当调用查询结果的时候，才会发送sql语句，什么时候用，什么时候发送sql语句
         *              延迟加载，特点：
         *                  得到的是动态代理对象
         *                  什么时候用什么时候查
         *
         */
        Customer customer = em.getReference(Customer.class, 1l);
        System.out.println(customer.toString());
        //4，提交事务，
        tx.commit();
        // 释放资源
        em.close();
    }
    /**
     * s删除客户
     */
    @Test
    public void testRemove(){
        //1, 通过工具类获取entityManger
        EntityManager em = JpaUtils.getEntityManager();
        //2，开启事务
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        //3， 增删改查
        //3.1 根据id查询客户
        final Customer customer = em.find(Customer.class, 1L);
        // 3.2 调用remove  方法删除操作
       em.remove(customer);
        //4，提交事务，
        tx.commit();
        // 释放资源
        em.close();
    }
    /**
     * 更新客户
     */
    @Test
    public void testUpdate(){
        //1, 通过工具类获取entityManger
        EntityManager em = JpaUtils.getEntityManager();
        //2，开启事务
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        //3， 增删改查
        //3.1 客户的更新
        final Customer customer = em.find(Customer.class, 2L);
        customer.setCustIndustry("it教育");
        // 3.2 调用update
        em.merge(customer);
        //4，提交事务，
        tx.commit();
        // 释放资源
        em.close();
    }
}
