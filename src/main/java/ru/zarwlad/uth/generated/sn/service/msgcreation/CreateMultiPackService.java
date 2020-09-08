package ru.zarwlad.uth.generated.sn.service.msgcreation;

import ru.zarwlad.uth.generated.sn.Documents;
import ru.zarwlad.uth.generated.sn.MultiPack;
import ru.zarwlad.uth.generated.sn.constants.LegalEntityConst;
import ru.zarwlad.uth.generated.sn.storeddata.model.hierarchy.HieEntry;
import ru.zarwlad.uth.generated.sn.util.DateTimeUtil;

import java.util.stream.Collectors;

public class CreateMultiPackService {
    public static Documents createMultiPack (boolean aggSscc, HieEntry pallet){
        Documents doc = new Documents();
        MultiPack multiPack = new MultiPack();
        multiPack.setSubjectId(LegalEntityConst.legalEntity.getCounterpartyId());
        multiPack.setOperationDate(DateTimeUtil.getGDateNow());
        doc.setMultiPack(multiPack);

        if (!aggSscc){
            MultiPack.BySgtin bySgtin = new MultiPack.BySgtin();

            for (HieEntry box : pallet.getNested()) {
                MultiPack.BySgtin.Detail detail = new MultiPack.BySgtin.Detail();
                detail.setSscc(box.getCode());
                MultiPack.BySgtin.Detail.Content content = new MultiPack.BySgtin.Detail.Content();
                content.getSgtin().addAll(box.getNested().stream()
                        .map(HieEntry::getCode).collect(Collectors.toList()));
                detail.setContent(content);
                bySgtin.getDetail().add(detail);
            }
            multiPack.setBySgtin(bySgtin);
        }
        else {
            MultiPack.BySscc bySscc = new MultiPack.BySscc();
            MultiPack.BySscc.Detail detail = new MultiPack.BySscc.Detail();
            detail.setSscc(pallet.getCode());

            MultiPack.BySscc.Detail.Content content = new MultiPack.BySscc.Detail.Content();
            content.getSscc().addAll(pallet.getNested().stream()
                    .map(HieEntry::getCode).collect(Collectors.toList()));
            detail.setContent(content);
            bySscc.getDetail().add(detail);

            multiPack.setBySscc(bySscc);
        }

        return doc;
    }
}
