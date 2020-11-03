package com.example.demo.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue
    private long id;

    @Null
    private String title , isbn;

}