package net.craftminecraft.bungee.dontdothat;

import com.google.common.collect.ObjectArrays;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import net.cubespace.Yamler.Config.Config;
import net.cubespace.Yamler.Config.InvalidConfigurationException;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;

public class MainConfig extends Config {

    public MainConfig(Plugin plugin) {
        this.CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
        try {
            this.init();
        } catch (InvalidConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String mode = "blacklist";
    public List<String> servers = new ArrayList<String>() {
        {
            add("login");
        }
    };
    
    public List<String> list = new ArrayList<String>() {
        {
            add("end");
            add("mute");
        }
    };
    
    public List<String> movemsg = new ArrayList<String>() {
        {
            add("&3[DontDoThat] &4You may not use command : %cmd% on this server");
        }
    };
    
    public String[] parsedeniedmsg(String cmd) {
        String[] msgs = new String[0];
        for (String i : getMoveMsg()) {
            msgs = ObjectArrays.concat(msgs,ChatColor.translateAlternateColorCodes('&',i).replaceAll("%cmd%", cmd));
        }
        return msgs;
    }
    
    public String[] getMoveMsg() {
        String[] msgs = new String[0];
        for (String i : movemsg) {
            msgs = ObjectArrays.concat(msgs, i.split("\\\\n"), String.class);
        }
        return msgs;
    }
}
