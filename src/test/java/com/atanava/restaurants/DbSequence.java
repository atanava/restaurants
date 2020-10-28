package com.atanava.restaurants;

import static com.atanava.restaurants.model.AbstractBaseEntity.START_SEQ;

public enum DbSequence {
    NOT_FOUND(10),

    ADMIN_ID(START_SEQ),
    USER1_ID(START_SEQ + 1),
    USER2_ID(START_SEQ + 2),

    RESTAURANT1_ID(START_SEQ + 3),
    RESTAURANT2_ID(START_SEQ + 4),

    DISH1_ID(START_SEQ + 5),
    DISH2_ID(START_SEQ + 6),
    DISH3_ID(START_SEQ + 7),
    DISH4_ID(START_SEQ + 8),
    DISH5_ID(START_SEQ + 9),

    MENU_ITEM6_ID(START_SEQ + 15),
    MENU_ITEM7_ID(START_SEQ + 16),
    MENU_ITEM8_ID(START_SEQ + 17),
    MENU_ITEM9_ID(START_SEQ + 18),
    MENU_ITEM10_ID(START_SEQ + 19),

    VOTE1_ID(START_SEQ + 25),
    VOTE2_ID(START_SEQ + 26),
    ;

    public int value;

    DbSequence(int value) {
        this.value = value;
    }
}
