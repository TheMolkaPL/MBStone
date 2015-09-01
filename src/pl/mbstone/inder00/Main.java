package pl.mbstone.inder00;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import pl.mbstone.inder00.Listeners;
import pl.mbstone.inder00.data.FileManager;
import pl.mbstone.inder00.data.Settings;

public class Main extends JavaPlugin {

	private static Plugin plugin;
	
	private static Main inst;
	
	public Main(){
		inst = this;
	}
	
	public static Main getInst() {
		return inst;
	}
	
	@Override
	public void onEnable()
	{
		plugin = this;
		FileManager.checkFiles();
		Settings.checkFiles();
		Bukkit.getPluginManager().registerEvents(new Listeners(), this);
		getCommand("lvl").setExecutor(new Cmds());
		getCommand("mbstone").setExecutor(new Cmds());
		
		PluginDescriptionFile pdf = this.getDescription();
		System.out.print("[MBStone] Proces wlaczania... (0%)");
		if(pdf.getName().contains("MBStone")){
			System.out.print("[MBStone] Weryfikacja... (50%)");
			if(pdf.getAuthors().contains("Inder00")){
				System.out.print("[MBStone] Weryfikacja... (100%)");
				System.out.print("[MBStone] Wlaczono plugin.");
				
			    ItemStack ender = new ItemStack(Material.ENDER_STONE, 1);
			    ItemMeta meta = ender.getItemMeta();
			    meta.setDisplayName("§eStoniarka");
			    ender.setItemMeta(meta);
			    
			    ShapedRecipe Stoniarka = new ShapedRecipe(ender).shape(new String[] {
			      "ABC", 
			      "DEF", 
			      "GHI" })
			      
			      .setIngredient('A', Material.REDSTONE)
			      .setIngredient('B', Material.IRON_INGOT)
			      .setIngredient('C', Material.REDSTONE)
			      .setIngredient('D', Material.IRON_INGOT)
			      .setIngredient('E', Material.STONE)
			      .setIngredient('F', Material.IRON_INGOT)
			      .setIngredient('G', Material.REDSTONE)
			      .setIngredient('H', Material.PISTON_BASE)
			      .setIngredient('I', Material.REDSTONE);
			    getServer().addRecipe(Stoniarka);
				
			} else {
				System.out.print("[MBStone] Blad podczas weryfikowania.");
				System.out.print("[MBStone] Zatrzymywanie...");
				Bukkit.getPluginManager().disablePlugin(this);
			}
		} else {
			System.out.print("[MBStone] Blad podczas weryfikowania.");
			System.out.print("[MBStone] Zatrzymywanie...");
			Bukkit.getPluginManager().disablePlugin(this);
		}
	}
	@Override
	public void onDisable()
	{
		System.out.print("[MBStone] Proces wylaczania...");
	}
	public static Plugin getPlugin(){
		return plugin;
	}
}
