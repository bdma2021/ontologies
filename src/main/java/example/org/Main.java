package example.org;

import example.org.utils.Constants;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.util.ModelBuilder;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class Main {

    // GraphDB
    private static final String GRAPHDB_SERVER = "http://localhost:7200/";
    private static final String REPOSITORY_ID = "sdm-lab3";

    public static void main(String[] args) {
        System.out.println("Started creating models");
        Repository db = new HTTPRepository(GRAPHDB_SERVER, REPOSITORY_ID);

        ModelBuilder builder = new ModelBuilder().setNamespace(Constants.NAME_SPACE);

        ModelBuilder tboxBuilder = builder.namedGraph("sdm:tbox");
        Tbox tbox = Tbox.getInstance();
        tbox.createTbox(tboxBuilder);

        ModelBuilder aboxBuilder = builder.namedGraph("sdm:abox");
        Abox abox = Abox.getInstance();
        abox.createAbox(aboxBuilder);

        Model model = builder.build();

        // Open a connection to the database
        try (RepositoryConnection conn = db.getConnection()) {
            // add the model
            conn.add(model);
            System.out.println("Models successfully added to GraphDB");
        } finally {
            // Close the connection
            db.shutDown();
        }

        // Write models to rdf (trix) files
        Map<String, String> graphNames = new HashMap<>();
        graphNames.put("http://example.org/sdm#tbox", "BDMAG12C-B1-FonsecaHernandezMusaj.trix");
        graphNames.put("http://example.org/sdm#abox", "BDMAG12C-B2-FonsecaHernandezMusaj.trix");

        for (Resource context: model.contexts()) {
            try {
                OutputStream out = new FileOutputStream(graphNames.get(context.toString()));
                Rio.write(model.filter(null, null, null, context), out, RDFFormat.TRIX);
                out.close();
                System.out.println("Created file " + graphNames.get(context.toString()) + " for " + context);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
}
