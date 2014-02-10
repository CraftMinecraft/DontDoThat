package net.craftminecraft.bungee.dontdothat;

import net.cubespace.Yamler.Config.InvalidConfigurationException;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ReloadCommand extends Command {
	DontDoThat plugin;
	public ReloadCommand(DontDoThat plugin) {
		super("ddt", "dontdothat.admin");
		this.plugin = plugin;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length != 1) {
			sender.sendMessage("Derp");
		}
                if (sender instanceof ProxiedPlayer
                        && plugin.getConfig().servers.contains(
                        ((ProxiedPlayer)sender).getServer().getInfo().getName())) {
                    sender.sendMessages(plugin.getConfig().parsedeniedmsg("ddt"));
                    return;
                }
                
		switch (args[0]) {
		case "reload":
			try {
				plugin.getConfig().reload();
			} catch (InvalidConfigurationException e) {
				e.printStackTrace();
				sender.sendMessage(ChatColor.RED + "[DontDoThat]" + ChatColor.YELLOW + " Derped. Look at console.");
				return;
			}
			sender.sendMessage(ChatColor.RED + "[DontDoThat]" + ChatColor.YELLOW + " Config reloaded.");
		}
	}

}
