SassyBarista is a java implementation of a CSS preprocessor. The goal is that it will 
eventually be fully compatible with the SCSS syntax of SASS (http://sass-lang.com). It's 
based on a JavaCC grammar and is in a comparatively early stage, although some advanced 
features are already supported. Thereare still a few glitches that prevents some standard 
CSS files from parsing correctly, but I'm working on getting rid of them. I'd love to hear 
from you if you try it and encounter such parsing bugs. You can contact me at emil@quench.at.

If you need a way to parse css and manipulate it programatically this project might also
fit nicely, since it was partly designed for that purpose. The java API should be rather
straight forward to use directly.

The fact that it is meant to be usable as a css parser has some consequences for the design
of the library. For example, the use of an AST is limited to expressions. This means that
there is no total ordering of nodes outside of each scope. Whether or not this is a problem
for you, I do not know. For my applications it is not.

Currently supported:

 * Correctly parses most of the CSS specification. If you find examples of valid
   css that it can't process, I'd like to hear about it.
 * Nesting of rules
 * Variables (has to be defined in the global scope right now)
 * Mixins (parameters are supported, although not keyword arguments, nesting rules
   within mixins works as expected)
 * Selector inheritance (chaining and multiple, no partial substitutions)
 * Basic arithmetic
 * A basic @if statement (no else if, no logical operators)
 * Functions are supported, although currently no functions are implemented.
 * Parent references
 * Interpolation using #{} in selectors and property-names.
 
Planned:

 * Improved error messages (currently javacc's default ParseException are used, which
   can be really confusing)
 * Various output styles
 * @else, @else if, @for, @each and @while
 * Keyword arguments to mixins
 
Not planned:

 * Function directives (@function)
 
For samples of the capabilities, look in the testcases directory. It contains a .scss-file
with the test case, and a manually controlled .css file. When running tests using "ant test"
these are validated.
 
To build it you'll need to download JavaCC and specify the path to it in build.xml. Simply 
runt ant to build:

    ant jar
    
You can then try ut using:

    java -jar sassybarista.jar test.scss
 
Some relevant links:

 * http://sass-lang.com/docs/yardoc/file.SASS_REFERENCE.html
 * http://javacc.java.net/
 * http://www.w3.org/TR/css3-selectors/
 * http://www.w3.org/TR/css3-syntax/
