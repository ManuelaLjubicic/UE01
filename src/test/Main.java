package test;

import others.Alignment;
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

    private static String inputPath;
    private static String outputPath;
    private static int len;
    private static Alignment align = Alignment.LEFT;

    public static void main(String[] args) {


        if(args[0].equals("pushA") || (args[0].equals("pullA"))){
            if(args.length == 3){
                inputPath = "\\" + args[1];
                outputPath = args[2];

                if(args[0].equals("pullA")){
                    pullPipelineA();
                    System.out.println("pullA output was successful!");
                }else{
                    pushPipelineA();
                    System.out.println("pushA output was successful!");
                }

            }else{
                System.out.println("Es müssen drei Parameter mitgegeben werden.");
            }
        }else if(args[0].equals("pullB") || (args[0].equals("pushB") || args[0].equals("pullAandB") || (args[0].equals("pushAandB")))){
            if(args.length == 5){
                inputPath =  "\\" + args[1];
                outputPath = args[2];
                len = Integer.parseInt(args[3]);

                if(args[4].equals("center")){
                    align = Alignment.CENTER;
                }else if(args[4].equals("right")){
                    align = Alignment.RIGHT;
                }

                if(args[0].equals("pullB")){
                    pullPipelineB();
                    System.out.println("pullB output was successful!");
                }else if(args[0].equals("pushB")){
                    pushPipelineB();
                    System.out.println("pushB output was successful!");
                }else if(args[0].equals("pullAandB")){
                    pullPipelineAandB();
                    System.out.println("pullAandB output was successful!");
                }else{
                    pushPipelineAandB();
                    System.out.println("pushAandB output was successful!");
                }
            }
        }else{
            System.out.println("Method doesn't exist.");
        }

    }

    public static void pullPipelineA() {

        PullPipe<LinkedList<String>> pipe1 = new PullPipe<>();
        PrintSink filter1 = new PrintSink(pipe1, outputPath);

        PullPipe<LineWithLineNumber> pipe2 = new PullPipe<>();
        CreateIndexFilter filter2 = new CreateIndexFilter(pipe2);

        PullPipe<WordArray> pipe3 = new PullPipe<>();
        ShiftFilter filter3 = new ShiftFilter(pipe3);


        PullPipe<LineWithLineNumber> pipe4 = new PullPipe<>();
        LineToWordFilter filter4 = new LineToWordFilter(pipe4);

        ReadLineFromRessourceSource filter5 = new ReadLineFromRessourceSource(new File("").getAbsolutePath() + inputPath);

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

    public static void pushPipelineA() {
        PushPipe<LineWithLineNumber> pipe1 = new PushPipe<>();
        ReadLineFromRessourceSource filter1 = new ReadLineFromRessourceSource(pipe1);

        PushPipe<WordArray> pipe2 = new PushPipe<>();
        LineToWordFilter filter2 = new LineToWordFilter(pipe2);

        PushPipe<LineWithLineNumber> pipe3 = new PushPipe<>();
        ShiftFilter filter3 = new ShiftFilter(pipe3);

        PushPipe<LinkedList<String>> pipe4 = new PushPipe<>();
        CreateIndexFilter filter4 = new CreateIndexFilter(pipe4);

        PrintSink filter5 = new PrintSink(outputPath);

        pipe1.setSuccessorFilter(filter2);
        pipe2.setSuccessorFilter(filter3);
        pipe3.setSuccessorFilter(filter4);
        pipe4.setSuccessorFilter(filter5);


        try {
            filter1.write(new File("").getAbsolutePath() + inputPath);
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }

    public static void pullPipelineB() {

        PullPipe<LinkedList<String>> pipe1 = new PullPipe<>();
        PrintSink filter1 = new PrintSink(pipe1, outputPath);

        PullPipe<LineWithLineNumber> pipe2 = new PullPipe<>();
        LineToListFilter filter2 = new LineToListFilter(pipe2);

        PullPipe<WordTransfer> pipe3 = new PullPipe<>();
        WordToLineFilter filter3 = new WordToLineFilter(pipe3, len, align);

        PullPipe<CharTransfer> pipe4 = new PullPipe<>();
        CharToWordFilter filter4 = new CharToWordFilter(pipe4);

        CharacterSource filter5 = new CharacterSource(new File("").getAbsolutePath() + inputPath);

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

    public static void pushPipelineB() {
        PushPipe<CharTransfer> pipe1 = new PushPipe<>();
        CharacterSource filter1 = new CharacterSource(pipe1);

        PushPipe<WordTransfer> pipe2 = new PushPipe<>();
        CharToWordFilter filter2 = new CharToWordFilter(pipe2);

        PushPipe<LineWithLineNumber> pipe3 = new PushPipe<>();
        WordToLineFilter filter3 = new WordToLineFilter(pipe3, len, align);

        PushPipe<LinkedList<String>> pipe4 = new PushPipe<>();
        LineToListFilter filter4 = new LineToListFilter(pipe4);

        PrintSink filter5 = new PrintSink(outputPath);


        pipe1.setSuccessorFilter(filter2);
        pipe2.setSuccessorFilter(filter3);
        pipe3.setSuccessorFilter(filter4);
        pipe4.setSuccessorFilter(filter5);

        try {
            filter1.write(new File("").getAbsolutePath() + inputPath);
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }


    public static void pullPipelineAandB() {

        PullPipe<LinkedList<String>> pipe1 = new PullPipe<>();
        PrintSink filter1 = new PrintSink(pipe1, outputPath);

        PullPipe<LineWithLineNumber> pipe2 = new PullPipe<>();
        CreateIndexFilter filter2 = new CreateIndexFilter(pipe2);

        PullPipe<WordArray> pipe3 = new PullPipe<>();
        ShiftFilter filter3 = new ShiftFilter(pipe3);

        PullPipe<LineWithLineNumber> pipe4 = new PullPipe<>();
        LineToWordFilter filter4 = new LineToWordFilter(pipe4);

        PullPipe<WordTransfer> pipe5 = new PullPipe<>();
        //Allignment wird hier nicht durchgeführt, da der LineToWordFilter alle Leerzeichen wieder entfernt
        WordToLineFilter filter5 = new WordToLineFilter(pipe5, len, align);

        PullPipe<CharTransfer> pipe6 = new PullPipe<>();
        CharToWordFilter filter6 = new CharToWordFilter(pipe6);

        CharacterSource filter7 = new CharacterSource(new File("").getAbsolutePath() + inputPath);

        pipe1.setPredecessorFilter(filter2);
        pipe2.setPredecessorFilter(filter3);
        pipe3.setPredecessorFilter(filter4);
        pipe4.setPredecessorFilter(filter5);
        pipe5.setPredecessorFilter(filter6);
        pipe6.setPredecessorFilter(filter7);

        try {
            filter1.read();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }

    public static void pushPipelineAandB() {
        PushPipe<CharTransfer> pipe1 = new PushPipe<>();
        CharacterSource filter1 = new CharacterSource(pipe1);

        PushPipe<WordTransfer> pipe2 = new PushPipe<>();
        CharToWordFilter filter2 = new CharToWordFilter(pipe2);

        PushPipe<LineWithLineNumber> pipe3 = new PushPipe<>();
        //Allignment wird hier nicht durchgeführt, da der LineToWordFilter alle Leerzeichen wieder entfernt
        WordToLineFilter filter3 = new WordToLineFilter(pipe3, len, align);

        PushPipe<WordArray> pipe4 = new PushPipe<>();
        LineToWordFilter filter4 = new LineToWordFilter(pipe4);

        PushPipe<LineWithLineNumber> pipe5 = new PushPipe<>();
        ShiftFilter filter5 = new ShiftFilter(pipe5);

        PushPipe<LinkedList<String>> pipe6 = new PushPipe<>();
        CreateIndexFilter filter6 = new CreateIndexFilter(pipe6);

        PrintSink filter7 = new PrintSink(outputPath);

        pipe1.setSuccessorFilter(filter2);
        pipe2.setSuccessorFilter(filter3);
        pipe3.setSuccessorFilter(filter4);
        pipe4.setSuccessorFilter(filter5);
        pipe5.setSuccessorFilter(filter6);
        pipe6.setSuccessorFilter(filter7);

        try {
            filter1.write(new File("").getAbsolutePath() + inputPath);
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }
}

