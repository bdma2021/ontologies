package example.org;


import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Namespace;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.util.Values;
import org.eclipse.rdf4j.model.vocabulary.RDFS;
import example.org.utils.Constants;

public class Tbox {
    private static Tbox tbox;
    private static final Namespace NAME_SPACE = Constants.NAME_SPACE;

    // Classes
    static final IRI AUTHOR = Values.iri(NAME_SPACE, "Author");
    static final IRI REVIEWER = Values.iri(NAME_SPACE, "Reviewer");

    static final IRI PAPER = Values.iri(NAME_SPACE, "Paper");
    static final IRI POSTER = Values.iri(NAME_SPACE, "Poster");
    static final IRI DEMO_PAPER = Values.iri(NAME_SPACE, "DemoPaper");
    static final IRI SHORT_PAPER = Values.iri(NAME_SPACE, "ShortPaper");
    static final IRI FULL_PAPER = Values.iri(NAME_SPACE, "FullPaper");
    static final IRI PUBLICATION = Values.iri(NAME_SPACE, "Publication");

    static final IRI AREA = Values.iri(NAME_SPACE, "Area");
    static final IRI VENUE = Values.iri(NAME_SPACE, "Venue");
    static final IRI JOURNAL = Values.iri(NAME_SPACE, "Journal");
    static final IRI CONFERENCE = Values.iri(NAME_SPACE, "Conference");

    static final IRI REGULAR_CONFERENCE = Values.iri(NAME_SPACE, "RegularConference");
    static final IRI WORKSHOP = Values.iri(NAME_SPACE, "Workshop");
    static final IRI SYMPOSIUM = Values.iri(NAME_SPACE, "Symposium");
    static final IRI WORK_GROUP = Values.iri(NAME_SPACE, "WorkGroup");

    static final IRI CHAIR = Values.iri(NAME_SPACE, "Chair");
    static final IRI EDITOR = Values.iri(NAME_SPACE, "Editor");
    static final IRI VENUE_RESPONSIBLE = Values.iri(NAME_SPACE, "VenueResponsible");
    static final IRI REVIEW = Values.iri(NAME_SPACE, "Review");
    static final IRI DECISION = Values.iri(NAME_SPACE, "Decision");

    static final IRI PROCEEDINGS = Values.iri(NAME_SPACE, "Proceedings");
    static final IRI VOLUME = Values.iri(NAME_SPACE, "Volume");
    static final IRI EDITION = Values.iri(NAME_SPACE, "Edition");

    // Properties
    static final IRI WROTE = Values.iri(NAME_SPACE, "wrote");
    static final IRI NAME = Values.iri(NAME_SPACE, "name");

    static final IRI TITLE = Values.iri(NAME_SPACE, "title");
    static final IRI SUBMITTED_TO = Values.iri(NAME_SPACE, "submittedTo");
    static final IRI PAPER_AREA = Values.iri(NAME_SPACE, "paperArea");
    static final IRI JOURNAL_AREA = Values.iri(NAME_SPACE, "journalArea");
    static final IRI CONFERENCE_AREA = Values.iri(NAME_SPACE, "conferenceArea");

    static final IRI IS_EDITOR_OF = Values.iri(NAME_SPACE, "isEditorOf");
    static final IRI IS_CHAIR_OF = Values.iri(NAME_SPACE, "isChairOf");
    static final IRI DEFINED = Values.iri(NAME_SPACE, "defined");
    static final IRI DECIDED = Values.iri(NAME_SPACE, "decided");
    static final IRI ASSIGNED_REVIEWER = Values.iri(NAME_SPACE, "assignedReviewer");
    static final IRI SUBJECT_PAPER = Values.iri(NAME_SPACE, "subjectPaper");

    static final IRI HAS_VOLUME = Values.iri(NAME_SPACE, "hasVolume");
    static final IRI HAS_PROCEEDINGS = Values.iri(NAME_SPACE, "hasProceedings");
    static final IRI HAS_PUBLICATION = Values.iri(NAME_SPACE, "hasPublication");
    static final IRI YEAR = Values.iri(NAME_SPACE, "year");

    private Tbox() {}

    public static Tbox getInstance() {
        if(tbox == null) {
            tbox = new Tbox();
        }
        return tbox;
    }

