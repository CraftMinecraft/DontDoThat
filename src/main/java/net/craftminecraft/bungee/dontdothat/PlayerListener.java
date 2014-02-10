package net.craftminecraft.bungee.dontdothat;

import java.util.Iterator;
import net.md_5.bungee.api.ReconnectHandler;

import net.md_5.bungee.api.config.ServerInfo;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.AbstractReconnectHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.event.EventHandler;

public class PlayerListener implements Listener {

    DontDoThat plugin;

    public PlayerListener(DontDoThat plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommand(ChatEvent ev) {
        if (!ev.isCommand() || !(ev.getSender() instanceof ProxiedPlayer))
            return;
        ProxiedPlayer sender = (ProxiedPlayer) ev.getSender();
        if (!plugin.getConfig().servers.contains(sender.getServer().getInfo().getName()))
                return;
        
        Iterator<String> it = this.plugin.getConfig().list.iterator();
        if (this.plugin.getConfig().mode.equals("blacklist")) {
            while (it.hasNext()) {
                String next = it.next();
                if (ev.getMessage().substring(1).equalsIgnoreCase(next)) {
                    ev.setCancelled(true);
                    sender.sendMessages(plugin.getConfig().parsedeniedmsg(
                            ev.getMessage().substring(1).split(" ")[0]));
                    break; // no need to keep this up !
                }
            }
        } else {
            while (it.hasNext()) {
                String next = it.next();
                if (ev.getMessage().substring(1).equalsIgnoreCase(next)) {
                    return;
                }
            }
            ev.setCancelled(true);
            sender.sendMessages(plugin.getConfig().parsedeniedmsg(
                     ev.getMessage().substring(1).split(" ")[0]));
        }
    }
}