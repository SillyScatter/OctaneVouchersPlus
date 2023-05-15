package com.octane.sllly.octanevouchersplus;

import com.octanepvp.splityosis.octaneeconomies.api.OctaneEconomiesAPI;
import com.octanepvp.splityosis.octanevouchers.api.OctaneVouchersAPI;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Octanevouchersplus extends JavaPlugin {

    public static Octanevouchersplus plugin;

    public static OctaneVouchersAPI vouchersAPI;

    public static OctaneEconomiesAPI economiesAPI;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        plugin = this;
        vouchersAPI=(OctaneVouchersAPI) getServer().getPluginManager().getPlugin("OctaneVouchers");
        economiesAPI=(OctaneEconomiesAPI) getServer().getPluginManager().getPlugin("OctaneEconomies");
        reloadPlugin();
        new CommandSystem("octanevouchersplus","ovp").registerCommandBranch(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void reloadPlugin(){
        ConfigurationSection moneyVoucherSection = getConfig().getConfigurationSection("moneyvoucher");
        if (moneyVoucherSection != null){
            for (String key : moneyVoucherSection.getKeys(false)) {
                String name = moneyVoucherSection.getString(key+".name");
                String eco = moneyVoucherSection.getString(key+".eco");
                String msg = moneyVoucherSection.getString(key+".msg");
                int min = moneyVoucherSection.getInt(key+".min");
                int max = moneyVoucherSection.getInt(key+".max");
                boolean confirmGUI = moneyVoucherSection.getBoolean(key+".confirmgui");
                boolean dupeProtection = moneyVoucherSection.getBoolean(key+".dupeprotection");
                ItemStack itemStack = Util.getItemFromSection(moneyVoucherSection.getConfigurationSection(key+".item"));
                MoneyVoucher moneyVoucher = new MoneyVoucher(name,itemStack,confirmGUI,dupeProtection,min,max,eco,msg);
                vouchersAPI.registerVoucher(moneyVoucher);
            }
        }
        ConfigurationSection commandVoucherSection = getConfig().getConfigurationSection("commandvoucher");
        if (commandVoucherSection != null){
            for (String key : commandVoucherSection.getKeys(false)) {
                int repeat = 1;
                String name = commandVoucherSection.getString(key+".name");
                boolean confirmGUI = commandVoucherSection.getBoolean(key+".confirmgui");
                boolean dupeProtection = commandVoucherSection.getBoolean(key+".dupeprotection");
                repeat = commandVoucherSection.getInt(key + ".repeat");
                ItemStack itemStack = Util.getItemFromSection(commandVoucherSection.getConfigurationSection(key+".item"));
                ChancePicker<String> commands = new ChancePicker<>();

                ConfigurationSection commandSection = commandVoucherSection.getConfigurationSection(key+".commands");
                if (commandSection != null){
                    for (String commandSectionKey : commandSection.getKeys(false)) {
                        String cmd = commandSection.getString(commandSectionKey+".command");
                        int weight = commandSection.getInt(commandSectionKey+".weight");
                        commands.addOption(cmd,weight);
                    }
                }
                CommandVoucher commandVoucher = new CommandVoucher(name,itemStack,confirmGUI,dupeProtection,commands, repeat);
                vouchersAPI.registerVoucher(commandVoucher);
            }
        }
    }
}
