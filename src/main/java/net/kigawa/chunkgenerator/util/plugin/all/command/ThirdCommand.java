package net.kigawa.chunkgenerator.util.plugin.all.command;

import net.kigawa.escapeplugin.util.plugin.all.KigawaPlugin;

public abstract class ThirdCommand extends Command {
    public ThirdCommand(KigawaPlugin kigawaPlugin) {
        super(kigawaPlugin);
    }

    @Override
    public int getWordNumber() {
        return 2;
    }
}
