package selenium.pages

import geb.Page

import java.nio.file.FileSystem
import java.nio.file.FileSystems
import java.nio.file.Path

import static selenium.utils.Platform.isWindows

/**
 * Created with IntelliJ IDEA.
 * User: lei.wang
 * Date: 13/11/03
 * Time: 11:40
 * To change this template use File | Settings | File Templates.
 */
public class SecondPage extends Page {
    static String url
    static {
        FileSystem fileSystem = FileSystems.getDefault()
        if (isWindows()) {
            Path path = fileSystem.getPath("..\\..\\..\\testHTML\\html\\formSample02.html").toRealPath()
            url = "file:///" + path.toString().replace("\\", "/")
        } else {
            Path path = fileSystem.getPath("../../../testHTML/html/formSample02.html").toRealPath()
            url = "file://" + path
        }
    }

    static at = { title == "Seleniumテスト用ファイル02" }

    static content = {
        textElement { text -> $(text: contains(text)) }
    }

}

