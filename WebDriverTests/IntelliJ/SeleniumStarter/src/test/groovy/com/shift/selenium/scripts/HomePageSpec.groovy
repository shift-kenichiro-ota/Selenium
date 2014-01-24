package com.shift.selenium.scripts

import geb.spock.GebSpec
import selenium.pages.HomePage
import selenium.pages.HomePageGeb
import selenium.pages.SecondPageGeb

/**
 * Created by kenichiro_ota on 2014/01/22.
 */
class HomePageSpec extends GebSpec {
    def "ElementsExistenceAndDefaultValues"() {
        when:
        to HomePageGeb

        then:
        at HomePageGeb
        textElement("Seleniumテスト用ファイル")
        checkbox.size()  == 3
        radioMale.attr("checked")
        ! radioFemale.attr("checked")
        selectCountry.text() == "日本"
    }


    def "ControlLogic"() {
        when:
        to HomePageGeb
        enterName("Kevin Wang")

        then:
        withAlert (wait: 2) { buttonAlert.click() } == "名前: Kevin Wang"
    }

    def "Calculation"() {
        when:
        to HomePageGeb
        calculate(2.0, 3.1)

        then:
        waitFor(10) { textResult.value() == "5.1" }
    }

    def "PageTransition"() {
        when:
        to HomePageGeb
        buttonSubmit.click()

        then:
        at SecondPageGeb
        textElement("Seleniumテスト用ファイル02")
    }
}
