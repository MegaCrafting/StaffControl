package com.MegaCraft.StaffControl.Utils;

import com.earth2me.essentials.CommandSource;
import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

class EssentialsHook {
    private final Essentials ess = (Essentials)Bukkit.getPluginManager().getPlugin("Essentials");

    EssentialsHook() {
    }

    boolean isAfk(String pl) {
        User user = this.getUser(pl);
        return user != null ? user.isAfk() : false;
    }

    Player getReplyTo(String pl) {
        Player player;
        CommandSource source = this.getUser(pl).getReplyTo();
        if (source != null && source.isPlayer() && (player = source.getPlayer()) != null && player.isOnline()) {
            return player;
        }
        return null;
    }

    private User getUser(String pl) {
        return this.ess.getUserMap().getUser(pl);
    }
}

