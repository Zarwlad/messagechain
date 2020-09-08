package ru.zarwlad.uth.generated.sn.service.msgcreation;

import ru.zarwlad.uth.generated.sn.Documents;
import ru.zarwlad.uth.generated.sn.ForeignEmission;
import ru.zarwlad.uth.generated.sn.constants.BusinessPartnerConst;
import ru.zarwlad.uth.generated.sn.constants.LegalEntityConst;
import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;
import ru.zarwlad.uth.generated.sn.storeddata.model.hierarchy.HieEntry;
import ru.zarwlad.uth.generated.sn.util.DateTimeUtil;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

public class CreateForeignEmissionService {
    public static Documents createForeignEmi(Batch b, HieEntry pallet){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Documents doc = new Documents();
        ForeignEmission foreignEmission = new ForeignEmission();
        doc.setForeignEmission(foreignEmission);

        foreignEmission.setSubjectId(LegalEntityConst.legalEntity.getCounterpartyId());
        foreignEmission.setOperationDate(DateTimeUtil.getGDateNow());

        foreignEmission.setPackingId(BusinessPartnerConst.businessPartner.getCounterpartyId());
        foreignEmission.setControlId(BusinessPartnerConst.businessPartner.getCounterpartyId());
        foreignEmission.setSeriesNumber(b.getBatch());
        foreignEmission.setExpirationDate(b.getDateExp().format(formatter));
        foreignEmission.setGtin(b.getTradeItem().getGtin());

        ForeignEmission.Signs signs = new ForeignEmission.Signs();
        signs.getSgtin().addAll(pallet.getAllSgtins().stream().map(HieEntry::getCode).collect(Collectors.toList()));
        foreignEmission.setSigns(signs);

        return doc;
    }
}
