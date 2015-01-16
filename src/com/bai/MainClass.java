package com.bai;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class MainClass {
	public static void main(String[] args) throws IOException {
		double[] datas=CSVReader.getContent("resources/diabetic_data.csv");
	
		EBDCModel model=EBDCBuilder.build(datas);
		System.out.println(Arrays.toString(datas));
		model.print();
		int[] res=model.discretize(datas);
		HashMap<Integer, Integer> map=new HashMap<>();
		for (int i = 0; i < res.length; i++) {
			int data=res[i];
			if(map.containsKey(data)){
				map.put(data, map.get(data)+1);
			}
			else {
				map.put(data, 1);
			}
		}
		System.out.println(map);
		System.out.println(map.keySet());
		System.out.println(map.values());
	}
}
