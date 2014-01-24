package selenium.pages

import geb.Page

import java.nio.file.FileSystems
import java.nio.file.Path

import static selenium.utils.Platform.isWindows

/**
 * Created by kenichiro_ota on 2014/01/22.
 */
class HomePage extends Page {
    static String url
    static {
        java.nio.file.FileSystem fileSystem = FileSystems.getDefault();
        if (isWindows()) {
            Path path = fileSystem.getPath("..¥¥..¥¥..¥¥testHTML¥¥index.html").toRealPath()
            url = "file:///" + path.toString().replace("¥¥", "/")
        } else {
            Path path = fileSystem.getPath("../../../testHTML/index.html").toRealPath()
            url = "file://" + path
        }
    }

    static at = { title == "Seleniumテストタイトル" }
    static content = {
        textElement { text -> $(text: contains(text)) }
        checkbox { $("input[type=checkbox]") }
        radioMale { $("#seibetsu1") }
        radioFemale { $("#seibetsu2") }
        selectCountry { $("#kokumei1").find("option", value:$("#kokumei1").value()) }
        textName { $("#name1") }
        buttonAlert { $("form").ALERT() }
        textPrice1 { $("form").PRICE01() }
        textPrice2 { $("form").PRICE02() }
        buttonCalculate { $("form").calcBtn() }
        textResult { $("form").RESULT() }
        buttonSubmit (to: SecondPage) { $("form").submit() }
    }

    def enterName(String name) {
        textName.value(name)
    }

    def calculate (price1, price2) {
        textPrice1.value(price1)
        textPrice2.value(price2)
        buttonCalculate.click()
    }
}
