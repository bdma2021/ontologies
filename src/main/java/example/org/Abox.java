package example.org;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import example.org.utils.Constants;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.model.util.Values;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.model.vocabulary.RDFS;

import java.io.FileReader;
import java.util.List;

public class Abox {
    private static Abox abox;

    private static final String AREA_FILE = "src/main/resources/data/area.csv";
    private static final String WROTE_FILE = "src/main/resources/data/author_wrote_paper.csv";
    private static final String AUTHOR_FILE = "src/main/resources/data/authors.csv";
    private static final String CONFERENCE_AREA_FILE = "src/main/resources/data/conf_area.csv";
    private static final String CONFERENCE_FILE = "src/main/resources/data/conferences.csv";
    private static final String DECISION_FILE = "src/main/resources/data/decisions.csv";
    private static final String EDITION_FILE = "src/main/resources/data/editions.csv";
    private static final String HAS_PROCEEDINGS_FILE = "src/main/resources/data/has_proceedings.csv";
    private static final String HAS_PUBLICATION_FILE = "src/main/resources/data/has_publication.csv";
    private static final String HAS_VOLUME_FILE = "src/main/resources/data/has_volume.csv";
    private static final String IS_CHAIR_OF_FILE = "src/main/resources/data/is_chair_of.csv";
    private static final String IS_EDITOR_OF_FILE = "src/main/resources/data/is_editor_of.csv";
    private static final String JOURNAL_AREA_FILE = "src/main/resources/data/journal_area.csv";
    private static final String PAPER_AREA_FILE = "src/main/resources/data/paper_area.csv";
    private static final String PAPER_FILE = "src/main/resources/data/papers.csv";
    private static final String REVIEWS_FILE = "src/main/resources/data/reviews.csv";
    private static final String SUBMITTED_TO_FILE = "src/main/resources/data/submited_to.csv";
    private static final String VENUE_RESPONSIBLE_FILE = "src/main/resources/data/venue_responsible.csv"; // No need to handle this as we infer the type using IS_CHAIR_OF, IS_EDITOR_OF
    private static final String VENUE_FILE = "src/main/resources/data/venues.csv"; // No need to handle this as we infer the type using AREAS


    private Abox() {}

    public static Abox getInstance() {
        if(abox == null) {
            abox = new Abox();
        }
        return abox;
    }

    public void createAbox(ModelBuilder builder) {
        createAreas(builder);
        createWrote(builder);
        createAuthors(builder);
        createConferenceArea(builder);
        createConferences(builder);
        createDecisions(builder);
        createHasProceedings(builder);
        createHasPublication(builder);
        createHasVolume(builder);
        createIsChairOf(builder);
        createIsEditorOf(builder);
        createJournalArea(builder);
        createPaperArea(builder);
        createPapers(builder);
        createReviews(builder);
        createSubmittedTo(builder);
    }

    private void createAreas(ModelBuilder builder) {
        List<String[]> areas = this.readCSV(abox.AREA_FILE);
        areas.forEach(line -> {
            builder.subject(iri(line[0])).add(RDFS.LABEL, line[1]);
        });
    }

    private void createWrote(ModelBuilder builder) {
        List<String[]> wrote = this.readCSV(abox.WROTE_FILE);
        wrote.forEach(line -> {  // all authors wrote exactly 1 paper
            builder.subject(iri(line[0])).add(Tbox.WROTE, iri(line[1]));
        });
    }

    private void createAuthors(ModelBuilder builder) {
        List<String[]> authors = this.readCSV(abox.AUTHOR_FILE);
        authors.forEach(line -> {
            builder.subject(iri(line[0])).add(Tbox.NAME, line[1]);
        });
    }

    private void createConferenceArea(ModelBuilder builder) {
        List<String[]> conferenceArea = this.readCSV(abox.CONFERENCE_AREA_FILE);
        conferenceArea.forEach(line -> {
            builder.subject(iri(line[0])).add(Tbox.CONFERENCE_AREA, iri(line[1]));
        });
    }

    private void createConferences(ModelBuilder builder) {
        List<String[]> conferences = this.readCSV(abox.CONFERENCE_FILE);
        conferences.forEach(line -> {
            switch (line[1]) {
                case "RegularConference":
                    builder.subject(iri(line[0])).add(RDF.TYPE, Tbox.REGULAR_CONFERENCE);
                    break;
                case "Symposium":
                    builder.subject(iri(line[0])).add(RDF.TYPE, Tbox.SYMPOSIUM);
                    break;
                case "WorkGroup":
                    builder.subject(iri(line[0])).add(RDF.TYPE, Tbox.WORK_GROUP);
                    break;
                case "Workshop":
                    builder.subject(iri(line[0])).add(RDF.TYPE, Tbox.WORKSHOP);
                    break;
            }
        });
    }

