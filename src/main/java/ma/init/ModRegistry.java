package ma.init;

import ma.init.registry.AbstractDef;

public class ModRegistry {
    static void commonInit() {
        AbstractDef.DEFINITIONS.forEach(AbstractDef::registerCommon);
    }

    static void clientInit() {
        AbstractDef.DEFINITIONS.forEach(AbstractDef::registerClient);
    }

    static void serverInit() {
        AbstractDef.DEFINITIONS.forEach(AbstractDef::registerServer);
    }

    static void close() {
        AbstractDef.DEFINITIONS.forEach(AbstractDef::close);
    }
}
