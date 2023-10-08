package com.janson.springboot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

/**
 * @Description:
 * @Author: shanjian
 * @Date: 2023/9/5 10:44 AM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {

    private Long customerId;

    private String address;
}
