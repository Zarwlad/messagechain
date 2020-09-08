package ru.zarwlad.uth.generated.sn.service;

import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SgtinGenerator {
    private static final String[] chars = {"a", "b", "c", "d", "e", "f", "h", "g", "x", "y", "z"};

    public static List<String> generateSgtins(int count, Batch batch){
        List<String> sgtins = new ArrayList<>();
        String gtin = batch.getTradeItem().getGtin();
        Random random = new Random(0);

        for (int i = 0; i < count; i++) {
            long l = Math.abs(random.nextLong());
            String[] num = Long.toString(l).split("");

            StringBuilder ser = new StringBuilder();
            ser.append(gtin);

            if (num.length == 13){
                ser.append(l);
            }
            else if (num.length > 13){
                for (int j = 0; j < 13; j++) {
                    ser.append(num[j]);
                }
            }
            else {
                int k = num.length;
                ser.append(l);
                for (; k < 13; k++) {
                    int charI = random.nextInt(chars.length);
                    ser.append(chars[charI]);
                }
            }

            sgtins.add(ser.toString());
        }

        return sgtins;
    }
}
