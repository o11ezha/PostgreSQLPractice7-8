package com.project.PostgreSQLPractice78.mainEntities;

import javax.persistence.*;

@Entity
@Table (name = "organisation")
public class Organisation {

    @Id
    @Column(name = "organisation_code")
    private Integer organisationCode;
    private String organisationName;
    private Long organisationTelephone;
    private Integer organisationPostalCode;
    private String organisationEmail;
    private String organisationAddress;

    public Integer getOrganisationCode() {
        return organisationCode;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public Long getOrganisationTelephone() {
        return organisationTelephone;
    }

    public Integer getOrganisationPostalCode() {
        return organisationPostalCode;
    }

    public String getOrganisationEmail() {
        return organisationEmail;
    }

    public String getOrganisationAddress() {
        return organisationAddress;
    }
}
