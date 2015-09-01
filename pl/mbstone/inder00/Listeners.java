package pl.mbstone.inder00;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.ChatColor;

import pl.mbstone.inder00.Main;
import pl.mbstone.inder00.data.FileManager;

public class Listeners implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent e) throws IOException {

		final Block blok = e.getBlock();
		final Location loc = blok.getLocation();
		final Location loc1 = new Location(loc.getWorld(), loc.getX(), loc.getY() - 1.0D, loc.getZ());
		if (blok.getType() == Material.STONE) {
			int stone = FileManager.getUser(e.getPlayer()).getInt("stone");
			int lvl1 = FileManager.getUser(e.getPlayer()).getInt("lvl");
			int stones = stone + 1;
			int lvl = lvl1*1200;
			int lvl2 = lvl1+1;
			int lvl3 = lvl2 -1;


			if(stones >= lvl){
				if(lvl2 <= 31){
					Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&eGracz &c" + e.getPlayer().getName() + " &eawansowal na kolejny poziom (&c" + lvl3 + "&e)"));
				} else {
					e.getPlayer().sendMessage("§eAwansowales/as na kolejny poziom (§c" + lvl3 + "§e)");
				}
				File f;
				if (FileManager.updatePlayer(e.getPlayer()) != null) {
					f = new File(FileManager.getUsersFolder(), e.getPlayer().getName() + ".yml");
					f.createNewFile();
				} else {
					f = FileManager.getPFile(e.getPlayer());
				}
				YamlConfiguration fYml = YamlConfiguration.loadConfiguration(f);
				fYml.set("name", e.getPlayer().getName());
				fYml.set("uuid", e.getPlayer().getUniqueId().toString());
				fYml.set("ip", e.getPlayer().getAddress().getAddress().toString().replace("/", ""));
				fYml.set("stone", stones);
				fYml.set("lvl", lvl2);
				fYml.save(f);
			} else {
				File f;
				if (FileManager.updatePlayer(e.getPlayer()) != null) {
					f = new File(FileManager.getUsersFolder(), e.getPlayer().getName() + ".yml");
					f.createNewFile();
				} else {
					f = FileManager.getPFile(e.getPlayer());
				}
				YamlConfiguration fYml = YamlConfiguration.loadConfiguration(f);
				fYml.set("name", e.getPlayer().getName());
				fYml.set("uuid", e.getPlayer().getUniqueId().toString());
				fYml.set("ip", e.getPlayer().getAddress().getAddress().toString().replace("/", ""));
				fYml.set("stone", stones);
				fYml.set("lvl", lvl1);
				fYml.save(f);
			}
			if (loc1.getBlock().getType() == Material.ENDER_STONE) {
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(), new Runnable() {
					public void run() {

						if (loc1.getBlock().getType() == Material.ENDER_STONE) {
							loc.getBlock().setType(Material.STONE);
						}

					}

				}, Data.getTicks("regenerate"));
			}
		}

	}

	@EventHandler
	public void onPlace(BlockPlaceEvent e) {

		Block blok = e.getBlock();
		Location loc1 = new Location(blok.getLocation().getWorld(), blok.getLocation().getX(),
				blok.getLocation().getY() + 1.0D, blok.getLocation().getZ());
		if (blok.getType() == Material.ENDER_STONE) {
			if (loc1.getBlock().getType() == Material.AIR) {
				loc1.getBlock().setType(Material.STONE);
			}
		}

	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) throws IOException {
		File f;
		if (FileManager.getPFile(e.getPlayer()) == null) {
			f = new File(FileManager.getUsersFolder(), e.getPlayer().getName() + ".yml");
			f.createNewFile();
		} else {
			f = FileManager.getPFile(e.getPlayer());
		}
		YamlConfiguration fYml = YamlConfiguration.loadConfiguration(f);
		fYml.set("name", e.getPlayer().getName());
		fYml.set("uuid", e.getPlayer().getUniqueId().toString());
		fYml.set("ip", e.getPlayer().getAddress().getAddress().toString().replace("/", ""));
		fYml.set("stone", 0);
		fYml.set("lvl", 1);
		fYml.save(f);
	}

	
}
