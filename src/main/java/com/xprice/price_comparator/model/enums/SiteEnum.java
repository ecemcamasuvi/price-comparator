package com.xprice.price_comparator.model.enums;

public enum SiteEnum {
    MEDIAMARKT(1L, "Mediamarkt"),
    TEKNOSA(2L, "Teknosa"),
    PAZARAMA(3L, "Pazarama");

    private final Long id;
    private final String name;

    SiteEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
