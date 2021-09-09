package net.kigawa.chunkgenerator.util.plugin.all.command;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;

public abstract class ForthCommand extends Command {
    public ForthCommand(KigawaPlugin kigawaPlugin) {
        super(kigawaPlugin);
    }

    @Override
    public int getWordNumber() {
        return 3;
    }
}