package cn.itcast;

import cn.itcast.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/18
 * jpql测试
 */
public class JpqlTest {
    /**
     * 查询全部：
     *      jqpl：from cn.itcast.domain.Customer
     *      sql：select * from cst_customer
     */
    @Test
    public void testFindAll(){
        //1， 获取Entitymanager对象
        EntityManager em=JpaUtils.getEntityManager();
        //2 开启事务
        final EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //3 查询全部
        String jpql="from Customer";
        //创建Query对象，query对象才是执行spql对象
        Query query = em.createQuery(jpql);
        //发送查询，并封装结果集
        List list = query.getResultList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        // 4.提交事务
        transaction.commit();

        // 释放资源
        em.close();
    }
    /**
     * 排序查询：
     *      jqpl：from cn.itcast.domain.Customer order by custId
     *      sql：select * from cst_customer order by cust_id DESC
     *  进行jpql查询：
     *      1. 创建query对象
     *      2. 对参数进行复制
     *      3. 查询并返回结果
     */
    @Test
    public void testOrder(){
        //1， 获取Entitymanager对象
        EntityManager em=JpaUtils.getEntityManager();
        //2 开启事务
        final EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //3 查询全部
        String jpql="from cn.itcast.domain.Customer order by custId DESC";
        //创建Query对象，query对象才是执行spql对象
        Query query = em.createQuery(jpql);
        //发送查询，并封装结果集
        List list = query.getResultList();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        // 4.提交事务
        transaction.commit();

        // 释放资源
        em.close();
    }
    /**
     * 计数查询：
     *
     *     使用jpql查询，统计客户的总数：
     *      sql：select count（cus_id） from customer
     *      jpql:select count(custId) from Customer
     */
    @Test
    public void testCount(){
        //1， 获取Entitymanager对象
        EntityManager em=JpaUtils.getEntityManager();
        //2 开启事务
        final EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //3 查询全部

        //1 根据jpql语句创建query对象
        String jpql="select count(custId) from Customer";
        //2 参数赋值
        // 3 发送查询，并封装结果
        Query query = em.createQuery(jpql);
        //发送查询，并封装结果集
        /**
         * getResultList：直接将查询结果封装成为list集合
         * getSingleResult：得到唯一的结果集
         */
        final Object singleResult = query.getSingleResult();
        System.out.println(singleResult);

        // 4.提交事务
        transaction.commit();

        // 释放资源
        em.close();
    }
    /**
     * 分页查询：
     * sql：select * from cst_customer limit ?,?
     * jpql:from Customer
     */
    @Test
    public void testPage(){
        //1， 获取Entitymanager对象
        EntityManager em=JpaUtils.getEntityManager();
        //2 开启事务
        final EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //3 查询全部

        //1 根据jpql语句创建query对象
        String jpql="from Customer";

        // 3 发送查询，并封装结果
        Query query = em.createQuery(jpql);
        //发送查询，并封装结果集
        /**
         * getResultList：直接将查询结果封装成为list集合
         * getSingleResult：得到唯一的结果集
         */
        final List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println(o);
        }
        //2 参数赋值
        //起始索引，
        query.setFirstResult(0);
        //每页查询的条数
        query.setMaxResults(2);
        // 4.提交事务
        transaction.commit();

        // 释放资源
        em.close();
    }
    /**
     * 条件查询：
     *  查询客户名称以“传值”开头的
     *      sql: select * from cst_customer where cust_name like '搜索%'
     *      jpql： from Customer where custName like ?
     */
    @Test
    public void testCondition(){
        //1， 获取Entitymanager对象
        EntityManager em=JpaUtils.getEntityManager();
        //2 开启事务
        final EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        //3 查询全部

        //1 根据jpql语句创建query对象
        String jpql="from Customer where custName like ?";

        // 3 发送查询，并封装结果
        Query query = em.createQuery(jpql);
        //发送查询，并封装结果集
        /**
         * getResultList：直接将查询结果封装成为list集合
         * getSingleResult：得到唯一的结果集
         */

        //2 参数赋值  占位符参数
        // 第一个参数： 占位符的索引位置：（从1开始） 第二个参数：取值
        final Query query1 = query.setParameter(1, "chenya%");
        final List resultList = query.getResultList();
        for (Object o : resultList) {
            System.out.println(o);
        }
        // 4.提交事务
        transaction.commit();

        // 释放资源
        em.close();
    }
}
