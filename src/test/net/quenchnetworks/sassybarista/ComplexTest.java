package net.quenchnetworks.sassybarista;

import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.StringReader;

import static net.quenchnetworks.sassybarista.IoStreamHandling.closeQuietly;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Things that don't work:
 *
 * @else blocks
 * <p/>
 * Empty parenthesese in mixins, e.g. hello-world() must be hello-world
 * <p/>
 * Comma separated lists of things in a variable, e.g. $fontstack: helvetica, arial, sans-serif made it work by putting
 * quotes around it but not sure if thats good
 * <p/>
 * Single line comments. eg // some comment
 * <p/>
 * Names in parameters , e.g. input[type=submit] - its looking for a string was able to do input[type="submit"]
 * <p/>
 * <p/>
 * :: behaviours, e.g. hr::after
 */
public class ComplexTest {

    private final SassFileProcessor processor = new SassFileProcessor();


    @Test
    @Ignore("WIP")
    public void can_parse_some_sass() {

        String resultingCss = processor.parseSomeSassFrom(new File("testcases/complex/application.scss"));

        System.out.println(resultingCss);

    }

    @Test
    public void can_pre_process_imports() {
        String source = "@import \"mixins\";\n" +
                "@import \"fonts\";\n" +
                "@import \"global\";\n" +
                "@import \"example\";\n" +
                "@import \"response\";";

        String resultingSource = processor.preProcessImports(new StringReader(source), new File("testcases/complex/application.scss"));

        System.out.println(resultingSource);

    }

    @Test
    public void can_work_out_the_filename_of_an_import() {

        assertThat(processor.importFileNameFrom("global"), is("_global.scss"));
        assertThat(processor.importFileNameFrom("compass/reset"), is("compass/_reset.scss"));
        assertThat(processor.importFileNameFrom("some/real/long/path/reset"), is("some/real/long/path/_reset.scss"));

    }


}
