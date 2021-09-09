package net.kigawa.chunkgenerator.util.plugin.all.command;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;

public abstract class SecondCommand extends Command {
    public SecondCommand(KigawaPlugin kigawaPlugin) {
        super(kigawaPlugin);
    }

    @Override
    public int getWordNumber() {
        return 1;
    }
}
