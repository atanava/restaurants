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
    MENU_3(17),

    VOTE_1(18),
    VOTE_2(19),
    VOTE_3(20),
    VOTE_4(21),
    VOTE_5(22),
    VOTE_6(23),

//    today data
    MENU_4(24),
    MENU_5(25),

    VOTE_7(26),
    VOTE_8(27),

    NEW_ITEM(28),
    ;

    public final int id;

    DbSequence(int value) {
        this.id = START_SEQ + value;
    }
}
