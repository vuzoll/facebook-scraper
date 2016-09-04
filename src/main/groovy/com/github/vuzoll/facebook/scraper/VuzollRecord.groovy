package com.github.vuzoll.facebook.scraper

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode
@ToString
class VuzollRecord {

    String name

    Collection<LocationRecord> locations

    Collection<EducationRecord> education

    Collection<CareerRecord> career
}
