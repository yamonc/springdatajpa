package cn.itcast.domain;

/**
 * @Created by Intellij IDEA.
 * @author: 陈亚萌
 * @Date: 2020/1/18
 */

import javax.persistence.*;

/**
 * 客户实体类：
 *  配置映射关系：
 *      1. 实体类和表的映射关系
 *      2. 实体类中属性和表中字段的映射关系
 *      @Entity声明实体列
 *      @Table：声明实体类和表的映射关系  name 属性是配置数据库的名称
 *      @Id：声明主键
 *      strategy
 *           @GeneratedValue(strategy = GenerationType.IDENTITY)  表示主键生成策略是自增 mysql
 *              * 底层数据库必须支持自动增长，底层数据库支持的自动增长方式，对id递增
 *           GenerationType.SEQUENCE： 序列  Oracle
 *                  底层数据库必须支持序列
 *           GenerationType.TABLE ：
 *              jpa提供的一种机制，通过数据库表的形式帮我们完成主键自增
 *           GenerationType.AUTO:
 *              由程序自动帮我们选择主键生成策略
 *      @Column(name = 'cust_id')配置属性和字端的映射关系  name代表数据库表中的字段名
 */
@Entity
@Table(name = "cst_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId;
    @Column(name = "cust_name")
    private String custName;
    @Column(name = "cust_source")
    private String custSource;
    @Column(name = "cust_level")
    private String custLevel;
    @Column(name = "cust_industry")
    private String custIndustry;
    @Column(name = "cust_phone")
    private String custPhone;
    @Column(name = "cust_address")
    private String custAddress;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }
}
