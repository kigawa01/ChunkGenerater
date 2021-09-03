package net.kigawa.chunkgenerator.util.plugin.gate.command.gate;

import net.kigawa.chunkgenerator.util.plugin.gate.GateManager;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.all.command.SecondCommand;

public abstract class GateCommandBase extends SecondCommand {
    private GateManager manager;

    public GateCommandBase(KigawaPlugin kigawaPlugin, GateManager gate) {
        super(kigawaPlugin);
        this.manager = gate;
    }

    public GateManager getManager() {
        return manager;
    }
}
