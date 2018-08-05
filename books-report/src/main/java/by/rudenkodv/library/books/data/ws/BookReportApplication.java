package by.rudenkodv.library.books.data.ws;

import com.jaspersoft.mongodb.connection.MongoDbConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@RefreshScope
@RestController
public class BookReportApplication {

    @Value("${mongo.host}")
    private String host;

    @Value("${mongo.port}")
    private Integer port;

    @Value("${mongo.dbname}")
    private String dataBaseName;

    @GetMapping(value = "/report")
    public ResponseEntity<InputStreamResource> showReport() throws Exception {
        generateReport();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=booksReports.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(new ByteArrayInputStream(Files.readAllBytes(new File("booksReports.pdf").toPath()))));
    }

    public void generateReport() throws Exception {
        JasperReport jasperFile = (JasperReport) JRLoader.loadObject(getJasperFile());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, null, getDataSource());
        OutputStream stream = new FileOutputStream(new File("booksReports.pdf"));
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
    }

    private File getJasperFile() throws Exception {
        String fileReportName = "booksReports.jasper";
        File fileReport = new File(fileReportName);
        if (fileReport.exists() && !fileReport.isDirectory()) {
            return fileReport;
        }
        InputStream booksReportsStream = getClass().getResourceAsStream("/booksReports.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(booksReportsStream);
        JRSaver.saveObject(jasperReport, fileReportName);
        return fileReport;
    }

    public Connection getDataSource() {
        String mongoURI = String.format("mongodb://%s:%s/%s", host, port, dataBaseName);
        try {
            return new MongoDbConnection(mongoURI, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}