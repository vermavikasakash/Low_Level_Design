package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@AllArgsConstructor
@ToString
public class Product {
    private double price;
    private int productId;
    private String name;
}
