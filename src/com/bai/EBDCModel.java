package com.bai;

import java.util.Arrays;
import java.util.List;

public class EBDCModel {
	private List<Double> sections;
	
	public EBDCModel(List<Double> sections) {
		this.sections=sections;
	}
	
	public void union(List<Integer> unionList){
	
		for (Integer integer : unionList) {
			sections.remove(integer.intValue());
		}
	}
	
	private int binarySearch(double data,int i,int j){
		if(i>=j){
			if(j>=0&&j<sections.size()){
				if(data>=sections.get(j))
					return j+1;
				else 
					return j;
			}
			else if(j<0){
				return 0;
			}
			else {
				return sections.size();
			}
		}	
		else if(data==sections.get((i+j)/2)){
			return (i+j)/2;
		}else if(data>sections.get((i+j)/2)){
			return binarySearch(data,(i+j)/2+1,j);
		}else {
			return binarySearch(data, i, (i+j)/2-1);
		}	
	}
	private int search(double data){
		int i=0;
		for(i=0;i<sections.size()&&data>=sections.get(i);i++);
		return i;
	}
	
	
	public int[] discretize(double[] datas){
		int[] res=new int[datas.length];
		for (int i=0;i<datas.length;i++) {
//			res[i]=binarySearch(datas[i], 0, sections.size()-1);
			res[i]=search(datas[i]);
		}
		return res;
	}
	
	public void print(){
		System.out.println(Arrays.toString(sections.toArray()));
	}
	
//	public static void main(String[] args) {
//		EBDCModel ebdcModel=new EBDCModel(Arrays.asList(new Double(1),new Double(2)));
//		System.out.println(Arrays.toString(ebdcModel.discretize(new double[]{0,1.1,1.2,3,2.4,5.1,8})));
//	}
}
