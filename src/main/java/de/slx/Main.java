package de.slx;

import de.slx.cmds.Command;
import de.slx.cmds.TestCmd;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    /**
     * Puts all (wanted) commands into a map
     *
     * @return A map with the command as key and the command object as value
     */
    public static Map<String, Command> getCommands() {
        Map<String, Command> commands = new HashMap<>();
        new TestCmd().register(commands);
        return commands;
    }

    /**
     * Main method
     *
     * @param args The command and it's arguments
     */
    public static void main(String[] args) {
        System.out.println(processCommand(args));
    }

    /**
     * Executes the command with its arguments
     *
     * @param args The command and it's arguments
     * @return An error message if one does occur
     */
    private static String processCommand(String[] args) {
        if (args.length == 0)
            return "ERR: No command given";

        Map<String, Command> commands = getCommands();
        String command = args[0];
        args = Arrays.copyOfRange(args, 1, args.length);

        if (!commands.containsKey(command))
            return "ERR: Command not recognized";

        if (!commands.get(command).checkArguments(args))
            return "ERR: Wrong use of arguments";

        return "uh... success?";
    }
}
