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
@Table(name = "address")
public class AddressDataSet extends DataSet {
    private String street;

    public AddressDataSet(String street) {
        this.street = street;
    }
}