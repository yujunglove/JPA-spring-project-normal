package com.greedy.springjpa.menu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_CATEGORY")
public class Category {

    @Id
    @Column(name="CATEGORY_CODE")
    private int categoryCode;
    @Column(name="CATEGORY_NAME")
    private String categoryName;
    @Column(name="REF_CATEGORY_CODE")
    private Integer refCategoryCode;

    public Category() {}

    public Category(int categoryCode, String categoryName, Integer refCategoryCode) {
        super();
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    @Override
    public String toString() {
        return "Category [categoryCode=" + categoryCode + ", categoryName=" + categoryName + ", refCategoryCode="
                + refCategoryCode + "]";
    }



}
