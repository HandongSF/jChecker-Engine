package edu.isel.csee.jchecker.core.stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class JavaStage implements IGradeStage {

	
	@Override
	public int compile(String dpath)
	{
		int state = -1;
		ProcessBuilder builder = null;
		Process process = null;
		
		listup(dpath);
		
		try {
			builder = new ProcessBuilder(getCommand());
			builder.directory(new File(dpath));
			
			process = builder.start();
			state = process.waitFor();
			process.destroy();
		} catch(Exception e) {
			System.out.println("Compile Error: Fatal error in compile stage.");
			e.printStackTrace();
		}
		
		File list = new File(dpath + "/srclist.txt");
		list.delete();
		return state;
	}

	
	@Override
	public boolean build(ArrayList<String> cases, String output, String dpath)
	{
		boolean result = false;
		BufferedReader stdout = null;
		ProcessBuilder builder = null;
		Process process = null;
		
		
		try {
			builder = new ProcessBuilder(cases);
			builder.directory(new File(dpath));
			
			process = builder.start();
			
			
			stdout = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
			
			StringBuffer sb = new StringBuffer();
			String line;
			boolean flag = false;
			
			
			while( (line = stdout.readLine()) != null)
			{
				if (line.contains("> Task :app:run")) {
					flag = true;
					continue;
				}
				
				
				if (line.contains("BUILD SUCCESSFUL in"))
					flag = true;
				
				if (flag)
					sb.append(line + "\n");
			}
			
			
			String answer = sb.toString();
			
			if (output.equals(answer.trim()))
				result = true;
			
			
			stdout.close();
			process.destroy();
			
			
		} catch(Exception e) {
			System.out.println("Runtime Error: Fatal error in execute stage.");
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	
	public ArrayList<String> getTest(String packagePath, String[] input)
	{
		ArrayList<String> command = new ArrayList<>();
		
		command.add("java -cp bin");
		command.add(packagePath);
		
		
		for (String segment : input)
			command.add(segment);
		
		
		return command;
	}
	
	
	
	public ArrayList<String> getTest(String packagePath)
	{
		ArrayList<String> command = new ArrayList<>();
		
		command.add("java -cp bin");
		command.add(packagePath);

		
		return command;
	}
	
	
	
	private ArrayList<String> getCommand()
	{
		ArrayList<String> command = new ArrayList<>();
		
		command.add("javac -encoding UTF-8 -Xlint:deprecation -g:none -d bin @srclist.txt");

		
		return command;
	}
	
	
	
	private void listup(String dpath)
	{
		String command = "find . -name *.java > srclist.txt";
		ProcessBuilder builder = null;
		Process process = null;
		
		
		try {
			builder = new ProcessBuilder(command);
			builder.directory(new File(dpath));
			process = builder.start();
			process.waitFor();
			process.destroy();
		} catch (Exception e) {
			System.out.println("Error: No java files in the path: " + dpath);
			e.printStackTrace();
		}
		
	}
}
