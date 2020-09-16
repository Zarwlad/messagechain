package ru.zarwlad.uth.generated.sn;

import ru.zarwlad.uth.generated.sn.service.GenerateForeignChainService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        for (int i = 0; i < 280; i++) {
            GenerateForeignChainService.generateChainViaDefImportConfig1();
        }

    }
}
