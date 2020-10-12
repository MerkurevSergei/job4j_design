package ru.job4j.merkurevsergei.tomany.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "model")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;
    private String name;

    public Model(String name) {
        this.name = name;
    }
}
