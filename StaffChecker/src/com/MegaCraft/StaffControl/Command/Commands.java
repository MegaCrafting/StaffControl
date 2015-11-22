package com.MegaCraft.StaffControl.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;

import com.MegaCraft.StaffControl.StaffControl;

import java.util.Arrays;
import java.util.List;

public class Commands {

	private StaffControl plugin;

	public static boolean isToggledForAll = false;

	public Commands(StaffControl plugin) {
		this.plugin = plugin;
		init();
	}

	public static String[] commandaliases = { "staffcontrol", "check", "c", "staffc"};
	
	/*
	 * Command
	 */
	public static String[] loginaliases = { "login", "l" };
	public static String[] helpaliases = { "help", "h" };
	private void init() {
		PluginCommand StaffControl = plugin.getCommand("staffcontrol");
		new LoginCommand();
		new HelpCommand();

		/**
		 * Set of all of the Classes which extend Command
		 */

		CommandExecutor exe;

		exe = new CommandExecutor() {
			@Override
			public boolean onCommand(CommandSender s, Command c, String label, String[] args) {
				for (int i = 0; i < args.length; i++) {
					args[i] = args[i];
				}

				if (args.length == 0 && Arrays.asList(commandaliases).contains(label.toLowerCase())) {
					s.sendMessage(ChatColor.DARK_AQUA + "/staffcontrol help [Command]" + ChatColor.AQUA + "Display help.");
					s.sendMessage(ChatColor.DARK_AQUA + "Aliases: " + ChatColor.AQUA + "staffcontrol, check, c, staffc");
					return true;
				}

				List<String> sendingArgs = Arrays.asList(args).subList(1, args.length);
				for (SCCommand command : SCCommand.instances.values()) {
					if (Arrays.asList(command.getAliases()).contains(args[0].toLowerCase())) {
						command.execute(s, sendingArgs);
						return true;
					}
				}
				return true;
			}
		};
		StaffControl.setExecutor(exe);
	}
}
