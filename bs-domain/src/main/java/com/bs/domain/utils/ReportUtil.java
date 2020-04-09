package com.bs.domain.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

/**
 *
 * @author Milton BO
 */
public class ReportUtil {
    
    public static void createPDF(Map parameters, InputStream jrxml, String output, Connection conn) throws JRException {
        JasperPrint print = JasperFillManager.fillReport(jrxml, parameters, conn);
        JasperExportManager.exportReportToPdfFile(print, output);
    }
    
    public static void createPDF(Map parameters, InputStream jrxml, String output, Collection datasource) throws JRException {
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(datasource);
        JasperPrint print = JasperFillManager.fillReport(jrxml, parameters, beanColDataSource);
        JasperExportManager.exportReportToPdfFile(print, output);
    }

    public static void createXLS(Map parameters, InputStream jrxml, String output, Connection conn) throws JRException {
        JasperPrint xlsPrint = JasperFillManager.fillReport(jrxml, parameters, conn);
        JRXlsExporter xlsExporter = new JRXlsExporter();
        xlsExporter.setExporterInput(new SimpleExporterInput(xlsPrint));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
        SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
        xlsReportConfiguration.setOnePagePerSheet(false);
        xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
        xlsReportConfiguration.setDetectCellType(true);
        xlsReportConfiguration.setWhitePageBackground(false);
        xlsReportConfiguration.setFontSizeFixEnabled(true);
        xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
        xlsExporter.setConfiguration(xlsReportConfiguration);
        xlsExporter.exportReport();
    }
    
    public static void createXLS(Map parameters, InputStream jrxml, String output, Collection datasource) throws JRException {
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(datasource);
        JasperPrint xlsPrint = JasperFillManager.fillReport(jrxml, parameters, beanColDataSource);
        JRXlsExporter xlsExporter = new JRXlsExporter();
        xlsExporter.setExporterInput(new SimpleExporterInput(xlsPrint));
        xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
        SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
        xlsReportConfiguration.setOnePagePerSheet(false);
        xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
        xlsReportConfiguration.setDetectCellType(true);
        xlsReportConfiguration.setWhitePageBackground(false);
        xlsReportConfiguration.setFontSizeFixEnabled(true);
        xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
        xlsExporter.setConfiguration(xlsReportConfiguration);
        xlsExporter.exportReport();
    }
    
}
