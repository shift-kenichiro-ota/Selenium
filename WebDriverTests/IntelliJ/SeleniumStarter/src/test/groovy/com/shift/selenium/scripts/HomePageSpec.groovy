package com.shift.selenium.scripts

import geb.spock.GebSpec
import selenium.pages.HomePage
import selenium.pages.SecondPage

/**
 * Created by kenichiro_ota on 2014/01/22.
 */
class HomePageSpec extends GebSpec {
    def "ElementsExistenceAndDefaultValues"() {
        when:
        to HomePage

        then:
        textElement("Seleniumテスト用ファイル")
        checkbox.size()  == 3
        radioMale.attr("checked")
        ! radioFemale.attr("checked")
        selectCountry.text() == "日本"
    }


    def "ControlLogic"() {
        when:
        to HomePage
        enterName("Kevin Wang")

        then:
        withAlert (wait: 2) { buttonAlert.click() } == "名前: Kevin Wang"
    }

    def "Calculation"() {
        when:
        to HomePage
        calculate(2.0, 3.1)

        then:
        waitFor(10) { textResult == "5.1" }
    }

    def "PageTransition"() {
        when:
        to HomePage
        buttonSubmit.click()

        then:
        textElement("Seleniumテスト用ファイル02")
    }
}
