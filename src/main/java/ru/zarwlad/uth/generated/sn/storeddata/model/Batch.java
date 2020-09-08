package ru.zarwlad.uth.generated.sn.storeddata.model;

import lombok.*;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Batch {
    private TradeItem tradeItem;
    private String batch;
    private LocalDate dateMdf;
    private LocalDate dateExp;
}
