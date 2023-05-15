package com.octane.sllly.octanevouchersplus;

import dev.splityosis.commandsystem.SYSCommand;
import dev.splityosis.commandsystem.SYSCommandBranch;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class CommandSystem extends SYSCommandBranch {

    public CommandSystem(String... names) {
        super(names);
        setPermission("octanevouchersplus.admin");
        setUnknownCommandMessage(Arrays.asList("&cGod bless you, try again, do it properly."));

        addCommand(new SYSCommand("reload")
                .executes((commandSender, strings) -> {
                    Octanevouchersplus.plugin.reloadPlugin();
                    Util.sendMessage(commandSender, "&2reloaded hopefully probably");
                }));
    }
}