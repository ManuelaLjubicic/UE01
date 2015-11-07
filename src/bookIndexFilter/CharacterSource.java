package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.IPushPipe;
import transferObject.CharTransfer;
import transferObject.LineWithLineNumber;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.LinkedList;

/**
 * Created by manue on 05.11.2015.
 */
public class CharacterSource extends AbstractFilter<String, CharTransfer> {

    private String _filePath;
    private FileReader _file;
    private LinkedList<CharTransfer> _chars;

    public CharacterSource(IPushPipe<CharTransfer> output) throws InvalidParameterException {
        super(output);
    }

    public CharacterSource(String filePath) throws InvalidParameterException {
        this._filePath = filePath;
        readCharacter();
    }


    @Override
    public CharTransfer read() throws StreamCorruptedException {
        return _chars.remove(0);
    }

    @Override
    public void run() {

    }

    @Override
    public void write(String filePath) throws StreamCorruptedException {
        _filePath = filePath;
        readCharacter();
        for (CharTransfer c : _chars) {
            writeOutput(c);
        }

    }

    private void readCharacter(){

        try {
            _file = new FileReader(_filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist.");
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(_file);
        CharTransfer charTransfer = new CharTransfer();
        charTransfer.setIsEndOfSignal(false);
        _chars = new LinkedList<>();

        int r;
        char c;
        try {
            while ((r = br.read()) != -1) {
                while ((r != 10) && (r != 13)) {

                    try {
                        c = (char) r;
                        charTransfer = new CharTransfer();
                        charTransfer.setC(c);
                        _chars.add(charTransfer);

                        r = br.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (r == -1) {
                charTransfer = new CharTransfer();
                charTransfer.setIsEndOfSignal(true);
                _chars.add(charTransfer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
