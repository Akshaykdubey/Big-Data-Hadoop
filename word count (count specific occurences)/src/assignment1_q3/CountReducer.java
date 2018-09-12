
package assignment1_q3;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.*;
import MiscUtils.sortByValues;

public class CountReducer extends Reducer <Text, IntWritable, Text, IntWritable> {

    private Map<Text, IntWritable> word = new HashMap<>();
    private int tempFreq = 0;
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, 
            Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        word.put(new Text(key), new IntWritable(sum));    
    }
    
    @Override
    public void cleanup(Context context) throws IOException, InterruptedException{ 
    //Cleanup is called once at the end to finish off anything for reducer
    //Here we will write our final output
        Map<Text, IntWritable> sortedMap = sortByValues.sort(word);

            int counter = 0;
            for (Text key : sortedMap.keySet()) {
                if (counter++ == 5) {
                    break;
                }
                context.write(key, sortedMap.get(key));
            }
     } 
    
}
