import java.util.*;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String getResult(String inputStr) {

        if (inputStr.split("\\s+").length == 1) {
            return inputStr + " 1";
        } else {
            //split the input string with 1 to n pieces of spaces
            List<Input> inputList = getInputs(inputStr);

            //get the map for the next step of sizing the same word
            List<Word> wordList = getWords(inputList);

            wordList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

            return result(wordList);
        }
    }

    private String result(List<Word> wordList) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Word w : wordList) {
            String s = w.getValue() + " " + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private List<Word> getWords(List<Input> inputList) {
        Map<String, List<Input>> map = getListMap(inputList);

        List<Word> wordList = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Word word = new Word(entry.getKey(), entry.getValue().size());
            wordList.add(word);
        }
        return wordList;
    }

    private List<Input> getInputs(String inputStr) {
        String[] arr = inputStr.split("\\s+");

        List<Input> inputList = new ArrayList<>();
        for (String s : arr) {
            Input input = new Input(s);
            inputList.add(input);
        }
        return inputList;
    }

    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getValue(), arr);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}