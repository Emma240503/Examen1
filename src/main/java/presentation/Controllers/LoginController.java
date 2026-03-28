package presentation.Controllers;

import jakarta.servlet.http.HttpSession;
import org.example.examen1.Service;
import org.example.examen1.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private Service service;

    @GetMapping("/")
    public String loginForm() {
        return "index";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String usuario,
            @RequestParam String clave,
            HttpSession session) {

        Usuario u = service.login(usuario, clave);
        if (u == null) return "redirect:/?error";

        session.setAttribute("usuarioId",  u.getId());
        session.setAttribute("usuarioRol", u.getRol());
        return "redirect:/plan";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}