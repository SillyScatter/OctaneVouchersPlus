package com.octane.sllly.octanevouchersplus;

import com.octanepvp.splityosis.octanevouchers.vouchers.Voucher;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public class CommandVoucher extends Voucher {

    private final ChancePicker<String> commands;

    private final int repeat;

    public CommandVoucher(@NonNull String name, @NonNull ItemStack item, boolean confirmGui, boolean dupeProtection, ChancePicker<String> commands, int repeat) {
        super(name, item, confirmGui, dupeProtection);
        this.commands=commands;
        this.repeat = repeat;
    }

    @Override
    public boolean canRedeem(Player player) {
        return true;
    }

    @Override
    public void onRedeem(Player player) {
        for (int i = 0; i < repeat; i++) {
            String command = commands.pick().replace("%player%",player.getName());
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command);
        }
    }
}
