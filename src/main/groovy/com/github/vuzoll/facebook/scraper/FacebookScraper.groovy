package com.github.vuzoll.facebook.scraper

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import java.util.concurrent.TimeUnit

class FacebookScraper {

    static final String FACEBOOK = 'https://www.facebook.com'

    Random random = new Random()

    VuzollRecord scrap(String facebookId) {
        // let's be polite with facebook
        Thread.sleep(TimeUnit.MILLISECONDS.toMillis(random.nextInt(500)))

        String url = "$FACEBOOK/$facebookId"

        Document document = Jsoup.connect(url).get()

        def educationElement = document.select(':containsOwn(КНУ им. Т. Г. Шевченко)').parents()[1]
        def educationText = educationElement.select(':containsOwn(Випуск)').text()

        VuzollRecord vuzollRecord = new VuzollRecord()

        vuzollRecord.raw = document.html()

        vuzollRecord.name = '?'
        vuzollRecord.town = '?'

        EducationRecord educationRecord = new EducationRecord()
        educationRecord.university = 'КНУ им. Т. Г. Шевченко'
        educationRecord.graduateYear = educationText.substring('Випуск'.length(), educationText.indexOf('·')).trim()
        educationRecord.speciality = educationText.substring(educationText.indexOf('·') + 1, educationText.lastIndexOf('·')).trim()
        educationRecord.town = educationText.substring(educationText.lastIndexOf('·') + 1).trim()
        vuzollRecord.education << educationRecord

        CareerRecord careerRecord = new CareerRecord()
        careerRecord.company = '?'
        careerRecord.position = '?'
        careerRecord.town = '?'
        careerRecord.year = '?'
        vuzollRecord.career << careerRecord

        return vuzollRecord
    }
}