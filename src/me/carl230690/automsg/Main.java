package me.carl230690.automsg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
  public static int broadLoop = 0;
  public static int timeInSeconds;

  public void onEnable()
  {
    getLogger().info("DAM has been enabled!");
    timeInSeconds = 900;
    this.saveDefaultConfig();
    getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
    {
      public void run() {
        String prefix = "[" + ChatColor.LIGHT_PURPLE + Main.this.getConfig().getString("prefix") + ChatColor.WHITE + "] " + ChatColor.GREEN;
        if (Main.timeInSeconds == 4500) {
          Bukkit.broadcastMessage(prefix + Main.this.getConfig().getString("messages.message1").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
        }
        if (Main.timeInSeconds == 3600) {
          Bukkit.broadcastMessage(prefix + Main.this.getConfig().getString("messages.message2").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
        }
        if (Main.timeInSeconds == 2700) {
          Bukkit.broadcastMessage(prefix + Main.this.getConfig().getString("messages.message3").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
        }
        if (Main.timeInSeconds == 1800) {
          Bukkit.broadcastMessage(prefix + Main.this.getConfig().getString("messages.message4").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
        }
        if (Main.timeInSeconds == 900) {
          Bukkit.broadcastMessage(prefix + Main.this.getConfig().getString("messages.message5").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
        }
        if (Main.timeInSeconds == 0) {
          Bukkit.broadcastMessage(prefix + Main.this.getConfig().getString("messages.message6").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
          Main.timeInSeconds = 1080;
        }
        if (Main.timeInSeconds > 0)
          Main.timeInSeconds -= 1;
      }
    }
    , 20L, 20L);
  }
  public void onDisable() {
    getLogger().info("Doragon Auto Msg has been disabled!");
  }

  public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
  {
    if (cmd.getName().equalsIgnoreCase("dam")) {
      sender.sendMessage(ChatColor.GREEN + "Doragon Auto Msg v1.0 by carl230690.");
      return true;
    }if (cmd.getName().equalsIgnoreCase("dambcast")) {
      if (sender.hasPermission("dam.bcast")) {
        if (args.length == 0) {
          sender.sendMessage(ChatColor.RED + "Usage: /dambcast (message)");
          Bukkit.broadcastMessage(Main.this.getConfig().getString("messages.message1").replaceAll("(&([a-f0-9]))", "\u00A7$2"));
          return true;
        }if (args.length > 0) {
          StringBuilder sb = new StringBuilder();
          for (int i = 0; i < args.length; i++) {
            sb.append(args[i]).append(" ");
          }
          String allArgs = sb.toString().trim();
          Bukkit.broadcastMessage("[" + ChatColor.LIGHT_PURPLE + getConfig().getString("prefix") + ChatColor.WHITE + "] " + ChatColor.GREEN + allArgs);
          return true;
        }
      } else {
        sender.sendMessage(ChatColor.RED + "You do not have permission.");
        return true;
      }
    } else if (cmd.getName().equalsIgnoreCase("damstop")) {
      if (sender.hasPermission("dam.stop")) {
        timeInSeconds = -1;
        sender.sendMessage(ChatColor.DARK_AQUA + "DAM has been halted! Do /damstart to restart the Auto Msg!");
        return true;
      }
    } else if (cmd.getName().equalsIgnoreCase("damstart")) {
      if (sender.hasPermission("dam.start")) {
        timeInSeconds = 900;
        sender.sendMessage(ChatColor.DARK_AQUA + "dam has been restarted! Do /damstop to stop the Auto Msg!.");
        return true;
      }
      sender.sendMessage(ChatColor.RED + "You do not have permission");
      return true;
    }

    return false;
  }
}