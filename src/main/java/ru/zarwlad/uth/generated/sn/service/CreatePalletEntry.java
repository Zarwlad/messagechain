package ru.zarwlad.uth.generated.sn.service;

import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;
import ru.zarwlad.uth.generated.sn.storeddata.model.hierarchy.HieEntry;

import java.util.ArrayList;
import java.util.List;

public class CreatePalletEntry {
    public static HieEntry createPalletEntry (int boxCount, int sgtinInBox, Batch batch){
        List<String> ssccCodes = SsccGenerator.generateSsccs(boxCount + 1);

        List<HieEntry> boxes = new ArrayList<>();
        for (int i = 0; i < boxCount; i++) {
            List<String> sgtins = SgtinGenerator.generateSgtins(sgtinInBox, batch);
            HieEntry ssccBox = HieEntry.createSscc(ssccCodes.remove(0), HieEntry.createSgtins(sgtins, batch));
            boxes.add(ssccBox);
        }
        return HieEntry.createSscc(ssccCodes.remove(0), boxes);
    }
}
