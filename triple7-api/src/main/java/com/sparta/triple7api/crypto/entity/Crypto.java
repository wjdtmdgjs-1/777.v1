package com.sparta.triple7api.crypto.entity;

import com.sparta.triple7api.common.entity.Timestamped;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Crypto extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "description")
    private String description;

}
