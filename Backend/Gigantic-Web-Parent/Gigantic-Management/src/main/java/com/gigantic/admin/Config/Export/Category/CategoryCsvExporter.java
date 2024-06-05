package com.gigantic.admin.Config.Export.Category;

import com.gigantic.admin.Config.Export.AbstractExporter;
import com.gigantic.entity.Category;
import org.springframework.context.annotation.Configuration;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Configuration
public class CategoryCsvExporter extends AbstractExporter {

    public void export(List<Category> listCategories, HttpServletResponse response) throws IOException {
        // Set the response header for the CSV file
        super.setResponseHeader(response, "text/csv", ".csv", "_categories_");

        // Use try-with-resources to ensure the csvWriter is closed properly
        try (ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE)) {

            // Add a title row with more details
            csvWriter.writeComment("Gigantic Mall");
            csvWriter.writeComment("Exported Category List");
            csvWriter.writeComment("Date: " + java.time.LocalDate.now());
            csvWriter.writeComment("Generated by: Category Management System");

            // Add a blank line for separation
            csvWriter.writeComment("");

            // Set CSV headers
            String[] csvHeader = {"Category ID", "Category Name", "Parent Category", "Enabled"};
            String[] fieldMapping = {"id", "name", "parentName", "enabled"};

            csvWriter.writeHeader(csvHeader);

            // Write data rows
            for (Category category : listCategories) {
                // Set the parent name if the category has a parent
                String parentName = category.getParent() != null ? category.getParent().getName() : "N/A";
                // Create a new Category object to avoid modifying the original one
                Category exportCategory = new Category(category.getId(), category.getName(), category.getAlias(), category.getImage(), category.isEnabled(), category.getAllParentIDs(), category.getParent(), category.getChildren());
                // Replace "--" with "  " in the category name
                exportCategory.setName(exportCategory.getName().replace("--", "  "));
                // Set the parent name for the CSV export
                category.setParentName(category.getParent() != null ? category.getParent().getName() : "N/A");

                csvWriter.write(exportCategory, fieldMapping);
            }

            // Add a footer row
            csvWriter.writeComment("");
            csvWriter.writeComment("End of Category List");
        }
    }
}
