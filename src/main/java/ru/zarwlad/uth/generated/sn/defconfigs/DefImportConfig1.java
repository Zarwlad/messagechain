package ru.zarwlad.uth.generated.sn.defconfigs;

import ru.zarwlad.uth.generated.sn.constants.BatchConst;
import ru.zarwlad.uth.generated.sn.constants.BusinessPartnerConst;
import ru.zarwlad.uth.generated.sn.constants.LegalEntityConst;
import ru.zarwlad.uth.generated.sn.constants.LocationConst;
import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;
import ru.zarwlad.uth.generated.sn.storeddata.model.BusinessPartner;
import ru.zarwlad.uth.generated.sn.storeddata.model.LegalEntity;
import ru.zarwlad.uth.generated.sn.storeddata.model.Location;

import java.time.LocalDate;

public class DefImportConfig1 {
    public static final Batch batch = BatchConst.batch1;
    public static final Location foreignMahloc = LocationConst.foreignPlant;
    public static final Location russianMahloc = LocationConst.russianWarehouse;
    public static final BusinessPartner control = BusinessPartnerConst.businessPartner;
    public static final BusinessPartner packer = BusinessPartnerConst.businessPartner;
    public static final BusinessPartner seller = BusinessPartnerConst.businessPartner;
    public static final Location customZone = LocationConst.customZone;
    public static final String invoiceNum = "foreignInvoice";
    public static final LocalDate invoiceDate = LocalDate.parse("2020-01-01");
    public static final String whAcceptNum = "acceptDoc";
    public static final LocalDate iwhAcceptDate = LocalDate.parse("2020-09-01");
}
