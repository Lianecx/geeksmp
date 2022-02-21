package com.commandgeek.geeksmp.commands;

import com.commandgeek.geeksmp.managers.MessageManager;
import com.commandgeek.geeksmp.managers.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBroadcast implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player player && !player.hasPermission("geeksmp.command.broadcast") && !TeamManager.isStaff(player)) {
            new MessageManager("errors.no-permission").send(player);
            return true;
        }

        if (args.length >= 1) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                StringBuilder message = new StringBuilder();
                for (String item : args) {
                    if (!message.toString().equals("")) message.append(" ");
                    message.append(item);
                }
                online.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&d&lBROADCAST&8] &5" + message));
            }
        }
        return true;
    }
}
