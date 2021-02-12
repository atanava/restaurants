package com.atanava.restaurants.testdata;

import com.atanava.restaurants.TestMatcher;
import com.atanava.restaurants.dto.RestaurantTo;
import com.atanava.restaurants.model.Restaurant;
import com.atanava.restaurants.util.RestaurantUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.atanava.restaurants.testdata.DbSequence.*;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {

    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Restaurant.class, "dishes", "menus", "votes");

    public static TestMatcher<RestaurantTo> REST_TO_MATCHER = TestMatcher.usingAssertions(RestaurantTo.class,
//     No need use ignoringAllOverriddenEquals, see https://assertj.github.io/doc/#breaking-changes
            (a, e) -> assertThat(a).usingRecursiveComparison()
                    .ignoringFields("todayMenuTo.restaurantId", "todayMenuTo.dishTos.restaurantId")
                    .isEqualTo(e),
            (a, e) -> {
                throw new UnsupportedOperationException();
            });

    public static TestMatcher<RestaurantTo> REST_TO_WITHOUT_MENUS_MATCHER = TestMatcher.usingEqualsComparator(RestaurantTo.class);


    public static final Restaurant rest1 = new Restaurant(RESTAURANT_1.id, "Troika");
    public static final Restaurant rest2 = new Restaurant(RESTAURANT_2.id, "Gloria");

    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }

    public static RestaurantTo getRestTo() {
        return RestaurantUtil.createToFromRestaurant(rest1, MenuTestData.menuOfTroika2);
    }

    public static List<RestaurantTo> getAllExpRestTos() {
        return Stream.of(rest2, rest1)
                .map(r -> RestaurantUtil.createToFromRestaurant(r, null))
                .collect(Collectors.toList());
    }

    public static Restaurant getDuplicate() {
        return new Restaurant(null, "Troika");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(rest1);
        updated.setName("UpdatedName");
        return updated;
    }


}
