package com.janson.reactive.repository;

import com.janson.reactive.domain.Account;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/26 4:35 下午
 */
public interface ReactiveAccountRepository extends R2dbcRepository<Account, Long> {

    @Query("insert into account(ACCOUNT_CODE,ACCOUNT_NAME) values (:accountCode,:accountName)")
    Mono<Boolean> addAccount(String accountCode,String accountName);

    @Query("SELECT * FROM account WHERE id=:id")
    Mono<Account> getAccountById(Long id);


}
