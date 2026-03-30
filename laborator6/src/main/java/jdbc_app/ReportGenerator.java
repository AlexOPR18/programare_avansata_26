package jdbc_app;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportGenerator {

    public void generateHTML() {
        String templatePath = "template.html";
        String outputPath = "raport.html";

        try {
        	String htmlTemplate = new String(Files.readAllBytes(Paths.get(templatePath)));

            StringBuilder rows = new StringBuilder();
            Connection con = Database.getInstance().getConnection();
            
            try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM view_movie_report");
                 ResultSet rs = pstmt.executeQuery()) {
                
                while (rs.next()) {
                    rows.append("        <tr>\n");
                    rows.append("            <td>").append(rs.getString("title")).append("</td>\n");
                    rows.append("            <td>").append(rs.getDate("release_date")).append("</td>\n");
                    rows.append("            <td>").append(rs.getInt("duration")).append("</td>\n");
                    rows.append("            <td>").append(rs.getDouble("score")).append("</td>\n");
                    rows.append("        </tr>\n");
                }
            } finally {
                con.close();
            }

            String finalHtml = htmlTemplate.replace("<!--ROWS_PLACEHOLDER-->", rows.toString());

            try (FileWriter writer = new FileWriter(outputPath)) {
                writer.write(finalHtml);
            }

            System.out.println("SUCCES! Raportul HTML a fost generat: " + outputPath);

        } catch (Exception e) {
            System.err.println("Eroare la generarea raportului: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
