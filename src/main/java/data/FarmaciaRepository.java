package data;

import org.example.examen1.Farmacia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FarmaciaRepository extends CrudRepository<Farmacia, String> {
    Farmacia findByUsuario_Id(String usuarioId);
}