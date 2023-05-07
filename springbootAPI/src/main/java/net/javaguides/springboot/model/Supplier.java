package net.javaguides.springboot.model;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int supplierId;

    @Column(name = "email")
    private String email;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    // constructors, getters and setters, etc.
}
