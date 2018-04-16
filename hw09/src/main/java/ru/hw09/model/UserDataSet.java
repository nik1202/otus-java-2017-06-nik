package ru.hw09.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "userDataSet")
public class UserDataSet extends DataSet {
    private String name;
    private int age;

    public UserDataSet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private AddressDataSet address;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PhoneDataSet> phones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDataSet that = (UserDataSet) o;
        return age == that.age &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "ID = " + getId() + ", name = " + name + ", age = " + age
                + ", Address = " + address.getStreet() + ", Phones = " + phonesToString(phones);
    }

    private String phonesToString(List<PhoneDataSet> phones) {
        StringBuilder builder = new StringBuilder();
        phones.forEach( phone -> builder.append(phone.getNumber()).append(", "));
        return builder.substring(0, builder.length() - 2);
    }
}
