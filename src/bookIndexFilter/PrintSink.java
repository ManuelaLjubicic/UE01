package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.IPullPipe;
import interfaces.IPushPipe;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

/**
 * Created by micha on 04.11.2015.
 */
public class PrintSink extends AbstractFilter<LinkedList<String>, Object>{

    String _fileName;

    public PrintSink(IPullPipe<LinkedList<String>> input, String _fileName) throws InvalidParameterException {
        super(input);
        this._fileName = _fileName;
    }

    public PrintSink(String fileName) throws InvalidParameterException {
        super();
        _fileName = fileName;
    }


    @Override
    public Object read() throws StreamCorruptedException {

        writeIntoFile(readInput());
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(LinkedList<String> value) throws StreamCorruptedException {
        writeIntoFile(value);
    }

    private void writeIntoFile(LinkedList<String> value){

        try {
            FileWriter fw = new FileWriter(_fileName);
            for(String s : value){
                fw.write(s +"\r\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
