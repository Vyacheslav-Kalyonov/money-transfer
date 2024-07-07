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

    // TODO сделать enumerated поле (пока оставлю текст)
    @Column(name = "type_action")
    private String typeAction;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private Account account;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "date")
    private Date date;


    @ManyToOne()
    @JoinColumn(name = "adressee", referencedColumnName = "id")
    private Account addressee;

    @Override
    public String toString() {
        return "Сумма: " + amount + "\nДата: " + date + "\nОперация: " + typeAction + "\nАдресат: " + addressee;
    }

    public void transfer(Account account, Account accountForTransfer, BigDecimal amount) {
        this.setAccount(account);
        this.setAddressee(accountForTransfer);
        this.setAmount(amount);
        this.setDate(new Date());
        this.setTypeAction("transfer");
    }
}
