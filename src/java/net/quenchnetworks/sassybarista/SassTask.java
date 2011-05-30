package net.quenchnetworks.sassybarista;

import java.io.*;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.DirectoryScanner;

public class SassTask extends Task 
{
	public FileSet inFiles;
	public Path outputDir;

	public SassTask()
	{
	}
	
	@Override
	public void execute()
	throws BuildException
	{
		if (outputDir == null) {
			throw new BuildException("No output directory specified.");
		}
		
		String[] outDirs = outputDir.list();
		if (outDirs.length != 1) {
			throw new BuildException("Only one output dir may be specified.");
		}
		
		String outDir = outDirs[0];
		
		if (inFiles == null) {
			throw new BuildException("No input files specified.");
		}
	
		DirectoryScanner scanner = inFiles.getDirectoryScanner();
		String[] files = scanner.getIncludedFiles();
		for (String file : files) {
			try {
				File inFile = new File(inFiles.getDir(), file);
				System.out.println("Processing " + file);
				
				String newName = inFile.getName();
				newName = newName.replace(".scss", ".css");
				File outFile = new File(outDir, newName);
				processFile(inFile, outFile);
			}
			catch (Exception e) {
				throw new BuildException(e);
			}
		}
	}
	
	private void processFile(File inFile, File outFile)
	throws IOException, SerializationException, ParseException
	{
		PrintStream stream = new PrintStream(outFile);
		CssSerializer serializer = new CssSerializer(stream);
		serializer.render(new FileReader(inFile));
		stream.close();
	}
	
	public void add(FileSet fileSet)
	{
		this.inFiles = fileSet;
	}
	
	public void setOutputdir(Path path)
	{
		this.outputDir = path;
	}
}
