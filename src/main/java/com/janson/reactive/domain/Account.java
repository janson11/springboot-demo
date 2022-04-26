package com.janson.reactive.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/26 4:36 下午
 */
@Table("account")
public class Account {

    @Id
    private Long id;

    private String accountCode;
    private String accountName;

    public Account() {
    }

    public Account(Long id, String accountCode, String accountName) {
        this.id = id;
        this.accountCode = accountCode;
        this.accountName = accountName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
