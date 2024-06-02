package TodoListforGDSC.demo.authen.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public class JwtEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,unique = true)
    private Integer accountId;
    @Column(nullable = false,unique = true)
    private String jwt;

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }



    public String getJwt() {
        return jwt;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
}
