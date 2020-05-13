package me.zeroeightsix.kami.command.commands;

import me.zeroeightsix.kami.command.Command;
import me.zeroeightsix.kami.command.syntax.ChunkBuilder;
import me.zeroeightsix.kami.module.modules.hidden.CustomChat;

import static me.zeroeightsix.kami.KamiMod.MODULE_MANAGER;
import static me.zeroeightsix.kami.util.MessageSendHelper.sendErrorMessage;
import static me.zeroeightsix.kami.util.MessageSendHelper.sendWarningMessage;

/**
 * @author S-B99
 * Created by S-B99 on 17/02/20
 */
public class CustomChatCommand extends Command {
    public CustomChatCommand() {
        super("customchat", new ChunkBuilder().append("ending").build(), "chat");
        setDescription("Allows you to customize CustomChat's custom setting");
    }

    @Override
    public void call(String[] args) {
        CustomChat cC = MODULE_MANAGER.getModuleT(CustomChat.class);
        if (cC == null) {
            sendErrorMessage("&cThe CustomChat module is not available for some reason. Make sure the name you're calling is correct and that you have the module installed!!");
            return;
        }
        if (!cC.isEnabled()) {
            sendWarningMessage("&6Warning: The CustomChat module is not enabled!");
            sendWarningMessage("The command will still work, but will not visibly do anything.");
        }
        if (!cC.textMode.getValue().equals(CustomChat.TextMode.CUSTOM)) {
            sendWarningMessage("&6Warning: You don't have custom mode enabled in CustomChat!");
            sendWarningMessage("The command will still work, but will not visibly do anything.");
        }
        for (String s : args) {
            if (s == null)
                continue;
            cC.customText.setValue(s);
            sendChatMessage("Set the Custom Text Mode to <" + s + ">");
        }
    }
}
