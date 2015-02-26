// Package Declaration
package com.crystalcraftmc.crystalspace.config;

// Java Imports

import com.crystalcraftmc.crystalspace.handlers.LangHandler;
import com.crystalcraftmc.crystalspace.handlers.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

// CrystalSpace Imports
// Bukkit Imports

/**
 * Converts old pre-v2 worlds into v2 IDs.
 * 
 * @author iffa
 */
public class SpaceConfigUpdater {
    // Variables
    private static boolean hadToBeUpdated = false;

    /**
     * Checks if the configuration files had to be "fixed".
     * 
     * @return True if had to be updated
     */
    public static boolean wasUpdated() {
        return hadToBeUpdated;
    }

    /**
     * Checks if a config file needs updating to v2.
     * 
     * @param configfile ConfigFile to check
     * 
     * @return True if needs updating
     */
    private static boolean needsUpdate(SpaceConfig.ConfigFile configfile) {
        if(configfile.equals(SpaceConfig.ConfigFile.CONFIG)){
            if (SpaceConfig.getConfig(configfile).contains("worlds")) {
                try {
                    for (String world : SpaceConfig.getConfig(configfile).getConfigurationSection("worlds").getKeys(false)) {
                        if (SpaceConfig.getConfig(configfile).getConfigurationSection("worlds." + world).contains("generation")) {
                            hadToBeUpdated = true;
                            return true;
                        }
                    }
                } catch (NullPointerException ex) {
                    return false;
                }
            }
        }
	if(configfile.equals(SpaceConfig.ConfigFile.IDS)){
	    for(String id : SpaceConfig.getConfig(configfile).getConfigurationSection("ids").getKeys(false)){
		if(SpaceConfig.getConfig(configfile).contains("ids."+id+".generation.spout-only.blackholes")){
		    hadToBeUpdated = true;
		    return true;
		}
	    }
	}
        return false;
    }

    /**
     * Updates config files to be compatible with latest.
     */
    public static void updateConfigs() {
        if (needsUpdate(SpaceConfig.ConfigFile.CONFIG)) {
            // Variables
            MessageHandler.print(Level.INFO, LangHandler.getConfigUpdateStartMessage());
            YamlConfiguration configFile = SpaceConfig.getConfig(SpaceConfig.ConfigFile.CONFIG);
            YamlConfiguration idsFile = SpaceConfig.getConfig(SpaceConfig.ConfigFile.IDS);

            for (String world : configFile.getConfigurationSection("worlds").getKeys(false)) {
                // Generation values
                for (String key : configFile.getConfigurationSection("worlds." + world + "." + "generation").getKeys(false)) {
                    Object value = configFile.get("worlds." + world + ".generation." + key);
                    idsFile.set("ids." + world + ".generation." + key, value);
                    MessageHandler.debugPrint(Level.INFO, "Moved " + key + " of " + world + " to ids.yml with a value of " + value);
                }
                // Suit values
                for (String key : configFile.getConfigurationSection("worlds." + world + "." + "suit").getKeys(false)) {
                    Object value = configFile.get("worlds." + world + ".suit." + key);
                    idsFile.set("ids." + world + ".suit." + key, value);
                    MessageHandler.debugPrint(Level.INFO, "Moved " + key + " of " + world + " to ids.yml with a value of " + value);
                }
                // Helmet values
                for (String key : configFile.getConfigurationSection("worlds." + world + "." + "helmet").getKeys(false)) {
                    Object value = configFile.get("worlds." + world + ".helmet." + key);
                    idsFile.set("ids." + world + ".helmet." + key, value);
                    MessageHandler.debugPrint(Level.INFO, "Moved " + key + " of " + world + " to ids.yml with a value of " + value);
                }
                // Misc. values
                for (String key : configFile.getConfigurationSection("worlds." + world).getKeys(false)) {
                    // So we don't make bad things happen. Skrillex (Y)
                    if (key.equalsIgnoreCase("generation") || key.equalsIgnoreCase("suit") || key.equalsIgnoreCase("helmet")) {
                        continue;
                    }
                    Object value = configFile.get("worlds." + world + "." + key);
                    idsFile.set("ids." + world + "." + key, value);
                    MessageHandler.debugPrint(Level.INFO, "Moved " + key + " of " + world + " to ids.yml with a value of " + value);
                }

                // Removing the world from config.yml.
                configFile.set("worlds." + world, null);
                MessageHandler.debugPrint(Level.INFO, "Removed " + world + " from config.yml.");
            }
            // Saving both files since converting is done.
            try {
                configFile.save(SpaceConfig.getConfigFile(SpaceConfig.ConfigFile.CONFIG));
                idsFile.save(SpaceConfig.getConfigFile(SpaceConfig.ConfigFile.IDS));
                MessageHandler.debugPrint(Level.INFO, "Saved changes to ids and config.yml.");
            } catch (IOException ex) {
                // In case of any error.
                MessageHandler.print(Level.SEVERE, LangHandler.getConfigUpdateFailureMessage(ex));
            }
            // It was all done.
            MessageHandler.print(Level.INFO, LangHandler.getConfigUpdateFinishMessage());
        }

	if (needsUpdate(SpaceConfig.ConfigFile.IDS)) {
            // Variables
            MessageHandler.print(Level.INFO, LangHandler.getConfigUpdateStartMessage());
            YamlConfiguration idsFile = SpaceConfig.getConfig(SpaceConfig.ConfigFile.IDS);

            for (String id : idsFile.getConfigurationSection("ids").getKeys(false)) {
		if(idsFile.contains("ids."+id+".generation.spout-only.blackholes")){
		    idsFile.set("ids."+id+".generation.spoutblackholes", idsFile.get("ids."+id+".generation.spout-only.blackholes"));
		    idsFile.set("ids."+id+".generation.spout-only.blackholes", null);
		}
            }

            // Saving files since converting is done.
            try {
                idsFile.save(SpaceConfig.getConfigFile(SpaceConfig.ConfigFile.IDS));
                MessageHandler.debugPrint(Level.INFO, "Saved changes to ids.yml.");
            } catch (IOException ex) {
                // In case of any error.
                MessageHandler.print(Level.SEVERE, LangHandler.getConfigUpdateFailureMessage(ex));
            }
            // It was all done.
            MessageHandler.print(Level.INFO, LangHandler.getConfigUpdateFinishMessage());
        }

        File oldPlanets = new File(Bukkit.getPluginManager().getPlugin("CrystalSpace").getDataFolder(), "planets.yml");
        File newPlanets = new File(Bukkit.getPluginManager().getPlugin("CrystalSpace").getDataFolder(), "planets/planets.yml");
        if(oldPlanets.exists()){
            if(newPlanets.exists()){
                newPlanets.delete();
            }
            oldPlanets.renameTo(newPlanets);
            SpaceConfig.loadConfig(SpaceConfig.ConfigFile.DEFAULT_PLANETS);
        }
    }

    /**
     * Constructor of SpaceConfigUpdater.
     */
    private SpaceConfigUpdater() {
    }
}