    public void createTbox(ModelBuilder builder) {
        builder.subject(Tbox.REVIEWER).add(RDFS.SUBCLASSOF, Tbox.AUTHOR);

        builder.subject(Tbox.POSTER).add(RDFS.SUBCLASSOF, Tbox.PAPER);
        builder.subject(Tbox.DEMO_PAPER).add(RDFS.SUBCLASSOF, Tbox.PAPER);
        builder.subject(Tbox.FULL_PAPER).add(RDFS.SUBCLASSOF, Tbox.PAPER);
        builder.subject(Tbox.SHORT_PAPER).add(RDFS.SUBCLASSOF, Tbox.PAPER);
        builder.subject(Tbox.PUBLICATION).add(RDFS.SUBCLASSOF, Tbox.PAPER);

        builder.subject(Tbox.JOURNAL).add(RDFS.SUBCLASSOF, Tbox.VENUE);
        builder.subject(Tbox.CONFERENCE).add(RDFS.SUBCLASSOF, Tbox.VENUE);

        builder.subject(Tbox.EDITOR).add(RDFS.SUBCLASSOF, Tbox.VENUE_RESPONSIBLE);
        builder.subject(Tbox.CHAIR).add(RDFS.SUBCLASSOF, Tbox.VENUE_RESPONSIBLE);

        builder.subject(Tbox.WORK_GROUP).add(RDFS.SUBCLASSOF, Tbox.CONFERENCE);
        builder.subject(Tbox.SYMPOSIUM).add(RDFS.SUBCLASSOF, Tbox.CONFERENCE);
        builder.subject(Tbox.WORKSHOP).add(RDFS.SUBCLASSOF, Tbox.CONFERENCE);
        builder.subject(Tbox.REGULAR_CONFERENCE).add(RDFS.SUBCLASSOF, Tbox.CONFERENCE);

        builder.subject(Tbox.VOLUME).add(RDFS.SUBCLASSOF, Tbox.EDITION);
        builder.subject(Tbox.PROCEEDINGS).add(RDFS.SUBCLASSOF, Tbox.EDITION);

        builder.subject(Tbox.NAME).add(RDFS.DOMAIN, Tbox.AUTHOR);
        builder.subject(Tbox.NAME).add(RDFS.RANGE, RDFS.LITERAL);
        builder.subject(Tbox.WROTE).add(RDFS.DOMAIN, Tbox.AUTHOR);
        builder.subject(Tbox.WROTE).add(RDFS.RANGE, Tbox.PAPER);

        builder.subject(Tbox.SUBMITTED_TO).add(RDFS.DOMAIN, Tbox.PAPER);
        builder.subject(Tbox.SUBMITTED_TO).add(RDFS.RANGE,Tbox.VENUE);
        builder.subject(Tbox.TITLE).add(RDFS.DOMAIN, Tbox.PAPER);
        builder.subject(Tbox.TITLE).add(RDFS.RANGE, RDFS.LITERAL);
        builder.subject(Tbox.PAPER_AREA).add(RDFS.DOMAIN, Tbox.PAPER);
        builder.subject(Tbox.PAPER_AREA).add(RDFS.RANGE, Tbox.AREA);
        builder.subject(Tbox.JOURNAL_AREA).add(RDFS.DOMAIN, Tbox.JOURNAL);
        builder.subject(Tbox.JOURNAL_AREA).add(RDFS.RANGE, Tbox.AREA);
        builder.subject(Tbox.CONFERENCE_AREA).add(RDFS.DOMAIN, Tbox.CONFERENCE);
        builder.subject(Tbox.CONFERENCE_AREA).add(RDFS.RANGE, Tbox.AREA);

        builder.subject(Tbox.IS_EDITOR_OF).add(RDFS.DOMAIN, Tbox.EDITOR);
        builder.subject(Tbox.IS_EDITOR_OF).add(RDFS.RANGE, Tbox.JOURNAL);
        builder.subject(Tbox.IS_CHAIR_OF).add(RDFS.DOMAIN, Tbox.CHAIR);
        builder.subject(Tbox.IS_CHAIR_OF).add(RDFS.RANGE, Tbox.CONFERENCE);
        builder.subject(Tbox.DEFINED).add(RDFS.DOMAIN, Tbox.VENUE_RESPONSIBLE);
        builder.subject(Tbox.DEFINED).add(RDFS.RANGE, Tbox.REVIEW);
        builder.subject(Tbox.DECIDED).add(RDFS.DOMAIN, Tbox.REVIEW);
        builder.subject(Tbox.DECIDED).add(RDFS.RANGE, Tbox.DECISION);
        builder.subject(Tbox.ASSIGNED_REVIEWER).add(RDFS.DOMAIN, Tbox.REVIEW);
        builder.subject(Tbox.ASSIGNED_REVIEWER).add(RDFS.RANGE, Tbox.REVIEWER);
        builder.subject(Tbox.SUBJECT_PAPER).add(RDFS.DOMAIN, Tbox.REVIEW);
        builder.subject(Tbox.SUBJECT_PAPER).add(RDFS.RANGE, Tbox.PAPER);

        builder.subject(Tbox.HAS_VOLUME).add(RDFS.DOMAIN, Tbox.JOURNAL);
        builder.subject(Tbox.HAS_VOLUME).add(RDFS.RANGE, Tbox.VOLUME);
        builder.subject(Tbox.HAS_PROCEEDINGS).add(RDFS.DOMAIN, Tbox.CONFERENCE);
        builder.subject(Tbox.HAS_PROCEEDINGS).add(RDFS.RANGE, Tbox.PROCEEDINGS);
        builder.subject(Tbox.HAS_PUBLICATION).add(RDFS.DOMAIN, Tbox.EDITION);
        builder.subject(Tbox.HAS_PUBLICATION).add(RDFS.RANGE, Tbox.PUBLICATION);
        builder.subject(Tbox.YEAR).add(RDFS.DOMAIN, Tbox.PUBLICATION);
        builder.subject(Tbox.YEAR).add(RDFS.RANGE, RDFS.LITERAL);
    }
}
