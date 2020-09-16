package ru.zarwlad.uth.generated.sn.service.msgcreation;

import ru.zarwlad.uth.generated.sn.Documents;
import ru.zarwlad.uth.generated.sn.ForeignEmission;
import ru.zarwlad.uth.generated.sn.constants.BusinessPartnerConst;
import ru.zarwlad.uth.generated.sn.constants.LegalEntityConst;
import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;
import ru.zarwlad.uth.generated.sn.storeddata.model.BusinessPartner;
import ru.zarwlad.uth.generated.sn.storeddata.model.LegalEntity;
import ru.zarwlad.uth.generated.sn.storeddata.model.Location;
import ru.zarwlad.uth.generated.sn.storeddata.model.hierarchy.HieEntry;
import ru.zarwlad.uth.generated.sn.util.DateTimeUtil;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

public class CreateForeignEmissionService {
    public static Documents createForeignEmi(Location location,
                                             BusinessPartner control,
                                             BusinessPartner packer,
                                             Batch b,
                                             HieEntry pallet,
                                             String externalOperationId){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Documents doc = new Documents();
        ForeignEmission foreignEmission = new ForeignEmission();
        doc.setForeignEmission(foreignEmission);

        foreignEmission.setSubjectId(location.getLocationId());
        foreignEmission.setOperationDate(DateTimeUtil.getGDateNow());
        foreignEmission.setExternalOperationId(externalOperationId);

        foreignEmission.setPackingId(control.getCounterpartyId());
        foreignEmission.setControlId(packer.getCounterpartyId());
        foreignEmission.setSeriesNumber(b.getBatch());
        foreignEmission.setExpirationDate(b.getDateExp().format(formatter));
        foreignEmission.setGtin(b.getTradeItem().getGtin());

        ForeignEmission.Signs signs = new ForeignEmission.Signs();
        signs.getSgtin().addAll(pallet.getAllSgtins().stream().map(HieEntry::getCode).collect(Collectors.toList()));
        foreignEmission.setSigns(signs);

        return doc;
    }
}
