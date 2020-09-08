package ru.zarwlad.uth.generated.sn.service.msgcreation;

import ru.zarwlad.uth.generated.sn.Documents;
import ru.zarwlad.uth.generated.sn.ForeignEmission;
import ru.zarwlad.uth.generated.sn.ForeignShipment;
import ru.zarwlad.uth.generated.sn.constants.BusinessPartnerConst;
import ru.zarwlad.uth.generated.sn.constants.LegalEntityConst;
import ru.zarwlad.uth.generated.sn.constants.LocationConst;
import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;
import ru.zarwlad.uth.generated.sn.storeddata.model.hierarchy.HieEntry;
import ru.zarwlad.uth.generated.sn.util.DateTimeUtil;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class CreateForeignShipmentService {
    public static Documents createForeignShip(List<HieEntry> pallets, String docNum, LocalDate docDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Documents doc = new Documents();
        ForeignShipment foreignShipment = new ForeignShipment();
        doc.setForeignShipment(foreignShipment);

        foreignShipment.setContractType(1);
        foreignShipment.setCustomReceiverId(LocationConst.customZone.getLocationId());
        foreignShipment.setDocNum(docNum);
        foreignShipment.setDocDate(docDate.toString());
        foreignShipment.setOperationDate(DateTimeUtil.getGDateNow());
        foreignShipment.setSellerId(BusinessPartnerConst.businessPartner.getCounterpartyId());
        foreignShipment.setReceiverId(LegalEntityConst.legalEntity2.getCounterpartyId());
        foreignShipment.setSubjectId(LegalEntityConst.legalEntity1.getCounterpartyId());

        ForeignShipment.OrderDetails orderDetails = createOrderDetails(pallets);
        foreignShipment.setOrderDetails(orderDetails);

        return doc;
    }
    
    private static ForeignShipment.OrderDetails createOrderDetails (List<HieEntry> highLevelCodes){
        ForeignShipment.OrderDetails details = new ForeignShipment.OrderDetails();

        highLevelCodes.forEach(x -> {
            String tagName = x.getCode().length() > 18 ? "sgtin" : "sscc";
            JAXBElement<String> code = new JAXBElement<>(new QName(tagName), String.class, x.getCode());
            details.getSgtinOrSscc().add(code);
        });
        return details;
    }
}
