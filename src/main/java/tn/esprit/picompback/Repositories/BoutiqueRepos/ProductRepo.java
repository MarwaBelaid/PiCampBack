package tn.esprit.picompback.Repositories.BoutiqueRepos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.picompback.Entities.Enumeration.CategorieEquipement;
import tn.esprit.picompback.Entities.Enumeration.ColorEquipement;
import tn.esprit.picompback.Entities.Enumeration.SizeEquipement;
import tn.esprit.picompback.Entities.Equipement;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Equipement,Long> {
    @Query("SELECT e FROM Equipement e WHERE " +
            "(:priceMin IS NULL OR e.prix >= :priceMin) AND " +
            "(:priceMax IS NULL OR e.prix <= :priceMax) AND " +
            "(:size IS NULL OR e.size = :size) AND " +
            "(:color IS NULL OR e.color = :color) AND " +
            "(:category IS NULL OR e.categorie = :category)")
    List<Equipement> searchProducts(float priceMin, float priceMax, SizeEquipement size, ColorEquipement color, CategorieEquipement category);

}
