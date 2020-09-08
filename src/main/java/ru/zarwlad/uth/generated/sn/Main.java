package ru.zarwlad.uth.generated.sn;

import ru.zarwlad.uth.generated.sn.constants.BatchConst;
import ru.zarwlad.uth.generated.sn.service.CreatePalletEntry;
import ru.zarwlad.uth.generated.sn.service.SgtinGenerator;
import ru.zarwlad.uth.generated.sn.service.SsccGenerator;
import ru.zarwlad.uth.generated.sn.service.msgcreation.CreateForeignEmissionService;
import ru.zarwlad.uth.generated.sn.service.msgcreation.CreateMultiPackService;
import ru.zarwlad.uth.generated.sn.storeddata.model.Batch;
import ru.zarwlad.uth.generated.sn.storeddata.model.hierarchy.HieEntry;

import javax.print.Doc;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws JAXBException, DatatypeConfigurationException, IOException {
        JAXBContext context = JAXBContext.newInstance(Documents.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        Batch b = BatchConst.batch1;

        HieEntry pal = CreatePalletEntry.createPalletEntry(60, 30, b);

        Documents doc1 = CreateForeignEmissionService.createForeignEmi(b, pal);
        Documents doc2 = CreateMultiPackService.createMultiPack(false, pal);
        Documents doc3 = CreateMultiPackService.createMultiPack(true, pal);

        Path p1 = Paths.get("C:\\Users\\vzaremba\\Desktop\\data\\doc1.xml");
        if (Files.notExists(p1))
            Files.createFile(p1);
        Path p2 = Paths.get("C:\\Users\\vzaremba\\Desktop\\data\\doc2.xml");
        if (Files.notExists(p2))
            Files.createFile(p2);
        Path p3 = Paths.get("C:\\Users\\vzaremba\\Desktop\\data\\doc3.xml");
        if (Files.notExists(p2))
            Files.createFile(p2);

        m.marshal(doc1, p1.toFile());
        m.marshal(doc2, p2.toFile());
        m.marshal(doc3, p3.toFile());

    }
}
