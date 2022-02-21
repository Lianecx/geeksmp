package com.commandgeek.geeksmp.commands;

import com.commandgeek.geeksmp.managers.MessageManager;
import com.commandgeek.geeksmp.managers.TeamManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandIP implements CommandExecutor {

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player && !player.hasPermission("geeksmp.command.ip") && !TeamManager.isStaff(player)) {
            new MessageManager("errors.no-permission").send(player);
            return true;
        }

        if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                new MessageManager("errors.invalid-player")
                    .replace("%player%", args[0])
                    .send(sender);
                return true;
            }

            String IP = target.getAddress().getHostString();
            new MessageManager("information.player.ip")
                    .replace("%player%", target.getName())
                    .replace("%ip%", IP)
                    .send(sender);
            return true;

        } else if(sender instanceof Player player && args.length == 0) {
            String IP = player.getAddress().getHostString();
            new MessageManager("information.player.ip")
                .replace("%player%", player.getName())
                .replace("%ip%", IP)
                .send(player);
            return true;
        }

        new MessageManager("errors.invalid-arguments").send(sender);
        return true;
    }
}
