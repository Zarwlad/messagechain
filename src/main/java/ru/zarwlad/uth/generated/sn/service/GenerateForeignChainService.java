package ru.zarwlad.uth.generated.sn.service;

import ru.zarwlad.uth.generated.sn.Documents;
import ru.zarwlad.uth.generated.sn.defconfigs.DefImportConfig1;
import ru.zarwlad.uth.generated.sn.service.msgcreation.CreateForeignEmissionService;
import ru.zarwlad.uth.generated.sn.service.msgcreation.CreateMultiPackService;
import ru.zarwlad.uth.generated.sn.storeddata.model.hierarchy.HieEntry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class GenerateForeignChainService {
    public static void generateChainViaDefImportConfig1() throws JAXBException, IOException {
        int palCount = 32;
        int boxCount = 60;
        int sgtinsInBox = 600;

        String externalOperationId = "1";

        JAXBContext context = JAXBContext.newInstance(Documents.class);


        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        ZonedDateTime start = ZonedDateTime.now();
        System.out.println(start);
        List<HieEntry> pallets = new ArrayList<>();
        for (int i = 0; i < palCount; i++) {
            System.out.println("генерирую иерархию " + i);
             pallets.add(CreatePalletEntry.createPalletEntry(boxCount, sgtinsInBox, DefImportConfig1.batch));
        }
        String folder = "C:\\Users\\vzaremba\\Desktop\\data\\1\\";
        for (HieEntry pallet : pallets) {
            System.out.println("создаю 321 + 915е для кода " + pallet.getCode());
            String sname = pallet.getCode();
            Path fEmiFileName = Paths.get(folder + "321_st-format_" + sname + ".xml");
            Files.createFile(fEmiFileName);

            Documents fEmi = CreateForeignEmissionService.createForeignEmi(
                    DefImportConfig1.foreignMahloc,
                    DefImportConfig1.control,
                    DefImportConfig1.packer,
                    DefImportConfig1.batch,
                    pallet,
                    externalOperationId
            );

            marshaller.marshal(fEmi, fEmiFileName.toFile());


            Documents mPackSgtin = CreateMultiPackService.createMultiPack(
                    false,
                    pallet,DefImportConfig1.foreignMahloc,
                    externalOperationId);
            Path mPackSgtinFileName = Paths.get(folder + "915_st-format_sgtins_" + sname + ".xml");
            Files.createFile(mPackSgtinFileName);

            marshaller.marshal(mPackSgtin, mPackSgtinFileName.toFile());

            Documents mPackSscc = CreateMultiPackService.createMultiPack(
                    true,
                    pallet,DefImportConfig1.foreignMahloc,
                    externalOperationId);
            Path mPackSsccFileName = Paths.get(folder + "915_st-format_sscc_" + sname + ".xml");
            Files.createFile(mPackSsccFileName);
            marshaller.marshal(mPackSscc, mPackSsccFileName.toFile());
        }

        ZonedDateTime end = ZonedDateTime.now();
        System.out.println(end);

        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());

    }
}
