package bookIndexFilter;

import filter.AbstractFilter;
import interfaces.IPushPipe;
import transferObject.LineWithLineNumber;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Created by manue on 02.11.2015.
 */
public class CreateIndexFilter extends AbstractFilter<LineWithLineNumber, LinkedList<String>> {

    private LinkedList<String> indexList = new LinkedList<>();

    public CreateIndexFilter(IPushPipe<LinkedList<String>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    public LinkedList<String> read() throws StreamCorruptedException {
        return null;
    }

    @Override
    public void run() {

    }

    @Override
    public void write(LineWithLineNumber value) throws StreamCorruptedException {

       if(!value.isEndOfSignal()){
           String indexEntry = value.getLine() + " " + value.getLineNumber();

           Collections.sort(indexList, new SortIgnoreCase());
       }else{

           for(String s : indexList){
               System.out.println(s);
           }

           writeOutput(indexList);
       }

    }

    public class SortIgnoreCase implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            String s1 = (String) o1;
            String s2 = (String)o2;

            return s1.toLowerCase().compareTo((s2.toLowerCase()));
        }
    }
}