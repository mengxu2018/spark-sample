package org.apache.spark.examples;/* SimpleApp.java */
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;

public class SimpleApp {
    public static void main(String[] args) {
        String logFile = "examples/src/main/resources/people.json";
        SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache();




//        long numAs = logData.filter(new FilterFunction<String>() {
//            @Override
//            public boolean call(String s) throws Exception {
//                return s.contains("a");
//            }
//        }).count();
//
//        long numBs = logData.filter(new FilterFunction<String>() {
//            @Override
//            public boolean call(String s) throws Exception {
//                return s.contains("b");
//            }
//        }).count();

        FilterFunction<String> f1 = s -> s.contains("a");
        FilterFunction<String> f2 = s -> s.contains("b");
        long numAs = logData.filter(f1).count();
        long numBs = logData.filter(f2).count();

//        long numAs = logData.filter(s -> s.contains("a")).count();
//        long numBs = logData.filter(s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        spark.stop();
        System.out.println("hello world");
    }
}