package com.MegaCraft.StaffControl.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.data.Group;
import org.anjocaido.groupmanager.data.UserVariables;
import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class GMHook {

	private GroupManager groupManager;
	private final Plugin plugin;

	public GMHook(final Plugin plugin) {
		this.plugin = plugin;
	}

	public boolean delPerm(final Player base, final String perm) {
		final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(base);
		if (handler == null) {
			return false;
		}

		handler.getUser(base.getName()).removePermission(perm);
		return true;
	}

	public String getGroup(final Player base) {
		final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
		if (handler == null) {
			return null;
		}
		return handler.getGroup(base.getName());
	}

	public List<String> getGroups(final Player base) {
		final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
		if (handler == null) {
			return null;
		}
		return Arrays.asList(handler.getGroups(base.getName()));
	}

	public String getLastName(final Player base) {
		final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(base);
		if (handler == null) {
			return null;
		}
		return handler.getUser(base.getName()).getLastName();

	}

	public String getPrefix(final Player base) {
		final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
		if (handler == null) {
			return null;
		}
		return handler.getUserPrefix(base.getName());
	}

	public List<String> getSubGroups(final Player base) {
		final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(base);
		if (handler == null) {
			return null;
		}
		final List<String> grps = new ArrayList<String>();
		for (final String subGroup : handler.getGroups().keySet()) {
			grps.add(subGroup);

		}

		return grps;
	}

	public String getSuffix(final Player base) {
		final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
		if (handler == null) {
			return null;
		}
		return handler.getUserSuffix(base.getName());
	}

	public UserVariables getVars(final Player base) {
		final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(base);
		if (handler == null) {
			return null;
		}
		return handler.getUser(base.getName()).getVariables();
	}

	/*
	 * public void setSuffix(Player base, String suffix) { final
	 * AnjoPermissionsHandler handler =
	 * groupManager.getWorldsHolder().getWorldPermissions(base);
	 * base.sendMessage("Checking handler."); if (handler == null) { return; }
	 *
	 * handler.addUserInfo("Suffix", "Suffix", suffix); base.sendMessage(
	 * "setting suffix to: " + suffix); return; }
	 */
	public boolean hasPermission(final Player base, final String node) {
		final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
		if (handler == null) {
			return false;
		}
		return handler.has(base, node);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPluginDisable(final PluginDisableEvent event) {
		if (groupManager != null) {
			if (event.getPlugin().getDescription().getName().equals("GroupManager")) {
				groupManager = null;
			}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPluginEnable(final PluginEnableEvent event) {
		final PluginManager pluginManager = plugin.getServer().getPluginManager();
		final Plugin GMplugin = pluginManager.getPlugin("GroupManager");

		if ((GMplugin != null) && GMplugin.isEnabled()) {
			groupManager = (GroupManager) GMplugin;

		}
	}

	public void saveUsers() {

		groupManager.getWorldsHolder().saveChanges();

	}

	public boolean setGroup(final Player base, final String group) {
		final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(base);
		if ((handler == null) && (group == null)) {
			return false;
		}
		handler.getUser(base.getName()).setGroup(handler.getGroup(group));

		return true;
	}

	public void setLastName(final Player base, final String name) {
		final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(base);
		if ((handler == null) || (name == null)) {
			return;
		}

		handler.getUser(base.getName()).setLastName(name);
		return;

	}

	public boolean setPerm(final Player base, final String perm) {
		final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(base);
		if (handler == null) {
			return false;
		}

		handler.getUser(base.getName()).addPermission(perm);
		return true;
	}

	public void setPrefix(final Player base, final String prefix) {
		final AnjoPermissionsHandler handler = groupManager.getWorldsHolder().getWorldPermissions(base);
		if (handler == null) {
			return;
		}

		handler.addUserInfo("prefix", "prefix", prefix);
		return;
	}

	public boolean setSubGroup(final Player base, final String group) {
		final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(base);
		if (handler == null) {
			return false;
		}
		Group sGroup = null;
		for (final String subGroup : handler.getGroups().keySet()) {
			if (subGroup == group) {
				sGroup = handler.getGroups().get(subGroup);
			}

		}
		if (sGroup != null) {
			handler.getUser(base.getName()).addSubGroup(sGroup);
		}
		return true;

	}

	public boolean setSuffix(final Player base, final String suffix) {
		final OverloadedWorldHolder handler = groupManager.getWorldsHolder().getWorldData(base);
		if ((handler == null) && (suffix == null)) {
			return false;
		}
		final Map<String, Object> vars = new HashMap<String, Object>();
		if (suffix.equalsIgnoreCase("null")) {
			vars.remove("suffix");
		} else {
			vars.put("suffix", suffix);
		}
		handler.getUser(base.getName()).setVariables(vars);

		return true;
	}
}