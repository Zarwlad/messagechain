package ru.zarwlad.uth.generated.sn.defconfigs;

import ru.zarwlad.uth.generated.sn.BaseConfnumInfoType;
import ru.zarwlad.uth.generated.sn.constants.BatchConst;
import ru.zarwlad.uth.generated.sn.constants.BusinessPartnerConst;
import ru.zarwlad.uth.generated.sn.constants.LocationConst;
import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;
import ru.zarwlad.uth.generated.sn.storeddata.model.BusinessPartner;
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
    public static final String invoiceNum = "foreignInvoic1e";
    public static final String invoiceDate = "01.02.2020";
    public static final String whAcceptNum = "acceptDoc";
    public static final String whAcceptDate = "01.09.2020";
    public static final String sellingDocNum = "123-test";
    public static final String sellingDocDate = "10.09.2020";

    private static BaseConfnumInfoType changeAbleConfnumInfo = new BaseConfnumInfoType();
    static {
        changeAbleConfnumInfo.setConfirmDoc("1");
        changeAbleConfnumInfo.setDocDate("01.01.2020");
        changeAbleConfnumInfo.setDocNum("123DocNum");
    }

    public static final BaseConfnumInfoType baseConfnumInfoType = changeAbleConfnumInfo;

}
