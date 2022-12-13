package com.vti.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Department")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Department implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DepartmentID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DepartmentName")
    @NonNull
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Account> accounts;

    @Column(name = "Type")
    @Enumerated(EnumType.STRING)
    private Type type;

    public Department(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public enum Type {
        Dev, Test, ScrumMaster, PM
    }

}
