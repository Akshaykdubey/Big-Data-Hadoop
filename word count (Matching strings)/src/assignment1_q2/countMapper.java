/*
 * @author Akshay Dubey
 * @project Big Data assignment1
 * @question 2
 */
 
package assignment1_q2;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.Mapper;

public class countMapper extends Mapper <LongWritable, Text, Text, Text> {

    private Text word = new Text("Word count");
    private Text file = new Text();

    private Set<String> patternsToSkip = new HashSet<String>();

    public void map(LongWritable key, Text value, Context context
                    ) throws IOException, InterruptedException {
      file.set(((FileSplit) context.getInputSplit()).getPath().getName().toString());
      String line = value.toString().toLowerCase();
	  patternsToSkip.add(",");
	  patternsToSkip.add(":");
	  patternsToSkip.add(";");
	  patternsToSkip.add(".");
	  patternsToSkip.add("!");
	  patternsToSkip.add("*");
	  patternsToSkip.add("'");
	  patternsToSkip.add(">");
	  patternsToSkip.add("<");
	  patternsToSkip.add("-");
	  patternsToSkip.add("(");
	  patternsToSkip.add(")");
	  patternsToSkip.add("[");
	  patternsToSkip.add("]");
	  patternsToSkip.add("?");
	  patternsToSkip.add("#");
	  patternsToSkip.add("@");
	  patternsToSkip.add("/");
	  patternsToSkip.add("{");
	  patternsToSkip.add("}");
	  String patterns[] = new String[patternsToSkip.size()];
	  patterns = patternsToSkip.toArray(patterns);
	  
      for (String pattern : patterns) {
        line = line.replace(pattern, "");
      }
      
      StringTokenizer itr = new StringTokenizer(line);
      while (itr.hasMoreTokens()) {
        word.set(itr.nextToken());
        context.write(word, file);
      }
    }
    
}
