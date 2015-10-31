package transferObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karin on 31.10.2015.
 */
public class WordArray {
    List<String> _wordArray ;
    public WordArray(){
        _wordArray = new ArrayList<>();
    }
    public void addToWordArray(String word){
        _wordArray.add(word);
    }

}
