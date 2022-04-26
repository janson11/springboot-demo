package com.janson.reactive.controller;

import com.janson.reactive.domain.Account;
import com.janson.reactive.repository.ReactiveAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2022/4/26 4:32 下午
 */
@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private ReactiveAccountRepository reactiveAccountRepository;

    /**
     * 根据用户id查询用户信息
     *
     * @param accountId
     * @return
     */
    @GetMapping("/{accountId}")
    public Mono<Account> getAccountById(@PathVariable("accountId") Long accountId) {
        return reactiveAccountRepository.getAccountById(accountId);
    }

    @PostMapping("/")
    public Mono<Boolean> addAccount(@RequestBody Account account) {
        return reactiveAccountRepository.addAccount(account.getAccountCode(), account.getAccountName());
    }


}
