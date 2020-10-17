import java.io.FileNotFoundException;

public class IniParser {

    public IData parse(String filename) throws FileNotFoundException {
        ISource source = new FileSource(filename);
        IData data = new Data();
        String section = "";
        String name;
        String value;
        while (source.hasNextLine()) {

            if (source.test('[')) {
                section = source.getSection();
                data.addSection(section);
                source.nextLine();

            } else if (source.test(';')) {
                source.nextLine();

            } else if (source.isEmpty()) {
                source.nextLine();

            } else if (!source.test('\n')) {
                name = source.getName();
                value = source.getValue();
                data.addPair(section, name, value);
                source.nextLine();
            }

        }
        return data;
    }

}
