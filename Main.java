import StringFormat.StringWork;

import java.io.*;

public class Main{
    public static void main (String[] args)throws IOException{
        StringWork sw = new StringWork();
        sw.load("editor.in");
        sw.printTextLines("editor1.out");
        sw.printCommands("editor2.out");
        sw.printFormatedText("editor3.out");
    }
}
