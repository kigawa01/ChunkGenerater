package net.kigawa.chunkgenerator.util.plugin.all.command;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;

public abstract class Subcommand extends Command {
    public Subcommand(KigawaPlugin kigawaPlugin) {
        super(kigawaPlugin);
    }

    @Override
    public int getWordNumber() {
        return 1;
    }
}
