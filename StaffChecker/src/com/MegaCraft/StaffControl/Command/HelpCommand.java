package com.MegaCraft.StaffControl.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class HelpCommand extends SCCommand {
	public HelpCommand() {
		super("help", "/staffcontrol help", "This command provides information on how to use other commands in StaffControl.", new String[] { "help", "h" });
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {
		if (!hasPermission(sender) || !correctLength(sender, args.size(), 0, 1))
			return;
		else if (args.size() == 0) {
			for (SCCommand command : instances.values()) {
				sender.sendMessage(ChatColor.YELLOW + command.getProperUse());
			}
			return;
		}

		String arg = args.get(0);

		if (instances.keySet().contains(arg.toLowerCase())) {
			instances.get(arg).help(sender, true);
		} else if (Arrays.asList(Commands.loginaliases).contains(arg)) {
			sender.sendMessage(ChatColor.DARK_AQUA + "Command: " + ChatColor.AQUA + "/staffcontrol login [name]");
			sender.sendMessage(ChatColor.DARK_AQUA + "Info: " + ChatColor.AQUA + "Use this to see when the last time a staff member was last online and how many times they have been on in the past two weeks.");
			sender.sendMessage(ChatColor.DARK_AQUA + "Aliases: " + ChatColor.AQUA + "login, l");
		} else {
			sender.sendMessage(ChatColor.RED + "That isn't a valid help topic. Use /staffcontrol help for more information.");
		}
	}
}
