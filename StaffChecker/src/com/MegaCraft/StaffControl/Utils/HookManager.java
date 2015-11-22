package com.MegaCraft.StaffControl.Utils;

import org.bukkit.entity.Player;

public class HookManager {
    private static EssentialsHook essentials;
    
    private HookManager() {
    }

    public static boolean isEssentialsLoaded() {
        if (essentials != null) {
            return true;
        }
        return false;
    }

    public static boolean isAfk(String pl) {
        return HookManager.isEssentialsLoaded() ? essentials.isAfk(pl) : false;
    }

    public static Player getReplyTo(String pl) {
        return HookManager.isEssentialsLoaded() ? essentials.getReplyTo(pl) : null;
    }
}

