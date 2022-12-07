package com.project.PostgreSQLPractice78.mainEntities;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @Column(name = "client_code")
    private String clientCode;
    @ManyToOne
    @JoinColumn(name = "organisation_code", referencedColumnName = "organisation_code" )
    private Organisation organisationCode;
    private String clientName;
    private Long clientTelephone;
    private Integer clientPostalCode;
    private String clientEmail;
    private String clientAddress;


    public String getClientCode() {
        return clientCode;
    }

    public Organisation getOrganisationCode() {
        return organisationCode;
    }

    public String getClientName() {
        return clientName;
    }

    public Long getClientTelephone() {
        return clientTelephone;
    }

    public Integer getClientPostalCode() {
        return clientPostalCode;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public String getClientAddress() {
        return clientAddress;
    }
}