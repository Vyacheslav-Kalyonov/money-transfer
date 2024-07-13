package com.example.moneyTransfer.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Action")
@Data
public class Action {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type_action")
    private String typeAction;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private Account account;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    @ManyToOne
    @JoinColumn(name = "addressee", referencedColumnName = "id")
    private Account addressee;

    public void transfer() {
        this.setDate(new Date());
        this.setTypeAction(TypeActionEnum.TRANSFER.name());
    }

    public void addMoney(Account account) {
        this.setDate(new Date());
        this.setTypeAction(TypeActionEnum.ADD.name());
        this.setAccount(account);
        this.setAddressee(account);
    }

    @Override
    public String toString() {
        return "";
    }
}
