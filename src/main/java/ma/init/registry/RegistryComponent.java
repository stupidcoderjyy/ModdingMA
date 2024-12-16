package ma.init.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class RegistryComponent<D> implements IRegistryComponent<D> {
    private List<IRegistryComponent<D>> components = new ArrayList<>();
    private boolean closed = false;
    private final Supplier<String> nameGetter;

    public RegistryComponent(Supplier<String> nameGetter) {
        this.nameGetter = nameGetter;
    }

    public RegistryComponent<D> add(IRegistryComponent<D> c) {
        ensureOpen();
        components.add(c);
        return this;
    }

    public RegistryComponent<D> add(int i, IRegistryComponent<D> c) {
        ensureOpen();
        components.add(i, c);
        return this;
    }

    public RegistryComponent<D> remove(int i) {
        ensureOpen();
        components.remove(i);
        return this;
    }

    public RegistryComponent<D> replace(int i, IRegistryComponent<D> c) {
        ensureOpen();
        components.set(i, c);
        return this;
    }

    public RegistryComponent<D> override() {
        ensureOpen();
        components.clear();
        return this;
    }

    @Override
    public void register(D def) {
        ensureOpen();
        components.forEach(c -> c.register(def));
        closed = true;
        components = null;
    }

    private void ensureOpen() {
        if (closed) {
            throw new IllegalStateException("RegistryComponent closed: " + this);
        }
    }

    @Override
    public String toString() {
        return nameGetter.get();
    }
}
