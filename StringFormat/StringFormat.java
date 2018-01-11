package StringFormat;


import java.util.*;

public class StringFormat{
    private ArrayList<String> lines;
    private List<Command> commands;

    public StringFormat(){
        lines = new ArrayList<>();
        commands = new ArrayList<>();
    }

    public void createFormatter(String text, String commandsText){
        lines = new ArrayList<>(Arrays.asList(text.split("[\\n\\r]+")));
        for(String command:commandsText.split(";+")){
            commands.add(new Command(command));
        }
    }

    public List<String> getLines (){
        return lines;
    }

    public List<Command> getCommands (){
        return commands;
    }

    public List<String> applyCommands(){
        List<String> result = new ArrayList<>();
        String line;
        for(Command item:commands){
            line = lines.get(item.getLineNum());
            lines.remove(item.getLineNum());
            lines.add(item.getLineNum(),item.applyTo(line));
            result.addAll(lines);
            result.add("");
        }
        return result;
    }
}
