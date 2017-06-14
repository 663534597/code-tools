package com.yijia.codegen.models.datasource;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "persistence-unit-transaction-type")
@XmlEnum
public enum PersistenceUnitTransactionType {

    JTA,
    RESOURCE_LOCAL;

    public String value() {
        return name();
    }

    public static PersistenceUnitTransactionType fromValue(String v) {
        return valueOf(v);
    }

}
