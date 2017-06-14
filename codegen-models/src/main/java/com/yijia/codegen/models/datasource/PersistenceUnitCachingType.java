package com.yijia.codegen.models.datasource;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "persistence-unit-caching-type")
@XmlEnum
public enum PersistenceUnitCachingType {

    ALL,
    NONE,
    ENABLE_SELECTIVE,
    DISABLE_SELECTIVE,
    UNSPECIFIED;

    public String value() {
        return name();
    }

    public static PersistenceUnitCachingType fromValue(String v) {
        return valueOf(v);
    }

}
