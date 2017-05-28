package com.sp_angle.app.minecraft_emd.makeclass;

import com.sp_angle.app.minecraft_emd.dataBase.DataBase;

public class Maker
{
	public String make(){
		StringBuilder makeb=new StringBuilder();
		
		for(int c1 = 0; c1<= DataBase.script.length-1; c1++){
			makeb.append(DataBase.script[c1]);
		}
		return makeb.toString();
	}
	
	public static String space(String target,int space){
		StringBuilder spaceb=new StringBuilder();
		for(int c2=0; c2<space; c2++){
			spaceb.append("    ");
		}
		spaceb.append(target);
		return spaceb.toString();
	}
	
	public int getmkh(){
		int mk=0;
		for(mk= DataBase.script.length-1; mk>0; mk--){
			if(DataBase.script[mk].indexOf("}")!=-1){
				break;
			}
		}
		if(mk==0)mk--;
		return mk+1;
	}
	
	public static int getmk(){
		int mk;
		for(mk=0; mk<= DataBase.script.length; mk++){
			if(DataBase.script[mk].indexOf(DataBase.hookOfKind)!=-1){
				for(;; mk++){
					int tk=0;
					if(DataBase.script[mk].indexOf("{")!=-1)tk++;
					if(DataBase.script[mk].indexOf("}")!=-1)tk--;
					if(tk==-1){
						for(int b = DataBase.script.length-1; b>=mk; b--){
							DataBase.script[b]= DataBase.script[b-1];
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
