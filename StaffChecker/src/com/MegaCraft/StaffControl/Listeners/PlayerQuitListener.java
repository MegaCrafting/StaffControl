package com.MegaCraft.StaffControl.Listeners;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.MegaCraft.StaffControl.StaffControl;
import com.MegaCraft.StaffControl.Utils.GMHook;
import com.MegaCraft.StaffControl.Utils.ReadandWrite;

import net.milkbowl.vault.permission.Permission;

public class PlayerQuitListener implements Listener {
	StaffControl plugin;
	
	public PlayerQuitListener(StaffControl plugin) {
		this.plugin = plugin;
	}
	Date date = new Date();
	SimpleDateFormat time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
    @EventHandler(ignoreCancelled=true)
    public void onPlayerQuit(PlayerQuitEvent e) {		
        Player player = e.getPlayer();

    	final RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager()
				.getRegistration(Permission.class);
		final Permission perms = rsp.getProvider();
		final GMHook GM = StaffControl.getGM();

		if (perms == null) {
			System.out.println("Warning: perms wasn't found!");
		}
	    
	    
        for(String s : GM.getSubGroups(player)) {
	    	s.toLowerCase();
	    	switch(s) {
	    	case "helper":
	    	case "moderator":
	    	case "admin":
	    	case "megaadmin":
	    	case "manager":
	        	Date date = new Date();
				SimpleDateFormat time = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				ReadandWrite.writer(player.getName(), "Logged in", time.format(date), "Logins");
	    	}
	    }
    }
}