package go_to_occurrence.generics;

public class StringSearchUtilities {

    public static int findClosestOccurrence(String text, String needle, int index) {
        String partBeforeIndex = text.substring(0, index);
        String partAfterIndex = text.substring(index);

        int previousOccurrenceIndex = partBeforeIndex.lastIndexOf(needle);
        int nextOccurrenceIndex = partAfterIndex.indexOf(needle);

        if (nextOccurrenceIndex != -1) {
            nextOccurrenceIndex = nextOccurrenceIndex + index;
        }

        if (nextOccurrenceIndex == -1 && previousOccurrenceIndex == -1) {
            return -1;
        }

        if (nextOccurrenceIndex == -1) {
            return previousOccurrenceIndex;
        }

        if (previousOccurrenceIndex == -1) {
            return nextOccurrenceIndex;
        }

        if (index - previousOccurrenceIndex > nextOccurrenceIndex - index) {
            return nextOccurrenceIndex;
        } else {
            return previousOccurrenceIndex;
        }
    }

}
