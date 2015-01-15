package com.bai;

import java.io.IOException;
import java.util.Arrays;

public class MainClass {
	public static void main(String[] args) throws IOException {
		double[] datas=CSVReader.getContent("resources/diabetic_data.csv");
	
		EBDCModel model=EBDCBuilder.build(datas);
		System.out.println(Arrays.toString(datas));
		model.print();
	}
}
