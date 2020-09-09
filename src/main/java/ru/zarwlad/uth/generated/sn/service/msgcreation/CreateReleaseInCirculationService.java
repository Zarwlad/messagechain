package ru.zarwlad.uth.generated.sn.service.msgcreation;

import ru.zarwlad.uth.generated.sn.Documents;
import ru.zarwlad.uth.generated.sn.ForeignShipment;
import ru.zarwlad.uth.generated.sn.ReleaseInCirculation;
import ru.zarwlad.uth.generated.sn.storeddata.model.Location;
import ru.zarwlad.uth.generated.sn.storeddata.model.hierarchy.HieEntry;
import ru.zarwlad.uth.generated.sn.util.DateTimeUtil;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import java.util.List;

public class CreateReleaseInCirculationService {
    public static Documents createReleaseInCirc(List<HieEntry> pallets,
                                                Location location,
                                                String externalOperationId){
        Documents documents = new Documents();
        ReleaseInCirculation releaseInCirculation = new ReleaseInCirculation();
        documents.setReleaseInCirculation(releaseInCirculation);

        releaseInCirculation.setSubjectId(location.getLocationId());
        releaseInCirculation.setOperationDate(DateTimeUtil.getGDateNow());
        releaseInCirculation.setExternalOperationId(externalOperationId);

        ReleaseInCirculation.ReleaseInfo releaseInfo = new ReleaseInCirculation.ReleaseInfo();
        releaseInfo.setConfirmationNum("testConNum");
        releaseInfo.setDocNum("test");
        releaseInfo.setDocDate("2020-01-01");

        releaseInCirculation.setReleaseInfo(releaseInfo);
        releaseInCirculation.setSigns(createSigns(pallets));

        return documents;
    }

    private static ReleaseInCirculation.Signs createSigns (List<HieEntry> highLevelCodes){
        ReleaseInCirculation.Signs signs = new ReleaseInCirculation.Signs();

        highLevelCodes.forEach(x -> {
            String tagName = x.getCode().length() > 18 ? "sgtin" : "sscc";
            JAXBElement<String> code = new JAXBElement<>(new QName(tagName), String.class, x.getCode());
            signs.getSgtinOrSscc().add(code);
        });
        return signs;
    }
}
