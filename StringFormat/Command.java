package StringFormat;

import java.util.*;

public class Command{
    private String name;
    private int start;
    private int end;
    private int lineNum;

    public Command(String command){
        StringTokenizer st = new StringTokenizer(command," ");
        name = st.nextToken();
        lineNum = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

    }

    public String getName (){
        return name;
    }

    public int getStart (){
        return start;
    }

    public int getEnd (){
        return end;
    }

    public int getLineNum (){
        return lineNum;
    }

    @Override
    public String toString (){
        StringBuilder sb = new StringBuilder(name).append(" ").append(start).append(" ").append(end).append(" ").append(lineNum);
        return sb.toString();
    }

    public String applyTo(String str){
        StringBuilder sb = new StringBuilder(str);
        switch(name){
            case "R":
                sb.replace(start,end,new StringBuilder(str.substring(start,end)).reverse().toString());
                break;
            case "S":
                StringBuilder sorted = new StringBuilder();
                String[] arr = str.substring(start,end).split("");
                Arrays.asList(str.substring(start,end).split("")).stream().sorted(new Comparator<String>(){
                    @Override
                    public int compare (String o1, String o2){
                        return o1.toLowerCase().compareTo(o2.toLowerCase());
                    }
                }).forEach(s->sorted.append(s));
                sb.replace(start,end,sorted.toString());
               break;
        }
        return sb.toString();
    }
}
