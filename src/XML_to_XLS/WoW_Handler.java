package XML_to_XLS;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implements the methods in order to work with the specific XML file
 */
public class WoW_Handler extends DefaultHandler {
    /**
     * Contains a list of every lessons in XML file
     */
    private List<Lesson> lessons = new ArrayList<>();
    /**
     * Contains a list groups  of every lessons in xml file
     */
    private List<String> groups = new ArrayList<>();
    /**
     * Contains a list tutors  of every lessons in xml file
     */
    private List<String> tutors = new ArrayList<>();
    /**
     * Contains a list flats  of every lessons in xml file
     */
    private List<String> flats = new ArrayList<>();
    /**
     * Current lesson
     */
    private Lesson lesson = new Lesson();

    WoW_Handler() {
        super();
    }
    /**
     * Method returns the list of lessons
     *
     * @return list of lessons
     */
    public List<Lesson> getLessons() {
        return lessons;
    }

    /**
     * Set values in the current lesson , depending on the element name
     * Method calls when element is started
     * @param qName - name of the element
     * @param attributes - value of the attribute
     */
    private void setValue(String qName, Attributes attributes) {
        switch (qName) {
            case "Cycle": {
                lesson.setCycle(new Cycle(attributes.getValue("Text")
                        , Integer.parseInt(attributes.getValue("Index"))));
            }
            break;
            case "Pair": {
                lesson.setPair(new Pair(attributes.getValue("Text")
                        , Integer.parseInt(attributes.getValue("Index"))));
            }
            break;
            case "Day": {
                lesson.setDay(new Day(attributes.getValue("Text")
                        , Integer.parseInt(attributes.getValue("Index"))));
            }
            break;
            case "Subject": {
                lesson.setSubject(attributes.getValue("Text"));
            }
            break;
            case "SubjectType": {
                lesson.setSubjectType(attributes.getValue("Text"));
            }
            break;
            case "Group": {
                groups.add((attributes.getValue("Text")+"\n"));
            }
            break;
            case "Tutor": {
                tutors.add((attributes.getValue("Text")+"\n"));
            }
            break;
            case "Flat": {
                flats.add(attributes.getValue("Text"));
            }
            break;
            default:
                break;
        }
    }

    /**
     * Add the values in the lists
     * Method calls in the end of the element
     * @param qName - name of the element
     */
    private void checkAndAdd(String qName) {
        if (qName.equals("Groups")) {
            lesson.setGroups((ArrayList<String>) groups);
        }
        if (qName.equals("Tutors")) {
            lesson.setTutors((ArrayList<String>) tutors);
        }
        if (qName.equals("Flats")) {
            lesson.setFlats((ArrayList<String>)flats);
        }
        if (qName.equals("Lesson")) {
            lessons.add(lesson);
            lesson = new Lesson();
            groups = new ArrayList<>();
            tutors = new ArrayList<>();
            flats = new ArrayList<>();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        setValue(qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        checkAndAdd(qName);
    }

    @Override
    public String toString() {
        return "WoW_Handler{" +"lessons=" + lessons +'}';
    }
}
