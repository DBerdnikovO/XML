import java.util.*;

public class Marks {

    private double trueAverage = 0.0;
    private final Map<String, List<Double>> titleMark = new HashMap<>();
    private final Map<String, Integer> countTitle = new HashMap<>();

    public Marks() {}

    public void setTrueAverage() {
        for (String title1: titleMark.keySet()) {

            for (String title2: countTitle.keySet()) {
                if (title1.equals(title2)) {
                    trueAverage += titleMark.get(title1).stream().reduce(0.0, Double::sum)/countTitle.get(title1);
                }
            }
        }
        trueAverage = trueAverage/countTitle.size();
    }

    public void setMarks(List<String> marks) {

        for (int i = 0;i<marks.size();i++) {
            List<Double> doubles = new ArrayList<>();
            if (i%2==0) {
                countTitle.put(marks.get(i),Collections.frequency(marks, marks.get(i)));
                for (int j = 0;j<marks.size();j++) {
                    if(marks.get(j).equals(marks.get(i))) {
                        doubles.add(Double.parseDouble(marks.get(j+1)));
                        titleMark.put(marks.get(i),doubles);
                    }
                }
            }
        }

        setTrueAverage();
    }

    public Map<String, List<Double>> getTitleMark() {
        return titleMark;
    }

    public Map<String, Integer>  getCountTitle() {
        return countTitle;
    }

    public Double getTrueAverage() {
        return trueAverage;
    }

}
