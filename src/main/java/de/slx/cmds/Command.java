package de.slx.cmds;

import java.util.Map;

public interface Command {
    /**
     * @return The name by which the command gets recognized
     */
    public String getName();

    /**
     * registers itself into a map
     * @param cmds the map of commands to register to
     */
    public void register(Map<String, Command> cmds);

    /**
     * checks if arguments are valid
     * @param args arguments to check
     * @return valid
     */
    public boolean checkArguments(String[] args);
}
