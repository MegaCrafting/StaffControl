package com.MegaCraft.StaffControl;

import java.io.File;
import java.util.logging.Logger;

import org.anjocaido.groupmanager.GroupManager;

import com.MegaCraft.StaffControl.Command.Commands;
import com.MegaCraft.StaffControl.Listeners.PlayerChatListener;
import com.MegaCraft.StaffControl.Listeners.PlayerJoinListener;
import com.MegaCraft.StaffControl.Listeners.PlayerQuitListener;
import com.MegaCraft.StaffControl.Utils.GMHook;

public class StaffControl extends org.bukkit.plugin.java.JavaPlugin {
	public static Logger log;
	public static StaffControl plugin;
	private GroupManager groupManager;
	private static GMHook GM = null;
	
	public GroupManager getGroupManager() {
		return groupManager;
	}

	public void setGroupManager(final GroupManager groupManager) {
		this.groupManager = groupManager;
	}
		
	public static GMHook getGM() {
		return StaffControl.GM;
	}

	public static void setGM(final GMHook gM) {
		StaffControl.GM = gM;
	}
	
	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
		StaffControl.plugin = this;
		StaffControl.GM = new GMHook(StaffControl.plugin);
		StaffControl.GM.onPluginEnable(null);
		
		final File mainfile = new File(plugin.getDataFolder().toString());
		if (!mainfile.exists()) {
			mainfile.mkdir();
		}
		
		final File logins = new File(plugin.getDataFolder().toString() + "/Logins/");
		if (!logins.exists()) {
			logins.mkdir();
		}
		
		final File afks = new File(plugin.getDataFolder().toString() + "/AFKs/");
		if (!afks.exists()) {
			afks.mkdir();
		}
		new Commands(this);
		getServer().getPluginManager().registerEvents(new PlayerChatListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerQuitListener(this), this);
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
	}
}

/*
 * Location:
 * C:\Users\Chandler\Desktop\KorraEdits\MegaBending.jar!\com\MegaCraft\
 * MegaBending\MegaBending.class Java compiler version: 7 (51.0) JD-Core
 * Version: 0.7.1
 */