package com.spring.project.users.userinfo;

import com.spring.project.users.User;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_info")
public class CardInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User userId;

    @Column(name = "cardNum", nullable = false)
    private String cardNum;
    
    @Column(name = "cardName", nullable = false)
    private String cardName;
    
    @Column(name = "expDate", nullable = false)
    private String expirationDate;

    @Column(name = "secCode", nullable = false)
    private String securityCode;

    public CardInfo(User userId, String cardNum, String cardName, String expirationDate, String securityCode) {
        this.userId = userId;
        this.cardNum = cardNum;
        this.cardName = cardName;
        this.expirationDate = expirationDate;
        this.securityCode = securityCode;
    }

    public CardInfo() {}

    // Setters


    public void setUserId(User userId) {
        this.userId = userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCardNumber(String num) {
        this.cardNum = num;
    }

    public void setCardName(String name) {
        this.cardName = name;
    }

    public void setExpirationDate(String date) {
        this.expirationDate = date;
    }

    public void setSecurityCode(String code) {
        this.securityCode = code;
    }

    // Getters

    public User getUserId() {
        return this.userId;
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return this.cardNum;
    }
    public String getCardName() {
        return this.cardName;
    }
    public String getExpirationDate() {
        return this.expirationDate;
    }
    public String getSecurityCode() {
        return this.securityCode;
    }

}
