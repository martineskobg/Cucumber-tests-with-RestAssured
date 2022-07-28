package step.definitions.parameters;

import io.cucumber.java.ParameterType;

public class ParameterTypes {

    @ParameterType(value = "true|True|TRUE|false|False|FALSE")
    public Boolean booleanValue(String value) {
        return Boolean.valueOf(value);
    }
}
