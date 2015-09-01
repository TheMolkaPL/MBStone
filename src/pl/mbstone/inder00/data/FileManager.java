package pl.mbstone.inder00.data;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import pl.mbstone.inder00.Main;

public class FileManager {

	private static YamlConfiguration user;
	private static File users = new File(Main.getInst().getDataFolder(), "users");
	
	public static void checkFiles(){
		if(!Main.getInst().getDataFolder().exists()){
			Main.getInst().getDataFolder().mkdir();
		}
		if(!users.exists()){
			users.mkdir();
		}
		File c = new File(Main.getInst().getDataFolder(), "config.yml");
		if(!c.exists()){
			Main.getInst().saveResource("config.yml", true);
		}
	}
	
	
	public static File getPFile(Player p){
		File f = new File(users, p.getName() + ".yml");
		if(!f.exists()) return null;
		return f;
	}
	public static File updatePlayer(Player p){
		File f = new File(users, p.getName() + ".yml");
		if(f.exists()) return null;
		return f;
	}
	public static YamlConfiguration getUser(Player p){
		File users = new File(Main.getInst().getDataFolder(), "users/" + p.getName() + ".yml");
		user = YamlConfiguration.loadConfiguration(users);
		return user;
	}
	
	public static File getUsersFolder(){
		return users;
	}
}
