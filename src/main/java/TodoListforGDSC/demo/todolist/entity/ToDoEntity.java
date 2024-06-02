package TodoListforGDSC.demo.todolist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Entity
@Table(name = "Task_List")
public class ToDoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String detail;
    @Column
    @CreationTimestamp
    private LocalDateTime time;
    @Column(nullable = false)
    private String status;
    @ManyToOne
    @JoinColumn(name="category_id")
    @JsonIgnore
    private CategoryEntity category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
