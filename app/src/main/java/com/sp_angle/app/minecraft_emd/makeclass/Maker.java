package com.sp_angle.app.minecraft_emd.makeclass;

import com.sp_angle.app.minecraft_emd.dataBase.dataBase;

public class Maker
{
	dataBase dataBase= new dataBase();
	public String make(){
		StringBuilder makeb=new StringBuilder();
		
		for(int c1=0; c1<=dataBase.script.length-1; c1++){
			makeb.append(dataBase.script[c1]);
		}
		return makeb.toString();
	}
	
	public String space(String target,int space){
		StringBuilder spaceb=new StringBuilder();
		for(int c2=0; c2<space; c2++){
			spaceb.append("    ");
		}
		spaceb.append(target);
		return spaceb.toString();
	}
	
	public int getmkh(){
		int mk=0;
		for(mk=dataBase.script.length-1;mk>0;mk--){
			if(dataBase.script[mk].indexOf("}")!=-1){
				break;
			}
		}
		if(mk==0)mk--;
		return mk+1;
	}
	
	public int getmk(){
		int mk;
		for(mk=0;mk<=dataBase.script.length;mk++){
			if(dataBase.script[mk].indexOf(dataBase.hookOfKind)!=-1){
				for(;; mk++){
					int tk=0;
					if(dataBase.script[mk].indexOf("{")!=-1)tk++;
					if(dataBase.script[mk].indexOf("}")!=-1)tk--;
					if(tk==-1){
						for(int b=dataBase.script.length-1;b>=mk;b--){
							dataBase.script[b]=dataBase.script[b-1];
						}
					    break;
					}
				}		
		    	break;
			}
		}
		return mk;
	}
}
