package TodoListforGDSC.demo.todolist.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ToDoEntity> toDoEntities;

    public Integer getId() {
        return id;
    }
}
