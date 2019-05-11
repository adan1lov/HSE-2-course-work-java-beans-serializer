package XMLParser;

import Syntacse.XML.Element;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class MyParser {

    String input;
    long ObjectBegin = 0;

    public MyParser(String path) throws IOException {
        List<String> xml = Files.readAllLines(Paths.get(path));
        StringBuilder s = new StringBuilder();
        for (String str : xml) {
            s.append(str);
        }
        input = s.toString();
        System.out.println(input);
    }

    public void parse() {
        removeHeader();

        while (true) {
            boolean b = false;
            long start = ObjectBegin;
            while (input.charAt((int) start) != '<') {
                start++;
            }
            long end = start;
            while (input.charAt((int) end) != '>') {
                end++;
            }
            long start2 = end;
            b = input.charAt((int) start + 1) != '/';
            String str = input.substring((int) start + 1, (int) end);
            String[] params = str.split(" ");
            String qName = params[0];

            if (b) {
                Element elem = new Element();
                for (int i = 1; i < params.length; i++) {
                    elem.setValue(params[i].substring(0, params[i].indexOf('=')), params[i].substring(params[i].indexOf('\"')+1, params[i].lastIndexOf('\"')));
                }
                //START
            } else {
                //END
            }
            if (start2 + 1 == input.length()) {
                return;
            }
            while (input.charAt((int) start2) != '<') {
                start2++;
            }
            if (b) {
                //CHARACTER
                System.out.println("\t" + input.substring((int) end + 1, (int) start2));
            }
            ObjectBegin = start2;
        }

    }

    private void removeHeader() {
        if (input.substring(2, 5).toLowerCase().equals("xml")) {
            int i = 5;
            while (input.charAt(i) != '>') {
                i++;
            }
            ObjectBegin = i + 1;

        }
    }
}
