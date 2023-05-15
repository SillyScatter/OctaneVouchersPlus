package com.octane.sllly.octanevouchersplus;

import com.octanepvp.splityosis.octanevouchers.vouchers.Voucher;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.nullness.qual.NonNull;

public class MoneyVoucher extends Voucher {

    private final int min;

    private final int max;

    private final String economy;

    private final String msg;

    public MoneyVoucher(@NonNull String name, @NonNull ItemStack item, boolean confirmGui, boolean dupeProtection, int min, int max, String economy, String msg) {
        super(name, item, confirmGui, dupeProtection);
        this.min = min;
        this.max = max;
        this.economy = economy;
        this.msg = msg;
    }

    @Override
    public boolean canRedeem(Player player) {
        return true;
    }

    @Override
    public void onRedeem(Player player) {
        int amount = Util.generateRandomNumber(min,max);
        Octanevouchersplus.economiesAPI.getEconomy(economy).deposit(player,amount);
        String symbol = Octanevouchersplus.economiesAPI.getEconomy("gems").getSymbol();
        String amountFormatted = Util.formatNumberWithCommas(amount);
        if (economy.equalsIgnoreCase("vault")){
            symbol = "$";
        }
        player.sendMessage(Util.colorize(msg.replace("%symbol%",symbol).replace("%amount%",amountFormatted)));
    }
}
