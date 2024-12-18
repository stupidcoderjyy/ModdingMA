package ma.init.registry;

import ma.init.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class AbstractDef<D extends AbstractDef<D>> {
    public static final List<AbstractDef<?>> DEFINITIONS = new ArrayList<>();
    private RegistryComponent<D> clientRegistry = new RegistryComponent<>(() -> this + "(client)");
    private RegistryComponent<D> serverRegistry = new RegistryComponent<>(() -> this + "(server)");
    private RegistryComponent<D> commonRegistry = new RegistryComponent<>(() -> this + "(common)");
    private RegistryComponent<D> dataGenRegistry = new RegistryComponent<>(() -> this + "(dataGen)");
    private boolean closed = false;

    public AbstractDef() {
        DEFINITIONS.add(this);
    }

    public void registerClient() {
        ensureOpen();
        clientRegistry.register((D)this);
    }

    public void registerServer() {
        ensureOpen();
        serverRegistry.register((D)this);
    }

    public void registerCommon() {
        ensureOpen();
        commonRegistry.register((D)this);
    }

    public void close() {
        closed = true;
        clientRegistry = null;
        serverRegistry = null;
        commonRegistry = null;
        dataGenRegistry = null;
    }

    public void genData() {
        ensureOpen();
        dataGenRegistry.register((D)this);
        clientRegistry = null;
        serverRegistry = null;
        commonRegistry = null;
        dataGenRegistry = null;
    }

    public D modifyClientRegistry(Consumer<RegistryComponent<D>> handler) {
        handler.accept(clientRegistry);
        return (D)this;
    }

    public D modifyServerRegistry(Consumer<RegistryComponent<D>> handler) {
        handler.accept(serverRegistry);
        return (D)this;
    }

    public D modifyCommonRegistry(Consumer<RegistryComponent<D>> handler) {
        handler.accept(commonRegistry);
        return (D)this;
    }

    public D modifyDataGenRegistry(Consumer<RegistryComponent<D>> handler) {
        if (Mod.ENV_DATA_GEN) {
            handler.accept(dataGenRegistry);
        }
        return (D)this;
    }

    public RegistryComponent<D> commonRegistry() {
        return commonRegistry;
    }

    public RegistryComponent<D> clientRegistry() {
        return clientRegistry;
    }

    public RegistryComponent<D> serverRegistry() {
        return serverRegistry;
    }

    public RegistryComponent<D> dataGenRegistry() {
        return dataGenRegistry;
    }

    private void ensureOpen() {
        if (closed) {
            throw new IllegalStateException("registry closed");
        }
    }
}
