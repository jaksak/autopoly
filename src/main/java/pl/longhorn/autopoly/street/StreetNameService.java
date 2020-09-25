package pl.longhorn.autopoly.street;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

@Service
public class StreetNameService {

    public static String getRandomLineFromFile(File f) throws FileNotFoundException {
        String result = null;
        Random rand = new Random();
        int n = 0;
        for (Scanner sc = new Scanner(f); sc.hasNext(); ) {
            ++n;
            String line = sc.nextLine();
            if (rand.nextInt(n) == 0)
                result = line;
        }

        return result;
    }

    @SneakyThrows
    public String getRandom() {
        var streetFile = ResourceUtils.getFile("classpath:streets.txt");
        return getRandomLineFromFile(streetFile);
    }
}
