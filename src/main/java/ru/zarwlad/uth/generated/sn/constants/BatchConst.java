package ru.zarwlad.uth.generated.sn.constants;

import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;

import java.time.LocalDate;

public class BatchConst {
    public static final Batch batch1 = new Batch(TradeItemConst.tradeItem1, "01batchTI1", LocalDate.parse("2020-01-01"), LocalDate.parse("2021-01-01"));
    public static final Batch batch2 = new Batch(TradeItemConst.tradeItem1, "02batchTI1", LocalDate.parse("2020-01-01"), LocalDate.parse("2021-01-01"));
    public static final Batch batch3 = new Batch(TradeItemConst.tradeItem2, "03batchTI2", LocalDate.parse("2020-01-01"), LocalDate.parse("2021-01-01"));
    public static final Batch batch4 = new Batch(TradeItemConst.tradeItem2, "04batchTI2", LocalDate.parse("2020-01-01"), LocalDate.parse("2021-01-01"));
    public static final Batch batch5 = new Batch(TradeItemConst.tradeItem3, "05batchTI3", LocalDate.parse("2020-01-01"), LocalDate.parse("2021-01-01"));
    public static final Batch batch6 = new Batch(TradeItemConst.tradeItem3, "06batchTI3", LocalDate.parse("2020-01-01"), LocalDate.parse("2021-01-01"));
}
