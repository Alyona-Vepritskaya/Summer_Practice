package XML_to_XLS;

import java.util.ArrayList;
import java.util.List;

public class Lesson {

    private ArrayList<String> Groups;
    private ArrayList<String> Tutors;
    private ArrayList<String> Flats;
    private Cycle Cycle;
    private Day Day;
    private Pair Pair;
    private String Subject;
    private String SubjectType;

    public Lesson() {
    }

    public Lesson(ArrayList<String> groups, ArrayList<String> tutors, ArrayList<String> flats, XML_to_XLS.Cycle cycle, XML_to_XLS.Day day, XML_to_XLS.Pair pair, String subject, String subjectType) {
        Groups = groups;
        Tutors = tutors;
        Flats = flats;
        Cycle = cycle;
        Day = day;
        Pair = pair;
        Subject = subject;
        SubjectType = subjectType;
    }

    public ArrayList<String> getGroups() {
        return Groups;
    }

    public void setGroups(ArrayList<String> groups) {
        Groups = groups;
    }

    public ArrayList<String> getTutors() {
        return Tutors;
    }

    public void setTutors(ArrayList<String> tutors) {
        Tutors = tutors;
    }

    public ArrayList<String> getFlats() {
        return Flats;
    }

    public void setFlats(ArrayList<String> flats) {
        Flats = flats;
    }

    public XML_to_XLS.Cycle getCycle() {
        return Cycle;
    }

    public void setCycle(XML_to_XLS.Cycle cycle) {
        Cycle = cycle;
    }

    public XML_to_XLS.Day getDay() {
        return Day;
    }

    public void setDay(XML_to_XLS.Day day) {
        Day = day;
    }

    public XML_to_XLS.Pair getPair() {
        return Pair;
    }

    public void setPair(XML_to_XLS.Pair pair) {
        Pair = pair;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getSubjectType() {
        return SubjectType;
    }

    public void setSubjectType(String subjectType) {
        SubjectType = subjectType;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                " G = " + Groups +
                ", T = " + Tutors +
                ", F = " + Flats +
                ", C = " + Cycle +
                ", D = " + Day +
                ", P = " + Pair +
                ", S = " + Subject +
                ", ST = " + SubjectType +
                '}'+"\n";
    }
}
