package ru.job4j.merkurevsergei.lazyexception.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "auto_model")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class AutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn
    private AutoBrand autoBrand;

    public AutoModel(String name) {
        this.name = name;
    }
}
