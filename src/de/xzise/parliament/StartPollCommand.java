package de.xzise.parliament;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.xzise.parliament.votes.Poll;

public class StartPollCommand implements CommandExecutor {

    private final List<Poll> polls;
    
    public StartPollCommand(List<Poll> polls) {
        this.polls = polls;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // TODO Start a new poll
        int newId = this.polls.size();
        this.polls.add(new Poll(newId));
        return true;
    }

}
