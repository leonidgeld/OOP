public interface ISource {
    void nextLine();

    boolean hasNextLine();

    boolean isEmpty();

    boolean test(char expected);

    String getSection();

    String getName();

    String getValue();
}
