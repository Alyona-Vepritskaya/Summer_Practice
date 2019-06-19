package XML_to_XLS;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

public class WoW_Handler extends DefaultHandler {
    private ArrayList<Lesson> lessons = new ArrayList<>();
    private ArrayList<String> groups = new ArrayList<>();
    private ArrayList<String> tutors = new ArrayList<>();
    private ArrayList<String> flats = new ArrayList<>();
    private Lesson lesson = new Lesson();

    WoW_Handler() {
        super();
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

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

    private void checkAndAdd(String qName) {
        if (qName.equals("Groups")) {
            lesson.setGroups(groups);
        }
        if (qName.equals("Tutors")) {
            lesson.setTutors(tutors);
        }
        if (qName.equals("Flats")) {
            lesson.setFlats(flats);
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
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
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
