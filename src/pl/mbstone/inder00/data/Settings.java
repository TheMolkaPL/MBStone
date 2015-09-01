package pl.mbstone.inder00.data;

import java.io.File;
import org.bukkit.configuration.file.YamlConfiguration;

import pl.mbstone.inder00.Main;

public class Settings {

	private static YamlConfiguration config;

	public static void checkFiles(){
		if(!Main.getInst().getDataFolder().exists()){
			Main.getInst().getDataFolder().mkdir();
		}
		File c = new File(Main.getInst().getDataFolder(), "config.yml");
		if(!c.exists()){
			Main.getInst().saveResource("config.yml", true);
		}
		config = YamlConfiguration.loadConfiguration(c);
	}
	public static void reloadConfig()
	{
		File c = new File(Main.getInst().getDataFolder(), "config.yml");
		config = YamlConfiguration.loadConfiguration(c);
	}
	
	public static YamlConfiguration getConfig(){
		return config;
	}
}
