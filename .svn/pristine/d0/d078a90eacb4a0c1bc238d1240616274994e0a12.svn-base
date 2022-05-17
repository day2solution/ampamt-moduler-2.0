package com.ampamt.moduler.constant;

import java.util.LinkedHashMap;
import java.util.Map;

public enum CommandType {

    B2C("B2C"),
    B2B("B2B"),
    B2B2C("B2B2C");

    private String commandType;

    public class Constant {
        private Constant() {
        }
        public static final String COMMAND_HEADER = "Command";
    }

    CommandType(final String commandType) {
        this.commandType = commandType;
    }

    public String toValue() {
        return this.commandType;
    }

    private static final Map<String, CommandType> BY_VALUE_MAP = new LinkedHashMap<>();
    static {
        for (CommandType commandEnum : CommandType.values()) {
            BY_VALUE_MAP.put(commandEnum.commandType, commandEnum);
        }
    }

    public static CommandType getEnumByValue(final String value) {
        if(BY_VALUE_MAP.containsKey(value)){
            return BY_VALUE_MAP.get(value);
        }
        return CommandType.B2B;
    }
}
