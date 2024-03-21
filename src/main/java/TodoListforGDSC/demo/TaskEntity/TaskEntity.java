package TodoListforGDSC.demo.TaskEntity;

import jakarta.persistence.*;

import lombok.Data;

@Data
@jakarta.persistence.Entity
@Table(name = "Bảng họạt động")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String title;
    @Column
    private String detail;

}
