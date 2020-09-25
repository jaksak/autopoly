package pl.longhorn.autopoly.street;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

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
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        var streetUrl = classloader.getResource("streets.txt");
        assert streetUrl != null;
        var streetFile = new File(streetUrl.getFile());
        return getRandomLineFromFile(streetFile);
    }
}
