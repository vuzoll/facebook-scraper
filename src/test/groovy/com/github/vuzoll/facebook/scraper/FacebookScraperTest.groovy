package com.github.vuzoll.facebook.scraper

import spock.lang.Ignore
import spock.lang.Specification

class FacebookScraperTest extends Specification {

    def 'scrap Yaroslav Yermilov'() {
        setup:
        FacebookScraper facebookScraper = new FacebookScraper();

        when:
        VuzollRecord actual = facebookScraper.scrap('yaroslav.yermilov')

        then:
        actual.name == 'Yaroslav Yermilov'

        actual.locations.size() == 1
        actual.locations[0].name == 'Kyiv, Ukraine'

        actual.education.size() == 1
        actual.education[0].university == 'КНУ им. Т. Г. Шевченко'
        actual.education[0].graduateYear == '2015'
        actual.education[0].speciality == 'Информатика'
        actual.education[0].town == 'Kyiv, Ukraine'

        actual.career.size() == 2
        actual.career[0].company == 'Інститут Програмних Систем НАН України'
        actual.career[1].company == 'EPAM Systems'
    }

    def 'scrap Vlad Grytsun'() {
        setup:
        FacebookScraper facebookScraper = new FacebookScraper();

        when:
        VuzollRecord actual = facebookScraper.scrap('vlad.grytsun')

        then:
        actual.name == 'Vlad Grytsun'

        actual.locations.size() == 1
        actual.locations[0].name == 'Mountain View, California'
        actual.locations[0].since == '1 лютого 2014 р.'

        actual.education.size() == 1
        actual.education[0].university == 'Taras Shevchenko National University of Kyiv'
        actual.education[0].graduateYear == '2013'
        actual.education[0].speciality == 'Cybernetics'
        actual.education[0].town == 'Kyiv, Ukraine'

        actual.career.size() == 1
        actual.career[0].company == 'Facebook'
    }

    def 'scrap Andrii Usachov'() {
        setup:
        FacebookScraper facebookScraper = new FacebookScraper();

        when:
        VuzollRecord actual = facebookScraper.scrap('andrii.usachov')

        then:
        actual.name == 'Andrii Usachov'

        actual.education.size() == 1
        actual.education[0].university == 'Taras Shevchenko National University of Kyiv'
        actual.education[0].graduateYear == '2015'
        actual.education[0].speciality == 'Particle Physics'
        actual.education[0].town == 'Kyiv, Ukraine'

        actual.career.size() == 0
    }

    @Ignore('for some reason, facebook always return 404')
    def 'scrap Dmytro Ignatenko'() {
        setup:
        FacebookScraper facebookScraper = new FacebookScraper();

        expect:
        facebookScraper.scrap('dymonchyk')
    }

    def 'scrap Mykola Terelya'() {
        setup:
        FacebookScraper facebookScraper = new FacebookScraper();

        when:
        VuzollRecord actual = facebookScraper.scrap('100001940106241')

        then:
        actual.name == 'Mykola Terelya'

        actual.locations.size() == 1
        actual.locations[0].name == 'Kyiv, Ukraine'

        actual.education.size() == 1
        actual.education[0].university == 'Taras Shevchenko National University of Kyiv'
        actual.education[0].town == 'Kyiv, Ukraine'

        actual.career.size() == 0
    }
}
