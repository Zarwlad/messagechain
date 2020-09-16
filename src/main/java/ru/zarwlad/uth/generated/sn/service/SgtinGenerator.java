package ru.zarwlad.uth.generated.sn.service;

import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;

import java.util.*;

public class SgtinGenerator {

    public static List<String> generateSgtins(int count, Batch batch){
        List<String> sgtins = new ArrayList<>();
        String gtin = batch.getTradeItem().getGtin();

        for (int i = 0; i < count; i++) {
            UUID uuid = UUID.randomUUID();
            String newCode = uuid.toString().replace("-", "").substring(0, 13);
            sgtins.add(gtin + newCode);
        }

        return sgtins;
    }
}