    private void createDecisions(ModelBuilder builder) {
        List<String[]> decisions = this.readCSV(abox.DECISION_FILE);
        decisions.forEach(line -> {
            builder.subject(iri(line[0]))
                    .add(RDFS.LABEL, line[1])
                    .add(RDFS.COMMENT, line[2]);
        });
    }

    private void createHasProceedings(ModelBuilder builder) {
        List<String[]> hasProceedings = this.readCSV(abox.HAS_PROCEEDINGS_FILE);
        hasProceedings.forEach(line -> {
            builder.subject(iri(line[0])).add(Tbox.HAS_PROCEEDINGS, iri(line[1]));
        });
    }

    private void createHasPublication(ModelBuilder builder) {
        List<String[]> hasPuplication = this.readCSV(abox.HAS_PUBLICATION_FILE);
        hasPuplication.forEach(line -> {
            builder.subject(iri(line[0])).add(Tbox.HAS_PUBLICATION, iri(line[1]));
        });
    }

    private void createHasVolume(ModelBuilder builder) {
        List<String[]> hasVolume = this.readCSV(abox.HAS_VOLUME_FILE);
        hasVolume.forEach(line -> {
            builder.subject(iri(line[0])).add(Tbox.HAS_VOLUME, iri(line[1]));
        });
    }

    private void createIsChairOf(ModelBuilder builder) {
        List<String[]> isChairOf = this.readCSV(abox.IS_CHAIR_OF_FILE);
        isChairOf.forEach(line -> {
            builder.subject(iri(line[0])).add(Tbox.IS_CHAIR_OF, iri(line[1]));
        });
    }

    private void createIsEditorOf(ModelBuilder builder) {
        List<String[]> isEditorOf = this.readCSV(abox.IS_EDITOR_OF_FILE);
        isEditorOf.forEach(line -> {
            builder.subject(iri(line[0])).add(Tbox.IS_EDITOR_OF, iri(line[1]));
        });
    }

    private void createJournalArea(ModelBuilder builder) {
        List<String[]> journalArea = this.readCSV(abox.JOURNAL_AREA_FILE);
        journalArea.forEach(line -> {
            builder.subject(iri(line[0])).add(Tbox.JOURNAL_AREA, iri(line[1]));
        });
    }

    private void createPaperArea(ModelBuilder builder) {
        List<String[]> paperArea = this.readCSV(abox.PAPER_AREA_FILE);
        paperArea.forEach(line -> {
            builder.subject(iri(line[0])).add(Tbox.PAPER_AREA, iri(line[1]));
        });
    }

    private void createPapers(ModelBuilder builder) {
        List<String[]> papers = this.readCSV(abox.PAPER_FILE);
        papers.forEach(line -> {
            builder.subject(iri(line[0])).add(Tbox.TITLE, line[1]);
            switch (line[3]) {
                case "DemoPaper":
                    builder.subject(iri(line[0])).add(RDF.TYPE, Tbox.DEMO_PAPER);
                    break;
                case "FullPaper":
                    builder.subject(iri(line[0])).add(RDF.TYPE, Tbox.FULL_PAPER);
                    break;
                case "Poster":
                    builder.subject(iri(line[0])).add(RDF.TYPE, Tbox.POSTER);
                    break;
                case "ShortPaper":
                    builder.subject(iri(line[0])).add(RDF.TYPE, Tbox.SHORT_PAPER);
                    break;
            }
            if (iri(line[4]).equals("1")) {
                builder.subject(iri(line[0])).add(Tbox.YEAR, Integer.parseInt(line[2]));
            }
        });
    }

    private void createReviews(ModelBuilder builder) {
        List<String[]> reviews = this.readCSV(abox.REVIEWS_FILE);
        reviews.forEach(line -> {
            builder.subject(iri(line[4])).add(Tbox.DEFINED, iri(line[0]));
            builder.subject(iri(line[0]))
                    .add(Tbox.SUBJECT_PAPER, iri(line[1]))
                    .add(Tbox.DECIDED, iri(line[2]))
                    .add(Tbox.ASSIGNED_REVIEWER, iri(line[3]));
        });
    }

    private void createSubmittedTo(ModelBuilder builder) {
        List<String[]> submittedTo = this.readCSV(abox.SUBMITTED_TO_FILE);
        submittedTo.forEach(line -> {
            builder.subject(iri(line[0])).add(Tbox.SUBMITTED_TO, iri(line[1]));
        });
    }

    private List<String[]> readCSV(String fileName) {
        try (
                CSVReader reader = new CSVReaderBuilder(new FileReader(fileName))
                        .withSkipLines(1)
                        .build()
        ) {
            return reader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private IRI iri(String resource) {
        return Values.iri(Constants.NAME_SPACE, resource);
    }
}
