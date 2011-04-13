package de.xzise.parliament.votes;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class Poll {

    public enum PossibleVote {
        YES,
        NO,
        ABSTENTION;
    }
    
    private final int id;
    private final Map<Player, PossibleVote> votes = new HashMap<Player, PossibleVote>();

    public Poll(int id) {
        this.id = id;
    }
    
    /**
     * Returns the unique id of this vote.
     * @return the unique id of this vote.
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Votes for a player. Returns true, if the player has already voted. Otherwise false.
     * @param player The voting player.
     * @param vote The voted result.
     * @return If the player has already voted (and the vote was overwritten/changed).
     */
    public boolean vote(Player player, PossibleVote vote) {
        return this.votes.put(player, vote) != null;
    }
    
    /**
     * Returns the best selected result. If there is no result it returns null.
     * @return the best selected result. If there is no result it returns null.
     */
    public PossibleVote getBest() {
        Map<PossibleVote, Integer> voteCounts = new EnumMap<Poll.PossibleVote, Integer>(PossibleVote.class);
        PossibleVote bestVote = null;
        for (PossibleVote votes : this.votes.values()) {
            Integer voteCount = voteCounts.get(votes);
            if (voteCount == null) {
                voteCount = 0;
            }
            voteCounts.put(votes, ++voteCount);
            
            // New best?
            Integer bestValue = voteCounts.get(bestVote);
            if (bestValue == null || bestValue < voteCount) {
                bestVote = votes;
            }
        }
        return bestVote;
    }
    
}
