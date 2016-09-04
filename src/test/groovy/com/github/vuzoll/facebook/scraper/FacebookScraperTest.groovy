package com.github.vuzoll.facebook.scraper

import spock.lang.Specification

class FacebookScraperTest extends Specification {

    def 'scrap Yaroslav Yermilov'() {
        setup:
        FacebookScraper facebookScraper = new FacebookScraper();

        when:
        VuzollRecord actual = facebookScraper.scrap('yaroslav.yermilov')

        then:
        !actual.raw.isEmpty()

        actual.name == 'Yaroslav Yermilov'

        actual.locations.size() == 1
        actual.locations[0].name == 'Kyiv, Ukraine'
        actual.locations[0].since == null

        actual.education.size() == 1
        actual.education[0].university == 'КНУ им. Т. Г. Шевченко'
        actual.education[0].graduateYear == '2015'
        actual.education[0].speciality == 'Cybernetics'
        actual.education[0].town == 'Kyiv, Ukraine'

        actual.career.size() == 1
        actual.career[0].company == 'EPAM Systems'
        actual.career[0].position == 'Senior Software Engineer'
        actual.career[0].town == 'Kyiv, Ukraine'
        actual.career[0].year == '2016'
    }

    def 'scrap Vlad Grytsun'() {
        setup:
        FacebookScraper facebookScraper = new FacebookScraper();

        when:
        VuzollRecord actual = facebookScraper.scrap('vlad.grytsun')

        then:
        !actual.raw.isEmpty()

        actual.name == 'Vlad Grytsun'

        actual.locations.size() == 1
        actual.locations[0].name == 'Mountain View, California'
        actual.locations[0].since == '1 лютого 2014 р.'

        actual.education.size() == 1
        actual.education[0].university == 'КНУ им. Т. Г. Шевченко'
        actual.education[0].graduateYear == '2015'
        actual.education[0].speciality == 'Cybernetics'
        actual.education[0].town == 'Kyiv, Ukraine'

        actual.career.size() == 1
        actual.career[0].company == 'EPAM Systems'
        actual.career[0].position == 'Senior Software Engineer'
        actual.career[0].town == 'Kyiv, Ukraine'
        actual.career[0].year == '2016'
    }

    def 'scrap Andrii Usachov'() {
        setup:
        FacebookScraper facebookScraper = new FacebookScraper();

        when:
        VuzollRecord actual = facebookScraper.scrap('andrii.usachov')

        then:
        !actual.raw.isEmpty()

        actual.name == 'Andrii Usachov'

        actual.locations == null

        actual.education.size() == 1
        actual.education[0].university == 'КНУ им. Т. Г. Шевченко'
        actual.education[0].graduateYear == '2015'
        actual.education[0].speciality == 'Cybernetics'
        actual.education[0].town == 'Kyiv, Ukraine'

        actual.career.size() == 1
        actual.career[0].company == 'EPAM Systems'
        actual.career[0].position == 'Senior Software Engineer'
        actual.career[0].town == 'Kyiv, Ukraine'
        actual.career[0].year == '2016'
    }

    def 'scrap Dmytro Ignatenko'() {
        setup:
        FacebookScraper facebookScraper = new FacebookScraper();

        when:
        VuzollRecord actual = facebookScraper.scrap('dymonchyk')

        then:
        !actual.raw.isEmpty()

        actual.name == 'Dmytro Ignatenko'

        actual.locations.size() == 1
        actual.locations[0].name == 'Kyiv, Ukraine'
        actual.locations[0].since == null

        actual.education.size() == 1
        actual.education[0].university == 'КНУ им. Т. Г. Шевченко'
        actual.education[0].graduateYear == '2015'
        actual.education[0].speciality == 'Cybernetics'
        actual.education[0].town == 'Kyiv, Ukraine'

        actual.career.size() == 1
        actual.career[0].company == 'EPAM Systems'
        actual.career[0].position == 'Senior Software Engineer'
        actual.career[0].town == 'Kyiv, Ukraine'
        actual.career[0].year == '2016'
    }

    def 'scrap Mykola Terelya'() {
        setup:
        FacebookScraper facebookScraper = new FacebookScraper();

        when:
        VuzollRecord actual = facebookScraper.scrap('100001940106241')

        then:
        !actual.raw.isEmpty()

        actual.name == 'Mykola Terelya'

        actual.locations.size() == 1
        actual.locations[0].name == 'Kyiv, Ukraine'
        actual.locations[0].since == null

        actual.education.size() == 1
        actual.education[0].university == 'КНУ им. Т. Г. Шевченко'
        actual.education[0].graduateYear == '2015'
        actual.education[0].speciality == 'Cybernetics'
        actual.education[0].town == 'Kyiv, Ukraine'

        actual.career.size() == 1
        actual.career[0].company == 'EPAM Systems'
        actual.career[0].position == 'Senior Software Engineer'
        actual.career[0].town == 'Kyiv, Ukraine'
        actual.career[0].year == '2016'
    }
}
