package ru.zarwlad.uth.generated.sn.service.msgcreation;

import ru.zarwlad.uth.generated.sn.*;
import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;
import ru.zarwlad.uth.generated.sn.storeddata.model.Location;
import ru.zarwlad.uth.generated.sn.storeddata.model.hierarchy.HieEntry;
import ru.zarwlad.uth.generated.sn.util.DateTimeUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreateReceiveImporterService {
    public static Documents createReceiveImporter(List<HieEntry> pallets,
                                                  Location location,
                                                  String invoiceNum,
                                                  String invoceDate,
                                                  String externalOperationId,
                                                  Location customLocation,
                                                  String docNum,
                                                  String docDate){
        Documents documents = new Documents();
        ReceiveImporter receiveImporter = new ReceiveImporter();
        receiveImporter.setSubjectId(location.getLocationId());
        receiveImporter.setOperationDate(DateTimeUtil.getGDateNow());
        receiveImporter.setInvoiceDate(invoceDate);
        receiveImporter.setInvoiceNum(invoiceNum);
        receiveImporter.setExternalOperationId(externalOperationId);

        receiveImporter.setCustomShipperId(customLocation.getLocationId());
        receiveImporter.setDocDate(docDate);
        receiveImporter.setDocNum(docNum);
        receiveImporter.setOrderDetails(createOrderDetails(pallets));
        documents.setReceiveImporter(receiveImporter);

        return  documents;
    }
    private static ReceiveImporter.OrderDetails createOrderDetails (List<HieEntry> highLevelCodes){
        ReceiveImporter.OrderDetails details = new ReceiveImporter.OrderDetails();
        List<ReceiveImporter.OrderDetails.Union> unions = highLevelCodes.stream()
                .map(CreateReceiveImporterService::createSsccUnion)
                .collect(Collectors.toList());
        details.getUnion().addAll(unions);

        return details;
    }

    private static ReceiveImporter.OrderDetails.Union createSsccUnion(HieEntry pallet){
        ReceiveImporter.OrderDetails.Union union = new ReceiveImporter.OrderDetails.Union();
        ReceiveImporter.OrderDetails.Union.SsccDetail ssccDetail = new ReceiveImporter.OrderDetails.Union.SsccDetail();
        ssccDetail.setSscc(pallet.getCode());

        List<Batch> batches = new ArrayList<>(pallet.getAllBatches());

        List<ReceiveImporter.OrderDetails.Union.SsccDetail.Detail> detailList = batches.stream()
                .map(x -> {
                    ReceiveImporter.OrderDetails.Union.SsccDetail.Detail detail =  new ReceiveImporter.OrderDetails.Union.SsccDetail.Detail();
                    detail.setGtin(x.getTradeItem().getGtin());
                    detail.setSeriesNumber(x.getBatch());
                    detail.setCostTaxes(BigDecimal.TEN);
                    detail.setVatValue(BigDecimal.ONE);
                    return detail;
                })
                .collect(Collectors.toList());
        ssccDetail.getDetail().addAll(detailList);
        union.setSsccDetail(ssccDetail);
        union.setCostTaxes(BigDecimal.TEN);
        union.setVatValue(BigDecimal.ONE);
        return union;
    }

}
