package pl.mbstone.inder00;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pl.mbstone.inder00.data.FileManager;
import pl.mbstone.inder00.data.Settings;
public class Cmds implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {
		if(cmd.getName().equalsIgnoreCase("lvl")){
			Player p = (Player) sender;
			int stone = FileManager.getUser(p.getPlayer()).getInt("stone");
			int lvl1 = FileManager.getUser(p.getPlayer()).getInt("lvl");
			int lvl2 = lvl1*1200;
			int lvl3 = lvl1 -1;
			p.sendMessage("§eTwoj LVL §c" + lvl3 + " §e(§c" + stone + "/" + lvl2 + "§e)");
		}
		if(cmd.getName().equalsIgnoreCase("mbstone")){
			Player p = (Player) sender;
			if(p.hasPermission("mbstone.admin")){
				if(args.length > 0)
				{
					if(args.length < 2){
						if(args[0].equalsIgnoreCase("reload")){
							Settings.reloadConfig();
							p.sendMessage("§ePrzeladowano plik §cconfig.yml");
						} else {
							p.sendMessage("§ePoprawne uzycie §c/mbstone reload");
							return false;
						}
					} else {
						p.sendMessage("§ePoprawne uzycie §c/mbstone reload");
						return false;			
					}
				} else {
					p.sendMessage("§ePoprawne uzycie §c/mbstone reload");
					return false;
				}
			} else {
				p.sendMessage("§cBrak uprawnien (mbstone.admin)");
				return false;
			}
		}
		return false;
	}
}
