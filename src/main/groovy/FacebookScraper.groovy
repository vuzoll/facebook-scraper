import org.jsoup.Jsoup
import org.jsoup.nodes.Document

String url = 'https://www.facebook.com/yaroslav.yermilov'

Document document = Jsoup.connect(url).get()

def educationElement = document.select(':containsOwn(КНУ им. Т. Г. Шевченко)').parents()[1]
def educationText = educationElement.text()

println educationElement.html()

def result = [:]
result.university = 'КНУ им. Т. Г. Шевченко'
result.graduateYear = educationText.substring('Випуск'.length(), educationText.indexOf('·')).trim()
result.speciality = educationText.substring(educationText.indexOf('·') + 1, educationText.lastIndexOf('·')).trim()
result.town = educationText.substring(educationText.lastIndexOf('·') + 1).trim()

println result