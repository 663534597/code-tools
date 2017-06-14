package com.yijia.codegen.models.datasource;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "persistence-unit-validation-mode-type")
@XmlEnum
public enum PersistenceUnitValidationModeType {

    AUTO,
    CALLBACK,
    NONE;

    public String value() {
        return name();
    }

    public static PersistenceUnitValidationModeType fromValue(String v) {
        return valueOf(v);
    }

}
