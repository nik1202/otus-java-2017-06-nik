package ru.hw09.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "phone")
public class PhoneDataSet extends DataSet {
    private String number;

    public PhoneDataSet(String number) {
        this.number = number;
    }
}