package de.xzise.parliament;

import java.util.LinkedList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import de.xzise.parliament.votes.Poll;

public class Parliament extends JavaPlugin {
    
    private List<Poll> polls;

    @Override
    public void onDisable() {
        this.getServer().getLogger().info("Parliament disabled");
    }

    @Override
    public void onEnable() {
        this.polls = new LinkedList<Poll>();
        
        PlayerListener listener = new PlayerListener() {
            @Override
            public void onPlayerInteract(PlayerInteractEvent event) {
                Player p = event.getPlayer();
                Block b = event.getClickedBlock();
                if (event.getAction() == Action.LEFT_CLICK_BLOCK && b.getType() == Material.JUKEBOX && p.getItemInHand().getType() == Material.SIGN) {
                    p.sendMessage("You voted \\o/");
                }
            }
        };
        
        this.getServer().getPluginManager().registerEvent(Event.Type.PLAYER_INTERACT, listener, Priority.Normal, this);
        this.getServer().getLogger().info("Parliament enabled");
        
        
        this.getCommand("poll").setExecutor(new StartPollCommand(this.polls));
    }

}
