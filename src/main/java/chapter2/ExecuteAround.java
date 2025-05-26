package chapter2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ExecuteAround {

    public static final String FILE = Objects.requireNonNull(ExecuteAround.class.getResource("/test.txt")).getFile();

    public static void main(String[] args) throws FileNotFoundException {
        String res = processFileLimited();
        System.out.println(res);
        System.out.println("-------------------------------------");
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        System.out.println(oneLine);
        String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(twoLine);
        String allLine =  processFile((BufferedReader br) -> {
            StringBuilder buider = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                buider.append(line);
            }
            return buider.toString();
        });
        System.out.println(allLine);
    }

    public static String processFileLimited() {
        try(BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return  br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String processFile(BufferedReaderProccesor p) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE));
            return p.processor(br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    interface BufferedReaderProccesor {
        String processor(BufferedReader b) throws IOException;
    }
}
