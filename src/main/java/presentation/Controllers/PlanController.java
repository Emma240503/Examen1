package presentation.Controllers;

import jakarta.servlet.http.HttpSession;
import org.example.examen1.Paciente;
import org.example.examen1.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PlanController {

    @Autowired
    private Service service;

    @GetMapping("/plan")
    public String plan(
            @RequestParam(required = false) String cedula,
            @RequestParam(required = false) String error,
            HttpSession session,
            Model model) {

        if (session.getAttribute("usuarioId") == null) return "redirect:/";

        if (cedula != null && !cedula.isEmpty()) {
            Paciente paciente = service.buscarPaciente(cedula);
            model.addAttribute("cedula", cedula);
            model.addAttribute("paciente", paciente);
            if (paciente != null) {
                model.addAttribute("medicamentos", service.getMedicamentosPaciente(cedula));
            }
        }

        if (error != null) model.addAttribute("error", error);
        return "plan";
    }

    @PostMapping("/plan/compra")
    public String registrarCompra(
            @RequestParam Integer pmId,
            @RequestParam int cantidad,
            @RequestParam String cedula,
            HttpSession session) {

        if (session.getAttribute("usuarioId") == null) return "redirect:/";
        service.registrarCompra(pmId, cantidad);
        return "redirect:/plan?cedula=" + cedula;
    }

    @PostMapping("/plan/regalia")
    public String entregarRegalia(
            @RequestParam Integer pmId,
            @RequestParam String cedula,
            HttpSession session) {

        if (session.getAttribute("usuarioId") == null) return "redirect:/";
        String errorMsg = service.entregarRegalia(pmId);
        if (errorMsg != null) {
            return "redirect:/plan?cedula=" + cedula + "&error=" + errorMsg;
        }
        return "redirect:/plan?cedula=" + cedula;
    }
}

