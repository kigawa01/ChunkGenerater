package net.kigawa.chunkgenerator.util.plugin.stage.command;

import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.all.command.Subcommand;
import net.kigawa.chunkgenerator.util.plugin.stage.StageManager;

public abstract class StageCommandBase extends Subcommand {
    private StageManager manager;

    public StageCommandBase(KigawaPlugin kigawaPlugin, StageManager manager) {
        super(kigawaPlugin);
        this.manager = manager;
    }

    public StageManager getManager() {
        return manager;
    }
}

