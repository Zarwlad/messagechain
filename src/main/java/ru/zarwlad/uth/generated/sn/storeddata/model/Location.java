package ru.zarwlad.uth.generated.sn.storeddata.model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class Location {
    private LegalEntity legalEntity;
    private BusinessPartner businessPartner;
    private String locationId;

    public Location(LegalEntity legalEntity, String locationId){
        this.legalEntity = legalEntity;
        this.locationId = locationId;
        this.businessPartner = null;
    }

    public Location(BusinessPartner businessPartner, String locationId){
        this.legalEntity = null;
        this.locationId = locationId;
        this.businessPartner = businessPartner;
    }
}
