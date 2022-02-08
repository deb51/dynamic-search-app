package in.ashokit.pdfExport;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.bindings.InsurancePlanResponseBinding;
import in.ashokit.entity.InsurancePlanEntity;

public class UserPDFExporter {
	
    private List<InsurancePlanResponseBinding> listUsers;
    
    public UserPDFExporter(List<InsurancePlanResponseBinding> listUsers2) {
        this.listUsers = listUsers2;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Plan ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("User Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Plan Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Holder SSN", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Plan Status", font));
        table.addCell(cell);       
    }
     
    private void writeTableData(PdfPTable table) {
        for (InsurancePlanResponseBinding user : listUsers) {
            table.addCell(String.valueOf(user.getPlanId()));
            table.addCell(user.getPlanHolder());
            table.addCell(user.getPlanName());
            table.addCell(user.getHolderSsn());
            table.addCell(user.getPlanStatus());
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(12);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Insurance Plan", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.0f, 2.0f, 2.0f, 3.0f, 3.0f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();
         
    }

}
