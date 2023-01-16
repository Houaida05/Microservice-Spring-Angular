package org.ms.productservice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonManagedReference
    @OneToMany(mappedBy = "category",fetch=FetchType.LAZY)
    private Collection<Product> products= new ArrayList<>();

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
