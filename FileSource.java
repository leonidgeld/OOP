import javax.annotation.processing.FilerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSource implements ISource {

    private int pos = 0;
    private boolean isEOL;
    private final Scanner scanner;
    private String line;
    private static final String FILE_EXTENSION = "ini";

    public FileSource(String fileName) throws FileNotFoundException {
        File input = new File(fileName);
        scanner = new Scanner(input);
        if (!checkFileExtension(input)) {
            throw new FileNotFoundException("Wrong file extension");
        }
        line = scanner.nextLine();
    }

    public char next() {
        if (hasNext()) {
            return line.charAt(pos++);
        } else {
            nextLine();
            return next();
        }
    }

    public boolean hasNext() {
        isEOL = pos >= line.length();
        return !isEOL;
    }

    @Override
    public void nextLine() {
        line = scanner.nextLine();
        pos = 0;
        isEOL = false;
    }

    @Override
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }


    @Override
    public boolean test(char expected) {
        if (!isEmpty()) {
            if (line.charAt(pos) == expected) {
                next();
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return pos == 0 && pos >= line.length();
    }


    @Override
    public String getSection() {
        StringBuilder section = new StringBuilder();
        while (!test(']')) {
            section.append(line.charAt(pos++));
        }
        return section.toString();
    }

    @Override
    public String getName() {
        StringBuilder name = new StringBuilder();
        while (!test('=')) {
            name.append(line.charAt(pos++));
            skipWhitespace();
        }
        return name.toString();
    }

    @Override
    public String getValue() {
        StringBuilder value = new StringBuilder();
        skipWhitespace();
        while (hasNext() && !test(';') && !test(' ')) {
            value.append(line.charAt(pos++));
        }
        return value.toString();
    }

    private void skipWhitespace() {
        while (test(' ') || test('\r') || test('\n') || test('\t')) {
        }
    }

    private boolean checkFileExtension(File file) {
        String fileName = file.getName();
        String fileExtension;
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            fileExtension = "";
        }

        return FILE_EXTENSION.equals(fileExtension);
    }
}
