package com.atanava.restaurants.util;

import com.atanava.restaurants.dto.VoteTo;
import com.atanava.restaurants.model.Vote;

import java.util.Collection;
import java.util.stream.Collectors;

public class VoteUtil {

    public static VoteTo createToFromVote(Vote vote) {
        return new VoteTo(vote.id(), vote.getUserId(), vote.getRestaurantId(), vote.getDate());
    }

    public static Collection<VoteTo> createTosFromVotes(Collection<Vote> votes, Collection<VoteTo> col) {
        return votes.stream()
                .map(VoteUtil::createToFromVote)
                .collect(Collectors.toCollection(() -> col));
    }

}
