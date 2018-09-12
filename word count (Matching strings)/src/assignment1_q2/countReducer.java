/*
 * @author Akshay Dubey
 * @project Big Data assignment1
 * @question 2
 */
 
package assignment1_q2;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class countReducer extends Reducer <Text, Text, Text, Text> {

    enum CountersEnum { WORDS }
    @Override
    public void reduce(Text key, Iterable<Text> values, 
            Context context) throws IOException, InterruptedException {
        
        int sum = 0;
        boolean check=false;
        String fileName="";
        String textString="{";
        /*reducing by counting the filename against each key*/
        for (Text val : values){
            if(!check){
                fileName=val.toString();
                check=true;
            }
            if (fileName.equals(val.toString())){
                sum=sum+1; //for counting the number of occurance in each file
            }
            else{
                textString+=fileName + "="+sum +",";
                fileName=val.toString();
                sum=1;
            }

        }
        textString+= fileName + "="+sum +"}"; //making output pattern
        if (!textString.contains(",")){
			context.getCounter(CountersEnum.WORDS).increment(1);
            context.write(key, new Text(textString));
        }       
    }
}
