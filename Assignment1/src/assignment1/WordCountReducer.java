
package assignment1;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer
    extends Reducer<Text,IntWritable,Text,IntWritable> {
    
    enum CountersEnum { WORDS, WORDS_WITH_Z, WORDS_LESS_THAN_4 }
    private IntWritable result = new IntWritable();
    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
        
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }
        result.set(sum);
        context.getCounter(CountersEnum.WORDS).increment(1);
		if (key.toString().startsWith("z")){
			context.getCounter(CountersEnum.WORDS_WITH_Z).increment(1);
        if (sum < 4) {
            context.getCounter(CountersEnum.WORDS_LESS_THAN_4).increment(1);
        }
        context.write(key, result);
    }
}

