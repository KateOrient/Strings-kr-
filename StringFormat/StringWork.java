package StringFormat;


import java.io.*;
import java.util.*;

public class StringWork{
    private List<StringFormat> list;

    public StringWork(){
        list = new ArrayList<StringFormat>();
    }

    public void load(String fileName)throws IOException{
        Scanner scanner = new Scanner(new File(fileName)).useDelimiter("\r\n");
        StringBuilder text = new StringBuilder();
        String tmp;
        StringFormat sf;
        while (scanner.hasNext()){
            if(!(tmp = scanner.next()).matches("^([RS]{1} \\d+ \\d+ \\d+;)+$")){
                text.append(tmp).append("\n");
            }
            else{
                sf =  new StringFormat();
                sf.createFormatter(text.toString(),tmp);
                list.add(sf);
                text = new StringBuilder();
            }
        }
    }

    public void printTextLines(String fileName)throws IOException{
        List<String> strings = new ArrayList<>();
        for(StringFormat item:list){
            strings.addAll(item.getLines());
        }
        PrintStream ps = new PrintStream(fileName);
        strings.forEach(s->ps.println(s));
        ps.close();
    }

    public void printCommands(String fileName) throws IOException{
        List<Command> commands= new ArrayList<>();
        for(StringFormat item:list){
            commands.addAll(item.getCommands());
        }
        PrintStream ps = new PrintStream(fileName);
        commands.stream().sorted(new MyComparator()).forEach(c->ps.println(c));
        ps.close();
    }

    public void printFormatedText(String fileName) throws IOException{
        List<String> result = new ArrayList<>();
        for(StringFormat item:list){
            result.addAll(item.applyCommands());
        }
        PrintStream ps = new PrintStream(fileName);
        for(String item: result){
            ps.println(item);
        }
        ps.close();
    }

    private class MyComparator implements Comparator<Command>{
        @Override
        public int compare(Command o1, Command o2){
            return o1.getEnd()-o2.getStart()-o2.getEnd()+o2.getStart();
        }
    }

}
