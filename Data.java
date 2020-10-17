import java.lang.invoke.WrongMethodTypeException;
import java.util.HashMap;

public class Data implements IData {
    private final HashMap<String, HashMap<String, String>> data;

    public Data() {
        data = new HashMap<>();
    }

    @Override
    public void addSection(String section) {
        data.put(section, new HashMap<>());
    }

    @Override
    public void addPair(String section, String name, String value) {
        if (data.get(section) != null) {
            data.get(section).put(name, value);
        }
    }

    @Override
    public void tryGetDouble(String section, String name) {
        Double result = null;
        if (data.get(section) != null) {
            if (data.get(section).get(name) != null) {
                result = Double.parseDouble(data.get(section).get(name));
            } else {
                throw new WrongMethodTypeException("Parameter with the name: \"" + name + "\" does not exist");
            }
        } else {
            throw new WrongMethodTypeException("Section with the name: \"" + section + "\" does not exist");
        }
        System.out.println(result);
    }

    @Override
    public void tryGetString(String section, String name) {
        String result;
        if (data.get(section) != null) {
            if (data.get(section).get(name) != null) {
                result = data.get(section).get(name);
            } else {
                throw new WrongMethodTypeException("Parameter with the name: \"" + name + "\" does not exist");
            }
        } else {
            throw new WrongMethodTypeException("Section with the name: \"" + section + "\" does not exist");
        }
        if (result != null) {
            System.out.println(result);
        }
    }

    @Override
    public void tryGetInt(String section, String name) {
        Integer result = null;
        if (data.get(section) != null) {
            if (data.get(section).get(name) != null) {
                result = Integer.parseInt(data.get(section).get(name));
            } else {
                throw new WrongMethodTypeException("Parameter with the name: \"" + name + "\" does not exist");
            }
        } else {
            throw new WrongMethodTypeException("Section with the name: \"" + section + "\" does not exist");
        }
        System.out.println(result);
    }
}
