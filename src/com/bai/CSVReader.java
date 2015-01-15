package com.bai;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;
 class CSVReader {

     
     public static double[] getContent(String filePath) throws IOException{
    	 ArrayList<Double> list=new ArrayList<>();
         CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
         reader.readHeaders(); 
         int count=0;
         while (reader.readRecord()&&count<1000) {
             String[] strs = reader.getValues();
             try {
				list.add(Double.valueOf(strs[18]));
				count++;
			} catch (RuntimeException e) {
				
			}
         }
         reader.close();
         double[] res=new double[list.size()];
         for (int i=0;i<res.length;i++) {
			res[i]=list.get(i);
		}
         return res;
     }
    public static void main(String[] args) throws IOException {
//    	double[] res=getContent("resources/diabetic_data.csv");
//    	System.out.println(Arrays.toString(res));
	}
  

}