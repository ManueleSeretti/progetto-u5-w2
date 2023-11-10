package ManueleSeretti.progettou5w2.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Device {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private StatoDevice stato;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public void setUserId(User user) {
        this.userId = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStato(StatoDevice stato) {
        this.stato = stato;
    }
}
