package com.product;

import java.util.List;

public class ConditionVariables {

    public String type;
    public List<String> values;

    public ConditionVariables(){};

    public ConditionVariables(String type, List<String> values){
        this.setType(type);
        this.setValues(values);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
