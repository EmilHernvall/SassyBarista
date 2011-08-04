package net.quenchnetworks.sassybarista.ant;

import java.io.*;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.DirectoryScanner;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.models.*;

public class SassTask extends Task 
{
    public boolean concat = false;
    public boolean compact = false;
    public boolean outputfilenames = false;
    public FileSet inFiles;
    public Path outputDir;
    public Path outputFile;

    public SassTask()
    {
    }
    
    @Override
    public void execute()
    throws BuildException
    {
        if (concat) {
            System.out.println("Concatenating all found files into one.");
            concatAll();
        } else {
            System.out.println("Outputing files individually.");
            processAll();
        }
    }
    
    private void processAll()
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
                PrintStream stream = new PrintStream(outFile);
                processFile(inFile, stream);
                stream.close();
            }
            catch (Exception e) {
                throw new BuildException(e);
            }
        }
    }
    
    private void concatAll()
    {
        if (outputFile == null) {
            throw new BuildException("No output file specified.");
        }
        
        String[] outputFiles = outputFile.list();
        if (outputFiles.length != 1) {
            throw new BuildException("Only one output file may be specified.");
        }
        
        String outFile = outputFiles[0];
        
        if (inFiles == null) {
            throw new BuildException("No input files specified.");
        }

        try {
            PrintStream stream = new PrintStream(new File(outFile));
        
            DirectoryScanner scanner = inFiles.getDirectoryScanner();
            String[] files = scanner.getIncludedFiles();
            for (String file : files) {
                    File inFile = new File(inFiles.getDir(), file);
                    System.out.println("Processing " + file);
                    if (outputfilenames) {
                        stream.println("/* " + file + " */");
                    }

                    processFile(inFile, stream);
            }
            
            stream.close();
        }
        catch (Exception e) {
            throw new BuildException(e);
        }
    }
    
    private void processFile(File inFile, PrintStream stream)
    throws IOException, SerializationException, ParseException
    {
        SassParser parser = new SassParser(new FileReader(inFile));
        SassSheet sheet = parser.parse();
    
        SassSheetSerializer serializer = new SassSheetSerializer(stream);
        serializer.render(sheet);
    }
    
    public void add(FileSet fileSet)
    {
        this.inFiles = fileSet;
    }
    
    public void setConcat(boolean v)
    {
        this.concat = v;
    }
    
    public void setCompact(boolean v)
    {
        this.compact = v;
    }
    
    public void setOutputfilenames(boolean v)
    {
        this.outputfilenames = v;
    }
    
    public void setOutputdir(Path path)
    {
        this.outputDir = path;
    }
    
    public void setOutputfile(Path path)
    {
        this.outputFile = path;
    }
}
