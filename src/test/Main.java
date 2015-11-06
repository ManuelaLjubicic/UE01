package test;

import bookIndexFilter.*;
import bookIndexPipe.PullPipe;
import bookIndexPipe.PushPipe;
import transferObject.CharTransfer;
import transferObject.LineWithLineNumber;
import transferObject.WordArray;
import transferObject.WordTransfer;

import java.io.File;
import java.io.StreamCorruptedException;
import java.util.LinkedList;

/**
 * Created by Karin on 31.10.2015.
 */
public class Main {
    public static void main(String[] args) {

        pushPipelineB();

    }

    public static void pullPipelineA(){

        PullPipe<LinkedList<String>> pipe1 = new PullPipe<>();
        PrintSink filter1 = new PrintSink(pipe1, "testPull.txt");

        PullPipe<LineWithLineNumber> pipe2 = new PullPipe<>();
        CreateIndexFilter filter2 = new CreateIndexFilter(pipe2);

        PullPipe<WordArray> pipe3 = new PullPipe<>();
        ShiftFilter filter3 = new ShiftFilter(pipe3);


        PullPipe<LineWithLineNumber> pipe4 = new PullPipe<>();
        LineToWordFilter filter4 = new LineToWordFilter(pipe4);

        ReadLineFromRessourceSource filter5 = new ReadLineFromRessourceSource(new File("").getAbsolutePath() + "\\res\\aliceInWonderland.txt");

        pipe1.setPredecessorFilter(filter2);
        pipe2.setPredecessorFilter(filter3);
        pipe3.setPredecessorFilter(filter4);
        pipe4.setPredecessorFilter(filter5);

        try {
            filter1.read();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }

    }

    public static void pushPipelineA(){
        PushPipe<LineWithLineNumber> pipe1 = new PushPipe<>();
        ReadLineFromRessourceSource filter1 = new ReadLineFromRessourceSource(pipe1);

        PushPipe<WordArray> pipe2 = new PushPipe<>();
        LineToWordFilter filter2 = new LineToWordFilter(pipe2);

        PushPipe<LineWithLineNumber> pipe3 = new PushPipe<>();
        ShiftFilter filter3 = new ShiftFilter(pipe3);

        PushPipe<LinkedList<String>> pipe4 = new PushPipe<>();
        CreateIndexFilter filter4 = new CreateIndexFilter(pipe4);

        PushPipe<LinkedList<String>> pipe5 = new PushPipe<>();
        PrintSink filter5 = new PrintSink("testPush.txt");

        pipe1.setSuccessorFilter(filter2);
        pipe2.setSuccessorFilter(filter3);
        pipe3.setSuccessorFilter(filter4);
        pipe4.setSuccessorFilter(filter5);


        try {
            filter1.write(new File("").getAbsolutePath() + "\\res\\aliceInWonderland.txt");
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }

    public static void pushPipelineB(){
        PushPipe<CharTransfer> pipe1 = new PushPipe<>();
        CharacterSource filter1 = new CharacterSource(pipe1, 80);

        PushPipe<WordTransfer> pipe2 = new PushPipe<>();
        CharToWordFilter filter2 = new CharToWordFilter(pipe2);

        PushPipe<LineWithLineNumber> pipe3 = new PushPipe<>();
        WordToLineFilter filter3 = new WordToLineFilter(pipe3);

        PushPipe<LinkedList<String>> pipe4 = new PushPipe<>();
        LineToListFilter filter4 = new LineToListFilter(pipe4);

//        PushPipe<LinkedList<String>> pipe5 = new PushPipe<>();
//        PrintSink filter5 = new PrintSink("testPush_B");


//        PushPipe<LineWithLineNumber> pipe3 = new PushPipe<>();
//        ShiftFilter filter3 = new ShiftFilter(pipe3);
//
//        PushPipe<LinkedList<String>> pipe4 = new PushPipe<>();
//        CreateIndexFilter filter4 = new CreateIndexFilter(pipe4);
//
//        PushPipe<LinkedList<String>> pipe5 = new PushPipe<>();
//        PrintSink filter5 = new PrintSink("testPush.txt");
//
          pipe1.setSuccessorFilter(filter2);
          pipe2.setSuccessorFilter(filter3);
          pipe3.setSuccessorFilter(filter4);
//          pipe4.setSuccessorFilter(filter5);


        try {
            filter1.write(new File("").getAbsolutePath() + "\\res\\aliceInWonderland.txt");
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }
}


//TODO unwichtige Wörter aussortieren (HashMap?)
//TODO im LineToWordFilter die Satzzeichen (Sonderzeichen) entfernen
//TODO linksbündig, rechtsbündig, zentriert (ZEilenlänge im Mein mitgeben!!!)
//TODO pull seite
//TODO sysouts nervige kommentare und unnötige packete und klassen raus schmeißen