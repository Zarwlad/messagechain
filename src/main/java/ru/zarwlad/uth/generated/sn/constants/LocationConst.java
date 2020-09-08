package ru.zarwlad.uth.generated.sn.constants;

import ru.zarwlad.uth.generated.sn.storeddata.model.BusinessPartner;
import ru.zarwlad.uth.generated.sn.storeddata.model.Location;

public class LocationConst {
    public static final Location foreignPlant = new Location(LegalEntityConst.legalEntity1, "legalForeignPlant");
    public static final Location customZone = new Location(BusinessPartnerConst.customBroker, "customZone1");
    public static final Location russianWarehouse = new Location(LegalEntityConst.legalEntity2, "rfWarehouse");
    public static final Location counterPartyClient = new Location(BusinessPartnerConst.clientReceiver, "counterPartyClient");
}
