/*
 * @author Akshay Dubey
 * @project Big Data assignment1
 * @question 2
 */
 
package assignment1_q2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class Assignment1_q2 {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, " word count ");
        job.setJarByClass(Assignment1_q2.class);
        job.setMapperClass(countMapper.class);
        job.setReducerClass(countReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args [0]));
        FileOutputFormat.setOutputPath(job, new Path(args [1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }        
    
}
