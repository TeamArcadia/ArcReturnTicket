package hy.pxreturnticket.command.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReturnTicketTab implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            if (label.equalsIgnoreCase("rt")) {
                if (sender.isOp()) completions.add("reload");
                if (sender.isOp()) completions.add("create");
                if (sender.isOp()) completions.add("delete");

            } else if (label.equalsIgnoreCase("귀환서")) {
                if (sender.isOp()) completions.add("리로드");
                if (sender.isOp()) completions.add("생성");
                if (sender.isOp()) completions.add("삭제");
            }
            return StringUtil.copyPartialMatches(args[0], completions, new ArrayList<>());
        }

        return Collections.emptyList();
    }
}