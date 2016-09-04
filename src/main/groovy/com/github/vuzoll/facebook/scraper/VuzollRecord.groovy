package com.github.vuzoll.facebook.scraper

class VuzollRecord {

    String raw

    String name
    String town

    Collection<EducationRecord> education = new ArrayList<>()

    Collection<CareerRecord> career = new ArrayList<>()
}
