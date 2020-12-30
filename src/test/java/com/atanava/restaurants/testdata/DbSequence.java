package com.atanava.restaurants.testdata;

import static com.atanava.restaurants.model.AbstractBaseEntity.START_SEQ;

public enum DbSequence {
    NOT_FOUND(100),

    ADMIN(0),
    USER_1(1),
    USER_2(2),

    RESTAURANT_1(3),
    RESTAURANT_2(4),

    DISH_1(5),
    DISH_2(6),
    DISH_3(7),
    DISH_4(8),
    DISH_5(9),
    DISH_6(10),
    DISH_7(11),
    DISH_8(12),
    DISH_9(13),
    DISH_10(14),

    MENU_1(15),
    MENU_2(16),

    VOTE_1(17),
    VOTE_2(18),
    VOTE_3(19),
    VOTE_4(20),
    VOTE_5(21),
    VOTE_6(22),

//    today data
    MENU_3(23),

    VOTE_7(24),
    VOTE_8(25),

    NEW_ITEM(26),
    ;

    public final int id;

    DbSequence(int value) {
        this.id = START_SEQ + value;
    }
}
