package unalcol.gui.I18N;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
/**
*
* Language
* <P>Language used by the GUI.
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/quilt/src/quilt/Language.java" target="_blank">
* Source code </A> is available.
*
* <h3>License</h3>
*
* Copyright (c) 2017 by Jonatan Gomez-Perdomo. <br>
* All rights reserved. <br>
*
* <p>Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
* <ul>
* <li> Redistributions of source code must retain the above copyright notice,
* this list of conditions and the following disclaimer.
* <li> Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
* <li> Neither the name of the copyright owners, their employers, nor the
* names of its contributors may be used to endorse or promote products
* derived from this software without specific prior written permission.
* </ul>
* <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
* AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
* IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
* LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
* SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
* INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
* CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
* ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
* POSSIBILITY OF SUCH DAMAGE.
*
*
*
* @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
* (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
* @version 1.0
*/
public class I18NManager {
	public static final char MSG_SEPARATOR='='; 
	protected Hashtable<String, String> table = new Hashtable<String,String>();
	
	public I18NManager(){}

	public I18NManager(InputStream in) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder sb=new StringBuilder();
		String line = reader.readLine();
		while(line!=null && line.length()>0){
			sb.append(line);
			sb.append('\n');
			line = reader.readLine();
		}
		in.close();
		load(sb.toString());		
	}

/*	
	public I18NManager(String fileName, boolean asResource ){
		InputStream in=null;
		try{
			if( asResource ) in = this.getClass().getResourceAsStream(fileName);
			else in = new FileInputStream(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb=new StringBuilder();
			String line = reader.readLine();
			while(line!=null && line.length()>0){
				sb.append(line);
				sb.append('\n');
				line = reader.readLine();
			}
			in.close();
			load(sb.toString());
		}catch(Exception e){ System.err.println(e.getMessage()); }
	} 
*/	
	public void load(String config){
		table.clear();
		String[] lines = config.split("\\n");
		for( String line:lines ){
			String code = line.substring(0, line.indexOf(MSG_SEPARATOR));
			String message = line.substring(line.indexOf(MSG_SEPARATOR)+1);
			set(code,message);
		}
	}	
	
	protected String process( String message ){
		StringBuilder sb = new StringBuilder();
		int i=0; 
		while( i<message.length() ){
			char c = message.charAt(i); 
			if(c=='\\'){
				i++;
				if(i<message.length()){
					c = message.charAt(i);
					switch( c ){
						case '\\': sb.append('\\'); break;
						case 'n':  sb.append('\n'); break;
						case 't':  sb.append('\t'); break;
						default: sb.append('\\'); sb.append(c); 
					}
					i++;
				}else{
					sb.append(c);
				}
			}else{
				sb.append(c);
				i++;
			}
		}
		return sb.toString();
	}

	public void set( String code, String message){
		table.put(code,process(message));
	}
	
	public String get( String code ){
		return table.get(code);
	}	
	
	public static void main(String[] args){
		I18NManager manager = new I18NManager();
		manager.load("LOC=Localizacion\nMED=Medio\\n loco\n");
		System.out.println(manager.get("LOC"));
		System.out.println(manager.get("MED"));
	}
}