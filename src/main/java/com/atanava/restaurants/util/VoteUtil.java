package com.atanava.restaurants.util;

import com.atanava.restaurants.dto.VoteTo;
import com.atanava.restaurants.model.Vote;

public class VoteUtil {

    public static VoteTo createToFromVote(Vote vote) {
        return new VoteTo(vote.id(), vote.getDate(), vote.getUser().id(), vote.getRestaurant().id());
    }

}
