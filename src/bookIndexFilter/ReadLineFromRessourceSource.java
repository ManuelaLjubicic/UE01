package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.*;
import transferObject.LineWithLineNumber;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.LinkedList;

/**
 * Created by manue on 31.10.2015.
 */
public class ReadLineFromRessourceSource extends AbstractFilter<String, LineWithLineNumber> {

    private FileReader _file;
    private int _lineNumber = 1;
    private String _filePath;
    private LinkedList<LineWithLineNumber> _lines;

    //TODO beim zweiten Filter müssten wir hier das WordArray als Output mitgeben!!!
    public ReadLineFromRessourceSource(IPushPipe<LineWithLineNumber> output) throws InvalidParameterException {
        super(output);
    }

    public ReadLineFromRessourceSource(String filePath) throws InvalidParameterException {
        this._filePath = filePath;
        readLines();
    }

    @Override
    public LineWithLineNumber read() throws StreamCorruptedException {
        return _lines.remove(0);
    }

    @Override
    public void run() {
    }

    @Override
    public void write(String filePath) throws StreamCorruptedException {
        _filePath = filePath;
        readLines();
        for (LineWithLineNumber l : _lines) {
            writeOutput(l);
        }
    }


    private void readLines() {
        _lines = new LinkedList<>();
        try {
            _file = new FileReader(_filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist.");
            e.printStackTrace();
        }

        BufferedReader br = new BufferedReader(_file);
        StringBuffer sb = new StringBuffer();
        String s;
        LineWithLineNumber lineEntity = new LineWithLineNumber();
        lineEntity.setEndOfSignal(false);

        int r;
        char c;
        try {
            while ((r = br.read()) != -1) {
                while ((r != 10) && (r != 13)) {

                    try {
                        c = (char) r;
                        sb.append(c);
                        r = br.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (r != 13) {
                    _lineNumber++;
                }
                s = sb.toString();

                if (!s.isEmpty()) {
                    lineEntity = new LineWithLineNumber();
                    lineEntity.setLine(s);
                    lineEntity.setLineNumber(_lineNumber);
                    _lines.add(lineEntity);
                }
                sb.delete(0, sb.length());
            }

            if (r == -1) {
                lineEntity = new LineWithLineNumber();
                lineEntity.setEndOfSignal(true);
                lineEntity.setLine("");
                _lines.add(lineEntity);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



