package gg.enes.moderation.bukkit.commands;

import gg.enes.moderation.bukkit.ModerationLanguage;
import gg.enes.moderation.bukkit.ModerationTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public abstract class MTCommand implements CommandExecutor {
    /**
     * The plugin command.
     */
    private final PluginCommand pluginCommand;

    /**
     * Creates a new command.
     *
     * @param name The name of the command.
     */
    public MTCommand(final String name) {
        this.pluginCommand = ModerationTools.getInstance().getCommand(name);

        if (this.pluginCommand == null) {
            throw new IllegalArgumentException("Command not found: " + name);
        }
    }

    @Override
    public final boolean onCommand(final CommandSender sender, final @NonNull Command command, final @NonNull String label, final String[] args) {
        if (isPlayerOnly() && !(sender instanceof Player)) {
            sender.sendMessage(ModerationLanguage.getMessage("player_only"));
            return true;
        }

        if (getPermission() != null && !sender.hasPermission(getPermission())) {
            sender.sendMessage(ModerationLanguage.getMessage("no_permission"));
            return true;
        }

        execute(sender, args);
        return true;
    }

    /**
     * Retrieves the plugin command.
     *
     * @return The plugin command.
     */
    public PluginCommand getPluginCommand() {
        return pluginCommand;
    }

    protected abstract void execute(CommandSender sender, String[] args);
    protected abstract @Nullable String getPermission();
    protected abstract boolean isPlayerOnly();
}
