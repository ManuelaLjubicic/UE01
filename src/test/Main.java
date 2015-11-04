package test;

import bookIndexFilter.CreateIndexFilter;
import bookIndexFilter.LineToWordFilter;
import bookIndexFilter.ReadLineFromRessourceFilter;
import bookIndexFilter.ShiftFilter;
import bookIndexPipe.PushPipe;
import transferObject.LineWithLineNumber;
import transferObject.WordArray;

import java.io.File;
import java.io.StreamCorruptedException;
import java.util.LinkedList;

/**
 * Created by Karin on 31.10.2015.
 */
public class Main {
    public static void main(String[] args) {

        PushPipe<LineWithLineNumber> pipe1 = new PushPipe<>();
        ReadLineFromRessourceFilter filter1 = new ReadLineFromRessourceFilter(pipe1);

        PushPipe<WordArray> pipe2 = new PushPipe<>();
        LineToWordFilter filter2 = new LineToWordFilter(pipe2);

        PushPipe<LineWithLineNumber> pipe3 = new PushPipe<>();
        ShiftFilter filter3 = new ShiftFilter(pipe3);

        PushPipe<LinkedList<String>> pipe4 = new PushPipe<>();
        CreateIndexFilter filter4 = new CreateIndexFilter(pipe4);

        pipe1.setSuccessorFilter(filter2);
        pipe2.setSuccessorFilter(filter3);


        try {
            filter1.write(new File("").getAbsolutePath() + "\\res\\aliceInWonderland.txt");
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }
}
