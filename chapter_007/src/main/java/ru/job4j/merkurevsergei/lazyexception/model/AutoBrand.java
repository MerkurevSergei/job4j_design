package ru.job4j.merkurevsergei.lazyexception.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auto_brand")
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class AutoBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @ToString.Include
    private String name;

    @OneToMany(mappedBy = "autoBrand", cascade = CascadeType.ALL, orphanRemoval = true)
    List<AutoModel> models = new ArrayList<>();

    public AutoBrand(String name) {
        this.name = name;
    }

    public void addModel(AutoModel model) {
        models.add(model);
        model.setAutoBrand(this);
    }
}
