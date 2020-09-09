package ru.zarwlad.uth.generated.sn;

import ru.zarwlad.uth.generated.sn.constants.BatchConst;
import ru.zarwlad.uth.generated.sn.service.CreatePalletEntry;
import ru.zarwlad.uth.generated.sn.service.GenerateForeignChainService;
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
    public static void main(String[] args) throws JAXBException, IOException {
        GenerateForeignChainService.generateChainViaDefImportConfig1();
    }
}
