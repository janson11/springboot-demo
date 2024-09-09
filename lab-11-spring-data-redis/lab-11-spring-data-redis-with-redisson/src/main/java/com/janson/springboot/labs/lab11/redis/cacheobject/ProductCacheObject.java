package com.janson.springboot.labs.lab11.redis.cacheobject;

/**
 * @Description: 商品缓存对象
 * @Author: shanjian
 * @Date: 2024/3/12 17:09
 */
public class ProductCacheObject {

    /**
     * 产品编号
     */
    private Integer id;

    /**
     * 产品名
     */
    private String name;

    /**
     * 产品分类编号
     */
    private Integer cid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "ProductCacheObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cid=" + cid +
                '}';
    }
}



