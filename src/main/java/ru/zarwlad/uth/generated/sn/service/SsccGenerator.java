package ru.zarwlad.uth.generated.sn.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SsccGenerator {
    public static List<String> generateSsccs(int count){
        List<String> sscc = new ArrayList<>();

        Random l = new Random();
        for (int i = 0; i < count; i++) {
            long first = 0;
            List<String> nums = Arrays.asList(Long.toString(first).split(""));

            while (nums.size() < 18){
                first = Math.abs(l.nextLong());
                nums = Arrays.asList(Long.toString(first).split(""));
            }

            StringBuilder builder = new StringBuilder();
            for (int k = 0; k < 18; k++) {
                 builder.append(nums.get(k));
            }

            sscc.add(builder.toString());
        }
        return sscc;
    }
}
