package net.javaguides.springboot.model;


import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;
}
