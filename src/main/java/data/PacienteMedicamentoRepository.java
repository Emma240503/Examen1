package data;

import org.example.examen1.PacienteMedicamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PacienteMedicamentoRepository extends CrudRepository<PacienteMedicamento, Integer> {

    @Query("SELECT pm FROM PacienteMedicamento pm WHERE pm.paciente.id = :pacienteId")
    List<PacienteMedicamento> findByPacienteId(@Param("pacienteId") String pacienteId);
}