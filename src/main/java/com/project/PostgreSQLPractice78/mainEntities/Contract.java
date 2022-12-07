package com.project.PostgreSQLPractice78.mainEntities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name= "contract")
public class Contract {

    @Id
    @Column(name = "contract_code")
    private Integer contractCode;
    @ManyToOne
    @JoinColumn(name = "client_code", referencedColumnName = "client_code")
    private Client clientCode;
    private Date contractDateCreation;
    private Integer amountOfGraphicsCard;
    private Integer totalPrice;
    private Integer contractType;

    public Integer getContractCode() {
        return contractCode;
    }

    public Client getClientCode() {
        return clientCode;
    }

    public Date getContractDateCreation() {
        return contractDateCreation;
    }

    public Integer getAmountOfGraphicsCard() {
        return amountOfGraphicsCard;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public Integer getContractType() {
        return contractType;
    }
}
