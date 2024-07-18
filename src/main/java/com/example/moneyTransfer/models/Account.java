package com.example.moneyTransfer.models;

import com.example.moneyTransfer.util.Constants;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private Person owner;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "account")
    private List<Action> actions;

    public void beforeSave() {
        this.setRole(Constants.USER_ROLE);
        this.setBalance(BigDecimal.valueOf(0.01));
        this.setOwner(null);
    }
}
