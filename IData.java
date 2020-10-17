public interface IData {
    void addSection(String section);

    void addPair(String section, String name, String value);

    void tryGetInt(String section, String name);

    void tryGetDouble(String section, String name);

    void tryGetString(String section, String name);
}
