package net.kigawa.chunkgenerator.generator.command.generate;

import net.kigawa.chunkgenerator.generator.Generator;
import net.kigawa.chunkgenerator.util.plugin.all.KigawaPlugin;
import net.kigawa.chunkgenerator.util.plugin.all.command.SecondCommand;

public abstract class GenerateCommandBase extends SecondCommand {
    private Generator generator;

    public GenerateCommandBase(KigawaPlugin kigawaPlugin,Generator generator) {
        super(kigawaPlugin);
        this.generator=generator;
    }

    public Generator getGenerator() {
        return generator;
    }
}
