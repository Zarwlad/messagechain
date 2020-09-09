package ru.zarwlad.uth.generated.sn.service.msgcreation;

import ru.zarwlad.uth.generated.sn.Accept;
import ru.zarwlad.uth.generated.sn.Documents;
import ru.zarwlad.uth.generated.sn.storeddata.model.Location;
import ru.zarwlad.uth.generated.sn.storeddata.model.hierarchy.HieEntry;
import ru.zarwlad.uth.generated.sn.util.DateTimeUtil;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.time.LocalDate;
import java.util.List;

public class CreateIncomeAcceptService {
    public static Documents createIncomeAccept(List<HieEntry> pallets,
                                               String docNum,
                                               LocalDate docDate,
                                               String extermalOperationId,
                                               Location receiverLocation,
                                               Location senderLocation){
        Documents documents = new Documents();
        Accept accept = new Accept();
        accept.setConfirmPaused(false);
        accept.setCounterpartyId(senderLocation.getLocationId());
        accept.setDocDate(docDate.toString());
        accept.setDocNum(docNum);
        accept.setOperationDate(DateTimeUtil.getGDateNow());
        accept.setExternalOperationId(extermalOperationId);
        accept.setSubjectId(receiverLocation.getLocationId());
        accept.setOrderDetails(createOrderDetails(pallets));
        documents.setAccept(accept);

        return documents;
    }

    private static Accept.OrderDetails createOrderDetails (List<HieEntry> highLevelCodes){
        Accept.OrderDetails details = new Accept.OrderDetails();

        highLevelCodes.forEach(x -> {
            String tagName = x.getCode().length() > 18 ? "sgtin" : "sscc";
            JAXBElement<String> code = new JAXBElement<>(new QName(tagName), String.class, x.getCode());
            details.getSgtinOrSscc().add(code);
        });
        return details;
    }
}
