package com.github.vuzoll.facebook.scraper

import groovy.json.JsonSlurper
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import java.util.concurrent.TimeUnit

class FacebookScraper {

    static final String FACEBOOK = 'https://www.facebook.com'

    Random random = new Random()
    JsonSlurper jsonSlurper = new JsonSlurper()

    VuzollRecord scrap(String facebookId) {
        // let's be polite with facebook
        Thread.sleep(TimeUnit.MILLISECONDS.toMillis(200 + random.nextInt(300)))

        String url = "$FACEBOOK/$facebookId"

        Document document = Jsoup.connect(url).get()

        VuzollRecord vuzollRecord = new VuzollRecord()

        vuzollRecord.name = retrieveName(document)
        vuzollRecord.locations = retrieveLocation(document)

        vuzollRecord.education = retrieveEducation(document)
        vuzollRecord.career = retrieveCareer(document)

        return vuzollRecord
    }

    String retrieveName(Document document) {
        document.title().substring(0, document.title().indexOf(' | Facebook'))
    }

    Collection<LocationRecord> retrieveLocation(Document document) {
        try {
            String guess = document.select(':containsOwn(Місто проживання)').parents()[0].select('span').text()
            if (guess) {
                return [ new LocationRecord(name: guess) ]
            }
        } catch (Exception e) {
            // ignore
        }

        try {
            String guess = document.select(':containsOwn(Поточне місто)').parents()[0].select('span').text()
            if (guess) {
                int indexOfDate = 0;
                while (indexOfDate < guess.length() && !Character.isDigit(guess.charAt(indexOfDate))) {
                    indexOfDate++
                }
                if (indexOfDate < guess.length()) {
                    return [ new LocationRecord(name: guess.substring(0, indexOfDate - 1), since: guess.substring(indexOfDate)) ]
                } else {
                    return [ new LocationRecord(name: guess) ]
                }
            }
        } catch (Exception e) {
            // ignore
        }

        return null
    }

    Collection<EducationRecord> retrieveEducation(Document document) {
        try {
            def educationElement = document.select(':containsOwn(КНУ им. Т. Г. Шевченко)').parents()[1]
            def educationText = educationElement.select(':containsOwn(Випуск)').text()

            EducationRecord educationRecord = new EducationRecord()
            educationRecord.university = 'КНУ им. Т. Г. Шевченко'
            educationRecord.graduateYear = educationText.substring('Випуск'.length(), educationText.indexOf('·')).trim()
            educationRecord.speciality = educationText.substring(educationText.indexOf('·') + 1, educationText.lastIndexOf('·')).trim()
            educationRecord.town = educationText.substring(educationText.lastIndexOf('·') + 1).trim()

            return [ educationRecord ]
        } catch (Exception e) {
            // ignore
        }

        try {
            def educationElement = document.select(':containsOwn(Taras Shevchenko National University of Kyiv)').parents()[1]
            def educationText = educationElement.select(':containsOwn(Випуск)').text()

            EducationRecord educationRecord = new EducationRecord()
            educationRecord.university = 'Taras Shevchenko National University of Kyiv'
            try {
                educationRecord.graduateYear = educationText.substring('Випуск'.length(), educationText.indexOf('·')).trim()
            } catch (Exception e) {
                // ignore
            }
            try {
                educationRecord.speciality = educationText.substring(educationText.indexOf('·') + 1, educationText.lastIndexOf('·')).trim()
            } catch (Exception e) {
                // ignore
            }

            if (educationElement.text().contains('Kyiv, Ukraine')) {
                educationRecord.town = 'Kyiv, Ukraine'
            }

            return [ educationRecord ]
        } catch (Exception e) {
            // ignore
        }

        return []
    }

    Collection<CareerRecord> retrieveCareer(Document document) {
        jsonSlurper.parseText(document.select('script[type="application/ld+json"]')[0].data()).affiliation.collect {
            new CareerRecord(company: it.name)
        }
    }
}