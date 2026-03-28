package org.example.examen1;

import data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UsuarioRepository usuarioRepo;
    @Autowired
    private PacienteRepository pacienteRepo;
    @Autowired
    private PacienteMedicamentoRepository pmRepo;

    public Usuario login(String id, String clave) {
        Usuario u = usuarioRepo.findById(id).orElse(null);
        if (u == null) return null;
        if (!encoder.matches(clave, u.getClave())) return null;
        return u;
    }

    public Paciente buscarPaciente(String cedula) {
        return pacienteRepo.findById(cedula).orElse(null);
    }

    public List<PacienteMedicamento> getMedicamentosPaciente(String pacienteId) {
        return pmRepo.findByPacienteId(pacienteId);
    }

    public void registrarCompra(Integer pmId, int cantidad) {
        PacienteMedicamento pm = pmRepo.findById(pmId).orElseThrow();
        pm.setDosisafavor(pm.getDosisafavor() + cantidad);
        pmRepo.save(pm);
    }

    public String entregarRegalia(Integer pmId) {
        PacienteMedicamento pm = pmRepo.findById(pmId).orElseThrow();
        int plan = pm.getMedicamento().getPlan();
        if (pm.getDosisafavor() < plan) {
            return "No hay suficientes dosis. Necesita " + plan + ", tiene " + pm.getDosisafavor();
        }
        pm.setDosisafavor(pm.getDosisafavor() - plan);
        pmRepo.save(pm);
        return null;
    }
}