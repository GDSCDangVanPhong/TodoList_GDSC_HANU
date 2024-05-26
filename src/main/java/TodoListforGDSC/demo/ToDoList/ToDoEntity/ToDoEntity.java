package TodoListforGDSC.demo.ToDoList.ToDoEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Entity
@Table(name = "Bảng họạt động")
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
