package co.edu.unbosque.PayrollAPI.controller.implementation;

import co.edu.unbosque.PayrollAPI.controller.interfaces.IContabilidadController;
import co.edu.unbosque.PayrollAPI.model.dto.regular.AportePatronalDTO;
import co.edu.unbosque.PayrollAPI.model.dto.regular.PeriodoNominaDTO;
import co.edu.unbosque.PayrollAPI.model.entity.AportePatronalPeriodoView;
import co.edu.unbosque.PayrollAPI.model.entity.ContabilidadEmpleadoView;
import co.edu.unbosque.PayrollAPI.model.entity.ContabilidadTotalesPeriodoView;
import co.edu.unbosque.PayrollAPI.model.entity.ProvisionPrestacionPeriodoView;
import co.edu.unbosque.PayrollAPI.repository.IAportePatronalViewRepository;
import co.edu.unbosque.PayrollAPI.repository.IProvisionesViewRepository;
import co.edu.unbosque.PayrollAPI.service.interfaces.IContabilidadEmpleadoService;
import co.edu.unbosque.PayrollAPI.service.interfaces.IPeriodoNominaService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class ContabilidadControllerImpl implements IContabilidadController {

   private final IContabilidadEmpleadoService contabilidadService;
   private final IAportePatronalViewRepository aportePatronalService;
   private final IProvisionesViewRepository provisionPrestacionService;
    private final IPeriodoNominaService periodoNominaService;


    public ContabilidadControllerImpl(IContabilidadEmpleadoService contabilidadService, IAportePatronalViewRepository aportePatronalService, IProvisionesViewRepository provisionPrestacionService, IPeriodoNominaService periodoNominaService) {

        this.contabilidadService = contabilidadService;

        this.aportePatronalService = aportePatronalService;
        this.provisionPrestacionService = provisionPrestacionService;
        this.periodoNominaService = periodoNominaService;
    }

    @Override
    public String verContabilidad(@RequestParam("idPeriodo") Integer idPeriodo, Model model, HttpSession session)  {
        PeriodoNominaDTO periodo = periodoNominaService.spBuscarPeriodoPorId(idPeriodo);
        model.addAttribute("periodo", periodo);
        session.setAttribute("periodoId", periodo.getIdPeriodo());
        session.setAttribute("periodoNombre", periodo.getDescripcion());
        List<ContabilidadEmpleadoView> contabilidad = contabilidadService.consultarPorPeriodo(idPeriodo);
        model.addAttribute("contabilidad", contabilidad);
        ContabilidadTotalesPeriodoView totales = contabilidadService.getTotalesByPeriodo(idPeriodo);
        model.addAttribute("totales", totales);

        return "vistaContabilidad";
    }


    @Override
    public List<AportePatronalPeriodoView> getDetalleAportes(
            @RequestParam("idPeriodo") Integer idPeriodo,
            @RequestParam("idEmpleado") Integer idEmpleado) {
        return aportePatronalService.findAportesByPeriodoAndEmpleado(idPeriodo, idEmpleado);
    }

    @Override
    public List<ProvisionPrestacionPeriodoView> getDetalleProvisiones(
            @RequestParam("idPeriodo") Integer idPeriodo,
            @RequestParam("idEmpleado") Integer idEmpleado) {
        return provisionPrestacionService.findProvisionesByPeriodoAndEmpleado(idPeriodo, idEmpleado);
    }

    @Override
    public String verAportes(Integer idPeriodo, Model model,  HttpSession session) {
        PeriodoNominaDTO periodo = periodoNominaService.spBuscarPeriodoPorId(idPeriodo);
        model.addAttribute("periodo", periodo);
        session.setAttribute("periodoId", periodo.getIdPeriodo());
        session.setAttribute("periodoNombre", periodo.getDescripcion());
        model.addAttribute("aportes", contabilidadService.obtenerAportesPorPeriodo(idPeriodo));
        return "moduloAportes";
    }

    @Override
    public String verProvisiones(Integer idPeriodo, Model model, HttpSession session) {
        PeriodoNominaDTO periodo = periodoNominaService.spBuscarPeriodoPorId(idPeriodo);
        model.addAttribute("periodo", periodo);
        session.setAttribute("periodoId", periodo.getIdPeriodo());
        session.setAttribute("periodoNombre", periodo.getDescripcion());
        model.addAttribute("provisiones", provisionPrestacionService.findProvisionesByPeriodo(idPeriodo));
        return "moduloProvisiones";
    }


    @Override
    public void exportarAportesExcel(@RequestParam("idPeriodo") Integer idPeriodo, HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=aportes_periodo_" + idPeriodo + ".xlsx");

        List<AportePatronalDTO> aportes = contabilidadService.obtenerAportesPorPeriodo(idPeriodo);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Aportes Patronales");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Empleado");
        header.createCell(1).setCellValue("Tipo Contrato");
        header.createCell(2).setCellValue("Riesgo ARL");
        header.createCell(3).setCellValue("Tipo Aporte");
        header.createCell(4).setCellValue("Cantidad");
        header.createCell(5).setCellValue("Fecha");

        int rowIdx = 1;
        for (AportePatronalDTO dto : aportes) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(dto.getNombreEmpleado());
            row.createCell(1).setCellValue(dto.getTipoContrato());
            row.createCell(2).setCellValue(dto.getNivelRiesgo());
            row.createCell(3).setCellValue(dto.getTipoAporte());
            row.createCell(4).setCellValue(dto.getCantidad().doubleValue());
            row.createCell(5).setCellValue(dto.getFechaLiquidacion().toString());
        }

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @Override
    public void exportarProvisionesExcel(Integer idPeriodo, HttpServletResponse response) {
        List<ProvisionPrestacionPeriodoView> provisiones = provisionPrestacionService.findProvisionesByPeriodo(idPeriodo);

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Provisiones");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Empleado");
            header.createCell(1).setCellValue("Concepto");
            header.createCell(2).setCellValue("Cantidad");
            header.createCell(3).setCellValue("Fecha Registro");

            int rowIdx = 1;
            for (ProvisionPrestacionPeriodoView p : provisiones) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(p.getNombreEmpleado());
                row.createCell(1).setCellValue(p.getConceptoProvision());
                row.createCell(2).setCellValue(p.getCantidad().doubleValue());
                row.createCell(3).setCellValue(p.getFechaRegistro().toString());
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=provisiones.xlsx");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException("Error al generar el Excel de provisiones", e);
        }
    }


}
