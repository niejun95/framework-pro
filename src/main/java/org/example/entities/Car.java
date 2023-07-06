package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private Long id;

    private String carName;

    private String brand;

    private BigDecimal guidePrice;

    private String carType;
}
