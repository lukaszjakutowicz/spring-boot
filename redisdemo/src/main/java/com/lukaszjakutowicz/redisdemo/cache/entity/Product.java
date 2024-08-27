package com.lukaszjakutowicz.redisdemo.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1629057611158733042L;

    private Long id;
    private String name;
    private BigDecimal price;
    private String currency;
}
