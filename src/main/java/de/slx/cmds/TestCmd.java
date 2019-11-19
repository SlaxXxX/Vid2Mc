package de.slx.cmds;

import java.util.Map;

public class TestCmd implements Command {

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public void register(Map<String, Command> cmds) {
        cmds.put(getName(), this);
    }

    @Override
    public boolean checkArguments(String[] args) {
        return true;
    }
}
