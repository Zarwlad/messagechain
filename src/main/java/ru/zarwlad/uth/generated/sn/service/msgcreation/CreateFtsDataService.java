package ru.zarwlad.uth.generated.sn.service.msgcreation;

import ru.zarwlad.uth.generated.sn.*;
import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;
import ru.zarwlad.uth.generated.sn.storeddata.model.LegalEntity;
import ru.zarwlad.uth.generated.sn.storeddata.model.Location;
import ru.zarwlad.uth.generated.sn.storeddata.model.hierarchy.HieEntry;
import ru.zarwlad.uth.generated.sn.util.DateTimeUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreateFtsDataService {
    public static Documents createFtsData(List<HieEntry> pallets,
                                          Location location,
                                          String invoiceNum,
                                          LocalDate invoceDate,
                                          String externalOperationId,
                                          BaseConfnumInfoType confnumInfoType){
        Documents documents = new Documents();
        FtsData ftsData = new FtsData();
        ftsData.setSubjectId(location.getLocationId());
        ftsData.setOperationDate(DateTimeUtil.getGDateNow());
        ftsData.setInvoiceDate(invoceDate.toString());
        ftsData.setInvoiceDate(invoiceNum);
        ftsData.setExternalOperationId(externalOperationId);
        ftsData.setCustomProcedureCode("40");

        BaseFtsInfoType baseFtsInfoType = new BaseFtsInfoType();
        baseFtsInfoType.setCustomsCode("10130060");
        baseFtsInfoType.setGtdNumber("testTd");
        baseFtsInfoType.setRegistrationDate("2020-04-16");
        ftsData.setFtsInfo(baseFtsInfoType);

        ftsData.setOrderDetails(createOrderDetails(pallets, confnumInfoType));
        documents.setFtsData(ftsData);

        return  documents;
    }

    private static FtsData.OrderDetails createOrderDetails (List<HieEntry> highLevelCodes, BaseConfnumInfoType confnumInfoType){
        FtsData.OrderDetails details = new FtsData.OrderDetails();
        List<FtsData.OrderDetails.Union> unions = highLevelCodes.stream()
                .map(x -> createSsccUnion(x, confnumInfoType))
                .collect(Collectors.toList());
        details.getUnion().addAll(unions);

        return details;
    }

    private static FtsData.OrderDetails.Union createSsccUnion(HieEntry pallet, BaseConfnumInfoType confnumInfoType){
        FtsData.OrderDetails.Union union = new FtsData.OrderDetails.Union();
        FtsData.OrderDetails.Union.SsccDetail ssccDetail = new FtsData.OrderDetails.Union.SsccDetail();
        ssccDetail.setSscc(pallet.getCode());

        List<Batch> batches = new ArrayList<>(pallet.getAllBatches());

        List<FtsData.OrderDetails.Union.SsccDetail.Detail> detailList = batches.stream()
                .map(x -> {
            FtsData.OrderDetails.Union.SsccDetail.Detail detail =  new FtsData.OrderDetails.Union.SsccDetail.Detail();
            detail.setGtin(x.getTradeItem().getGtin());
            detail.setSeriesNumber(x.getBatch());
            detail.setConfnumInfo(confnumInfoType);
            detail.setCustomsValue(BigDecimal.TEN);
            return detail;
        })
        .collect(Collectors.toList());
        union.setSsccDetail(ssccDetail);
        union.setCustomsValue(BigDecimal.TEN);
        union.setConfnumInfo(confnumInfoType);
        return union;
    }
}
