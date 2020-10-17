import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) {
        IniParser ini = new IniParser();
        try {
            IData data = ini.parse("E:\\ITMO\\ООП\\INI_Parser\\src\\valid.ini");
            data.tryGetInt("COMMON", "StatisterTimeMs");
            data.tryGetDouble("COMMON", "LegacyValue");
            data.tryGetString("NCMD", "SampleRate");
            data.tryGetDouble("NCMD", "EnableChannelControl");
            data.tryGetInt("COMMON", "DiskCachePath");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
