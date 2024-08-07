package com.gigantic.user.config;

import com.gigantic.entity.User;
import org.springframework.context.annotation.Configuration;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
public class UserCSVExporter extends com.gigantic.admin.Config.Export.AbstractExporter {

    public void export(List<User> listUsers, HttpServletResponse response) throws IOException {
        super.setResponseHeader(response, "text/csv", ".csv", "users_");

        // Use try-with-resources to ensure the csvWriter is closed properly
        try (ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE)) {

            // Add a title row
            // Add a title row with more details
            csvWriter.writeComment("Gigantic Mall");
            csvWriter.writeComment("Exported Users List");
            csvWriter.writeComment("Date: " + java.time.LocalDate.now());
            csvWriter.writeComment("Generated by: User Management System");
            csvWriter.writeComment("List of Users");

            // Add a blank line for separation
            csvWriter.writeComment("");

            // Set CSV headers
            String[] csvHeader = {"User ID", "E-mail", "First Name", "Last Name", "Roles", "Enabled"};
            String[] fieldMapping = {"id", "email", "firstName", "lastName", "roles", "enabled"};

            csvWriter.writeHeader(csvHeader);

            // Write data rows
            for (User user : listUsers) {
                csvWriter.write(user, fieldMapping);
            }

            // Add a footer row
            csvWriter.writeComment("");
            csvWriter.writeComment("End of User List");
        }
    }
}
