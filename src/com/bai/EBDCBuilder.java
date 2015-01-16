package com.bai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class EBDCBuilder {
	
	public static EBDCModel build(double[] datas){
		HashMap<Double, Integer> map=new HashMap<>();
		int total=datas.length;
		
		for(double data:datas){
			if(map.containsKey(data)){
				map.put(data, map.get(data)+1);
			}
			else {
				map.put(data, 1);
			}
		}
		
		
		LinkedList<Double> list=new LinkedList<>();
		for(Double data:map.keySet()){
			list.add(data);
		}
		Collections.sort(list);
		LinkedList<Double> initSections=new LinkedList<>(list);
		EBDCModel model=new EBDCModel(initSections);
		
		
		double hmax=getE(map,total);
		System.out.println("e"+hmax);
		int maxk=list.size();
		
		ArrayList<Integer> unionList=new ArrayList<>();
		int k=maxk;
		double h=0;
		double deltah=0;
		int location=0;
		
		while(true){
			k--;
			double maxE=-Double.MAX_VALUE;
			int maxIndex=-1;
			for(int i=0;i<list.size()-1;i++){
				double pi=((double)map.get(list.get(i)))/total;
				double pj=((double)map.get(list.get(i+1)))/total;
				double res=pi*Math.log(pi)+pj*Math.log(pj)-(pi+pj)*Math.log(pi+pj);
				if(res>maxE){
					maxE=res;
					maxIndex=i;
				}
			}
			if(maxIndex==-1)
				break;
			deltah+=maxE;
			System.out.println(deltah);
			double tmph=Math.abs(-(maxk-2)*(hmax+deltah)+hmax*(k-1));
			
			System.out.println("h "+tmph);
			System.out.println("y "+(hmax+deltah));
			if(h<=tmph){
				h=tmph;
				location=unionList.size();
			}
			map.put(list.get(maxIndex), map.get(list.get(maxIndex))+map.get(list.get(maxIndex+1)));
			map.remove(list.get(maxIndex+1));
			list.remove(maxIndex+1);
			unionList.add(maxIndex+1);
			if(list.size()<=2)
				break;
		}
		System.out.println(Arrays.toString(unionList.toArray()));
		model.union(unionList.subList(0, location));
		return model;
	}

	private static double getE(HashMap<Double, Integer> map,int total) {
		double e=0;
		for(Integer num:map.values()){
			double p=((double)(num))/total;
			e-=p*Math.log(p);
		}
		return e;
	}
	
	public static void main(String[] args) {
		EBDCModel model=build(new double[]{1,2,2,3,3,3,4,4,4,4,5,5,5,5,6});
		model.print();
	}

}
