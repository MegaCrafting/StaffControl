package com.MegaCraft.StaffControl.Command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.MegaCraft.StaffControl.Utils.ReadandWrite;

import java.util.List;

public class LoginCommand extends SCCommand {

	public LoginCommand() {
		super("login", "/staffcontrol login [name]", "This command provides information on the staff member required.", new String[] { "login", "l" });
	}

	public void execute(CommandSender sender, List<String> args) {
		if (!correctLength(sender, args.size(), 1, 2)) {
			return;
		} else if (args.size() == 1) {
			if (!sender.isOp()) {
				return;
			}
			login(sender, args.get(0).toLowerCase());
		} else if (args.size() >= 2) {
			sender.sendMessage(ChatColor.AQUA + "Too many arguments!");
		}
	}

	private void login(CommandSender sender, String string) {
		ReadandWrite.reader(sender, string, "Logins");
	}
}
