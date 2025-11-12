package javaxx.newfeatures.textblocks;

import javax.script.ScriptException;
import java.text.MessageFormat;

public class TextBlocksWithArguments {

    public static void main(String[] args) throws ScriptException {
        WithArgumentsTextBlocks();
        jsExecute();
    }

    private static void WithArgumentsTextBlocks() {
        String json = """
                '{'
                   "id": {0, number,#},
                   "name": "{1}",
                   "salary": "{2, number}"
                '}'
                """;

        Object[] arguments = {
                1001,     // id
                "John Doe",  // name
                34000       // salary
        };

        String formattedTextBlock3 = MessageFormat.format(json, arguments);
        System.out.println(formattedTextBlock3);
    }

    private static void jsExecute() throws ScriptException {


        String jsCode="""
                         function hello() {
                             print('"Hello, world"');
                         }
                         
                         hello();
                         """;

        String query = """
               SELECT "EMP_ID", "LAST_NAME" FROM "EMPLOYEE_TB"
               WHERE "CITY" = 'INDIANAPOLIS'
               ORDER BY "EMP_ID", "LAST_NAME";
                """;

        System.out.println(jsCode);
        System.out.println(query);
    }
}
