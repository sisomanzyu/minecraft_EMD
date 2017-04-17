package com.sp_angle.app.minecraft_emd.makeclass;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;



public class Output{
	Maker maker=new Maker();
	public void output(){
		String filePath = Environment.getExternalStorageDirectory() + "/EMD/mods/makedByEMD.js";
        File file = new File(filePath);
		file.delete();
        file.getParentFile().mkdir();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file, true);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter bw = new BufferedWriter(osw);
			bw.write(maker.make());
            bw.flush();
			bw.close();
        } catch (Exception e) {
        }

	}
}
